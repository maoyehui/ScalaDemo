package demo.ClassAndProperties

import scala.io.Source

/**
  * @ProjectName: SBTTest
  * @Package: demo.ClassAndProperties
  * @ClassName: Person
  * @Description: 根据Scala的约定声明getter和setter方法
  * @Author: yehui.mao
  * @CreateDate: 2018/5/2 20:30
  * @UpdateUser: yehui.mao
  */

object Person1 extends App {
//  val p1 = new Person1("Jonathan")
//  p1.name = "Jony" // setter
//  println(p1.name) // getter

//  val p2 = new Person2
//  p2.age = 12
//  System.out.println(p2.age + "--->" + p2.id)

  val foo = new Foo
}

class Person1(var _name: String) {
  def name = _name; // accessor
  def name_=(aName: String) { _name = aName }  // mutator
}

// 类字段定义为var时，会自动生成getter和setter方法；定义为val时，会自动生成getter方法；不想生成getter和setter方法使用private关键词修饰
class Person2() {
  var age: Int = _
  val id: Double = 10
}

// 读文件
class Foo {
  val text = scala.io.Source.fromFile("/Users/robbin/IdeaProjects/weekly-report/README.md").getLines().foreach(println)
}