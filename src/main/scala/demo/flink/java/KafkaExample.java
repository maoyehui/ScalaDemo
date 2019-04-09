package demo.flink.java;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.util.Collector;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

/**
 * @ProjectName: SBTTest
 * @Package: demo.flink.java
 * @ClassName: KafkaExample
 * @Description:
 * @Author: yehui.mao
 * @CreateDate: 2019/4/9 13:40
 * @UpdateUser: yehui.mao
 */
public class KafkaExample {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("group.id", "flink");
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("auto.offset.reset", "earliest");

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        FlinkKafkaConsumer010<String> consumer = new FlinkKafkaConsumer010<>(Collections.singletonList("streaming"), new SimpleStringSchema(), properties);

        DataStreamSource<String> stream = env.addSource(consumer);

        DataStream<String> output = stream.flatMap(new TokenizeFlatMap());

        output.print();

        env.execute("flink connect kafka test");

    }

    public static class TokenizeFlatMap implements FlatMapFunction<String, String> {

        private transient ObjectMapper jsonParser;

        @Override
        public void flatMap(String value, Collector<String> out) throws Exception {
            if (jsonParser == null) {
                jsonParser = new ObjectMapper();
            }
            JsonNode jsonNode = jsonParser.readValue(value, JsonNode.class);

            if (jsonNode.has("value")) {
                String result = jsonNode.get("value").asText();
                if (!"".equals(result)) {
                    out.collect(result);
                }

            }
        }

    }

}
