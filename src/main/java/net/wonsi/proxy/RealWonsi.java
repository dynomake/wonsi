package net.wonsi.proxy;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.val;
import net.wonsi.api.Wonsi;
import net.wonsi.api.mapping.Table;
import net.wonsi.api.mapping.WonsiColumn;
import net.wonsi.api.table.WonsiTable;
import net.wonsi.column.ColumnUtil;
import net.wonsi.proxy.table.RealWonsiTable;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.function.Function;

@AllArgsConstructor
public class RealWonsi implements Wonsi {

    private Connection connection;

    @Override
    public <T> WonsiTable<T> getTable(@NonNull Class<T> tClass, @NonNull Function<ResultSet, T> deserializer) {
        Table tableAnnotation = tClass.getAnnotation(Table.class);

        if (tableAnnotation == null)
            throw new IllegalArgumentException("Class don't has annotation @Table, please read documentation!");

        return getTable(tClass, deserializer, tableAnnotation.value());
    }

    @Override
    public <T> WonsiTable<T> getTable(@NonNull Class<T> tClass) {
        return getTable(tClass, createDeserializer(tClass));
    }

    @Override
    public <T> WonsiTable<T> getTable(@NonNull Class<T> tClass, @NonNull Function<ResultSet, T> deserializer, @NonNull String tableName) {
        WonsiTable<T> table = new RealWonsiTable<>(connection, deserializer, tableName);
        table.createIfNotExits(tClass);
        return table;
    }

    @Override
    public <T> WonsiTable<T> getTable(@NonNull Class<T> tClass, @NonNull String tableName) {
        return getTable(tClass, createDeserializer(tClass), tableName);
    }

    private <T> Function<ResultSet, T> createDeserializer(@NonNull Class<T> tClass) {
        return (resultSet) -> {
            try {
                T object = tClass.newInstance();

                for (Field field : tClass.getDeclaredFields()) {
                    WonsiColumn column = field.getAnnotation(WonsiColumn.class);
                    val name = column.name().equals("null_wonsidv") ? field.getName() : column.name();
                    field.trySetAccessible();
                    val value = ColumnUtil.get(field.getType()).get(name, resultSet);
//                    field.getType().cast(
                    field.set(object, value.getClass().isAssignableFrom(field.getType()) ? value :
                            value.getClass().equals(Long.class) ? (((Long) value).longValue()) :
                                    field.getType().cast(value));
                }

                return object;
            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
        };
    }
}
