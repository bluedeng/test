import classes.{JavaEmail, JavaPersion}
import Utils.BasicUtils
import objects.BasicCalUtils

/**
  * Created by bluedeng on 2016/10/11.
  */
object ScalaMain {
  def main(args: Array[String]): Unit = {

    /*
    val peter = new JavaPersion()
    println(peter)
    peter.setName("peter")
    peter.setAge(32)
    println(peter)
    */

    /*
    val email = new JavaEmail()
    println(email)
    email.setName("zhushichong")
    email.setDomain("meituan.com")
    println(email)
    */

    val sum: Int = BasicUtils.sum(3, 5)
    println(sum.getClass)
  }
}
