package io.github.tobetwo.dreamfactory4s

import io.github.tobetwo.implicits._
abstract class Expression(val value: String = "", var deep: Int = 1)

case class StringExp(s: String) extends Expression(s)

case class Col(field: String) extends Expression(field.deCamelCase + "_") {
  def ===(o: Any) = "=" -> o e

  def !(o: Any) = "!=" -> o e

  def >(o: Any) = ">" -> o e

  def >=(o: Any) = ">=" -> o e

  def <(o: Any) = "<" -> o e

  def <=(o: Any) = "<=" -> o e

  def in(o: Any*) = "in" -> o e

  def ~>(o: Any*) = in(o)

  def notIn(o: Any*) = "not in" -> o e

  def !~>(o: Any*) = notIn(o)

  def like(o: String) = "like" -> s"%$o%" e

  def ~ = like _

  def startWith(o: String) = "like" -> s"$o%" e

  def ~*(o: String) = startWith(o)

  def endWith(o: String) = "like" -> s"%$o" e

  def *~(o: String) = endWith(o)

  def isNull = "is null" -> "" e

  def notNull = "is not null" -> "" e

  def inRange(start: Any, end: Any) = >=(start) & <=(end)

  def <>(start: Any, end: Any) = inRange(start, end)

  def notInRange(start: Any, end: Any) = <(start) | >(end)

  def ><(start: Any, end: Any) = notInRange(start, end)

  private def _this = this

  private implicit class ExpressionBuilder(tuple: (String, Any)) {
    def e = {
      val right = tuple._2 match {
        case o: String => if (o.nonEmpty) o.wrap else o
        case o: Int => o.toString
        case Col(f) => f
        case o: Array[String] => s"(${o.map(_.wrap) mkString ","})"
        case o: Array[Int] => s"(${o mkString ","})"
        case _ => ""
      }
      LogicExpression(_this, tuple._1, StringExp(right))
    }
  }

  private implicit class Wrapper(str: String) {
    def wrap = s"'$str'"
  }

}
object EmptyExpression extends LogicExpression(null, null, null)
case class LogicExpression(
  left: Expression,
  op: String,
  right: Expression
) extends Expression {

  def isEmpty = this == EmptyExpression

  def and[T](exp: LogicExpression) = &[T](exp)
  def &[T](exp: LogicExpression) = combine("and", exp)

  def or[T](exp: LogicExpression) = |[T](exp)
  def |[T](exp: LogicExpression) = combine("or", exp)

  private def combine(op: String, exp: LogicExpression) =
    if(!isEmpty && !exp.isEmpty) copy(this, op, exp)
    else if(isEmpty) exp
    else this

  def flatten: String = {
    def f(exp: Expression) = exp match {
      case e: LogicExpression => s"(${e.flatten})"
      case e: Expression => e.value
    }

    s"${f(left)} $op ${f(right)}"
  }

  override def toString: String = {
    def cr(e : Expression) = if(e.isInstanceOf[LogicExpression]) "\n" else ""
    if(left != null) left.deep += deep
    if(right != null) right.deep += deep
    val pre = List.fill(deep)("-+").mkString
    s"""$pre op: $op
      |$pre left: ${cr(left)}$left
      |$pre right: ${cr(right)}$right""".stripMargin
  }

}
