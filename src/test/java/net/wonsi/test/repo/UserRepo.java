package net.wonsi.test.repo;

import lombok.AllArgsConstructor;
import net.wonsi.database.WonsiDatabase;
import net.wonsi.test.model.User;

import java.util.Collection;

/**
 * Wonsi Object Mapping Technology
 *
 * Example Usage class. Created by suuft, at
 * 20/02/2023 14:20
 */
@AllArgsConstructor
public class UserRepo {

    private WonsiDatabase database;

    public User getByLogin(String login) {
        return database.getTable(User.class) // get table
                .select() // create select request
                .where(row -> row.getValue("login").equals(login)) // request parameters
                .limit(1) // request parameters
                .sync(); // get response & deserialize
    }

    public Collection<User> getAll() {
        return database.getTable(User.class) // get table
                .select() // create select request
                .all()
                .sync(); // get response & deserialize
    }

    public void save(User user) {
        database.getTable(User.class)
                .insert()
                .data(user.serialize())
                .async();
    }

    public void deleteByLogin(String login) {
       database.getTable(User.class) // get table
                .delete() // create delete request
                .where(row -> row.getValue("login").equals(login)) // request parameters
                .limit(1) // request parameters
                .sync(); // execute request
    }
}
