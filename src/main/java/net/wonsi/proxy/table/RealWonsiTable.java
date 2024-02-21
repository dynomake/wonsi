package net.wonsi.proxy.table;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.wonsi.api.mapping.WonsiColumn;
import net.wonsi.api.mapping.WonsiPrimary;
import net.wonsi.api.repository.BaseRepository;
import net.wonsi.api.request.*;
import net.wonsi.api.result.ExecutedReturningAction;
import net.wonsi.api.table.WonsiTable;
import net.wonsi.column.ColumnUtil;
import net.wonsi.proxy.repository.SimpleRepository;
import net.wonsi.proxy.request.RealDeleteRequest;
import net.wonsi.proxy.request.RealInsertRequest;
import net.wonsi.proxy.request.RealSelectRequest;
import net.wonsi.proxy.request.RealUpdateRequest;
import net.wonsi.proxy.result.RealExecutedReturningAction;
import net.wonsi.util.ExecutorUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@RequiredArgsConstructor
public class RealWonsiTable<T> implements WonsiTable<T> {

    private final Connection connection;
    private final Function<ResultSet, T> deserializer;
    private final String tableName;
    private String primaryKeyName;

    @Override
    public SelectRequest<T> select() {
        return new RealSelectRequest<T>(connection, tableName, deserializer);
    }

    @Override
    public InsertRequest<T> insert() {
        return new RealInsertRequest<T>(connection, tableName, primaryKeyName);
    }

    @Override
    public DeleteRequest delete() {
        return new RealDeleteRequest(connection, tableName);
    }

    @Override
    public UpdateRequest update() {
        return new RealUpdateRequest(connection, tableName);
    }

    @Override
    public String getName() {
        return tableName;
    }

    @Override
    public ExecutedReturningAction<T> customSelect(@NonNull String query) {
        return new RealExecutedReturningAction<T>(ExecutorUtil.getResult(query, connection), deserializer);
    }

    @Override
    public Request customQuery(@NonNull String query) {
        return new Request() {
            @Override
            public CompletableFuture<Void> async() {
                return CompletableFuture.runAsync(this::sync);
            }

            @Override
            public void sync() {
                ExecutorUtil.execute(query, connection);
            }
        };
    }

    @Override
    public <Id> BaseRepository<T, Id> createRepository(@NonNull Class<Id> idType) {
        return new SimpleRepository<>(this, primaryKeyName);
    }

    @Override
    public void createIfNotExits(Class<T> tclass) {
        List<String> arguments = new ArrayList<>();

        for (Field field : tclass.getDeclaredFields()) {
            WonsiColumn column = field.getAnnotation(WonsiColumn.class);
            val name = column.name().equals("null_wonsidv") ? field.getName() : column.name();
            val primary = (field.getAnnotation(WonsiPrimary.class) != null ? " PRIMARY KEY" : "");
            arguments.add(name + ' ' + ColumnUtil.get(field.getType()).convertToString(column.length()) + primary);
            if (!primary.isEmpty())
                primaryKeyName = name;
        }

        StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS `");
        query.append(tableName).append('`').append(" (");


        for (int i = 0; i < arguments.size(); i++) {
            query.append(arguments.get(i));
            if (arguments.size() != i + 1) query.append(", ");
        }

        query.append(')');

        ExecutorUtil.execute(query.toString(), connection);
    }
}
