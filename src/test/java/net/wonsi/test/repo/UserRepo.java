package net.wonsi.test.repo;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.wonsi.api.Wonsi;
import net.wonsi.api.table.WonsiTable;
import net.wonsi.test.model.User;
import net.wonsi.util.Condition;

import java.util.Collection;

/**
 * Wonsi Object Mapping Technology
 *
 * Example Usage class. Created by suuft, at
 * 20/02/2023 14:20
 */
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
