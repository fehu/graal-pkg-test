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

lazy val initializeAtRunTime = Seq(
  "org.neo4j.driver.internal.shaded.io.netty.buffer"
)

graalVMNativeImageOptions ++= Seq(
  "-H:IncludeResources=application.conf",
  "-H:IncludeResources=log.properties",
  "--no-fallback",
  "--allow-incomplete-classpath",
  "--initialize-at-build-time",
  "--initialize-at-run-time=" + initializeAtRunTime.mkString(","),
  // dev
  "-H:+ReportExceptionStackTraces",
  // This flag greatly helps to configure the image build to work as intended; the goal is to have as many classes initialized at build time and yet keep the correct semantics of the program. [[https://github.com/oracle/graal/blob/master/substratevm/CLASS-INITIALIZATION.md]]
  "-H:+PrintClassInitialization"
)
