package demo

/**
  *
  * @ProjectName: SBTTest
  * @Package: demo
  * @ClassName: test
  * @Description:
  * @Author: yehui.mao
  * @CreateDate: 2018/3/24 15:20
  * @UpdateUser: yehui.mao
  */
object NumberDemo {

  /*
  数值类型: Byte、Char、Double、Float、Int、Long和Short
  非数值类型: Unit和Boolean
  */

  def toInt(s: String): Option[Int]= {
    try {
      Some(s.toInt)
    } catch {
      case e: NumberFormatException => None
    } finally {}
  }

  def ~=(x: Double, y: Double, precision: Double) : Boolean = {
    if ((x-y).abs < precision)
      true
    else
      false
  }

  def main(args: Array[String]): Unit = {
    // 使用String的to*方法
    val i = "100".toInt

    // java.lang.Integer类的parseInt方法支持进制转换
    val n = Integer.parseInt("100", 2)
    println(n)

    // 使用match匹配和Option/Some/None模式
    val aString = "good afternoon"
    val result = toInt(aString) match {
      case Some(x) => print(x)
      case None => 0
    }
    println(result)

    // 检查数据类型，在类型转换前可以确认是否可以进行类型转换
    val a = 1000L
    println("a is Byte: " + a.isValidByte)
    println("a is Short: " + a.isValidShort)

    // 给变量增加类型声明
    val b = 0: Byte

    // 递增和递减，声明为val的字段是不可变的，他们不能递增或递减，声明为var的Int类型变量可以用过+=，-=递增或递减
    var c = 1
    c += 2

    // 两个应该相等的浮点数有可能实际上是不等的
    val d = 0.3
    val e = 0.1 + 0.2
    println("d = " + d + " e = " + e)
    println("d == e : " + (d==e))
    println("d ~= e : " + ~=(d, e, 0.0001))

    // 使用BigInt和BigDecimal类创建大数
    var big = BigInt(1234567890)

    // 生成随机数
    val r = scala.util.Random
    r.setSeed(100)
    println("Random num : " + r.nextInt())
    println("0<=随机数<100 : " + r.nextInt(100))
  }

}
