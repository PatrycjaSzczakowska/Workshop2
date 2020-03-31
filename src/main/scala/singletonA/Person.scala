package singletonA

object Person {
  private def age = 42

  def filter(l: List[Int]): List[Int] = l.filter(_ < age)
}
