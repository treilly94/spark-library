from pyspark.sql import DataFrame


def filter_on_list(df, target_col, values):
    # Get Scala API
    api = df._sc._jvm.com.example.api.FilterOnListAPI

    return DataFrame(api.filterOnList(df._jdf, target_col, values), df.sql_ctx)
