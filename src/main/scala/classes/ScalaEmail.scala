package classes

import scala.beans.BeanProperty

/**
  * Created by bluedeng on 2016/11/14.
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

object ScalaEmail {

  def apply: ScalaEmail = new ScalaEmail()
  def unapply(arg: ScalaEmail): Option[(String, String)] = Some(arg.name, arg.domain)

  def getIt(email: ScalaEmail): String = email match {
    case ScalaEmail(name, domain) => s"$name@$domain"
    case _ => ""
  }
}
