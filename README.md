#### Build
```
sbt "show graalvm-native-image:packageBin"
```

#### Run
```
target/graalvm-native-image/graal-pkg-test -Djava.library.path=${GRAAL_HOME}/jre/lib/amd64
```

Providing `java.library.path` is required because of `--enable-all-security-services` parameter.
