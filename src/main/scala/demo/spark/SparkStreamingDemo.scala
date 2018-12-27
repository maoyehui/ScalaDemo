package demo.spark

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._

/**
  * @ProjectName: SBTTest
  * @Package: demo.spark
  * @ClassName: SparkStreamingDemo
  * @Description:
  * @Author: yehui.mao
  * @CreateDate: 2018/9/6 15:13
  * @UpdateUser: yehui.mao
  */
object SparkStreamingDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("NetworkWordCount")

    val ssc = new StreamingContext(conf, Seconds(1))

    val lines = ssc.socketTextStream("localhost", 9999)

    val words = lines.flatMap(_.split(" "))

    val pairs = words.map(word => (word, 1))

    val wordCounts = pairs.reduceByKey(_ + _)

    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()
  }

}
