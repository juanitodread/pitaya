package org.juanitodread.model

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class Emitter(id: String)
case class Client(
  id: String,
  name: String,
  time: Long
)

case class CommonMessage(
  client: Client,
  emitter: Emitter,
  typ: String,
  body: String
)

/**
 * Object to format CommonMessage to JSON
 *
 * @author juan.sandoval
 *
 */
object CommonMessageSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val emitterFormat = jsonFormat1(Emitter)
  implicit val clientFormat = jsonFormat3(Client)
  implicit val commonMessageFormat = jsonFormat4(CommonMessage)
}