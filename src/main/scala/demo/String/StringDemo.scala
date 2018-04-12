package demo.String

/**
  *
  * @ProjectName: ScalaDemo
  * @Package: demo
  * @ClassName: test
  * @Description: Scala 字符串示例
  * @Author: yehui.mao
  * @CreateDate: 2018/3/24 15:20
  * @UpdateUser: yehui.mao
  */
object StringDemo extends App {
  // 比较两个字符串的实例
  var s1 = "Hello"
  var s2 = "Hello"
  var s3 = "H" + "ello"

  if(s1 == s2)
    println("s1 = s2")
  if(s2 == s3)
    println("s2 = s3")

  // 字符串转换成大小写
  var s4 = s1.toUpperCase()
  println("s4 = " + s4)
  var s5 = s1.toLowerCase()
  println("s5 = " + s5)

  // 多行字符串
  var foo =
    """This is
      |a multiline
      |String
    """.stripMargin

  println(foo)

  // 分割字符串
  "good night".split(" ").foreach(println)

  // map函数
  var s = "egg, milk, butter, coco puffs"
  s.split(",").map(_.trim).foreach(println)

  // 在字符串使用变量替换，在字符串前加字母's'，每个变量以'$'开头
  val name = "Fred"
  val age = 33
  val weight = 200
  println(s"$name is $age years old, and weigh $weight pounds.")
  println(f"$name is $age years old, and weigh $weight%.2f pounds.")

  // 在字符串字面量中使用表达式
  println(s"Age next year is ${age + 1}")

  // 对字符串不进行转译
  println(raw"foo\nbar")

  // 字符串插入
  println("%s is %d years old, and weigh %.2f pounds.".format(name, age, weight.toFloat))

  // 遍历字符串每个字符
  val upper = "hello, world".map(s => s.toUpper)
  val lower = "HWLLO, WORLD".map(_.toLower)
  println(upper + " ==> " + lower)

  // filter函数
  val lower_upper = upper.filter(_ != 'L').map(_.toLower)
  println(lower_upper)

  // for循环遍历字符串每个字符
  for(c <- lower) println(c)

  // 在for循环添加yield实际上是将每次循环的结果放到了一个临时存放区中。当循环结束时，在临时存放区中的所有元素以一个集合的形式返回。
  val up = for(c <- "helo, world") yield c.toUpper
  println(up)

  val result = for {
    c <- "Hello, world"
    if c != 'l'
  } yield c.toUpper

  // 正则表达式
  val numPattern = "[0-9]+".r
  val address = "123 Main Streer Suite 101"
  for(c <- numPattern.findAllIn(address)) println(c)

  // getOrElse()设置默认值
  val pattern = "\b[a-b]*\b".r
  println(pattern.findFirstIn(address).getOrElse("not match"))

  // replace()替换
  val newAddress = address.replaceAll("[0-9]", "X")
  print("newAddress --> "  + newAddress)

  // 访问字符串中的一个字符
  val c1 = "hello"(1)
  val c2 = "hello".apply(1)
}
