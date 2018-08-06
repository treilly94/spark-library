sum_columns <- function(x, col1, col2, newCol) {
  # Turn the dataset into a spark dataframe
  df <- spark_dataframe(x)
  # get the underlying connection so we can create new objects
  sc <- spark_connection(df)

  output <- sc %>%
    invoke_new("com.example.SumColumns") %>%
    invoke("sumColumns", df, col1, col2, newCol)

  output
}
