package scalaz.zio.internal

import scala.util.Try

import org.specs2.Specification

class OneShotSpec extends Specification {
  def is =
    "OneShotSpec".title ^ s2"""
    Make a new OneShot
     set must accept a non-null value. $setNonNull
     set must not accept a null value. $setNull
     isSet must report if a value is set. $isSet
     get must fail if no value is set. $getWithNoValue
    """

  def setNonNull = {
    val oneShot = OneShot.make[Int]
    oneShot.set(1)

    oneShot.get() must_=== 1
  }

  def setNull = {
    val oneShot = OneShot.make[Object]
    Try(oneShot.set(null)) must beFailedTry
  }

  def isSet = {
    val oneShot = OneShot.make[Int]
    oneShot.isSet must beFalse
    oneShot.set(1)
    oneShot.isSet must beTrue
  }

  def getWithNoValue = {
    val oneShot = OneShot.make[Object]
    Try(oneShot.get) must beFailedTry
  }
}