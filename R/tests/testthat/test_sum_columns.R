context("Testing Sum Column Using BDD")

sc <- test_spark_connection()

describe(" ", {
  it(" ", {
    input_data <- read_in_data(file.path(PROJHOME, "../resources/input/sum_columns.json"), sc, name= "sum_col_input_data")

    expected<- read_in_data(file.path(PROJHOME, "../resources/expected/sum_columns.json"), sc, name= "sum_col_expected_data") %>%
      dplyr::collect() %>%
      dplyr::select() %>%
      dplyr::arrange()

    actual_data <- sum_columns(sc =sc, input_data, column_1, column_2, new_column) %>%
      dplyr::collect() %>%
      dplyr::select() %>%
      dplyr::arrange()

    comparing_data(actual_data, expected_data)

    })
  })
