package org.juanitodread.controller.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes
import org.juanitodread.model._

import org.juanitodread.model.FacebookJsonSupport._
import spray.json._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._
import org.juanitodread.model.adapter.facebook.FacebookMessageAdapter
import org.juanitodread.broker.client.Producer

/**
 * Facebook router.
 *
 * @author juan.sandoval
 *
 */
class FacebookRoute {

  val route = path("facebook" / "webhook") {
    get {
      complete(HttpEntity(ContentTypes.`application/json`, """{"msg":"use POST method"}"""))
    } ~
      post {
        entity(as[SimpleFacebookMessage]) { facebookMessage =>

          Future {
            val messageAdapter = new FacebookMessageAdapter()
            val commonMessage = messageAdapter.toPlatform(facebookMessage)

            // Enqueue to RabbitMQ
            println(Thread.currentThread())
            Producer.produce(commonMessage.toJson(CommonMessageSupport.commonMessageFormat).toString())
            println(s"Enqueued this common message: ${commonMessage.toJson(CommonMessageSupport.commonMessageFormat)}")
          }

          complete(HttpEntity(ContentTypes.`application/json`, facebookMessage.toJson.toString()))
        }
      }
  }

}