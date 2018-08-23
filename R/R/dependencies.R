spark_dependecies <- function(spark_version, scala_version, ...){
  sparklyr::spark_dependency(
    jars = c(
      system.file(
        "../../target/spark-library-1.0-SNAPSHOT.jar",
        package="spark-library"
      )
    ),
    packages = c()
  )

}

#' @import sparklyr
.onload <- function(libname, pkgname) {
  sparklyr::register_extension(pkgname)
}
