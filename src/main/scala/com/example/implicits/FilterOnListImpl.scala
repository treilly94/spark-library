package com.example.implicits

import com.example.FilterOnList
import org.apache.spark.sql.DataFrame

object FilterOnListImpl {

  implicit class FilterOnListMethodsImpl(df: DataFrame) {
    def filterOnList(targetCol: String, values: List[Int]): DataFrame = {
      FilterOnList.filterOnList(df, targetCol, values)
    }
  }

}
