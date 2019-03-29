package demo.spark

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * @ProjectName: SBTTest
  * @Package: demo.spark
  * @ClassName: PageRankDemo
  * @Description: PageRank算法
  * @Author: yehui.mao
  * @CreateDate: 2018/12/27 15:00
  * @UpdateUser: yehui.mao
  */
object PageRankDemo extends App {
  val conf = new SparkConf()
    .setMaster("local[*]")
    .setAppName("PageRank Demo")
  val sc = new SparkContext(conf)

  val linkRoute = Seq("baidu sina google", "sina taobao jd", "google jd", "jd taobao")

  val links = sc.parallelize(linkRoute)
    .map(l => (l.split(" ")(0), l.split(" ")))
    .partitionBy(new HashPartitioner(2))
    .persist()

  var ranks = links.mapValues(v => 1.0)

  for (i <- 0 until 10) {
    val contributions = links.join(ranks).flatMap {
        case (pageId, (link, rank)) =>
          link.map(dest => (dest, rank / link.size))
      }
    ranks = contributions.reduceByKey((x, y) => x + y)
      .mapValues(v => 0.15 + 0.85 * v)
  }

  ranks.foreach(println(_))
}
