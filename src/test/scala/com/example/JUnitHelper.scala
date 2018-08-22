package com.example

import org.apache.log4j.{LogManager, Logger}
import org.apache.spark.sql.SparkSession

object JUnitHelper {

  var sparkSession: SparkSession = null
  val logger: Logger = LogManager.getRootLogger

  def setup: Unit = {

    if (sparkSession == null) sparkSession = SparkSession.builder()
                                                         .master("local")
                                                         .appName("spark-library")
                                                         .getOrCreate()

    logger.info("BeforeClass: Created Spark session.")
    sparkSession.sparkContext.setLogLevel("WARN")
  }

  def teardown: Unit = {

    if (sparkSession != null) {

      sparkSession.close
      sparkSession = null
      logger.info("AfterClass: Closed Spark session.")
    }
  }
}
