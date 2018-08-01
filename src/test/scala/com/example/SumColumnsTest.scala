package com.example

import com.example.SumColumns.sumColumns
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.junit.Test

class SumColumnsTest {

  val spark: SparkSession = SparkSession.builder().master("local").getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")

  @Test
  def testSumColumns(): Unit = {
    val input: DataFrame = spark.read.json("src/test/resources/input/sum_columns.json")
    println("Input")
    input.show()
    val expected: DataFrame = spark.read.json("src/test/resources/expected/sum_columns.json")
    println("Expected")
    expected.show()
    val output: DataFrame = sumColumns(input, "col1", "col2", "sum")
    println("Output")
    output.show()

    assert(output.collectAsList() == expected.collectAsList())
  }

}
