package test

import org.scalatest.FunSuite

class CalculatorFunSuiteTest extends FunSuite {

  test("testAdd") {
    assert(Calculator.add(2, 4) == 6)
  }

  test("testMultiply") {
    assert(Calculator.multiply(2, 4) == 8)
  }
}
