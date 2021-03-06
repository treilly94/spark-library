package com.example.implicits

import com.example.implicits.FilterOnListImpl._
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.junit.Test

class FilterOnListImplTest {
  val spark: SparkSession = SparkSession.builder().master("local").getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")

  @Test
  def testFilterOnList(): Unit = {
    val input: DataFrame = spark.read.json("./src/test/resources/input/filter_on_list.json")
    println("Input")
    input.show()
    val expected: DataFrame = spark.read.json("./src/test/resources/expected/filter_on_list.json")
    println("Expected")
    expected.show()
    val output: DataFrame = input.filterOnList("targetCol", List(1, 2, 5))
    println("Output")
    output.show()

    assert(output.collectAsList() == expected.collectAsList())
  }
}
