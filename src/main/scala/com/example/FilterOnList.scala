package com.example

import org.apache.spark.sql.DataFrame

object FilterOnList {

  def filterOnList(df: DataFrame, targetCol: String, values: List[Int]): DataFrame = {
    df
  }
}
