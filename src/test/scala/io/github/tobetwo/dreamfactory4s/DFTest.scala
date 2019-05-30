package io.github.tobetwo.dreamfactory4s

import org.scalatest.FunSuite

class DFTest extends FunSuite{
  test("base test") {
    println(DF.mysql.table("ibps_party_user").list)
  }
}
