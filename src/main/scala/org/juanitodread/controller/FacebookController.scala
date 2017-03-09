package org.juanitodread.controller

import org.juanitodread.controller.routes.FacebookRoute
import org.juanitodread.util.AppConf

import akka.http.scaladsl.server.Directives.pathPrefix

class FacebookController extends AppConf {
  val facebookRouter = new FacebookRoute()

  val routes = pathPrefix(apiVersion) {
    facebookRouter.route
  }

}