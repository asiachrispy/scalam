package com.chris.scala

/**
 * Created by chris on 2014/7/16.
 */


object WordCountByScala {
  def main(args: Array[String]) {
    val words = List("asia", "chris", "chris like scala java python")
      System.out.println(words.flatMap(_.split(" ")).map((_, 1)).reduceRight(_+_))
  }
}
