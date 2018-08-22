package com.example

import org.junit.Test

class SQLQueryTest {

  @Test
  def sqlQueryTestHappyPath(): Unit = {

    JUnitHelper.setup

    val df = JUnitHelper.sparkSession.read.json("./src/test/resources/input/filter_on_list.json")
    df.show

    // do this

    JUnitHelper.teardown
  }
}
