package chap01

// Run with >scala FileAsIterable.scala  or
// run with the REPL in chap01/ via
// scala> :load FileAsIterable.scala


class FileAsIterable(val file: String) {
  def iterator = scala.io.Source.fromFile(file).getLines()
}

object FileAsIterableExample extends App {
  var file: String = "D:\\gitspace\\scalam\\src\\main\\scala\\com\\chris\\scala\\chap01\\someFile.txt";

  val newIterator = new FileAsIterable(file) with Iterable[String]
  newIterator.foreach { line => println(line)}
}
