test_spark_connection <- function(){
  version <- Sys.getenv("SPARK_VERSION", unset = "2.2.0")

  if (exists(".test_livy_connection", envir = .GlobalEnv)) {
    spark_disconnect_all()
    Sys.sleep(3)
    livy_service_stop()
    remove(".test_livy_connection", envir = .GlobalEnv)
  }

  spark_installed <- spark_installed_versions()
  if (nrow(spark_installed[spark_installed$spark == version, ]) == 0) {
    options(sparkinstall.verbose = TRUE)
    spark_install(version)
  }

  expect_gt(nrow(spark_installed_versions()), 0)

  # generate connection if none yet exists
  connected <- FALSE
  if (exists(".test_spark_connection", envir = .GlobalEnv)) {
    sc <- get(".test_spark_connection", envir = .GlobalEnv)
    connected <- connection_is_open(sc)
  }

  if (!connected) {
    config <- spark_config()

    options(sparklyr.gateway.address = "127.0.0.1")
    options(sparklyr.sanitize.column.names.verbose = TRUE)
    options(sparklyr.verbose = TRUE)
    options(sparklyr.na.omit.verbose = TRUE)
    options(sparklyr.na.action.verbose = TRUE)

    setwd(tempdir())
    sc <- spark_connect(master = "local", version = version, config = config)
    assign(".test_spark_connection", sc, envir = .GlobalEnv)
  }

  # retrieve spark connection
  get(".test_spark_connection", envir = .GlobalEnv)
}


#' Reading in Data to a Spark DataFrame
#'
#' @param file_path A string that holds the path to the data
#' @param sc  A \code{spark_connection}
#' @param name A String that is the name of the data coming in, defaults to input_data
#'
#' @return Returns a \code{jobj}
#'
#' @examples
#' \dontrun{
#' input_data <- read_in_data(".../resources/inputs/input_data.json", sc, "input_data")
#' }
#' @export
read_in_data <- function(file_path, sc, name= "input_data"){
  # If statement to check the ending of the file path to see if its json or csv
  if(endsWith(file_path, "json")){
    # Brings in the data from the file and returns it as a spark dataframe
    sparklyr::spark_read_json(sc, name, path=file_path)%>%
      sparklyr::spark_dataframe()

  }
  else if(endsWith(file_path, "csv")){
    # Brings in the data from the file and returns it as a spark dataframe
    sparklyr::spark_read_csv(sc, name, path=file_path)%>%
      sparklyr::spark_dataframe()
  }
  else{
    stop("Wrong File forma, can only accpet json or csv. ")
  }
}

comparing_data <- function(actual, expected){
  tryCatch({
    expect_idential(actual,expected)
  },
  error = function(e){
    diff <- calculating_differences(actual, expected)
    writing_assertion_output(actual, expected, diff)
  }
  )

}

calculating_differences <- function(data1, data2){
  NULL
}


#' Writing Assertion Error Output
#'
#' @param test_name A String of the name of the test being called
#' @param actual A \code{jobj} which holds the data coming out of the function being tested
#' @param expected A \code{jobj} which holds the expected data that should have come out of the function being tested
#' @param differnces A \code{jobj} which holds the differences between the two dataset, defaults to Null
#'
#' @return
#'
#' @examples
#' \dontrun{
#'
#' }
#'
#' @export
writing_assertion_output <- function(test_name, actual, expected, differnces=NULL){
  # Creating the temp file name
  file_name <- tempfile(fileext = ".txt")
  # Opening the file in order to write to it
  file_num <- file(file_name, open="wt")

  # Writing to the file
  sink(file = file_name, append= FALSE)
  cat(paste0("\n ", test_name))
  cat(paste0("\n Actual Dataframe output\n"))
  print(actual, n= Inf, width=Inf)
  cat(paste0("\n Expected Dataframe\n"))
  print(expected, n= Inf, width=Inf)
  if(differnces.isNotNull){
    cat(paste0("\n Differencet\n"))
    print(differnces, n= Inf, width=Inf)
  }

  # Closing the file
  close(file_num)
  # Printing the file name so the tester knows where to look at the data
  cat("\n The difference can be seen in ", file_name, "\n", sep="")
}
