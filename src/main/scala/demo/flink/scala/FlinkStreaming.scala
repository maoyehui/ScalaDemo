package demo.flink.scala

import java.util.{Collections, Properties}

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.datastream.DataStreamSource
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.kafka.common.serialization.StringDeserializer

/**
  * @ProjectName: SBTTest
  * @Package: demo.flink
  * @ClassName: FlinkStreaming
  * @Description:
  * @Author: yehui.mao
  * @CreateDate: 2019/4/8 16:23
  * @UpdateUser: yehui.mao
  */
object FlinkStreaming extends App {

  val env = StreamExecutionEnvironment.getExecutionEnvironment()

  val properties = new Properties()
  properties.setProperty("bootstrap.servers", "localhost:9092")
  properties.setProperty("group.id", "flink")
  properties.setProperty("key.deserializer", classOf[StringDeserializer].getName)
  properties.setProperty("value.deserializer", classOf[StringDeserializer].getName)
  properties.setProperty("auto.offset.reset", "earliest")

  val consumer = new FlinkKafkaConsumer010[String](
    Collections.singletonList("streaming"),
    new SimpleStringSchema(),
    properties
  )
//  consumer.setStartFromGroupOffsets()

  val stream: DataStreamSource[String] = env.addSource(consumer)

  stream.print()
  env.execute("Flink Streaming Test")

}
