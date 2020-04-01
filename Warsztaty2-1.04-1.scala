// Databricks notebook source
// MAGIC %md
// MAGIC # Warsztaty 2
// MAGIC 
// MAGIC * Marcin Markiewicz 234090
// MAGIC * Patrycja Szczakowska 234121
// MAGIC 
// MAGIC Agenda:<br/>
// MAGIC <ol>
// MAGIC   <li>Singletons objects<br/></li>
// MAGIC   <li>Variables<br/></li>
// MAGIC   <li>Statements and instructions<br/></li> 
// MAGIC   <li>Functions as variables<br/></li>
// MAGIC   <li>Testing and Assertions in ScalaTest</li>
// MAGIC </ol>

// COMMAND ----------

// MAGIC %md
// MAGIC ### 1. Singleton
// MAGIC 
// MAGIC 
// MAGIC * Singleton to obiekt przechowujący metody i wartości
// MAGIC * Odpowiednikiem w Javie są zmienne statyczne ( w Scali ich nie ma)
// MAGIC * Definiowany przy pomocy słowa kluczowego object oraz identyfikatora
// MAGIC * Tworzona jest jego tylko jedna instancja (automatycznie), nie używa się słowa kluczowego new

// COMMAND ----------

package singletonA

object Person {
  
  private def age = 42

  def filter(l: List[Int]): List[Int] = l.filter(_ < age)
}

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC * Obiekt sigletona często definiowany jest jako obiekt towarzyszący dla klasy o tej samej nazwie (muszą być wówczas przechowywane w tym samym pliku)
// MAGIC * Obiekt singletona wykorzystywany jest do rozszerzania cech (Traits) lub klasy

// COMMAND ----------

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


// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC * Pola i metody są dostępne globalnie o ile nie wykorzystano modyfikatora dostępu
// MAGIC   + private - dostępne tylko w klasie towarzyszącej
// MAGIC   + private[this] - dostępne tylko w obiekcie singletona

// COMMAND ----------

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

// COMMAND ----------

package singletonA

import singletonA.ClassA.{descA, sqrtA}
import singletonA.ClassC.descC

class ClassAC() {

  def desc(): String = descA + ", " + descC

  def count(): Int = sqrtA() //+ sqrtC() 

}

// COMMAND ----------

import singletonA.ClassAC

val ac = new ClassAC()

println(ac.desc())


// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ### 2. Zmienne

// COMMAND ----------

// MAGIC %md
// MAGIC * Brak konieczności deklaracji typu (definiowania zmiennych,
// MAGIC zwracanych typów funkcji, wyjątkiem są parametry funkcji)
// MAGIC * Dwa rodzaje deklaracji: var i val
// MAGIC +  var – zmienna
// MAGIC +  val – stała

// COMMAND ----------

var x = 1 // Zmienna o wnioskowanym typie Int
var y : Int = 1 // Zmienna o określonym typie Int
var z : Array[String] = new Array[String](3) // Zmienna typu tablicowego
// val z // Błąd. Stała niezdefiniowana
var a = 13
var b : Int = 13
var c : String = "13"
val d = 13
val e : Int = 13
a = 14
// d = 14 // błąd 
var f = new Array[Int](7)
val g : String = "I’m a ticket"
val h : Char = 't'
val i : Short = 5
val j : Long = 999999999999999L
val k : Float = 9.49f
val l : Double = 18.98
val m : Byte = 0xF
val (n, o) = Tuple2(21.37, "Ticket")


// COMMAND ----------

// MAGIC %md 
// MAGIC + W przeciwieństwie do Javy, Scala nie posiada wbudowanych
// MAGIC typów – wszystko jest obiektem
// MAGIC + Pola obiektów i zmienne lokalne mogą być deklarowane z
// MAGIC wyrażeniami val lub var
// MAGIC + Parametry metod zawsze są wartościami stałymi val

// COMMAND ----------

// MAGIC %md
// MAGIC ### 3. Instrukcje i wyrażenia

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC * Instrukcje warunkowe
// MAGIC  + if
// MAGIC  + if-else

// COMMAND ----------

var aa = 5
if(aa<10) { aa = 15 }

val nn = 10
val mm = if(nn ==10) "10"; else "!10"

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC * Pętle 
// MAGIC  + for
// MAGIC  + while
// MAGIC  + do-while
// MAGIC  
// MAGIC  
// MAGIC W Scali nie występuje inkrementacja i dekrementacja

// COMMAND ----------

var i:Int = 0
while (i < 2){
  println(i)
  i += 1
}

// COMMAND ----------

for (i <- 1 to 3){
  println(i)
}

// COMMAND ----------

for(a <- 1 to 2; b <- 1 to 2) {
  println(a + ":" + b);
}

// COMMAND ----------

for (a <- 0 to 4 if a%2 == 0){
  println(a)
}

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC * Instrukcje wyboru
// MAGIC  + match case

// COMMAND ----------

var a = 0
var b = "0"
a match {
  case 1 => b = "1"
  case 2 => b = "2"
  case _ => b = "default"
}

// COMMAND ----------

var a:Any = 0

a match {
  case 0 => a = "zero"
  case "0" => a = 2
  case _ => a = "default"
}

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC * Instrukcje sterowania
// MAGIC  + break
// MAGIC  + continue
// MAGIC  + return

// COMMAND ----------

import scala.util.control.Breaks._

for (i <- 1 to 3){
  if ( i == 3 ) {
    break
  }
  println(i)
}

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ### 4. Funkcje jako zmienne

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Funkcje w języku Scala mogą być zapisywane jako zmienne.
// MAGIC Deklaruje się je w następujący sposób.

// COMMAND ----------

var fun = (liczba: Integer) => if (liczba%2 == 0) "parzysta" else "nieparzysta"
println(fun(2))

// COMMAND ----------

// MAGIC %md
// MAGIC Tak zdefiniowaną funkcję możemy traktować jak każdą zmienną,
// MAGIC więc możemy ją np. przekazać jako parametr do innej metody.

// COMMAND ----------

def printFun(funkcja: Integer => String, liczba: Integer) = {
println(funkcja(liczba))
}

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC ### 5.Testowanie i asercje w ScalaTest 

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Klasy używane do tworzenia testów
// MAGIC + FunSuite – pozwala na pisanie opisowych nazw testów
// MAGIC + FlatSpec – testy nazywane w stylu „X should Y,” „A must B,
// MAGIC + FunSpec – pozwala na zagnieżdżanie opisów testów
// MAGIC + WordSpec – testy budowane w stylu „A when B should C in D...”
// MAGIC + FreeSpec – swoboda w nazywaniu testów (brak potrzeby uzycią np.. when,
// MAGIC   should itp.)
// MAGIC + PropSpec – pozwala pisać testy w zakresie testowania własności,
// MAGIC + FeatureSpec – używany do testowania wymagań akceptacyjnych
// MAGIC + RefSpec (tylko JVM) – krótszy czas kompilacji, używane w dużych projektach

// COMMAND ----------

import collection.mutable.Stack
import org.scalatest._

// COMMAND ----------

class ExampleSpec extends FlatSpec with Matchers {

  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should be (2)
    stack.pop() should be (1)
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new Stack[Int]
    a [NoSuchElementException] should be thrownBy {
      emptyStack.pop()
    } 
  }
}

// COMMAND ----------

class Test extends FunSuite {
  test("An empty Set should have size 0") {
    assert(Set.empty.size == 0)
  }
}

// COMMAND ----------

class TVSet {
  private var on: Boolean = false
  def isOn: Boolean = on
  def pressPowerButton() {
    on = !on
  }
}


// COMMAND ----------

class TVSetSpec extends FeatureSpec with GivenWhenThen {
info("As a TV set owner")
info("I want to be able to turn the TV on and off")
info("So I can watch TV when I want")
info("And save energy when I'm not watching TV") 
  feature("TV power button") {
    scenario("User presses power button when TV is off") {
        Given("a TV set that is switched off")
           val tv = new TVSet
           assert(!tv.isOn)
        When("the power button is pressed")
            tv.pressPowerButton()
        Then("the TV should switch on")
            assert(tv.isOn)
        }
    scenario("User presses power button when TV is on") {
        Given("a TV set that is switched on")
            val tv = new TVSet
        tv.pressPowerButton()
            assert(tv.isOn)
        When("the power button is pressed")
        tv.pressPowerButton()
        Then("the TV should switch off")
            assert(!tv.isOn)
          }
      }
}

// COMMAND ----------

import org.scalatest.Assertions._
assert("hello".startsWith("h") && "goodbye".endsWith("y"))
// Error message: "hello" started with "h", but "goodbye" did not end with "y"
assert(num.isInstanceOf[Int])
// Error message: 1.0 was not instance of scala.Int
assert(Some(2).isEmpty)
// Error message: Some(2) was not empty
assert(1 + 1 === 3, "this is a clue")
assertResult(3, "this is a clue") { 1 + 1 }
val s = "hi"
assertThrows[IndexOutOfBoundsException] { // Result type: Assertion
s.charAt(-1)
}
assertCompiles("val a: Int = 1")
assertDoesNotCompile("val a: String = 1")
assertTypeError("val a: String = 1")

// COMMAND ----------


