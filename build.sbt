name := "SBTTest"version := "1.0"scalaVersion := "2.11.8"libraryDependencies ++= Seq(  "org.apache.spark" % "spark-core_2.11" % "2.3.1",  "org.apache.spark" % "spark-sql_2.11" % "2.3.1",  "org.apache.spark" % "spark-streaming_2.11" % "2.3.1",  "commons-httpclient" % "commons-httpclient" % "3.1",  "org.apache.spark" % "spark-streaming-kafka-0-10_2.11" % "2.3.1",  "org.apache.flink" % "flink-streaming-java" % "1.7.1",  "org.apache.flink" % "flink-clients" % "1.7.1",  "org.apache.flink" % "flink-java" % "1.7.1")