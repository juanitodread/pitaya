package org.juanitodread.broker.client

import org.juanitodread.util.AppConf
import com.rabbitmq.client.ConnectionFactory

/**
 * RabbitMQ connection object.
 *
 * @author juan.sandoval
 *
 */
object RabbitConnection extends AppConf {
  final private val factory = new ConnectionFactory()
  factory.setHost(rabbitHost)
  factory.setPort(rabbitPort)
  factory.setUsername(rabbitUsername)
  factory.setPassword(rabbitPassword)

  val inputQueue = rabbitInputQueue

  /**
   * Creates a new RabbitMQ factory object to get connections from the
   * configured RabbitMQ instance.
   *
   * @return A ConnectionFactory object
   */
  def getConnectionFactory = factory
}