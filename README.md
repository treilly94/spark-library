[![Build Status](https://travis-ci.org/treilly94/spark-library.svg?branch=master)](https://travis-ci.org/treilly94/spark-library)
# spark-library
This library contains methods written in spark-sql that can be imported and run in scala, java, and python.

## Structure
#### Core implementation
The core implementation of the methods are written in an object orientated way in the package *com.example*.  
It can be implemented as shown in the snippet below:  
```scala
import org.apache.spark.sql.DataFrame
import com.example.SumColumns.sumColumns

val df: DataFrame = // The dataframe the method will be applied to 

sumColumns(df, "col1", "col2", "sum")

```

#### Implicits wrapper
The core implementations have implicit wrappers that allow the methods to be applied straight to a spark dataframe. 
These can be found in the *com.example.implicits* package.  
They can be implemented as shown in the snippet below: 
```scala
import org.apache.spark.sql.DataFrame
import com.example.implicits.SumColumnsImpl._

val df: DataFrame = // The dataframe the method will be applied to 

df.sumColumns("col1", "col2", "sum")

```
This can make calling the methods and stringing multiple methods together cleaner.

#### Java API
The core implementation is wrapped in a java api to allow the method to be used in java. The api is also required 
for the python wrapper. These APIs can be found in the *com.example.api* package.  
They can be implemented as shown in the snippet below: 
```java
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import com.example.api.SumColumnsAPI;

Dataset<Row> df = // The dataframe the method will be applied to 

SumColumns.sumColumns(df, "col1", "col2", "sum");

```