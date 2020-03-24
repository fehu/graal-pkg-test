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
  ).dependsOn(substitutions)

lazy val substitutions = (project in file("substitutions"))
  .settings(
    name := "graal-pkg-test-substitutions",
    skip in publish := true,
    libraryDependencies += Dependencies.svm
  )

// GraalVM image
enablePlugins(GraalVMNativeImagePlugin)

lazy val initializeAtBuildTime = Seq(
  "org.neo4j.driver.internal.shaded.io.netty.buffer.EmptyByteBuf",
  "org.neo4j.driver.internal.shaded.io.netty.buffer.ReadOnlyByteBuf",
  "org.neo4j.driver.internal.shaded.io.netty.buffer.Unpooled",
  "org.neo4j.driver.internal.shaded.io.netty.buffer.UnpooledByteBufAllocator",
  "org.neo4j.driver.internal.shaded.io.netty.buffer.UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf",
  "org.neo4j.driver.internal.shaded.io.netty.buffer.UnpooledByteBufAllocator$UnpooledByteBufAllocatorMetric",
  "org.neo4j.driver.internal.shaded.io.netty.buffer.UnreleasableByteBuf",
  "org.neo4j.driver.internal.shaded.io.netty.buffer.UnsafeByteBufUtil",
)

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
  "--initialize-at-build-time=" + initializeAtBuildTime.mkString(","),
  "--enable-all-security-services",
  // dev
  "-H:+ReportExceptionStackTraces",
  // This flag greatly helps to configure the image build to work as intended; the goal is to have as many classes initialized at build time and yet keep the correct semantics of the program. [[https://github.com/oracle/graal/blob/master/substratevm/CLASS-INITIALIZATION.md]]
  "-H:+PrintClassInitialization"
)
