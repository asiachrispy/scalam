/**
 * Illustrates flatMap + countByValue for wordcount.
 */
package com.chris.scala

import org.apache.spark._
import org.apache.spark.SparkContext._

// local hdfs://name1:9000/test/README
// local[1] hdfs://name1:9000/test/README
// spark://name1:7077 hdfs://name1:9000/test/README
// spark://name1:7077
object WordCount {
    def main(args: Array[String]) {
      val master = args.length match {
        case x: Int if x > 0 => args(0)
        case _ => "local"
      }

      val sc = new SparkContext(master, "WordCount", System.getenv("SPARK_HOME"))
      val input = args.length match {
        case x: Int if x > 1 => sc.textFile(args(1))
        case _ => sc.parallelize(List("Hello world Bye world", "Hello hadoop Bye hadoop"))
      }
      val words = input.flatMap(_.split(" "))
      args.length match {
        case x: Int if x > 2 => {
          val counts = words.map((_, 1)).reduceByKey(_ + _)

          counts.saveAsTextFile(args(2))
        }
        case _ => {
          val wc = words.countByValue()
          println(wc.mkString(","))
        }
      }

    }
}
