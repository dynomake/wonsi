## | `Wonsi - Add as depend:`

[![Clojars Project](https://img.shields.io/clojars/v/io.github.dynomake/wonsi.svg)](https://clojars.org/io.github.dynomake/wonsi)

Here is how to add this framework depending on your project.
### | `Gradle`:
If you use Gradle with Groovy, then here is an example of adding dependencies:
```groovy
repositories {
    // other repositories
    maven {
        name = "clojars.org"
        url = uri("https://repo.clojars.org")
    }
}

dependencies {
    // other depends
    implementation 'io.github.dynomake:wonsi:VERSION'
}
```

### | `Maven`:

Repository:

```xml
<repository>
    <id>clojars.org</id>
    <url>https://repo.clojars.org</url>
</repository>
```

Depend:

```xml

<dependency>
    <groupId>io.github.dynomake</groupId>
    <artifactId>wonsi</artifactId>
    <version>VERSION</version>
</dependency>
```