package demo.ClassAndProperties

/**
  * @ProjectName: SBTTest
  * @Package: demo.ClassAndProperties
  * @ClassName: Employee
  * @Description:
  * @Author: yehui.mao
  * @CreateDate: 2018/4/12 22:39
  * @UpdateUser: yehui.mao
  */

/*
* 在伴生对象例实现apply方法，这样就可以不用new关键字去创建类的实例了
* */
object Employee{

  def apply() = new Employee("<no name>", 0)

  def apply(name: String) = new Employee(name, 0)

}

case class Employee(var name: String, var age: Int)
