package net.wonsi.proxy.database;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import net.wonsi.database.WonsiDatabase;
import net.wonsi.table.WonsiTable;

import java.sql.Connection;

@AllArgsConstructor
public class RealWonsiDatabase implements WonsiDatabase {

    private Connection connection;

    @Override
    public <T> WonsiTable<T> getTable(@NonNull Class<T> tClass) {
        return null;
    }
}
