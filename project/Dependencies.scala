import sbt._

object Dependencies {
  lazy val `akka-actor`         = "com.typesafe.akka"   %% "akka-actor"         % Version.akka
  lazy val `akka-http`          = "com.typesafe.akka"   %% "akka-http"          % Version.akkaHttp
  lazy val config               = "com.typesafe"        %  "config"             % Version.config
  lazy val `log4cats-core`      = "io.chrisdavenport"   %% "log4cats-core"      % Version.log4cats
  lazy val `log4cats-slf4j`     = "io.chrisdavenport"   %% "log4cats-slf4j"     % Version.log4cats
  lazy val scalatest            = "org.scalatest"       %% "scalatest"          % Version.scalatest
  lazy val shapeless            = "com.chuusai"         %% "shapeless"          % Version.shapeless
  lazy val `slf4j-simple`       = "org.slf4j"           %  "slf4j-simple"       % Version.slf4j
  lazy val `slothql-arrows`     = "com.arkondata"       %% "slothql-arrows"     % Version.slothql
  lazy val `slothql-cypher`     = "com.arkondata"       %% "slothql-cypher"     % Version.slothql

  object Version {
    lazy val akka       = "2.6.3"
    lazy val akkaHttp   = "10.1.11"
    lazy val cats       = "2.1.0"
    lazy val catsEffect = "2.1.1"
    lazy val config     = "1.4.0"
    lazy val log4cats   = "1.0.1"
    lazy val scalatest  = "3.0.5"
    lazy val shapeless  = "2.3.3"
    lazy val slf4j      = "1.7.30"
    lazy val slothql    = "a1ead734"
  }
}
