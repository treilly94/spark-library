package com.example

import org.apache.spark.sql.SparkSession

object ScalaTestObject {

  protected def maths(val1: Int, val2: Int): Int = {
    val1 + val2
  }

  def method(): Int = {
    maths(1, 4)
  }

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    println(method())
    println(spark.version)
  }
}
