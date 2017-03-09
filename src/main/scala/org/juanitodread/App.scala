package org.juanitodread

import scala.io.StdIn

import org.juanitodread.util.AppConf

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.server.Directives._segmentStringToPathMatcher
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.Directives.get
import akka.http.scaladsl.server.Directives.path
import akka.stream.ActorMaterializer
import org.juanitodread.controller.FacebookController

object App extends AppConf {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("app-rabbitmq")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val facebookController = new FacebookController()

    // Start the server, bind routes to interface and port and return as Future
    val server = Http().bindAndHandle(facebookController.routes, httpHost, httpPort)

    // Notify user that server has started
    println(s"Server started at http://${httpHost}:${httpPort} - To stop it press RETURN...")
    StdIn.readLine()
    server.flatMap(srv => srv.unbind())
      .onComplete(evt => system.terminate())
  }

}