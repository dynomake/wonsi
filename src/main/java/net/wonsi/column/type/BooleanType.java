package net.wonsi.column.type;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.sql.ResultSet;

public class BooleanType implements ColumnType<Boolean> {

    @Override
    public String convertToString(int length) {
        return "boolean";
    }

    @Override
    @SneakyThrows
    public Boolean get(@NonNull String name, @NonNull ResultSet resultSet) {
        return resultSet.getBoolean(name);
    }
}
