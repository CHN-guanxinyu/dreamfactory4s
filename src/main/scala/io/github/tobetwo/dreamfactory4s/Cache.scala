package io.github.tobetwo.dreamfactory4s

import io.github.tobetwo.rest4s.AbstractRest
import scalaj.http.HttpResponse

private[dreamfactory4s] class Cache extends AbstractRest[Cache] {
  def get(k: String) = new CacheResult(resource(k).getJson)

  def set(k: String, v: String = "", ttl: Int = 10) =
    resource(s"$k?ttl=$ttl").param("value", v).putJson.isNotError

  def delete(k: String) = {
    val result = get(k)
    if (result.exists) resource(k).deleteJson
    result.exists
  }
}

case class CacheResult(exists: Boolean = false, data: String) {
  def this(r: HttpResponse[Map[String, String]]) = this(exists = r.isNotError, data = r.body.getOrElse("value", ""))
}
