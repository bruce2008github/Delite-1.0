function parfor_1_read_individual_fcs_get_downsampling_info(filename,arcsinh_cofactor,downsampling_scaling_factor,used_markers,is_normalize, normalize_weight_factor)

    [fcsdat, fcshdr, fcsdatscaled] = fca_readfcs(filename);
    data = flow_arcsinh(fcsdat',arcsinh_cofactor);
    data = data(:,1:min(end,500000));
    
    display(['Read and downsample fcs file: '])
    display(['   ',filename,]);
    display(['   ',num2str(size(data,2)), ' cells']);
    for i=1:length(fcshdr.par), 
        marker_names{i,1} = fcshdr.par(i).name2;  
        if isequal(unique(fcshdr.par(i).name2),' ')
            marker_names{i,1} = fcshdr.par(i).name;  
        end
    end
    if exist('is_normalize') && is_normalize==1
        if ~exist('normalize_weight_factor')
            data = flow_normalize(data);
        else
            data = flow_normalize(data, normalize_weight_factor);
        end
    end
    if iscell(used_markers)
        [C,IA,IB] = intersect(used_markers,marker_names);
        used_markers = sort(IB);
    end
    [is_keep,local_density,keep_prob,kernel_width] = parfor_downsample_flow3(data(used_markers,:),5,downsampling_scaling_factor);
    save([filename(1:end-4),'.mat'],'data','marker_names','local_density','used_markers','kernel_width')
return


function [is_keep,local_density,keep_prob,kernel_width] = parfor_downsample_flow3(data,target_prctile,kernel_width_para)


if ~exist('kernel_width_para'), kernel_width_para = 10; end
if ~exist('target_prctile'),    target_prctile = 5;     end

matlabpool
fprintf('   finding empirical dist of the min distance between cells ... \n');
min_dist = zeros(1,min([size(data,2),2000,floor(2500000000/size(data,2))]));  % (1) number of cells if data matrix is small, (2) block size = 2000 if data large, (3) if number of cells is extremely large, we need to limit block size, otherwise line 38 will be out of memory
ind = sort(randsample(1:size(data,2),length(min_dist)));
data_tmp = data(:,ind);
all_dist = zeros(length(ind),size(data,2));
parfor i=1:size(data,2)
    all_dist(:,i) = sum(abs(repmat(data(:,i),1,size(data_tmp,2)) - data_tmp),1)';
end
all_dist(all_dist==0)=max(max(all_dist));
min_dist = min(all_dist');
med_min_dist = median(min_dist);
kernel_width = kernel_width_para * med_min_dist;
fprintf('   For this %d channel data, KERNEL_WIDTH is %3.3f\n', size(data,1),kernel_width);

local_density = zeros(1,size(data,2));
local_density_tmp = zeros(1,size(data,2));
fprintf('   finding local density for each cell ... \n   %10d',0);
count = 1; 
while sum(local_density==0)~=0
    ind = find(local_density==0); ind = ind(1:min(1000,end));
    data_tmp = data(:,ind);
    local_density_tmp = local_density(ind);
    all_dist = zeros(length(ind),size(data,2));
    parfor i=1:size(data,2)
        all_dist(:,i) = sum(abs(repmat(data(:,i),1,size(data_tmp,2)) - data_tmp),1)';
    end
    for i=1:size(data_tmp,2)
        local_density_tmp(i) = sum(kernel_width>=all_dist(i,:)); % sum(max(kernel_width-all_dist(i,:),0)/kernel_width); % 
        local_density(all_dist(i,:) < 1.5*med_min_dist) = local_density_tmp(i);
    end
    fprintf('\b\b\b\b\b\b\b\b\b\b%10d',sum(local_density~=0));
end
target_density = prctile(local_density,target_prctile);
keep_prob = (target_density./local_density);
fprintf('\n   Down-sample cells ... \n');
is_keep = rand(1,size(data,2))<keep_prob;

matlabpool close

return



function [is_keep,local_density,keep_prob] = parfor_downsample_flow(data,target_prctile,kernel_width_para)


if ~exist('kernel_width_para')
    kernel_width_para = 10;
end
if ~exist('target_prctile')
    target_prctile = 5;
end

min_dist = zeros(1,min(size(data,2),2000));
matlabpool
fprintf('   finding empirical dist of the min distance between cells ... \n');
parfor i=1:length(min_dist)
    ind = randsample(1:size(data,2),1);
    all_dist = sum(abs(repmat(data(:,ind),1,size(data,2)-1) - data(:,setdiff(1:end,ind))),1);
    min_dist(i) = min(all_dist);
end
matlabpool close
med_min_dist = median(min_dist);
kernel_width = kernel_width_para * med_min_dist;
fprintf('   For this %d channel data, KERNEL_WIDTH is %3.3f\n', size(data,1),kernel_width);

local_density = zeros(1,size(data,2));
fprintf('   finding local density for each cell ... %4d%%',0);
for i=1:size(data,2)
    if local_density(i)==0
        all_dist = sum(abs(repmat(data(:,i),1,size(data,2)) - data),1);
        local_density(i) = sum(max(kernel_width-all_dist,0)/kernel_width); % sum(kernel_width>=all_dist); 
        local_density(all_dist< 1*med_min_dist) = local_density(i);
    end
    if mod(i,1000)==1 || i == size(data,2)
        fprintf('\b\b\b\b\b%4d%%',floor(i/length(local_density)*100));
    end
end
fprintf('\n', size(data,1),kernel_width);

target_density = prctile(local_density,target_prctile);
keep_prob = (target_density./local_density);
fprintf('   Down-sample cells ... \n');
is_keep = rand(1,size(data,2))<keep_prob;

return

function [is_keep,local_density,keep_prob] = parfor_downsample_flow2(data,target_prctile,kernel_width_para)


if ~exist('kernel_width_para')
    kernel_width_para = 10;
end
if ~exist('target_prctile')
    target_prctile = 5;
end

min_dist = zeros(1,min(size(data,2),2000));
matlabpool
fprintf('   finding empirical dist of the min distance between cells ... \n');
parfor i=1:length(min_dist)
    ind = randsample(1:size(data,2),1);
    all_dist = sum(abs(repmat(data(:,ind),1,size(data,2)-1) - data(:,setdiff(1:end,ind))),1);
    min_dist(i) = min(all_dist);
end
med_min_dist = median(min_dist);
kernel_width = kernel_width_para * med_min_dist;
fprintf('   For this %d channel data, KERNEL_WIDTH is %3.3f\n', size(data,1),kernel_width);

local_density = zeros(1,size(data,2));
local_density_tmp = zeros(1,size(data,2));
fprintf('   finding local density for each cell ... \n   %10d',0);
count = 1; 
while sum(local_density==0)~=0
    tic
    ind = find(local_density==0); ind = ind(1:min(1000,end));
    data_tmp = data(:,ind);
    local_density_tmp = local_density(ind);
    indicators = repmat(logical(0),size(data_tmp,2),size(data,2));
    parfor i=1:size(data_tmp,2)
        if local_density_tmp(i)==0
            all_dist = sum(abs(repmat(data_tmp(:,i),1,size(data,2)) - data),1);
            local_density_tmp(i) = sum(max(kernel_width-all_dist,0)/kernel_width); % sum(kernel_width>=all_dist); 
            indicators(i,:) = all_dist< 1.5*med_min_dist;
        end
    end
    for i=1:size(data_tmp,2)
        if local_density(ind(i))==0
            local_density(indicators(i,:)) = local_density_tmp(i);
        end
    end
    toc
%     fprintf('\b\b\b\b\b\b\b\b\b\b%10d',sum(local_density~=0));
end
target_density = prctile(local_density,target_prctile);
keep_prob = (target_density./local_density);
fprintf('\n   Down-sample cells ... \n');
is_keep = rand(1,size(data,2))<keep_prob;

matlabpool close

return

