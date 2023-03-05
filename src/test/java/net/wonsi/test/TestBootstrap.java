package net.wonsi.test;

import net.wonsi.WonsiFactory;
import net.wonsi.api.Wonsi;
import net.wonsi.api.table.WonsiTable;
import net.wonsi.test.model.User;
import net.wonsi.test.mysql.MySqlUtil;
import net.wonsi.test.repo.UserRepo;
import net.wonsi.util.Condition;
import net.wonsi.util.StringUtil;

public class TestBootstrap {

    public static void main(String[] args) {
       Wonsi wonsi = WonsiFactory.createInstance(MySqlUtil.create());
       WonsiTable<User> table = wonsi.getTable(User.class, User::deserialize);

       UserRepo repository = new UserRepo(table);


       // допустим у валеры есть друзьяшки
       repository.save(new User(1, "https://vk.com/suuft", "@fuuft"));
       repository.save(new User(2, "https://vk.com/itzstonlex", null));
       repository.save(new User(3, "https://vk.com/frotter_", null));
       repository.save(new User(4, "https://vk.com/aquantixx", null));

       // но фроттер кинул валеру на 900 рублей
        repository.delete(3);
    }
}
