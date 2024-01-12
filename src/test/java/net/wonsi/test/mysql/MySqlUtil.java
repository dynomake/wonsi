package net.wonsi.test.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class MySqlUtil {

    @SneakyThrows
    public Connection create() {
        var url = "jdbc:h2:mem:";
        return DriverManager.getConnection(url);
    }
}
