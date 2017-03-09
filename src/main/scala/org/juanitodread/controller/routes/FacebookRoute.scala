package org.juanitodread.controller.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes

class FacebookRoute {

  val route = path("facebook" / "webhook") {
    post {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Starting...</h1>"))
    }
  }

}