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
      Dependencies.`neo4j-driver`,
      Dependencies.`slf4j-simple` % Runtime,
      Dependencies.`slothql-cypher`
    )
  )

// GraalVM image
enablePlugins(GraalVMNativeImagePlugin)

graalVMNativeImageOptions ++= Seq(
  "-H:IncludeResources=application.conf",
  "-H:IncludeResources=log.properties",
  "-H:ReflectionConfigurationFiles=" + baseDirectory.value / "graal" / "reflectconf.json",
  "--initialize-at-build-time=org.slf4j.LoggerFactory",
  "--initialize-at-build-time=org.slf4j.impl.SimpleLogger",
  "--initialize-at-build-time=org.slf4j.impl.SimpleLoggerConfiguration",
  "--initialize-at-build-time=org.slf4j.impl.SimpleLoggerFactory",
  "--initialize-at-build-time=org.slf4j.impl.StaticLoggerBinder",
  "--initialize-at-build-time=org.slf4j.impl.OutputChoice",
  "--initialize-at-build-time=org.slf4j.helpers.NOPLoggerFactory",
  "--initialize-at-build-time=org.slf4j.helpers.Util",
  "--initialize-at-build-time=org.slf4j.helpers.SubstituteLoggerFactory",
  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.PlatformDependent",
  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.PlatformDependent0",
  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.PlatformDependent$1",
  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.PlatformDependent$2",
  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.ReflectionUtil",
  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.logging.InternalLoggerFactory",
  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.logging.Slf4JLoggerFactory",
  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.logging.Slf4JLogger",
  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.SystemPropertyUtil",
  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.CleanerJava6",
  //
  "--enable-all-security-services",
  "--no-fallback",
  "--allow-incomplete-classpath",
  // dev
  "-H:+ReportExceptionStackTraces"
)
