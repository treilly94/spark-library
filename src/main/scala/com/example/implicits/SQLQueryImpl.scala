package com.example.implicits

import com.example.SQLQuery
import org.apache.spark.sql.DataFrame

object SQLQueryImpl {

  implicit class SQLQueryImpl(df: DataFrame) {

    /** Ease of use method.
      *
      * Creates a temporary Spark SQL view from a DataFrame, using a given name. Then performs an SQL query on the
      * view, returning a new DataFrame reference.
      *
      * If a temporary view already exists of the same name, that view is queried instead.
      *
      * @param name String - Name to give temporary view
      * @param query String - SQL query String
      * @return DataFrame
      */
    def sqlQuery(name: String, query: String): DataFrame = SQLQuery.sqlQuery(df, name, query)
  }

}
