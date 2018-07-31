package com.example

import org.apache.spark.sql.{Column, DataFrame}
import org.apache.spark.sql.functions.col

object SumColumns {

  protected def maths(col1: String, col2: String): Column = {
    col(col1) + col(col2)
  }

  def sumColumns(df: DataFrame, col1: String, col2: String, newCol: String): DataFrame = {
    df.withColumn(newCol, maths(col1, col2))
  }
}
