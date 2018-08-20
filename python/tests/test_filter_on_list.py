from unittest import TestCase

from pyspark.sql import SparkSession

from methods.filter_on_list import filter_on_list


class TestFilter_on_List(TestCase):

    def test_filter_on_list(self):
        spark = SparkSession.builder \
            .master("local[*]") \
            .config("spark.jars", "../target/spark-library-1.0-SNAPSHOT.jar") \
            .getOrCreate()

        spark.sparkContext.setLogLevel("ERROR")

        input = spark.read.json("../src/test/resources/input/filter_on_list.json")
        print("Input")
        input.show()
        expected = spark.read.json("../src/test/resources/expected/filter_on_list.json")
        print("Expected")
        expected.show()
        output = filter_on_list(input, "targetCol", [1, 2, 5])
        print("Output")
        output.show()

        self.assertEqual(expected.collect(), output.collect())
