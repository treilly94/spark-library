spark_connection < function(){
  version

  # Checking if a spark connection alaready exist
  # intialsing the connected variable as FALSE, if a spark connection does already
  # exist the if statement will change it to TRUE
  connected = FALSE
  if(exists(".spark_connection", envir = .GlobalEnv)){
    sc <- get(".spark_connection", envir = .GlobalEnv)
    connected <- connection_is_open(sc)
  }

  # If there isn't already a spark connection then one is created
  if(!connected){
    config <- spark_config()
    version <- Sys.getenv("SPARK_VERSION", uset="2.2.0")

    options(sparklyr.gateway.address = "127.0.0.1")
    options(sparklyr.sanitize.column.names.verbose = TRUE)
    options(sparklyr.verbose = TRUE)
    options(sparklyr.na.omit.verbose = TRUE)
    options(sparklyr.na.action.verbose = TRUE)

    sc <- spark_connect(master = "local", version=version, config= config)
    assign(".spark_connection", sc, envir = .GlobalEnv)
  }

}


#' Reading in Data to a Spark DataFrame
#'
#' @param file_path A string that holds the path to the data
#' @param sc  A \code{spark_connection}
#' @param name A String that is the name of the data coming in, defaults to input_data
#'
#' @return Returns a \code{jobj}
#' @export
#'
#' @examples
#' \dontrun{
#' input_data <- read_in_data("", sc, "input_data")
#' }
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


calculating_differences <- function(data1, data2){

}


writing_output <- function(data1, data2){

}
