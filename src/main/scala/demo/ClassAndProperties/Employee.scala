package demo.ClassAndProperties

/**
  * @ProjectName: SBTTest
  * @Package: demo.ClassAndProperties
  * @ClassName: Employee
  * @Description: 在伴生对象例实现apply方法，这样就可以不用new关键字去创建类的实例了
  * @Author: yehui.mao
  * @CreateDate: 2018/4/12 22:39
  * @UpdateUser: yehui.mao
  */

object Employee{

  def apply() = new Employee("<no name>", 0)

  def apply(name: String) = new Employee(name, 0)

}

/**
  * difference between case class and class:
  * 1. case class 初始化的时候可以不用new，当然你也可以加上，普通类一定需要加new
  * 2. case class 默认实现了equals 和hashCode
  * 3. case class 默认是可以序列化的，也就是实现了Serializable
  * 4. case class 构造函数的参数是public级别的
  * 5. case class 支持模式匹配
  * */
case class Employee(var name: String, var age: Int)
