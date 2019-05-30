package io.github.tobetwo.dreamfactory4s

package object implicits {
  implicit class StringToColumn(val sc: StringContext) {
    def $(args: Any*): Col = Col(sc.s(args: _*))
  }
}
