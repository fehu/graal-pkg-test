GraalVM Native Image
------------

#### Build
```
$ sbt graalvm-native-image:packageBin
```

#### Run
```
$ target/graalvm-native-image/graal-pkg-test
```

JVM (compare)
------------------

#### Build
```
$ sbt universal:stage
```

#### Run
```
$ target/universal/stage/bin/graal-pkg-test
```
