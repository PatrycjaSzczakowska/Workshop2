package singletonA

import singletonA.ClassA.{descA, sqrtA}
import singletonA.ClassC.descC

class ClassAC() {

  def desc(): String = descA + ", " + descC

  def count(): Int = sqrtA() //+ sqrtC()

}

