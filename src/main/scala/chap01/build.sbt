package chap01

name := "chapter1 examples"

scalaVersion := "2.10.0"

organization := "Scala in Action"

// append options passed to the Scala compiler
scalacOptions ++= Seq("-deprecation", "-unchecked", "-language:_")
