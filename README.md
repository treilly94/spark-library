[![Build Status](https://travis-ci.org/treilly94/spark-library.svg?branch=master)](https://travis-ci.org/treilly94/spark-library)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Slack Status](https://img.shields.io/badge/slack-online-purple.svg)](https://spark-library.slack.com/)
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
The core implementation is wrapped in a java api to allow the method to be used in java. It does this by converting any 
java parameters into scala ones. The api is also required for the python wrapper. 
These APIs can be found in the *com.example.api* package.  
They can be implemented as shown in the snippet below: 
```java
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import com.example.api.SumColumnsAPI;

Dataset<Row> df = // The dataframe the method will be applied to 

SumColumns.sumColumns(df, "col1", "col2", "sum");

```

#### Python wrapper
The python wrappers use py4j to pass the arguments into the java api, this then converts those arguments and passes 
them to the core scala implementation. The python files can be found in the python directory.  
They can be implemented as shown in the snippet below: 
```python
from methods.sum_columns import sum_columns

df = # The dataframe the method will be applied to 

sum_columns(df, "col1", "col2", "sum")

```

## Docker 
The dockerfile will create an environment appropriate for running this project in.
#### Building the image
The image can be built by running the following command in the root of this project
```
docker build -t spark-library-environment .
```
The -t argument provides a name for the docker image. This can be whatever you like.  
The . at the end of the command is required. It specifies that the docker file is in your cwd. If the dockerfile is not 
in the cwd this can be replaced with a filepath

#### Running the image
Once built the following command can be called to create a container 
```
docker run -it -d --rm --name spark-library-test spark-library-environment
```
You can then create an interactive shell using the below command
```
docker exec -it spark-library-test bash
```

> Note: The code in this image will be the code at the time that the image was built. Changes to the code will not be 
> synced between the container and host. If this is needed you could add volume mapping to the run command.
