package objects

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.io.Source
import scala.util.{Failure, Success}

/**
  * Created by bluedeng on 2017/1/6.
  */
object ScalaFuture extends App{

  val f: Future[List[Int]] = Future {
    val file  = Source.fromFile("/Users/bluedeng/Desktop/IDEA_projects/test/src/main/scala/objects/FutureTest.scala").getLines()
    file.map(_.length).toList
  }

  /*
  //阻塞式写法，加上Await.result or Thread.sleep(...)

  //for loop, map, flatMap ...
  //f map(suc => println(suc))

  //onComplete, onSuccess or onFailure
  f onComplete {
    case Success(suc) => println(suc)
    case Failure(fai) => println("failed because of " + fai)
  }

  //Await.result(f, Duration.fromNanos(100))

  Thread.sleep(1000)
  */
}