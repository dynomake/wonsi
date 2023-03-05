## | `Lithe DI - Usage:`
*You can found full example-code in:* [LINK](https://github.com/suuft/wonsi/tree/master/src/test/java/net/wonsi/test)

First, lets create an application class that ll run the JVM. E.i. - Main:
```java
public class Main {

    public static void main(String[] args) {
    }
}
```
Create an object that we will store. For example a user:
```java
// lombok helpers annotation, not required (setters, getters, constructors generation)
@Setter
@Getter
@AllArgsConstructor
// wonsi required annotation
@Table("app_users")
public class User {

    @WonsiPrimary
    @WonsiColumn(name = "identifier")
    private long identifier;

    @WonsiColumn(name = "vk")
    private String vkLink;

    @WonsiColumn(name = "tg")
    private String tgId;

    public static User deserialize(ResultSet data) {
        try {
            return new User(data.getInt("identifier"), data.getString("vk"), data.getString("tg"));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
```
Cool, now lets create a repository that would manage users (CRUD operations):
```java
@RequiredArgsConstructor
public class UserRepo {

    private final WonsiTable<User> table;

    public User getByLogin(String login) {
        return table
                .select()
                .where(Condition.is("login", login))
                .limit(1)
                .sync()
                .findFirst();
    }

    public Collection<User> getAll() {
        return table
                .select()
                .sync()
                .getAll();
    }

    public void save(User user) {
        table.insert()
                .updateOnDuplicate()
                .data(map -> {
                    map.put("identifier", user.getIdentifier());
                    map.put("vk", user.getVkLink());
                    map.put("tg", user.getTgId());
                })
                .sync();
    }

    public void delete(int identifier) {
        table.delete()
                .where(Condition.is("identifier", identifier))
                .limit(1)
                .sync();
    }
}
```
Super, now we can create an instance of the repository and do anything! Just look:
```java
public class TestBootstrap {

    public static void main(String[] args) {
       Wonsi wonsi = WonsiFactory.createInstance(YOU_SQL_DATABASE_CONNECTION);
       WonsiTable<User> table = wonsi.getTable(User.class, User::deserialize);

       UserRepo repository = new UserRepo(table);

       // okay, i have friends
       repository.save(new User(1, "https://vk.com/suuft", "@fuuft"));
       repository.save(new User(2, "https://vk.com/otherman", null));

       // OOOHH NOOO!!! Otherman cheated on me and we re not friends now.
        repository.delete(2);
    }
}
```
Well done, I hope this development will help you :heart: