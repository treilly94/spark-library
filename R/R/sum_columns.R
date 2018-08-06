#'
#'
#' @param sc
#' @param dataframe
#' @param column_1
#' @param column_2
#' @param new_column

sum_columns <- function(sc, dataframe, column_1, column_2, new_column) {


  # Checks to see if there is spark connection
  stopifnot(inherits(sc, c("spark_connection", "spark_Shell_connection", "DBIConnection")))
  # Checks to see if the dataframe is a spark/shell object
  stopifnot(inherits(dataframe, c("spark_jobj", "shell_jobj")))

  # Here we are checking that the parameters have come in as they should
  # Parameter types are important in R and Scala
  stopifnot(is.character(column_1) & is.character(column_2) & is.character(new_column))



  # Here we invoke the class and function within the object as the method
  # Then we invoke the method inside the class, with its parameters.
  # The types need to match in order to get the correct signutre otherwise you will get a no such method found error

  invoke_static(sc=sc, class="com.example", method="SumColumns", df=dataframe) %>%
    invoke(method = "sumColumns",
           df=dataframe,
           col1= column_1,
           col2=column_2,
           newCol=new_column)
}
