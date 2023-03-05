package net.wonsi.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@UtilityClass
public class ExecutorUtil {

    public ResultSet getResult(@NonNull PreparedStatement statement, @NonNull Connection connection) {
        try {
            return statement.executeQuery();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void execute(@NonNull PreparedStatement statement, @NonNull Connection connection) {
        try {
            statement.execute();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public ResultSet getResult(@NonNull String query, @NonNull Connection connection) {
        try {
            return connection.createStatement().executeQuery(query);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void execute(@NonNull String query, @NonNull Connection connection) {
        try {
            connection.createStatement().execute(query);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
