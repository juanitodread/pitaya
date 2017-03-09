name := "pitaya"
version := "0.0.1"

lazy val root = (project in file("."))

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.0.4"
)
