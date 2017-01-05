package objects

/**
  * Created by bluedeng on 2016/11/19.
  */
object ExtractorMatch extends App{
  abstract class Message{}

  class Email(var from: String, var title: String, var body: String) extends Message{
    override def toString: String = s"an email from $from about $title"
  }

  object Email {

    def apply(from: String,title: String,body: String): Email =
      new Email(from, title, body)

    def unapply(arg: Email): Option[(String, String, String)] =
      Some(arg.from, arg.title, arg.body)
  }

  class SMS(var from: String, var body: String) extends Message {
    override def toString: String = s"an SMS from $from"
  }

  object SMS {

    def apply(from: String, body: String): SMS =
      new SMS(from, body)

    def unapply(arg: SMS): Option[(String, String)] =
      Some(arg.from, arg.body)
  }

  def emailOrSMS(message: Message): String = message match {
    case Email(from, title, body) =>
      "You got an email from " + from + " with title: " + title
    case SMS(from, body) =>
      "You got an SMS from " + from + "! Message: " + message
    case _ => "failed to match any"
  }

  println(emailOrSMS(null))
  println(emailOrSMS(new Email("Jim", "sport", "table tennis")))
  println(emailOrSMS(new SMS("13000000000", "play cards?")))
  println(emailOrSMS(new Message{}))
}
