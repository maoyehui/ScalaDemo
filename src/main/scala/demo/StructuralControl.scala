package demo

/**
  * @ProjectName: ScalaDemo
  * @Package: demo
  * @ClassName: StructuralControl
  * @Description: Scala 结构控制示例
  * @Author: yehui.mao
  * @CreateDate: 2018/3/24 17:41
  * @UpdateUser: yehui.mao
  */
object StructuralControl {


  def main(args: Array[String]): Unit = {

    // for循环
    val a = Array("apple", "banana", "orange")
    for (e <- a) println(e)
    for (e <- a) {
      val s = e.toUpperCase()
      println(s)
    }

    // 从for循环中返回值
    val newArray = for (e <- a) yield {
      val s = e.toUpperCase()
      s
    }
    println(newArray.toList.toString)

    // for循环计数器
    for (i <- 0 until a.length) {
      println(s"$i is ${a(i)}")
    }
    for (i <- 1 to 2; j <- 1 to 2) println(s"i = $i, j = $j")

    // 卫语句
    for (i <- 1 to 10 if i < 4) println(i)

    // for推导不会改变集合的返回类型
    var fruits = scala.collection.mutable.ArrayBuffer[String]()
    fruits += "apple"
    fruits += "banana"
    fruits += "orange"
    println(fruits.asInstanceOf[AnyRef].getClass.getSimpleName)
    val out = for (e <- fruits) yield e.capitalize
    println(out.asInstanceOf[AnyRef].getClass.getSimpleName)

    val fruit = "apple" :: "banana" :: "orange" :: Nil
    val o = for (e <- fruit) yield e.toUpperCase()

    // 调用map方法可以起到相同的效果(map等价于for/yeild等)
    val result = fruit.map(_.toUpperCase())
  }
}
