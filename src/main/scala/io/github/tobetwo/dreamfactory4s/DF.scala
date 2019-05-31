package io.github.tobetwo.dreamfactory4s

import io.github.tobetwo.rest4s.{AbstractRest, Config}

object DF extends AbstractRest[DreamFactory] {
  def mysql = new Mysql() ~ Config.get(ConfigKeys.DF_MYSQL_SERVICE_NAME) param("limit", "10")

  def cache = new Cache() ~ Config.get(ConfigKeys.DF_CACHE_SERVICE_NAME)
}

private[dreamfactory4s] class DreamFactory extends AbstractRest[DreamFactory]