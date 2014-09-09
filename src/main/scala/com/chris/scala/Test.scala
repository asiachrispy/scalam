package com.chris.scala

import com.chenlb.mmseg4j.MMSegUtil
import org.ansj.splitWord.analysis.ToAnalysis
import org.apache.spark.SparkContext
import org.ansj.domain.Term
import scala.collection.mutable.ArrayBuffer

/**
 * Description : some words
 * Author: chris
 * Date: 2014/8/14
 */
object Test {

  def main(args: Array[String]) {
    System.out.println(MMSegUtil.participle("你好，java工程师python北京天安门是我的国家"))

    val master = args.length match {
      case x: Int if x > 0 => args(0)
      case _ => "local"
    }

    val sc = new SparkContext(master, "WordCount", System.getenv("SPARK_HOME"))
    val in = args.length match {
      case x: Int if x > 1 => sc.textFile(args(1))
      case _ => sc.parallelize(List("pandas.txt", "i like pandas"))
    }

    //in.map(s => {val name = s._1;val va = s._2;Array("/tt".concat(name.split("/")(5)) ,va.split(" ").mkString)}).collect
    // 改成词性标注的方式，统计词性的共现次数
    //val words = in.flatMap(line => line.split(" ")).toArray()
    val words = in.flatMap(parse(line))

    args.length match {
      case x: Int if x > 2 => {
        val wordss = new ArrayBuffer[String]()
        for (i <- 1 until words.size) {
          val key = words(i - 1) + "-" + words(i)
          wordss += key
        }
        System.out.println(wordss.mkString(","))
        val counts = sc.parallelize[String](wordss.toSeq).map((_, 1)).reduceByKey(_ + _)
        counts.saveAsTextFile(args(2))
      }
      case _ => {
      }
    }
  }

  def parse(line:String): List[String] = {
    val terms: List[Term] = ToAnalysis.parse(line.toString)
    terms.map(term => term.getNatureStr()).toList
  }
}
