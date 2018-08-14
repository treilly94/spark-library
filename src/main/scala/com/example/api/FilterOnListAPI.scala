package com.example.api

import java.util

import com.example.FilterOnList
import org.apache.spark.sql.{DataFrame, Dataset, Row}

import scala.collection.JavaConversions._

object FilterOnListAPI {
  def filterOnList(df: Dataset[Row], targetCol: String, values: util.ArrayList[Integer]): DataFrame = {
    FilterOnList.filterOnList(df, targetCol, values.map(_.toInt).toList)
  }
}
