package com.example.api;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FilterOnListAPITest {
    private SparkSession spark = SparkSession.builder().master("local").getOrCreate();

    @Test
    public void filterOnList() {
        Dataset<Row> input = spark.read().json("./src/test/resources/input/filter_on_list.json");
        System.out.println("Input");
        input.show();

        Dataset<Row> expected = spark.read().json("./src/test/resources/expected/filter_on_list.json");
        System.out.println("Expected");
        expected.show();

        ArrayList<Integer> values = new ArrayList<Integer>();
        values.add(1);
        values.add(2);
        values.add(5);

        Dataset<Row> output = FilterOnListAPI.filterOnList(input, "targetCol", values);
        System.out.println("Output");
        output.show();

        assertEquals(expected.collectAsList(), output.collectAsList());
    }
}
