package net.wonsi.column.type;

import lombok.NonNull;

import java.sql.ResultSet;

public interface ColumnType<T> {
    String convertToString(int length);
    T get(@NonNull String name, @NonNull ResultSet resultSet);
}
