package demo.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.log4j.{Level, Logger}

/**
  * @ProjectName: SBTTest
  * @Package: demo.spark
  * @ClassName: WordCount
  * @Description:
  * @Author: yehui.mao
  * @CreateDate: 2018/8/13 11:26
  * @UpdateUser: yehui.mao
  */
object WordCount extends App {
  Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
  val inputFile =  "file:///Users/robbin/IdeaProjects/pickup/README.md"
  val conf = new SparkConf().setAppName("WordCount").setMaster("local")
  val sc = new SparkContext(conf)
  val textFile = sc.textFile(inputFile)
  val wordCount = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey((a, b) => a + b)
  wordCount.foreach(println)
}
