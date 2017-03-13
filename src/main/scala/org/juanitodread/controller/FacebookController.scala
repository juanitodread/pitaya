package org.juanitodread.controller

import org.juanitodread.controller.routes.FacebookRoute
import org.juanitodread.util.AppConf

import akka.http.scaladsl.server.Directives.pathPrefix

/**
 * Facebook controller.
 *
 * @author juan.sandoval
 *
 */
class FacebookController extends AppConf {
  val facebookRouter = new FacebookRoute()

  val routes = pathPrefix(apiVersion) {
    facebookRouter.route
  }

}