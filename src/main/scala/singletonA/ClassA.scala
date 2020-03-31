package singletonA

class ClassA {

  import ClassA._

  def countA(): Int = valueA * sqrtA()

}

object ClassA {
  def valueA = 12

  def descA = "Class A"

  def sqrtA(): Int = valueA * valueA
}


//object ClassC {
//
//}

