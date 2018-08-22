package com.example.api;

import com.example.JUnitHelper;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.analysis.TempTableAlreadyExistsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SQLQueryAPITest {

    @Before
    public void start() {JUnitHelper.setup();}

    @After
    public void end() {JUnitHelper.teardown();}

    @Test
    public void sqlQueryTestHappyPath() {

        Dataset<Row> inputDF = JUnitHelper.sparkSession().read().json("./src/test/resources/input/filter_on_list.json");
        Dataset<Row> expectedDF = JUnitHelper.sparkSession().read().json("./src/test/resources/expected/filter_on_list.json");
        Dataset<Row> outputDF = SQLQueryAPI.sqlQuery(inputDF, "table", "SELECT * FROM table WHERE targetCol NOT IN (1,2,5)");

        System.out.println("Expected DataFrame:");
        expectedDF.show();

        System.out.println("Output DataFrame:");
        expectedDF.show();

        assertEquals(expectedDF.collectAsList(), outputDF.collectAsList());
        System.out.println("Assertion successful.");
    }

    @Test(expected = TempTableAlreadyExistsException.class)
    public void sqlQueryTestDuplicateView() {

        Dataset<Row> inputDF = JUnitHelper.sparkSession().read().json("./src/test/resources/input/filter_on_list.json");
        Dataset<Row> outputDF = SQLQueryAPI.sqlQuery(inputDF, "table", "SELECT * FROM table WHERE targetCol NOT IN (1,2,5)");
        SQLQueryAPI.sqlQuery(outputDF, "table", "SELECT * FROM table");
    }
}
