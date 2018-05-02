package demo.ClassAndProperties

/**
  * @ProjectName: SBTTest
  * @Package: demo.ClassAndProperties
  * @ClassName: Socket
  * @Description: 在构造函数的定义里给参数一个默认值
  * @Author: yehui.mao
  * @CreateDate: 2018/5/2 20:13
  * @UpdateUser: yehui.mao
  */
object Socket extends App {
  val socket = new Socket()
  val socket1 = new Socket1()
  System.out.println(socket.timeout)
  System.out.println(socket1)
}

// Class Socket等价于Class Socket1
class Socket (val timeout: Int = 10000)
// Class Socket1返回toString的值
class Socket1 (val timeout: Int) {
  def this() = this(1000);
  override def toString = s"Socket1: $timeout"
}
