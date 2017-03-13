package org.juanitodread.model.adapter.facebook

import org.juanitodread.model.adapter.MessageAdapter
import org.juanitodread.model._

/**
 * Facebook message adapter.
 *
 * @author juan.sandoval
 *
 */
class FacebookMessageAdapter extends MessageAdapter[SimpleFacebookMessage, CommonMessage] {
  def toPlatform(clientMessage: SimpleFacebookMessage): CommonMessage = {
    val emitter = Emitter(clientMessage.entry.head.messaging.head.sender.id)
    val client = Client(clientMessage.entry.head.id, "facebook", clientMessage.entry.head.time)
    CommonMessage(client, emitter, "facebook", clientMessage.entry.head.messaging.head.message.text)
  }

  def toClient(commonMessage: CommonMessage): SimpleFacebookMessage = {
    ???
  }
}