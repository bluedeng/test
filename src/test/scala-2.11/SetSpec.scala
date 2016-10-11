import org.scalatest.FlatSpec
import org.scalatest.tagobjects.Slow

/**
  * Created by bluedeng on 16/10/9.
  */
class SetSpec extends FlatSpec {

  //tagged as Slow
  "An empty Set" should "have size 0" taggedAs Slow in {
    assert(Set.empty.isEmpty)
  }

  //ignore
  ignore should "produce NoSuchElementException when head is invoked" in {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }

  //ignore
  "3 + 2" should "be 5" ignore {
    assertResult(5)(3 + 2)
  }
}
