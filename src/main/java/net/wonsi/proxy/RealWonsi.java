package net.wonsi.proxy;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import net.wonsi.api.Wonsi;
import net.wonsi.api.mapping.Table;
import net.wonsi.api.table.WonsiTable;
import net.wonsi.proxy.table.RealWonsiTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.function.Function;

@AllArgsConstructor
public class RealWonsi implements Wonsi {

    private Connection connection;

    @Override
    public <T> WonsiTable<T> getTable(@NonNull Class<T> tClass, @NonNull Function<ResultSet, T> deserializer) {
        Table tableAnnotation = tClass.getAnnotation(Table.class);

        if (tableAnnotation == null) throw new IllegalArgumentException("Class don't has annotation @Table, please read documentation!");


        WonsiTable<T> table = new RealWonsiTable<>(connection, deserializer, tableAnnotation.value());
        table.createIfNotExits(tClass);

        return table;
    }

}
