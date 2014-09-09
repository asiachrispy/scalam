name := "scalam"

version := "1.0"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.0.0",
  "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test")