package demo.ClassAndProperties

/**
  * @ProjectName: SBTTest
  * @Package: demo.ClassAndProperties
  * @ClassName: Person
  * @Description:
  * @Author: yehui.mao
  * @CreateDate: 2018/5/2 20:30
  * @UpdateUser: yehui.mao
  */
object Person extends App {
  val p = new Person("Jonathan")
  p.name = "Jony" // setter
  println(p.name) // getter
}

class Person(private var _name: String) {
  def name = _name; // accessor
  def name_=(aName: String) { _name = aName }  // mutator
}
