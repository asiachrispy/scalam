package com.chris.scala

/**
 * Created by chris on 2014/7/21.
 */
object Reg extends App {
  val numPattern = "[0-9]+".r

  val wsnumwsPattern = """\s+[0-9]+\s+""".r // use the “raw” string syntax

  for (matchString <- numPattern.findAllIn("99 bottles, 98 bottles"))
    println(matchString) // 99
  //98

  val matches = numPattern.findAllIn("99 bottles, 98 bottles").toArray // Array(99, 98)

  val m0 = numPattern.findFirstIn("99 bottles, 98 bottles") // Some(99)
  val m1 = wsnumwsPattern.findFirstIn("99 bottles, 98 bottles") // Some(98)

  numPattern.findPrefixOf("99 bottles, 98 bottles") // Some(99)
  wsnumwsPattern.findPrefixOf("99 bottles, 98 bottles") // None

  numPattern.replaceFirstIn("99 bottles, 98 bottles", "XX") // "XX bottles, 98 bottles"
  numPattern.replaceAllIn("99 bottles, 98 bottles", "XX") // "XX bottles, XX bottles"
}
