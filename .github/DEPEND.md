## | `Wonsi - Add as depend:`


Here is how to add this framework depending on your project.
### | `Gradle`:
If you use Gradle with Groovy, then here is an example of adding dependencies:
```groovy
repositories {
    // other repositories
    maven {
        name = "dynomakeRepo"
        url = uri("https://maven.dynomake.space/releases")
    }
}

dependencies {
    // other depends
    implementation 'space.dynomake:wonsi:1.0.7'
}
```

### | `Maven`:

Repository:

```xml
<repository>
    <id>dynomake-repo</id>
    <url>https://maven.dynomake.space/releases</url>
</repository>
```

Depend:

```xml

<dependency>
    <groupId>space.dynomake</groupId>
    <artifactId>wonsi</artifactId>
    <version>1.0.7</version>
</dependency>
```