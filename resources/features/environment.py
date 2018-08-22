from behave import fixture, use_fixture
import os
import sys

os.environ['SPARK_HOME'] = "C:/Users/Ian Edwards/projects/dependencies/spark-2.2.0-bin-hadoop2.6"
sys.path.append("C:/Users/Ian Edwards/projects/dependencies/spark-2.2.0-bin-hadoop2.6/python/")
sys.path.append("C:/Users/Ian Edwards/projects/dependencies/spark-2.2.0-bin-hadoop2.6/python/lib/py4j-0.10.4-src")

try:
    from pyspark.sql import SparkSession
except ImportError as e:
    print("Cannot import Spark Modules")


@fixture()
def create_session(context):
    context.spark = SparkSession.builder \
        .master("local") \
        .appName("Behave") \
        .config("spark.jars", "target/smlts-1.0.0-jar-with-dependencies.jar") \
        .getOrCreate()
    context.spark.sparkContext.setLogLevel("ERROR")


@fixture()
def kill_session(context):
    context.spark.stop()


def before_all(context):
    use_fixture(create_session, context)


def after_all(context):
    use_fixture(kill_session, context)













