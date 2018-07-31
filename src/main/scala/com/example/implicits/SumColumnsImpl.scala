package com.example.implicits

import org.apache.spark.sql.DataFrame
import com.example.SumColumns

object SumColumnsImpl {
  implicit class SumColumnMethodsImpl(df: DataFrame) {
    def sumColumns(col1: String, col2: String, newCol: String): DataFrame = {
      SumColumns.sumColumns(df = df, col1 = col1, col2 = col2, newCol = newCol)
    }
  }
}
