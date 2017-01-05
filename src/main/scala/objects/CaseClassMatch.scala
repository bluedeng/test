package objects

/**
  * Created by bluedeng on 2016/11/19.
  */
object CaseClassMatch extends App{

  abstract class Message{}

  case class Email(var from: String, var title: String, var body: String) extends Message{
    override def toString: String = s"an email from $from about $title"
  }

  case class SMS(var from: String, var body: String) extends Message {
    override def toString: String = s"an SMS from $from"
  }

  def emailOrSMS(message: Message): String = message match {
    case Email(from, title, _) if from == "LiLei"=>
      s"important email $title from $from"
    case Email(from, title, body) =>
      "You got an email from " + from + " with title: " + title
    case SMS(from, body) =>
      "You got an SMS from " + from + "! Message: " + message
    case _ => "failed to match any"
  }

  println(emailOrSMS(null))
  println(emailOrSMS(Email("LiLei", "important issue", "debt")))
  println(emailOrSMS(Email("Jim", "sport", "table tennis")))
  println(emailOrSMS(SMS("13000000000", "play cards?")))
  println(emailOrSMS(new Message{}))

  println(Email("LiLei", "meeting", "Sid Meier's Civilization 6"))

}
