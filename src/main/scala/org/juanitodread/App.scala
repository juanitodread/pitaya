package org.juanitodread

import org.juanitodread.controller.FacebookController
import org.juanitodread.util.AppConf

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import scala.io.StdIn

/**
 * Application start point.
 *
 * @author juan.sandoval
 *
 */
object App extends AppConf {

  /**
   * @param args
   */
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("app-rabbitmq")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val facebookController = new FacebookController()

    // Start the server, bind routes to interface and port and return as Future
    val server = Http().bindAndHandle(
      facebookController.routes,
      httpHost, httpPort
    )

    // Notify user that server has started
    println(s"Server started at http://${httpHost}:${httpPort} - To stop it press RETURN...")
    StdIn.readLine()
    server.flatMap(srv => srv.unbind())
      .onComplete(evt => system.terminate())
  }

}