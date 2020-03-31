package singletonA

class ClassC {

  import ClassC._

  def countC(): Int = sqrtC() //* valueC

}

object ClassC {
  private[this] def valueC = 12

  private def sqrtC(): Int = valueC * valueC

  def descC = "Class C"
}