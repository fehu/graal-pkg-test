package io.github.fehu.test

import java.net.URI

import com.typesafe.config.ConfigFactory
import org.neo4j.driver.v1.{ AuthToken, AuthTokens }

case class Neo4jConfig(uri: URI, authToken: AuthToken)

object Neo4jConfig {
  private lazy val conf = ConfigFactory.load()

  def fromEnv(): Neo4jConfig = {
    val address = conf.getString("persistence.neo4j.address")
    val user = conf.getString("persistence.neo4j.user")
    val pwd  = conf.getString("persistence.neo4j.pwd")
    Neo4jConfig(URI.create(address), AuthTokens.basic(user, pwd))
  }
}
