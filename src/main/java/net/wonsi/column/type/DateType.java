package net.wonsi.column.type;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.sql.Date;
import java.sql.ResultSet;

public class DateType implements ColumnType<Date> {

    @Override
    public String convertToString(int length) {
        return "date";
    }

    @Override
    @SneakyThrows
    public Date get(@NonNull String name, @NonNull ResultSet resultSet) {
        return resultSet.getDate(name);
    }
}
