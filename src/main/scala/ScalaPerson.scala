import scala.beans.BeanProperty

/**
  * Created by bluedeng on 16/10/10.
  */
class ScalaPerson(@BeanProperty val name: String, @BeanProperty val age: Int) {
  override def toString: String = s"this person is $name, age $age"
}


object ScalaPerson{
  def main(args: Array[String]): Unit = {
    val peter = new ScalaPerson("peter", 33)
    println(peter.name)
  }
}