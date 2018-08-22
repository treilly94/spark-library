package com.example

import org.apache.log4j.LogManager
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.catalyst.analysis.TempTableAlreadyExistsException

object SQLQuery {

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
  @throws(classOf[TempTableAlreadyExistsException])
  def sqlQuery(df: DataFrame, name: String, query: String): DataFrame = {

    val logger = LogManager.getRootLogger
    val fromClauseRegEx = "(?i)from ([^\\s]+)".r

    /** Prints a warning to Log4J if there exists a FROM clause referencing table names not equal to the name parameter.
      *
      * @param string String - Of the form "FROM [TABLE]"
      */
    def tableMatchWarning(string: String): Unit = {

      logger.warn("FROM clause argument '" + string.split(" ")(1)
                  + "' does not match given parameter name '" + name + ".'")
    }

    // Find "FROM [TABLE]" in query String, case insensitive.
    fromClauseRegEx.findAllIn(query)
                   .foreach(string => if (string.toLowerCase != "from " + name.toLowerCase) tableMatchWarning(string))

    // Create a temporary view.
    // Will raise a TempTableAlreadyExistsException if exists.
    df.createTempView(name)

    // Execute query.
    df.sparkSession.sql(query)
  }
}
