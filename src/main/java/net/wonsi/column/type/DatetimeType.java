package net.wonsi.column.type;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.Timestamp;

public class DatetimeType implements ColumnType<Timestamp> {

    @Override
    public String convertToString(int length) {
        return "datetime";
    }

    @Override
    @SneakyThrows
    public Timestamp get(@NonNull String name, @NonNull ResultSet resultSet) {
        return resultSet.getTimestamp(name);
    }
}
