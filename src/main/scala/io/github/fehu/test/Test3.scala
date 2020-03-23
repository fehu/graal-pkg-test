package io.github.fehu.test

import cats.effect.IO
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger
import org.neo4j.driver.exceptions.ServiceUnavailableException
import org.neo4j.driver.{ Config, Driver, GraphDatabase }

object Test3 extends App {
  implicit val cs = IO.contextShift(scala.concurrent.ExecutionContext.global)
  implicit val logger = Slf4jLogger.getLogger[IO]

  private val driverCfg = Config
    .builder()
//    .withLogging(new ConsoleLogging(Level.FINEST))
    .build()
  private def newDriver(cfg: Neo4jConfig): Driver =
    try GraphDatabase.driver(cfg.uri, cfg.authToken, driverCfg)
    catch {
      case _: ServiceUnavailableException => sys.exit(2)
    }

  val driverIO = for {
    cfg <- IO { Neo4jConfig.fromEnv() }
    _ <- logger.info(s"cfg = $cfg")
    driver <- IO { newDriver(cfg) }
    _ <- logger.info(s"driver = $driver")
  } yield driver

  val io = driverIO.bracket{ driver =>
    for {
      session <- IO { driver.session() }
      _ <- logger.info(s"session = $session")
//      tx <- IO { session.beginTransaction() }
//      _ <- logger.info(s"tx = $tx")
//      _ <- IO { tx.success() }
      result <- IO { session.run("MATCH (n) RETURN n") }
      _      <- logger.info(s"result = $result")
//      _ <- logger.info("done")
    } yield ()
  }(IO delay _.close())

  io.unsafeRunSync()
}
