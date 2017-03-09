package org.juanitodread

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.Http
import scala.io.StdIn
import org.juanitodread.util.AppConf

object App extends AppConf {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("app-rabbitmq")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val route = path("") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Starting</h1>"))
      }
    }

    // Start the server, bind routes to interface and port and return as Future
    val server = Http().bindAndHandle(route, httpHost, httpPort)

    // Notify user that server has started
    println(s"Server started at http://${httpHost}:${httpPort} - To stop it press RETURN...")
    StdIn.readLine()
    server.flatMap(srv => srv.unbind())
      .onComplete(evt => system.terminate())
  }

}