package test

import org.scalatest.FlatSpec

class CalculatorFlatSpecTest extends FlatSpec {

  "The addition result" should "be 6" in {
    assert(Calculator.add(2, 4) == 6)
  }

  "The multiplication result" should "be 6" in {
    assert(Calculator.multiply(2, 4) == 8)
  }
}
