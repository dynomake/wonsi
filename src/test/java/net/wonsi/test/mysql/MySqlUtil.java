package net.wonsi.test.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.SQLException;

@UtilityClass
public class MySqlUtil {

    public Connection create() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setUser("root");
        dataSource.setPassword("nope");
        dataSource.setDatabaseName("wonsitesting");

        dataSource.setEncoding("UTF-8");
        dataSource.setAutoReconnect(true);
        dataSource.setUseSSL(true);
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
