package org.juanitodread.util

import com.typesafe.config.ConfigFactory

trait AppConf {
  private val config = ConfigFactory.load()

  // HttpConfig
  private val httpConfig = config.getConfig("http")

  // RabbitConfig
  private val rabbitConfig = config.getConfig("rabbit")

  // expose configuration
  val httpHost = httpConfig.getString("host")
  val httpPort = httpConfig.getInt("port")
}