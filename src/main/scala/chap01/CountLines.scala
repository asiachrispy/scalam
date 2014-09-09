package chap01

// Run with >scala CountLines.scala  or
// run with the REPL in chap01/ via
// scala> :load CountLines.scala

object CountLines extends App {
  var file: String = "D:\\gitspace\\scalam\\src\\main\\scala\\com\\chris\\scala\\chap01\\someFile.txt";

  val src = scala.io.Source.fromFile(file)
  val count = src.getLines().map(x => 1).sum
  println(count)
}
