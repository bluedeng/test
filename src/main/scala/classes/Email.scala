package classes

/**
  * Created by bluedeng on 2016/11/14.
  */
class Email(var name: String, var domain: String) {
  override def toString: String = s"$name@$domain"
}

object Email {

  def apply(name: String, domain: String): Email = new Email(name, domain)

  def unapply(arg: Email): Option[(String, String)] = Some(arg.name, arg.domain)
}

object exec extends App{

  def getIt(email: Email) = email match {
    case Email(x, y) => s"$x@$y"
    case _ => ""
  }

  println(getIt(Email.apply("dengtianzhi", "meituan")))
}
