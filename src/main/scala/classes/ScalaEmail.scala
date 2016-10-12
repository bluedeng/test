package classes

import scala.beans.BeanProperty

/**
  * Created by bluedeng on 2016/10/11.
  */
class ScalaEmail {

  @BeanProperty
  var name: String = ""
  @BeanProperty
  var domain: String = ""

  override def toString: String = {
    if (name.isEmpty || domain.isEmpty) {
      "ERROR, you should set the information before get"
    } else {
      s"Email address is $name@$domain"
    }
  }
}
