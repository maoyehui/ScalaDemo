package demo.ClassAndProperties

/**
  * @ProjectName: SBTTest
  * @Package: demo.ClassAndProperties
  * @ClassName: SingletonPattern
  * @Description:
  * @Author: yehui.mao
  * @CreateDate: 2018/4/12 23:09
  * @UpdateUser: yehui.mao
  */

/*
* 一个伴生对象就是定义在与类的同一个文件中，同时对象与类有相同的名字。
* 在伴生对象中的任意一个方法将会变成该对象的静态方法。
* */
object SingletonPattern {
  val singletonPattern = new SingletonPattern
  def getInstance = singletonPattern
}

class SingletonPattern private {
  override def toString = "This is the singleton pattern."
}