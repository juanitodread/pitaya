package org.juanitodread.model

import spray.json.DefaultJsonProtocol
import spray.json.JsonFormat
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

case class Message(mid: String, seq: Int, text: String)
case class Recipient(id: String)
case class Sender(id: String)

case class Messaging(
  sender: Sender,
  recipient: Recipient,
  timestamp: Long,
  message: Message
)

case class Entry(
  id: String,
  time: Long,
  messaging: List[Messaging]
)

case class SimpleFacebookMessage(
  obj: String,
  entry: List[Entry]
)

/**
 * Object to format SimpleFacebookMessage to JSON
 *
 * @author juan.sandoval
 *
 */
object FacebookJsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val messageFormat = jsonFormat3(Message)
  implicit val recipientFormat = jsonFormat1(Recipient)
  implicit val senderFormat = jsonFormat1(Sender)
  implicit val messagingFormat = jsonFormat4(Messaging)
  implicit val entryFormat = jsonFormat3(Entry)
  implicit val simpleFacebookMessage = jsonFormat2(SimpleFacebookMessage)
}