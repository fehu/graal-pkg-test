/*
package io.github.fehu.test

import akka.actor.{ Actor, ActorRef, ActorRefFactory, Props }
import cats.instances.list._
import com.arkondata.slothql.neo4j.Neo4jCypherTransactor
import org.neo4j.driver.v1.exceptions.ServiceUnavailableException
import org.neo4j.driver.v1.{ Config, Driver, GraphDatabase }

class SlothqlPersistenceActor(cfg: Neo4jConfig) extends Actor {
  val driver = SlothqlPersistenceActor.newDriver(cfg)
  override def postStop(): Unit = driver.close()

  val tx = new Neo4jCypherTransactor(driver)

  def receive: Receive = {
    case SlothqlPersistenceActor.ReadQuery(read)   => exec { sender() ! tx.runRead(read).unsafeRunSync() }
    case SlothqlPersistenceActor.WriteQuery(write) => exec {
      val s = sender()
      val res = tx.runWrite(write).unsafeRunSync()
      println(s"Sender: $s")
      println(s"Write res = $res")
      s ! res
    }
  }


  private def exec(f: => Unit): Unit =
    try f
    catch { case err: Throwable => sender() ! err }
}

object SlothqlPersistenceActor {
  val TxB = Neo4jCypherTransactor
  type Tx[R] = TxB.Tx[List, R]

  def apply()(implicit factory: ActorRefFactory): ActorRef = apply(Neo4jConfig.fromEnv())

  def apply(cfg: Neo4jConfig)(implicit factory: ActorRefFactory): ActorRef =
    factory.actorOf(Props(new SlothqlPersistenceActor(cfg)))

  protected def newDriver(cfg: Neo4jConfig): Driver =
    try GraphDatabase.driver(cfg.uri, cfg.authToken, Config.defaultConfig())
    catch {
      case _: ServiceUnavailableException => sys.exit(2)
    }

  case class ReadQuery[T](read: Tx[T])
  case class WriteQuery[T](write: Tx[T])
}*/
