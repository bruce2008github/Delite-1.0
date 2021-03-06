package ppl.delite.metrics

import collection.mutable.HashMap
import java.io.{BufferedWriter, File, PrintWriter, FileWriter}
import java.util.Vector
import ppl.delite.core.Config

/**
 * User: Anand Atreya
 */

object PerformanceTimer
{
  val currentTimer = new HashMap[String, Long]
  val times = new HashMap[String, Vector[Double]]

  def start(component: String, printMessage: Boolean = true) {
    if (!times.contains(component)) {
      times += component -> new Vector[Double]()
    }
    if (printMessage) println("[METRICS]: Timing " + component + " #" + times(component).size + " started")
    currentTimer += component -> System.nanoTime
  }

  def stop(component: String, printMessage: Boolean = true) {
    val x = (System.nanoTime - currentTimer(component)) / 1000000000.0
    times(component).add(x)    
    if (printMessage) println("[METRICS]: Timing " + component + " #" + (times(component).size - 1) + " stopped")
  }

  def print(component: String) {
    try {
//      println("[METRICS]: Times for component " + component + ":")
//      for (i <- 0 until times(component).size) {
//        val timeString = times(component).get(i) formatted ("%.6f") + "s"
//        println("[METRICS]:     " + timeString)
//      }
      val timeString = times(component).lastElement formatted ("%.6f") + "s"
      println("[METRICS]: Latest time for component " + component + ": " + timeString)
    }
    catch {
      case e: Exception => println("[METRICS]: No data for component " + component)
    }
  }

  def save(component: String) {
    try {
      val procstring = {
        if (Config.bypassExecutor && Config.debugEnabled) {
        // set procs to -1 for bypassExecutor case
          "-1"
        } else {
          Config.CPUThreadNum.toString
        }
      }
      var file: String = new File("./").getCanonicalPath + "/" + component + ".p" + procstring + ".times"
      val writer = new PrintWriter(new BufferedWriter(new FileWriter(file, false)))
      for (i <- 0 until times(component).size) {
        writer.println(times(component).get(i) formatted ("%.10f"))
      }
      writer.close()
    }
    catch {
      case e: Exception => println("[METRICS]: Unable to save data for component " + component)
    }
  }
}