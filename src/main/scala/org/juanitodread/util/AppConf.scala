package org.juanitodread.util

import com.typesafe.config.ConfigFactory

/**
 * Application configuration
 *
 * @author juan.sandoval
 *
 */
trait AppConf {
  private val config = ConfigFactory.load()

  // HttpConfig
  private val httpConfig = config.getConfig("http")

  // RabbitConfig
  private val rabbitConfig = config.getConfig("rabbit")

  // expose configuration
  val httpHost = httpConfig.getString("host")
  val httpPort = httpConfig.getInt("port")
  val apiVersion = httpConfig.getString("api-version")

  val rabbitHost = rabbitConfig.getString("host")
  val rabbitPort = rabbitConfig.getInt("port")
  val rabbitUsername = rabbitConfig.getString("username")
  val rabbitPassword = rabbitConfig.getString("password")
  val rabbitInputQueue = rabbitConfig.getString("input_queue")
}