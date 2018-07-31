package com.example.implicits

import com.example.implicits.SumColumnsImpl._
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.junit.Test

class SumColumnsImplTest {

  val spark: SparkSession = SparkSession.builder().master("local").getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")

  @Test
  def testSumColumns() {
    val input: DataFrame = spark.read.json("src/test/resources/input/sum_columns.json")
    println("Input")
    input.show()
    val expected: DataFrame = spark.read.json("src/test/resources/expected/sum_columns.json")
    println("Expected")
    expected.show()
    val output: DataFrame = input.sumColumns("col1", "col2", "sum")
    println("Output")
    output.show()

    assert(output.collectAsList() == expected.collectAsList())
  }

}
