package com.example.implicits

import com.example.JUnitHelper
import com.example.implicits.SQLQueryImpl._
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.catalyst.analysis.TempTableAlreadyExistsException
import org.junit._

class SQLQueryImplTest {

  @Before def start(): Unit = JUnitHelper.setup()
  @After def end(): Unit = JUnitHelper.teardown()

  @Test
  def sqlQueryTestHappyPath(): Unit = {

    val inputDF: DataFrame = JUnitHelper.sparkSession.read.json("./src/test/resources/input/filter_on_list.json")
    val expectedDF: DataFrame = JUnitHelper.sparkSession.read.json("./src/test/resources/expected/filter_on_list.json")
    val outputDF: DataFrame = inputDF.sqlQuery("table", "SELECT * FROM table WHERE targetCol NOT IN (1,2,5)")

    println("Expected DataFrame:")
    expectedDF.show

    println("Output DataFrame:")
    expectedDF.show

    assert(expectedDF.collect sameElements outputDF.collect)
    println("Assertion successful.")
  }

  @Test(expected = classOf[TempTableAlreadyExistsException])
  def sqlQueryTestDuplicateView(): Unit = {

    val inputDF: DataFrame = JUnitHelper.sparkSession.read.json("./src/test/resources/input/filter_on_list.json")
    var outputDF: DataFrame = inputDF.sqlQuery("table", "SELECT * FROM table WHERE targetCol NOT IN (1,2,5)")
    outputDF = outputDF.sqlQuery("table", "SELECT * FROM table")
  }
}
