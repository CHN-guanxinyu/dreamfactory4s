import sbt._

object Dependencies {
  val core = Seq(Lib.rest4s, Lib.scala_test)
}

object Version{
  val scala = "2.11.8"
  val scala_test = "3.0.0"
  val rest4s = "1.1-SNAPSHOT"
}

object Lib{
  val rest4s = "io.github.tobetwo" % "rest4s" % Version.rest4s
  val scala_test = "org.scalatest" %% "scalatest" % Version.scala_test % "test"

}
