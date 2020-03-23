package io.github.fehu.test

import java.util.logging.Level

import cats.effect.IO
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger
import org.neo4j.driver.internal.logging.{ ConsoleLogging, JULogging }
import org.neo4j.driver.v1.exceptions.ServiceUnavailableException
import org.neo4j.driver.v1.{ Config, Driver, GraphDatabase }

object Test3 extends App {
  implicit val cs = IO.contextShift(scala.concurrent.ExecutionContext.global)
  implicit val logger = Slf4jLogger.getLogger[IO]

//  private val driverCfg = Config.defaultConfig()
//  private val driverCfg = Config.build().withLogging(new JULogging(Level.FINEST)).toConfig
  private val driverCfg = Config.build().withLogging(new ConsoleLogging(Level.FINEST)).toConfig
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
