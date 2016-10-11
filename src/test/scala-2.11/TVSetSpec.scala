import org.scalatest.{FeatureSpec, GivenWhenThen}

/**
  * Created by bluedeng on 16/10/9.
  */

class TVSet {
  private var on: Boolean = false
  def isOn: Boolean = on
  def pressPowerButton(): Unit = {
    on = !on
  }
}

class TVSetSpec extends FeatureSpec with GivenWhenThen {
  info("As a TV set owner")
  info("I want to be able to turn the TV on and off")

  feature("TV power button") {
    scenario("User presses power button when TV is off") {
      Given("a TV set that is switched off")
      val tv = new TVSet
      assert(!tv.isOn)

      When("the power button is pressed")
      tv.pressPowerButton()

      Then("the TV should switch on")
      assert(tv.isOn)
    }

    scenario("User presses power button when TV is on") {
      Given("a TV set that is switched on")
      val tv = new TVSet
      tv.pressPowerButton()
      assert(tv.isOn)

      When("the power button is pressed")
      tv.pressPowerButton()

      Then("the TV should switch off")
      assert(!tv.isOn)
    }
  }
}
