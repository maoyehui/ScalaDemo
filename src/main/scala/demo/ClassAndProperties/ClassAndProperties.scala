package demo.ClassAndProperties

/**
  * @ProjectName: SBTTest
  * @Package: demo
  * @ClassName: ClassAndProperties
  * @Description:
  * @Author: yehui.mao
  * @CreateDate: 2018/4/7 18:06
  * @UpdateUser: yehui.mao
  */
object ClassAndProperties {

  def main(args: Array[String]): Unit = {
//    val p = new Person("Adm", "Meyer")
//    System.out.println(p.firstName + " " + p.lastName + " " + p.age)

    val em1 = Employee("John Smith", 30)
    val em2 = Employee.apply("John Smith", 30)
    val em3 = Employee.apply()
    System.out.println(em3)

    val singleton = SingletonPattern.getInstance
    System.out.println(singleton)
  }

  // 当实例化Person类对象时，会看到从类定义的开始到结束，因为在类里面的方法时构造函数的一部分
  class Person(var firstName: String, var lastName: String) {
    println("the constructor begins")

    // some class fields
    private val HOME = System.getProperty("user.home")
    var age = 0

    // some methods
    override def toString: String = s"$firstName $lastName is $age years old"

    def printHome: Unit = {
      println(s"HOME = $HOME")
    }

    def printFullName: Unit = {
      println(this) // uses toString
    }

    printHome
    printFullName
    println("still in the constructor")
  }

}


