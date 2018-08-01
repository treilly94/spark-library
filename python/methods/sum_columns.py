from pyspark.sql import DataFrame


def sum_columns(df, col1, col2, new_col):
    # Get the Scala API
    api = df._sc._jvm.com.example.api.SumColumnsAPI

    return DataFrame(api.sumColumns(df._jdf, col1, col2, new_col), df.sql_ctx)
