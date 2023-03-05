package net.wonsi.api;

import lombok.NonNull;
import net.wonsi.api.table.WonsiTable;

import java.sql.ResultSet;
import java.util.function.Function;

/**
 * Wonsi Object Mapping Technology.
 * Created by suuft, at 20/02/2023 17:12
 */
public interface Wonsi {

    /**
     * Get or create (if not exits) table by class
     *
     * @param tClass - class
     * @return - wonsi table handle
     */
    <T> WonsiTable<T> getTable(@NonNull Class<T> tClass, @NonNull Function<ResultSet, T> deserializer);
}
