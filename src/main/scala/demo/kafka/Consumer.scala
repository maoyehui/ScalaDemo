package demo.kafka

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.{SparkConf, TaskContext}
import org.apache.spark.streaming.kafka010.{KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

/**
  * @ProjectName: SBTTest
  * @Package: demo.kafka
  * @ClassName: Consumer
  * @Description:
  * @Author: yehui.mao
  * @CreateDate: 2019/3/29 17:12
  * @UpdateUser: yehui.mao
  */
object Consumer extends App {

  val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
  val ssc = new StreamingContext(conf, Seconds(5))

  val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> "localhost:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "spark",
    "auto.offset.reset" -> "latest",
    "enable.auto.commit" -> (false: java.lang.Boolean)
  )

  val topics = Array("streaming")
  val kafkaStream = KafkaUtils.createDirectStream[String, String](
    ssc,
    PreferConsistent,
    Subscribe[String, String](topics, kafkaParams)
  )

  kafkaStream.foreachRDD(rdd => {
    // ConsumerRecord(topic = maxwell, partition = 0, offset = 336754, CreateTime = 1553679302113, checksum = 807646051, serialized key size = 66, serialized value size = 265, key = {"database":"spero_apppush_service","table":"getui","pk.id":36490}, value = {"database":"spero_apppush_service","table":"getui","type":"delete","ts":1553679302,"xid":313982176,"commit":true,"data":{"id":36490,"createdAt":"2019-03-27 09:20:10","updatedAt":"2019-03-27 09:20:10","userId":"spero_3029","cid":"f3d3fe74e1a89788a8b8dd12fc99a142"}})
    val offsetRanges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
    if (!rdd.isEmpty()){
      rdd.foreachPartition(p => {
        val o: OffsetRange = offsetRanges(TaskContext.get.partitionId())
        println(s"${o.topic} ${o.partition} ${o.fromOffset} ${o.untilOffset}")
//        val mapper = new ObjectMapper() with ScalaObjectMapper
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//        mapper.registerModule(DefaultScalaModule)
        p.foreach(row => {
          println("FUCK: " + row)
//          val keyMapper: Map[String, Object] = mapper.readValue(row.key(), classOf[Map[String, Object]])
//          val valueMapper: Map[String, Object] = mapper.readValue(row.value(), classOf[Map[String, Object]])
          //          println(key)
        })
      })
    } else {
      // RDD is empty!
    }
    kafkaStream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
  })

  ssc.start()
  ssc.awaitTermination()
}
