package demo.kafka

import java.util.Properties
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord

/**
  * @ProjectName: SBTTest
  * @Package: demo.kafka
  * @ClassName: Producer
  * @Description:
  * @Author: yehui.mao
  * @CreateDate: 2019/3/29 15:44
  * @UpdateUser: yehui.mao
  */
object Producer extends App {

  private val kafkaProps = new Properties()
  kafkaProps.put("bootstrap.servers", "localhost:9092")
  kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  val producer = new KafkaProducer[String, String](kafkaProps)

  try {
    while (true) {
      val key = "{\"key\": " + System.currentTimeMillis().toString + "-" + (Math.random()*1000).toInt + "}"
      val value = "{\"value\": " + (Math.random()*1000).toInt + "}"
      val record = new ProducerRecord[String, String]("streaming", key, value)
      producer.send(record)
      Thread.sleep(2000)
    }
  }
  catch {
    case e: Exception =>
      e.printStackTrace()
  }

}
