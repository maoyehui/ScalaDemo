package demo.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql._

/**
  * @ProjectName: SBTTest
  * @Package: demo.spark
  * @ClassName: DataFrameDemo
  * @Description:
  * @Author: yehui.mao
  * @CreateDate: 2018/8/17 17:17
  * @UpdateUser: yehui.mao
  */
object SparkSqlDemo {

  case class Person(name: String, age: Long)

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)

    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local[4]")
      .getOrCreate()

//    val df = ceateDataFrame(spark)

//    dataFrameOperations(df, spark)

//    runSqlQueries(df, spark)

//    createDataSet(spark)

//    interOperateRDD(spark)

    specifySchema(spark)

    spark.stop()
  }

  // Create DataFrame
  def ceateDataFrame(spark: SparkSession): DataFrame = {
    val df = spark.read.json("src/main/file/userprofile.json")
    df.show()
    return df;
  }

  def dataFrameOperations(df: DataFrame, spark: SparkSession): Unit = {
    // 将RDD转换为DataFrames等隐式转换
    import spark.implicits._

    // Print the schema in a tree format
    df.printSchema()

    // Select only the "name" column
    df.select("name").show()

    // Select everybody, but increment the age by 1
    df.select($"name", $"age" + 1).show()

    // Select people older than 21
    df.filter($"age" > 21).show()

    df.map(s => {"Dr. " + s.getAs("name")}).show()

    // Count people by age
    df.groupBy("age").count().show()
  }

  def runSqlQueries(df: DataFrame, spark: SparkSession): Unit= {
    // Register the DataFrame as a SQL temporary view
    df.createOrReplaceTempView("tmp_eople")

    spark.sql("SELECT age, count(1) as cnt FROM tmp_people group by age").show()

    // Register the DataFrame as a global temporary view
    df.createGlobalTempView("global_people")

    // Global temporary view is tied to a system preserved database `global_temp`
    spark.sql("SELECT * FROM global_temp.global_people").show()

    // Global temporary view is cross-session
    spark.newSession().sql("SELECT * FROM global_temp.global_people").show()
  }

  def createDataSet(spark: SparkSession): Unit = {
    import spark.implicits._

    Seq(Person("Andy", 32)).toDS().show()

    // Encoders for most common types are automatically provided by importing spark.implicits._
    val primitiveDS = Seq(1, 2, 3).toDS()
    val array = primitiveDS.map(_ + 1).collect() // Returns: Array(2, 3, 4)
    array.foreach(item => print("-->" + item))
    println()

    // DataFrames can be converted to a Dataset by providing a class. Mapping will be done by name
    val path = "src/main/file/userprofile.json"
    val peopleDS = spark.read.json(path).as[Person]
    peopleDS.show()
  }

  def interOperateRDD(spark: SparkSession) : Unit = {
    import spark.implicits._

    val peopleDF = spark.sparkContext
      .textFile("src/main/file/person.txt")
      .map(_.split(","))
      .map(attributes => Person(attributes(0), attributes(1).trim.toInt))
      .toDF()
    peopleDF.show()
    peopleDF.createOrReplaceTempView("people")
    val teenagersDF = spark.sql("SELECT name, age FROM people WHERE age BETWEEN 12 AND 19")

    teenagersDF.map(teenager => "Name: " + teenager.getAs[String]("name")).show()

    implicit val mapEncoder = org.apache.spark.sql.Encoders.kryo[Map[String, Any]]
    teenagersDF.map(teenager => teenager.getValuesMap[Any](List("name", "age"))).collect().foreach(println)
  }

  def specifySchema(spark: SparkSession) : Unit = {
    import org.apache.spark.sql.types._
    val peopleRDD = spark.sparkContext.textFile("src/main/file/person.txt")
    val schemaString = "name age"
    val fields = schemaString.split(" ").map(fieldName => StructField(fieldName, StringType, nullable = true))
    val schema = StructType(fields)
    val rowRDD = peopleRDD.map(_.split(",")).map(attributes => Row(attributes(0), attributes(1).trim))
    val peopleDF = spark.createDataFrame(rowRDD, schema)
    peopleDF.createOrReplaceTempView("people")
    val results = spark.sql("SELECT * FROM people")
    import spark.implicits._
    results.map(attributes => {"Name: " + attributes(0)}).show()
  }

}
