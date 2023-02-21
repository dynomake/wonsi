package net.wonsi.test;

import net.wonsi.Wonsi;
import net.wonsi.WonsiFactory;
import net.wonsi.test.repo.UserRepo;

public class TestBootstrap {

    public static void main(String[] args) {
        Wonsi wonsi = WonsiFactory.createInstance(sqlConnection);

        UserRepo repo = new UserRepo(wonsi.getDatabase("testing_wonsi"));
    }
}
