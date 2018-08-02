from unittest import TestCase

from pyspark.sql import SparkSession

from methods.sum_columns import sum_columns


class TestSum_columns(TestCase):

    def test_sum_columns(self):
        spark = SparkSession.builder\
            .master("local[*]")\
            .config("spark.jars", "../target/spark-library-1.0-SNAPSHOT.jar")\
            .getOrCreate()

        spark.sparkContext.setLogLevel("ERROR")

        input = spark.read.json("../src/test/resources/input/sum_columns.json")
        print("Input")
        input.show()
        expected = spark.read.json("../src/test/resources/expected/sum_columns.json")
        print("Expected")
        expected.show()
        output = sum_columns(input, "col1", "col2", "sum")
        print("Output")
        output.show()

        self.assertEqual(expected.collect(), output.collect())
