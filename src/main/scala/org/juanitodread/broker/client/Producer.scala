package org.juanitodread.broker.client

import com.newmotion.akka.rabbitmq.ChannelActor
import com.newmotion.akka.rabbitmq.ChannelMessage
import com.newmotion.akka.rabbitmq.ConnectionActor
import com.newmotion.akka.rabbitmq.CreateChannel
import com.rabbitmq.client.Channel
import com.rabbitmq.client.MessageProperties

import akka.actor.ActorRef
import akka.actor.ActorSystem

/**
 * Producer to RabbitMQ using Akka Actors.
 *
 * @author juan.sandoval
 *
 */
object Producer {
  implicit val system = ActorSystem("rabbit-actor")

  val factory = RabbitConnection.getConnectionFactory
  val connectionActor = system.actorOf(ConnectionActor.props(factory), "rabbitmq")

  val queue = RabbitConnection.inputQueue
  val exchange = "amq.fanout"

  // this function will be called each time new channel received
  def setupChannel(channel: Channel, self: ActorRef) {
    channel.queueDeclare(queue, true, false, false, null)
    channel.queueBind(queue, exchange, "")
  }

  connectionActor ! CreateChannel(ChannelActor.props(setupChannel), Some("publisher"))

  def produce(message: String): Unit = {
    val publisher = system.actorSelection("/user/rabbitmq/publisher")

    def publish(channel: Channel) {
      channel.basicPublish(exchange, queue, MessageProperties.PERSISTENT_TEXT_PLAIN, toBytes(message))
    }
    publisher ! ChannelMessage(publish, dropIfNoChannel = false)
  }

  def fromBytes(x: Array[Byte]) = new String(x, "UTF-8")
  def toBytes(x: String) = x.toString.getBytes("UTF-8")
}