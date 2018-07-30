package com.example

object ScalaTestObject {

  protected def maths(val1: Int, val2: Int): Int = {
    val1 + val2
  }

  def method(): Int = {
    maths(1, 4)
  }

  def main(args: Array[String]): Unit = {
    print(method())
  }
}
