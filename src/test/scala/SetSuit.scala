import org.scalatest.FunSuite

/**
  * Created by bluedeng on 16/10/8.
  */
class SetSuit extends FunSuite {

  test("An empty set should have size 0") {
    assert(Set.empty.isEmpty)
  }

  test("Invoking head on an empty Set should produce NoSuchElementException") {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }
}
