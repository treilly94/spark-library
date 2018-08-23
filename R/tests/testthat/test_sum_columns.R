context("Testing Sum Column Using BDD")

sc <- test_spark_connection()

describe("That two columns are taken in and summed", {
  it("two integers columns", {
    input_data <- read_in_data(file.path(here("../resources/input/sum_columns.json")), sc, name= "sum_col_input_data")

    expected<- read_in_data(file.path(here("../resources/expected/sum_columns.json")), sc, name= "sum_col_expected_data") %>%
      dplyr::collect() %>%
      dplyr::select() %>%
      dplyr::arrange()

    actual_data <- sum_columns(sc=sc, input_data, "col1", "col2", "sum") %>%
      dplyr::collect() %>%
      dplyr::select() %>%
      dplyr::arrange()

    comparing_data("sumtest1", actual_data, expected_data)

    })
  })
