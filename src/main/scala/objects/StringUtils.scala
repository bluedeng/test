package objects

import classes.ScalaPerson

/**
  * Created by bluedeng on 2016/11/16.
  */
object StringUtils {
  implicit class StrWrapper(string: String) {
    def str2Person: ScalaPerson = new ScalaPerson(string, 25)
  }
}
