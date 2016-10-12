package objects

import java.util.NoSuchElementException

import scala.annotation.varargs
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

/**
  * Created by bluedeng on 2016/10/11.
  */
object BasicCalUtils {

  def sum(x: Int, y: Int): Int = x + y

  def join(s1: String, s2: String): String = s1 + s2

  @varargs def sumList(list: Int*): Int = list.sum

  @throws(classOf[ClassNotFoundException])
  @throws(classOf[NoSuchElementException])
  @throws(classOf[NoSuchElementException])
  def exceptionThrower(i: Int): Unit = i match {
    case 1 => throw new ClassNotFoundException
    case 2 => throw new NoSuchElementException
    case _ => throw new NoSuchMethodException
  }

  /*
  def sumTheList(list: ListBuffer[Int]): ListBuffer[Int] = {
    list.map(_ * 2)
  }
  */

  def sumTheList(list: java.util.List[Integer]): java.util.List[Int] = {
    bufferAsJavaList(list.map(_ * 2))
  }
}
