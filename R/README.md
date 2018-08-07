# R
Information on creating wrappers for spark functions can be found [here](https://spark.rstudio.com/extensions/#introduction) 
## Project setup
#### Installing dependencies
The following dependencies are required:  
* sparklyr - Spark
* devtools - Development of packages
* testthat - Testing
* roxygen2

They can be installed with the below code snippet:

```R
install.packages(c("sparklyr", "testthat"))
```

#### Creating the documentation
```R
devtools::document()
```

#### Build the package

## Running Tests
The tests are run using the devtools package. The below command will run the tests
```R
devtools::test()
```

## Build and deployment