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
//  "-H:SubstitutionResources=" + baseDirectory.value / "graal" / "substitutions.json",
  "-H:SubstitutionFiles=" + baseDirectory.value / "graal" / "substitutions.json",
//  "-H:Optimize=0",
  "-H:ReflectionConfigurationFiles=" + baseDirectory.value / "graal" / "reflectconf.json",
//  "--initialize-at-build-time=org.slf4j.LoggerFactory",
//  "--initialize-at-build-time=org.slf4j.impl.SimpleLogger",
//  "--initialize-at-build-time=org.slf4j.impl.SimpleLoggerConfiguration",
//  "--initialize-at-build-time=org.slf4j.impl.SimpleLoggerFactory",
//  "--initialize-at-build-time=org.slf4j.impl.StaticLoggerBinder",
//  "--initialize-at-build-time=org.slf4j.impl.OutputChoice",
//  "--initialize-at-build-time=org.slf4j.helpers.NOPLoggerFactory",
//  "--initialize-at-build-time=org.slf4j.helpers.Util",
//  "--initialize-at-build-time=org.slf4j.helpers.SubstituteLoggerFactory",
//  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.PlatformDependent",
//  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.PlatformDependent0",
//  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.PlatformDependent$1",
//  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.PlatformDependent$2",
//  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.ReflectionUtil",
//  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.logging.InternalLoggerFactory",
//  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.logging.Slf4JLoggerFactory",
//  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.logging.Slf4JLogger",
//  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.SystemPropertyUtil",
//  "--initialize-at-build-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.CleanerJava6",
//  "--initialize-at-run-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.PlatformDependent0",
//  "--initialize-at-run-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess",
//  "--initialize-at-run-time=org.neo4j.driver.internal.shaded.io.netty.util.internal.CleanerJava6",
  //
//  "-Dorg.neo4j.driver.internal.shaded.io.netty.noUnsafe=true",
//  "-Djava.io.tmpdir=/tmp",
  //
  "--enable-all-security-services",
  "--no-fallback",
  "--allow-incomplete-classpath",
  // dev
  "-H:+ReportExceptionStackTraces"
)



/*
  {
    "annotatedClass": "io.github.fehu.test.Target_io_netty_util_internal_CleanerJava6",
    "originalClass": "org.neo4j.driver.internal.shaded.io.netty.util.internal.CleanerJava6",
    "fields": [
      {
        "annotatedName": "CLEANER_FIELD_OFFSET",
        "alias": true,
        "kindClassName": "com.oracle.svm.core.annotate.RecomputeFieldValue.Kind.FieldOffset",
        "declClassName": "java.nio.DirectByteBuffer",
        "name": "cleaner"
      }
    ]
  },
  {
    "annotatedClass": "io.github.fehu.test.Target_io_netty_util_internal_shaded_org_jctools_util_UnsafeRefArrayAccess",
    "originalClass": "org.neo4j.driver.internal.shaded.io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess",
    "fields": [
      {
        "annotatedName": "REF_ELEMENT_SHIFT",
        "alias": true,
        "kindClassName": "com.oracle.svm.core.annotate.RecomputeFieldValue.Kind.ArrayIndexShift",
        "declClassName": "java.lang.Object[]",
        "name": "<init>"
      }
    ]
  }

 */