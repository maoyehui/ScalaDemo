package demo.spark

import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType

/**
  * @ProjectName: SBTTest
  * @Package: demo.spark
  * @ClassName: StructuredStreaming
  * @Description:
  * @Author: yehui.mao
  * @CreateDate: 2018/9/5 17:38
  * @UpdateUser: yehui.mao
  *
  * @commend: nc -lk 9999
  *          ./bin/run-example org.apache.spark.examples.sql.streaming.StructuredNetworkWordCount localhost 9999
  */
object StructuredStreaming {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder
      .appName("StructuredNetworkWordCount")
      .master("local[4]")
      .getOrCreate()

//    receive_1(spark)
    receive_2(spark)
  }

  def receive_1(spark: SparkSession): Unit = {
    import spark.implicits._

    val lines = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 9999)
      .load()

    // Split the lines into words
    val words = lines.as[String].flatMap(_.split(" "))

    // Generate running word count
    val wordCounts = words.groupBy("value").count()

    val query = wordCounts.writeStream
      .outputMode("complete")
      .format("console")
      .start()

    query.awaitTermination()
  }

  def receive_2(spark: SparkSession): Unit = {
    import spark.implicits._

    val socketDF = spark
      .readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 9999)
      .load()

    println(socketDF.isStreaming)

    socketDF.printSchema()

    val schema = new StructType()
      .add("avid", "string")
      .add("title", "string")
      .add("href", "string")
      .add("tags", "string")
      .add("favorite", "integer")
      .add("his_rank", "integer")
      .add("danmaku", "integer")
      .add("now_rank", "integer")
      .add("no_reprint", "integer")
      .add("like", "integer")
      .add("aid", "integer")
      .add("reply", "integer")
      .add("copyright", "integer")
      .add("view", "integer")
      .add("share", "integer")
      .add("coin", "integer")

    val csvDF = spark
      .readStream
      .option("seq", ",")
      .schema(schema)
      .csv("/Users/robbin/Desktop/bilibili.csv")

    val df = csvDF.writeStream.format("console").start()
    
  }

}
