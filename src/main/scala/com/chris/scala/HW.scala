package com.chris.scala

/* SimpleApp.scala */

import scala.io.Source

/**
 * Created by chris on 2014/7/10.
 */
object HW {
  var path = "D:\\gitspace\\scalam\\src\\main\\resources\\";
  var file = "D:\\gitspace\\scalam\\src\\main\\resources\\test.txt";

  def main(args: Array[String]): Unit = {
    println("hello world");
    println(decorate("chris,", "<<"));
    println(decorate("chris,", right = ">>"))
    //    read()
    //    url()
    write()
  }

  def decorate(str: String, left: String = "[", right: String = "]") =
    left + str + right


  def read() {
    val source = Source.fromFile(file, "UTF-8")
    val contents = source.mkString // the whole content as a String
    println(contents)
  }

  def url() {
    val source1 = Source.fromURL("http://zhangjunhd.github.io/2013/12/22/scala-note7-file.html", "UTF-8")
    val lineIterator = source1.getLines
    for (l <- lineIterator)
      println(l) // l is a String

  }

  def bin() {
    import java.io.File
    import java.io.FileInputStream

    val f = new File(file)
    val in = new FileInputStream(f)
    val bytes = new Array[Byte](f.length.toInt)
    in.read(bytes)
    in.close()
  }

  def write() {
    import java.io.PrintWriter

    val out = new PrintWriter(path + "numbers.txt")
    for (i <- 1 to 100)
      out.println(i)
    out.close()

    // use string format
    val quantity = 100
    val price = .1
    out.print("%6d %10.2f".format(quantity, price))
  }

  import java.io.File
  def subdirs(dir: File): Iterator[File] = {
    val children = dir.listFiles.filter(_.isDirectory)
    children.toIterator ++ children.toIterator.flatMap(subdirs _)
  }

}