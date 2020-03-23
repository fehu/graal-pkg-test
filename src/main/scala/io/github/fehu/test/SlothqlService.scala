/*
package io.github.fehu.test

import cats.effect.IO
import cats.instances.list._
import com.arkondata.slothql.neo4j.Neo4jCypherTransactor
import org.neo4j.driver.v1.exceptions.ServiceUnavailableException
import org.neo4j.driver.v1.{ Config, Driver, GraphDatabase }


class SlothqlService(cfg: Neo4jConfig)  {
  private val driver = SlothqlService.newDriver(cfg)
  private val tx = new Neo4jCypherTransactor(driver)

  def read[T](t: SlothqlService.Tx[T]): IO[List[T]] = tx.runRead(t)

  def write[T](t: SlothqlService.Tx[T]): IO[List[T]] = tx.runWrite(t)

  def close(): Unit = driver.close()
}

object SlothqlService {
  val TxB = Neo4jCypherTransactor
  type Tx[R] = TxB.Tx[List, R]

  def apply(): SlothqlService = apply(Neo4jConfig.fromEnv())

  def apply(cfg: Neo4jConfig): SlothqlService = new SlothqlService(cfg)

  protected def newDriver(cfg: Neo4jConfig): Driver =
    try GraphDatabase.driver(cfg.uri, cfg.authToken, Config.defaultConfig())
    catch {
      case _: ServiceUnavailableException => sys.exit(2)
    }
}*/
