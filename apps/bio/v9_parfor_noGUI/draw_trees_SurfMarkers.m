function draw_trees_SurfMarkers(FlowSPD_output_filename,surf_ind)

warning off
mkdir('figs')

loaded_result_file = FlowSPD_output_filename;

load(loaded_result_file)
if ~exist('template'), template = []; end
downsampled_data = data;
downsampled_assign = sample_group_assign;
adj = sample_group_progression_tree;
coeff = node_position;
for i=1:size(adj,1),  node_size(i) = sum(local_density(downsampled_assign==i)); end



mkdir('figs\downsample_data_figures\')
[cluster_median] = get_cluster_median(downsampled_data, downsampled_assign); 
cluster_median = cluster_median./max(abs(cluster_median(:)))*7;
max_intensity = max(max(abs(cluster_median(surf_ind,:))));
h1 = figure(1);
[nx,ny] = get_subplot_grid(length(surf_ind));
for i=1:length(surf_ind)
    figure(1); subplot(nx,ny,i);
    [coeff] = draw_on_Axes_mst_here2(adj,coeff,max_intensity, node_size,marker_names{surf_ind(i)},cluster_median(surf_ind(i),:),template);
    h2 = figure(2); 
    [coeff] = draw_on_Axes_mst_here2(adj,coeff,max_intensity, node_size,marker_names{surf_ind(i)},cluster_median(surf_ind(i),:),template);
    print(h2,['figs\downsample_data_figures\downsample_data_surface_markers_',remove_bad_filename_letters(marker_names{surf_ind(i)}),'.jpg'],'-djpeg')
end
print(h1,['figs\downsample_data_figures\downsample_data_surface_markers','.jpg'],'-djpeg')


return



function [coeff] = draw_on_Axes_mst_here2(adj,coeff,max_intensity, node_size, marker_name, cluster_median, template) 

hold off; plot(0); hold on;

if exist('template') && ~isempty(template)
    for i=1:length(template.BoxCenter_x)
        coordinates = [template.BoxCenter_x(i)-template.BoxWidth(i)/2,   template.BoxCenter_x(i)+template.BoxWidth(i)/2,  template.BoxCenter_x(i)+template.BoxWidth(i)/2,   template.BoxCenter_x(i)-template.BoxWidth(i)/2; ...
                       template.BoxCenter_y(i)-template.BoxHeight(i)/2,  template.BoxCenter_y(i)-template.BoxHeight(i)/2, template.BoxCenter_y(i)+template.BoxHeight(i)/2,  template.BoxCenter_y(i)+template.BoxHeight(i)/2];
        patch(coordinates(1,:),coordinates(2,:), reshape(template.BoxColor(:,i),1,3));
    end
end

% % get node size
% draw_node_size =  round(node_size/max(node_size)*10); 
% draw_node_size(draw_node_size<3)=3;
draw_node_size = flow_arcsinh(node_size,median(node_size)/2);
draw_node_size = ceil(draw_node_size/max(draw_node_size)*10);
draw_node_size(draw_node_size<5)=5;
% get color code
color_code_vector = standardize_color_code_here(cluster_median,max_intensity);


pairs = find_matrix_big_element(triu(adj,1),1);
for k=1:size(pairs,1), line(coeff(1,pairs(k,:)),coeff(2,pairs(k,:)),'color','g'); end
% draw nodes
        %colormap(lbmapv2(1000,'blueyellow')); cmap_tmp = colormap;
        colormap('jet'); cmap_tmp = colormap;
        for k=1:size(coeff,2), 
            color_tmp = interp1(((1:size(cmap_tmp,1))'-1)/(size(cmap_tmp,1)-1),cmap_tmp,color_code_vector(k));  % blue-red        
            plot(coeff(1,k),coeff(2,k),'o','markersize',draw_node_size(k),'markerfacecolor',color_tmp,'color',color_tmp); 
        end
hold off;
axis(reshape([-max(abs(coeff)');+max(abs(coeff)')],1,4)*1.1); axis off;

x_shift = 50-max_intensity; y_shift = -50+max_intensity;
patch([-max_intensity,max_intensity,max_intensity,-max_intensity]+x_shift,[0,0,5,5]+y_shift,[1 1 1]); hold on; 
for i=-max_intensity:(max_intensity)/100:max_intensity
    % color code is standardized between 0 and 1, according to max(abs(cluster_median))
    if abs(i)>max(abs(cluster_median)), continue; end
    color_tmp = interp1(((1:size(cmap_tmp,1))'-1)/(size(cmap_tmp,1)-1),cmap_tmp,i/max(abs(cluster_median))/2+0.5);
    patch([i,i+(max_intensity)/50,i+(max_intensity)/50,i]+x_shift,[0,0,5,5]+y_shift,color_tmp,'edgecolor',color_tmp); 
end
text(-max_intensity+x_shift-3, 0+y_shift-3, num2str(round(-max_intensity*10)/10),'fontsize',7) 
text(+max_intensity+x_shift, 0+y_shift-3, num2str(round(max_intensity*10)/10),'fontsize',7) 
text(max(abs(cluster_median))+x_shift-2, 5+y_shift+2, num2str(round(max(abs(cluster_median))*10)/10),'fontsize',7) 
title(marker_name)
return



%%%%%%%%%%%%%%%%
function color_code_vector = standardize_color_code_here(color_code_vector,max_intensity)
        color_code_vector(isinf(color_code_vector)) = min(color_code_vector(~isinf(color_code_vector)));
        if sum(isnan(color_code_vector))~=0  % this will never happen
            color_code_vector(isnan(color_code_vector)) = nanmedian(color_code_vector);
        end
        if exist('max_intensity')
            color_code_vector = ((color_code_vector ./ max(max_intensity) + 1)/2);
        else
            color_code_vector = ((color_code_vector ./ max(abs(color_code_vector)) + 1)/2);
        end
return





%%%%%%%%%%%%%%%%%
function [cluster_median] = get_cluster_median(data, idx)

cluster_median = zeros(size(data,1),max(idx))+NaN;
for i=1:max(idx)
    ind = find(idx==i);
    if ~isempty(ind)
        cluster_median(:,i) = nanmedian(data(:,ind),2);
    end
end
return




%%%
function [nx,ny] = get_subplot_grid(num_plots)
subplot_grid = [1, 1; ...
                1, 2; ...
                2, 2; ...
                2, 2; ...
                2, 3; ...
                2, 3; ...
                2, 4; ...
                3, 3; ...
                3, 3; ...
                3, 4; ...
                3, 4; ...
                3, 4; ...
                3, 5; ...
                3, 5; ...
                3, 5; ...
                4, 4; ...
                4, 5; ...
                4, 5; ...
                4, 5; ...
                4, 5; ...
                4, 6; ...
                4, 6; ...
                4, 6; ...
                4, 6; ...
                5, 5; ...
                5, 6; ...
                5, 6; ...
                5, 6; ...
                5, 6; ...
                5, 6; ...
                5, 7; ...
                5, 7; ...
                5, 7; ...
                5, 7; ...
                5, 7; ...
                5, 8; ...
                5, 8; ...
                5, 8; ...
                5, 8; ...
                5, 8; ...
                6, 8; ...
                6, 8; ...
                6, 8; ...
                6, 8; ...
                6, 8; ...
                6, 8; ...
                6, 8; ...
                6, 8; ];                
if num_plots>size(subplot_grid,1)
    error('Error: Come on, why you have this many markers? ask Peng how to fix this, or, look at the above variable in this function and figure it out yourself :)');
    return
end
nx = subplot_grid(num_plots,1);
ny = subplot_grid(num_plots,2);
return


%%%%%%%%%%%%%%%%%
function somename = remove_bad_filename_letters(somename)
somename = somename((somename>=48 & somename<=57) | (somename>=65 & somename<=90) | (somename>=97 & somename<=122) | (somename=='_'));
return



%%%%%%%%%%%%%%%%%
function node_median = clear_NaNs_in_node_median(node_median,adj)

tmp = sum(isnan(node_median),3);
if isempty(setdiff(unique(tmp(:)),0)) 
    return  % great, there is no NaN's
elseif length(setdiff(unique(tmp(:)),0))>1
    error('Error: CAUTION, this is not supposed to happen, ask Peng to debug it');
    return
end

% % if the code gets here, it means that there are NaNs in node_median, and
% % the NaNs in each slide (for each marker) are at the same positions
% % Now, let get ride of them, replacing the NaNs by their neighbors in adj
% % the reason is that, when upsampling one file, if one node doesn't have 
% % any cells, it's node_median is set as the mean of its direct neighbors.  

for file_ind = 1:size(node_median,1)  % for each file
    while sum(isnan(node_median(file_ind,:,1)))~=0
        nan_nodes = find(isnan(node_median(file_ind,:,1)));
        for i=1:length(nan_nodes)
            this_nan_node = nan_nodes(i);
            not_nan_neighbors = setdiff(find(adj(this_nan_node,:)==1),nan_nodes);
            if isempty(not_nan_neighbors)
                continue;
            else
                for slide = 1:size(node_median,3)
                    node_median(file_ind,this_nan_node,slide) = mean(node_median(file_ind,not_nan_neighbors,slide));
                end
            end
        end
    end
end
return







% %%% this part is useless now
% function [coeff] = draw_on_Axes_mst_here(adj,coeff,max_intensity, data, marker_name, assign,local_density) 
% % get node size
% for i=1:size(adj,1), 
%     if exist('local_density') && length(local_density)==length(data)
%         node_size(i) = sum(local_density(assign==i)); 
%     else
%         node_size(i) = sum(assign==i); 
%     end
% end
% draw_node_size =  round(node_size/max(node_size)*10); 
% draw_node_size(draw_node_size<3)=3;
% % get color code
% [cluster_median] = get_cluster_median(data, assign);
% color_code_vector = standardize_color_code_here(cluster_median);
% 
% hold off; plot(0); hold on;
% pairs = find_matrix_big_element(triu(adj,1),1);
% for k=1:size(pairs,1), line(coeff(1,pairs(k,:)),coeff(2,pairs(k,:)),'color','g'); end
% % draw nodes
%         %colormap(lbmapv2(1000,'blueyellow')); cmap_tmp = colormap;
%         colormap('jet'); cmap_tmp = colormap;
%         for k=1:size(coeff,2), 
%             color_tmp = interp1(((1:size(cmap_tmp,1))'-1)/(size(cmap_tmp,1)-1),cmap_tmp,color_code_vector(k));  % blue-red        
%             plot(coeff(1,k),coeff(2,k),'o','markersize',draw_node_size(k),'markerfacecolor',color_tmp,'color',color_tmp); 
%         end
% hold off;
% axis(reshape([-max(abs(coeff)');+max(abs(coeff)')],1,4)*1.1); axis off;
% 
% x_shift = 50-max_intensity; y_shift = -50+max_intensity;
% patch([-max_intensity,max_intensity,max_intensity,-max_intensity]+x_shift,[0,0,5,5]+y_shift,[1 1 1]); hold on; 
% for i=-max_intensity:(max_intensity)/100:max_intensity
%     % color code is standardized between 0 and 1, according to max(abs(cluster_median))
%     if abs(i)>max(abs(cluster_median)), continue; end
%     color_tmp = interp1(((1:size(cmap_tmp,1))'-1)/(size(cmap_tmp,1)-1),cmap_tmp,i/max(abs(cluster_median))/2+0.5);
%     patch([i,i+(max_intensity)/50,i+(max_intensity)/50,i]+x_shift,[0,0,5,5]+y_shift,color_tmp,'edgecolor',color_tmp); 
% end
% text(-max_intensity+x_shift-3, 0+y_shift-3, num2str(round(-max_intensity*10)/10),'fontsize',7) 
% text(+max_intensity+x_shift, 0+y_shift-3, num2str(round(max_intensity*10)/10),'fontsize',7) 
% text(max(abs(cluster_median))+x_shift-2, 5+y_shift+2, num2str(round(max(abs(cluster_median))*10)/10),'fontsize',7) 
% title(marker_name)
% return




