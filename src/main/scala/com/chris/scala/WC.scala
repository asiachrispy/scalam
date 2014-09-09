package com.chris.scala

import scala.collection.mutable.ArrayBuffer

/**
 * Created by chris on 2014/7/16.
 */


object WC {
  def main(args: Array[String]) {
    val words = List("asia", "chris", "python")
    val wordss = new ArrayBuffer[String]()
    for (i <- 1 until words.size) {
      val key = words(i - 1) + words(i)
      wordss += key
    }
    System.out.println(wordss.mkString(","))
  }
}
