package demo.flink.java;

import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.wikiedits.WikipediaEditEvent;
import org.apache.flink.streaming.connectors.wikiedits.WikipediaEditsSource;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

/**
 * @ProjectName: SBTTest
 * @Package: demo.flink
 * @ClassName: JavaFlinkStreaming
 * @Description:
 * @Author: yehui.mao
 * @CreateDate: 2019/4/8 21:10
 * @UpdateUser: yehui.mao
 */
public class WikipediaAnalysis {

    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<WikipediaEditEvent> edits = env.addSource(new WikipediaEditsSource());

        KeyedStream<WikipediaEditEvent, String> keyedEdits = edits.keyBy(new KeySelector<WikipediaEditEvent, String>() {
            @Override
            public String getKey(WikipediaEditEvent event) throws Exception {
                return event.getUser();
            }
        });

        DataStream<Tuple2<String, Long>> result = keyedEdits
                .timeWindow(Time.seconds(5))
                .fold(new Tuple2<>("", 0L), new FoldFunction<WikipediaEditEvent, Tuple2<String, Long>>() {
                    @Override
                    public Tuple2<String, Long> fold(Tuple2<String, Long> acc, WikipediaEditEvent event) {
                        acc.f0 = event.getUser();
                        acc.f1 += event.getByteDiff();
                        return acc;
                    }
                });

        result.print();

        try {
            env.execute("Flink Streaming Test By JAVA");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
