package com.example.methods

import org.apache.spark.sql.{Column, DataFrame}
import org.apache.spark.sql.functions.col

object NewColumn {

  protected def maths(col1: String, col2: String): Column = {
    col(col1) + col(col2)
  }

  def addColumn(df: DataFrame, col1: String, col2: String, name: String): DataFrame = {
    df.withColumn(name, this.maths(col1, col2))
  }

}
