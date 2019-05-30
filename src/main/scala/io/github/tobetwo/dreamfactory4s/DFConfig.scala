package io.github.tobetwo.dreamfactory4s

import io.github.tobetwo.rest4s.Config

object DFConfig{
  val DF_MYSQL_SERVICE_NAME: String = Config.get("df.mysql.service.name", "mysql")
}
