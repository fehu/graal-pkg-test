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
      Dependencies.`slf4j-simple` % Runtime,
      Dependencies.`slothql-cypher`
    )
  )

// GraalVM image
enablePlugins(GraalVMNativeImagePlugin)

graalVMNativeImageOptions ++= Seq(
  "-H:IncludeResources=application.conf",
  "-H:IncludeResources=log.properties",
  "--no-fallback",
  "--allow-incomplete-classpath",
  "--initialize-at-build-time=scala.Symbol",
  // dev
  "-H:+ReportExceptionStackTraces"
)
