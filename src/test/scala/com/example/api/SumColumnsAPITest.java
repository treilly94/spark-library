package com.example.api;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SumColumnsAPITest {

    private SparkSession spark = SparkSession.builder().master("local").getOrCreate();

    @Test
    public void sumColumns() {
        Dataset<Row> input = spark.read().json("./src/test/resources/input/sum_columns.json");
        System.out.println("Input");
        input.show();

        Dataset<Row> expected = spark.read().json("./src/test/resources/expected/sum_columns.json");
        System.out.println("Expected");
        expected.show();

        Dataset<Row> output = SumColumnsAPI.sumColumns(input, "col1", "col2", "sum");
        System.out.println("Output");
        output.show();

        assertEquals(expected.collectAsList(), output.collectAsList());
    }
}