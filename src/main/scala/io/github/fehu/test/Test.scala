package io.github.fehu.test

import cats.effect.IO
import cats.instances.list._
import com.arkondata.slothql.cypher.syntax._
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger

object Test extends App {
  import SlothqlService._

  val writeQ = parameterized { (name: Param[String], fullName: Param[String]) =>
    val props = Map("name" -> name.known, "fullName" -> fullName.known)
    Create {
      case u@Vertex("User", `props`) => u.id
    }
  }
  def write(name: String, fullName: String = ""): Tx[Long] = TxB.query(writeQ).withParams(name = name, fullName = fullName)

  val readQ = Match { case u@Vertex("User") => u.prop[String]("name") }

  // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // //

  implicit val cs = IO.contextShift(scala.concurrent.ExecutionContext.global)
  implicit val logger = Slf4jLogger.getLogger[IO]

  val service = SlothqlService()

  // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // //

  val io1 = service.write(write("foo"))
  val io2 = service.read(TxB.query(readQ))

  val io = for {
    wrote <- io1
    _     <- logger.info(s"Wrote: ${wrote.mkString(", ")}")
    read  <- io2
    _     <- logger.info(s"Read: ${read.mkString(", ")}")
  } yield ()

  try io.unsafeRunSync()
  finally service.close()
}
