package demo.ClassAndProperties

/**
  * @ProjectName: SBTTest
  * @Package: demo
  * @ClassName: Pizza
  * @Description:
  * @Author: yehui.mao
  * @CreateDate: 2018/4/12 21:08
  * @UpdateUser: yehui.mao
  */

/*
1. 辅助构造函数必须用this命名创建
2. 每个辅助构造函数必须从调用之前定义的构造函数开始
3. 每个构造函数必须有不同的参数列表
4. 一个构造函数通过this调用另一个不同的构造函数
*/

object Pizza extends App{

  val DEFAULT_CRUST_SIZE = 12
  val DEFAULT_CRUST_TYPE = "THIN"

  val p1 = new Pizza(Pizza.DEFAULT_CRUST_SIZE, Pizza.DEFAULT_CRUST_TYPE)
  val p2 = new Pizza(Pizza.DEFAULT_CRUST_SIZE)
  val p3 = new Pizza(Pizza.DEFAULT_CRUST_TYPE)
  val p4 = new Pizza()

}

class Pizza(var crustSize: Int, var crustType: String) {
  def this(crustSize: Int) {
    this(crustSize, Pizza.DEFAULT_CRUST_TYPE)
  }

  def this(crustType: String) {
    this(Pizza.DEFAULT_CRUST_SIZE, crustType)
  }

  def this() {
    this(Pizza.DEFAULT_CRUST_SIZE, Pizza.DEFAULT_CRUST_TYPE)
  }

  override def toString = s"A $crustSize inch pizza with a $crustType) crust"
}
