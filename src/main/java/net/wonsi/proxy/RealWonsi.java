package net.wonsi.proxy;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import net.wonsi.Wonsi;
import net.wonsi.database.WonsiDatabase;
import net.wonsi.proxy.database.RealWonsiDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@AllArgsConstructor
public class RealWonsi implements Wonsi {

    private Connection connection;

    @Override
    public WonsiDatabase getDatabase(@NonNull String database) {
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery("CREATE DATABASE IF NOT EXISTS `" + database + "`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new RealWonsiDatabase(connection);
    }
}
