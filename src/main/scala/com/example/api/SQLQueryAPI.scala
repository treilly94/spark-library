package com.example.api

import com.example.SQLQuery
import org.apache.spark.sql.DataFrame

object SQLQueryAPI {

  /** Ease of use method.
    *
    * Creates a temporary Spark SQL view from a DataFrame, using a given name. Then performs an SQL query on the
    * view, returning a new DataFrame reference.
    *
    * If a temporary view already exists of the same name, that view is queried instead.
    *
    * @param df DataFrame - Input data to query
    * @param name String - Name to give temporary view
    * @param query String - SQL query String
    * @return DataFrame
    */
  def sqlQuery(df: DataFrame, name: String, query: String): DataFrame = SQLQuery.sqlQuery(df, name, query)
}
