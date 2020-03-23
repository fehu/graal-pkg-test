/*
package io.github.fehu.test

import scala.concurrent.duration._

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import cats.effect.IO
import cats.instances.list._
import com.arkondata.slothql.cypher.syntax._
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger

object TestAkka extends App {
  import SlothqlPersistenceActor._

  val writeQ = parameterized { (name: Param[String], fullName: Param[String]) =>
    val props = Map("name" -> name.known, "fullName" -> fullName.known)
    Create {
      case u@Vertex("User", `props`) => u.id
    }
  }
  def write(name: String, fullName: String = ""): Tx[Long] = TxB.query(writeQ).withParams(name = name, fullName = fullName)

  val readQ = Match { case u@Vertex("User") => u.prop[String]("name") }

  // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // //

  implicit val system = ActorSystem()
  implicit val cs = IO.contextShift(system.dispatcher)
  implicit val timeout = Timeout(2.seconds)
  implicit val logger = Slf4jLogger.getLogger[IO]


  val actor = SlothqlPersistenceActor()

  // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // //

  val io1 = IO.fromFuture(IO {
    (actor ? WriteQuery(write("foo"))).mapTo[List[Long]]
  })

  val io2 = IO.fromFuture(IO {
    (actor ? ReadQuery(TxB.query(readQ))).mapTo[List[String]]
  })

  val io = for {
    wrote <- io1
    _     <- logger.info(s"Wrote: ${wrote.mkString(", ")}")
    read  <- io2
    _     <- logger.info(s"Read: ${read.mkString(", ")}")
  } yield ()

  io.unsafeRunSync()

  system.terminate()
}
*/
