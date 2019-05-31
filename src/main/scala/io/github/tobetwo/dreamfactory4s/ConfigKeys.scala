package io.github.tobetwo.dreamfactory4s

import io.github.tobetwo.rest4s.ConfigKey

object ConfigKeys {
  val DF_MYSQL_SERVICE_NAME = ConfigKey("df.mysql.service.name", "mysql")
  val DF_CACHE_SERVICE_NAME = ConfigKey("df.cache.service.name", "cache")
}
