// Works
/*
package io.github.fehu.test

import cats.effect.IO
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger

object Test1 extends App {
  implicit val cs = IO.contextShift(scala.concurrent.ExecutionContext.global)
  implicit val logger = Slf4jLogger.getLogger[IO]

  val io = for {
    _ <- logger.info("foo")
    _ <- logger.warn("bar")
  } yield ()

  io.unsafeRunSync()
}
*/
