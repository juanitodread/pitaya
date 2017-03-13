name := "pitaya"
version := "0.0.1"

lazy val root = (project in file("."))

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.0.4",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.4",
  "com.newmotion" %% "akka-rabbitmq" % "4.0.0"
)
