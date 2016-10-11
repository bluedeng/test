import org.scalatest.tagobjects.Slow
import org.scalatest.{BeforeAndAfter, FlatSpec}

import scala.collection.mutable.ListBuffer

/**
  * Created by bluedeng on 16/10/9.
  */
class ExampleSpec extends FlatSpec with BeforeAndAfter{

  val builder = new StringBuilder
  val buffer = new ListBuffer[String]

  before{
    builder.append("ScalaTest is ")
  }

  after{
    builder.clear()
    buffer.clear()
  }

  "Testing" should "be easy" taggedAs Slow in {
    builder.append("easy!")
    assert(builder.toString() == "ScalaTest is easy!")
    assert(buffer.isEmpty)
    buffer += "sweet"
  }

  it should "be fun" in {
    builder.append("fun!")
    assert(builder.toString === "ScalaTest is fun!")
    assert(buffer.isEmpty)
  }
}
