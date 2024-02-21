package net.wonsi.column.type;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.sql.ResultSet;

public class FloatType implements ColumnType<Float> {

    @Override
    public String convertToString(int length) {
        return "float";
    }

    @Override
    @SneakyThrows
    public Float get(@NonNull String name, @NonNull ResultSet resultSet) {
        return resultSet.getFloat(name);
    }
}
