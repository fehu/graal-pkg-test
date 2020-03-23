ThisBuild / scalaVersion     := "2.12.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "io.github.fehu"

lazy val root = (project in file("."))
  .settings(
    name := "graal-pkg-test",
    libraryDependencies ++= Seq(
//      Dependencies.`akka-actor`,
      Dependencies.config,
      Dependencies.`log4cats-core`,
      Dependencies.`log4cats-slf4j`,
      Dependencies.`neo4j-jdbc-driver`,
      Dependencies.netty,
      Dependencies.`slf4j-simple` % Runtime,
      Dependencies.`slothql-cypher`
    )
  )

// GraalVM image

enablePlugins(GraalVMNativeImagePlugin)

//val graalIncludeResources = Seq(
//  "application\\.conf",
//  "log\\.properties"
//)
val graalInitializeAtRuntime = Seq(
  "org.neo4j.driver.internal.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslEngine",
  "org.neo4j.driver.internal.shaded.io.netty.handler.ssl.ConscryptAlpnSslEngine",
  "org.neo4j.driver.internal.shaded.io.netty.channel.AbstractChannel",
  "org.neo4j.driver.internal.async.ChannelConnectedListener",
  "org.neo4j.driver.internal.shaded.io.netty.channel.EventLoop"
//  "io.netty.channel.EventLoop"
)

graalVMNativeImageOptions ++= Seq(
//  "-H:IncludeResources=" + graalIncludeResources.mkString(","),
  "-H:IncludeResources=application.conf",
  "-H:IncludeResources=log.properties",
  "-H:ReflectionConfigurationFiles=" + baseDirectory.value / "graal" / "reflectconf.json",
  "--initialize-at-build-time",
//  "--initialize-at-build-time=io.netty.netty-handler",
  "--initialize-at-run-time=" + graalInitializeAtRuntime.mkString(","),
  "--enable-all-security-services",
  "--no-fallback",
  "--allow-incomplete-classpath",
  // dev
  "-H:+ReportExceptionStackTraces",
//  "-Dio.netty.eventLoopThreads=8",
//  "-Dio.netty.noUnsafe=false",
//  "-Dio.netty.tmpdir=/tmp",
//  "-Dio.netty.bitMode=64",
//  "-Dio.netty.noPreferDirect=false",
)
