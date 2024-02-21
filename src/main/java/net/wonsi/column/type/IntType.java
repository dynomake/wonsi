package net.wonsi.column.type;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.sql.ResultSet;

public class IntType implements ColumnType<Long> {

    @Override
    public String convertToString(int length) {
        if (length <= 5) return "tinyint";
        if (length == 6) return "mediumint";
        if (length < 10) return "int";
        else return "bigint";
    }

    @Override
    @SneakyThrows
    public Long get(@NonNull String name, @NonNull ResultSet resultSet) {
        return resultSet.getLong(name);
    }
}
