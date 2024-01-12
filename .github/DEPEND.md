## | `Wonsi - Add as depend:`
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
    implementation 'io.github.dynomake:wonsi:1.0.2'
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
    <version>1.0.2</version>
</dependency>
```