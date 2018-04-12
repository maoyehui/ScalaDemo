package demo.StructuralControl

import java.io._

import scala.tools.nsc.interpreter.{InputStream, OutputStream}
import scala.util.control.Breaks
import scala.util.control.Breaks._

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

    //    forDemo()

    //    breakAndContinueDemo()

    //    labeledBreakDemo()

    //    ternaryOperator()

    //    switchDemo(123)

    tryCatchDemo
  }

  def forDemo(): Unit = {
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

  // 实现break和continue
  def breakAndContinueDemo(): Unit = {
    println("\n=== BREAK EXAMPLE ===")

    breakable {
      for (i <- 1 to 10) {
        println(i)
        if (i > 4) break
      }
    }

    println("\n=== CONTINUE EXAMPLE ===")
    val searchMe = "petter piper picked a peck of pickled peppers"
    var numPs = 0
    val sp = searchMe.split(" ")
    for (i <- 0 until sp.length) {
      breakable {
        if (sp(i)(0) == 'p') {
          numPs += 1
        } else {
          break
        }
      }
    }
    println("Found " + numPs + " p's in the string.")
  }

  //循环break语句
  def labeledBreakDemo(): Unit = {
    val inner = new Breaks
    val outer = new Breaks
    outer.breakable {
      for (i <- 1 to 5) {
        inner.breakable {
          for (j <- 'a' to 'e') {
            if (i == 1 && j == 'c') {
              inner.break
            } else {
              println(s"i: $i, j: $j")
            }
            if (i == 2 && j == 'b') {
              outer.break
            }
          }
        }
      }
    }
  }

  // 使用if模拟三目运算符,scala中没有三目运算符
  def ternaryOperator(): Unit = {
    println(if (5 < 8) if (6 > 8) "6>8" else "6<8" else "5>8")
  }

  // 模拟switch匹配表达式
  def switchDemo(x: Any): Unit = x match {
    case s: String => println(s + " is a string.")
    case i: Int => println("Int")
    case f: Float => println("Folat")
    case l: List[_] => println("List")
    case _ => println("unknown")
  }

  // try catch捕捉异常
  def tryCatchDemo = {
    var in = None: Option[InputStream]
    var out = None: Option[OutputStream]

    in = Some(new FileInputStream("src/main/file/io.txt"))
    out = Some(new FileOutputStream("src/main/file/io.txt.cpoy"))
    var c = 0
    try {
      while ( {
        c = in.get.read();
        c != -1
      }) {
        out.get.write(c)
      }
    } catch {
      case e: IOException => e.printStackTrace()
    } finally {
      print("entered finally ...")
      if (in.isDefined)
        in.get.close()
      if (out.isDefined)
        out.get.close()
    }

  }

}
