## | `Lithe DI - Usage:`
*You can found full example-code in:* [LINK](https://github.com/suuft/Lithe/tree/master/src/test/java/net/lithe/test)

First, lets create an application class that ll run the JVM. E.i. - Main:
```java
public class Main {

    public static void main(String[] args) {
    }
}
```
Lets create an abstract class (an interface, but you can create abstract class or a simple class) that ll have abstract methods for working with some data. I have this as metric, so this class ll count the number of sessions:
```java
public interface SessionCounter {
    int getSessions();
    void incrementSessions();
}
```
Okay come write it s implementation:
```java
public class MySessionCounter implements SessionCounter {

    private int sessions;

    @Override
    public int getSessions() {
        return sessions;
    }

    @Override
    public void incrementSessions() {
        sessions++;
    }
}
```
Good job! You should write a class that register dependencies, for further using - Adept, it will extends from `SimpleAdept`:
```java
public class CounterAdept extends SimpleAdept {
    @Override
    public void install() {
        register(SessionCounter.class, MySessionCounter.class);
    }
}
```
Done! So you can create an injector through the `Lithe` class and use the registered dependencies. I wrote an example in the Main class.
```java
public class Main {

    public static void main(String[] args) {
        Injector injector = Lithe.createInjector(new CounterAdept());
        System.out.println("Current sessions: " + injector.getInstance(SessionCounter.class).getSessions());

        System.out.println("Try increment #1");
        injector.getInstance(SessionCounter.class).incrementSessions();

        System.out.println("Try increment #2");
        injector.getInstance(SessionCounter.class).incrementSessions();

        System.out.println("Current sessions: " + injector.getInstance(SessionCounter.class).getSessions());
    }
}
```
The final output will show us the number 2. So you did everything right. Cool!