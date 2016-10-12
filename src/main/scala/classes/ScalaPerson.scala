package classes

import scala.beans.BeanProperty

/**
  * Created by bluedeng on 16/10/10.
  */
class ScalaPerson(@BeanProperty var name: String, @BeanProperty var age: Int) {
  override def toString: String = s"this person is $name, age $age"
}
