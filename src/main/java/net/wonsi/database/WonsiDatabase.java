package net.wonsi.database;

import lombok.NonNull;
import net.wonsi.table.WonsiTable;

public interface WonsiDatabase {

    <T> WonsiTable<T> getTable(@NonNull Class<T> tClass);
}
