package net.wonsi.test.mysql;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class MySqlUtil {

    @SneakyThrows
    public Connection create() {
        String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql111?useSSL=true";
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(url, "sql111", "i_will_never_show_it");
    }
}
