package net.wonsi.column.type;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.sql.ResultSet;

public class JsonType implements ColumnType<String> {

    @Override
    public String convertToString(int length) {
        return "json";
    }

    @Override
    @SneakyThrows
    public String get(@NonNull String name, @NonNull ResultSet resultSet) {
        return resultSet.getString(name);
    }
}