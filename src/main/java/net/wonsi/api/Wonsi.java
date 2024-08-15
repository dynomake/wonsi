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


    /**
     * Get or create (if not exits) table by class
     * if you are using this way with auto-serialisation & de, you can
     * use only long number types. DO NOT USE Long, int, short, Integer.
     *
     * @param tClass - class
     * @return - wonsi table handle
     */
    <T> WonsiTable<T> getTable(@NonNull Class<T> tClass);


    /**
     * Get or create (if not exits) table by class and name
     *
     * @param tClass - class
     * @return - wonsi table handle
     */
    <T> WonsiTable<T> getTable(@NonNull Class<T> tClass, @NonNull Function<ResultSet, T> deserializer, @NonNull String tableName);


    /**
     * Get or create (if not exits) table by class
     * if you are using this way with auto-serialisation & de, you can
     * use only long number types. DO NOT USE Long, int, short, Integer.
     *
     * @param tClass - class
     * @return - wonsi table handle
     */
    <T> WonsiTable<T> getTable(@NonNull Class<T> tClass, @NonNull String tableName);
}
