package org.juanitodread.controller

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model.{
  HttpEntity,
  ContentTypes
}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.{
  get,
  complete,
  path
}
import scala.io.StdIn

object IndexController {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("pitaya-system")
    implicit val materializer = ActorMaterializer()

    implicit val executionContext = system.dispatcher

    val route = path("hi") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, 
          "<h1>Say hello to akka-http</h1>"))
      }
    }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println("Server started @ http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture.flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }

}