/**
 * Illustrates flatMap + countByValue for wordcount.
 */
package com.chris.scala

import org.apache.spark._

// local hdfs://name1:9000/test/README
// local[1] hdfs://name1:9000/test/README
// spark://name1:7077 hdfs://name1:9000/test/README
// spark://name1:7077
object SegCorpus {
  def main(args: Array[String]) {
    val master = args.length match {
      case x: Int if x > 0 => args(0)
      case _ => "local"
    }

    val sc = new SparkContext(master, "SegCorpus", System.getenv("SPARK_HOME"))
    val input = args.length match {
      case x: Int if x > 1 => sc.wholeTextFiles(args(1))
      case _ => sc.parallelize(List("pandas", "i like pandas"))
    }

    args.length match {
      case x: Int if x > 2 => {
        //input.map(s => {val name = s._1;val va = s._2;Array("/tt".concat(name.split("/")(5)) ,MMSegUtil.participle(va))}).saveAsTextFile(args(2))
      }
//      case _ => {
//        val wc = words.countByValue()
//        println(wc.mkString(","))
//      }
    }

  }

}
