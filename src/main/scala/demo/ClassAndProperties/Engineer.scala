package demo.ClassAndProperties

/**
  * @ProjectName: SBTTest
  * @Package: demo.ClassAndProperties
  * @ClassName: Engineer
  * @Description: 当定义一个子类构造函数时，不要用var或者val声明类间公用的字段，在子类中用val或者var字段定义新的构造函数参数
  * @Author: yehui.mao
  * @CreateDate: 2018/5/3 21:46
  * @UpdateUser: yehui.mao
  */

// 子类定义extends时控制被其主构造函数调用的超类构造函数
class Engineer(name: String, age: Int, var position: String) extends Employee(name, age) {
  override def toString: String = s"name:$name, age=$age, position:$position"
}
