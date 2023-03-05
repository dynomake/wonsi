package net.wonsi.proxy.table;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import net.wonsi.api.mapping.WonsiColumn;
import net.wonsi.api.request.DeleteRequest;
import net.wonsi.api.request.InsertRequest;
import net.wonsi.api.request.Request;
import net.wonsi.api.request.SelectRequest;
import net.wonsi.api.result.ExecutedReturningAction;
import net.wonsi.api.table.WonsiTable;
import net.wonsi.column.ColumnUtil;
import net.wonsi.proxy.request.RealDeleteRequest;
import net.wonsi.proxy.request.RealInsertRequest;
import net.wonsi.proxy.request.RealSelectRequest;
import net.wonsi.proxy.result.RealExecutedReturningAction;
import net.wonsi.util.Debugger;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@AllArgsConstructor
public class RealWonsiTable<T> implements WonsiTable<T> {

    private Connection connection;
    private Function<ResultSet, T> deserializer;
    private String tableName;

    @Override
    public SelectRequest<T> select() {
        return new RealSelectRequest<T>(connection, tableName, deserializer);
    }

    @Override
    public InsertRequest<T> insert() {
        return new RealInsertRequest<T>(connection, tableName);
    }

    @Override
    public DeleteRequest delete() {
        return new RealDeleteRequest(connection, tableName);
    }

    @Override
    public ExecutedReturningAction<T> customSelect(@NonNull String query) {
        return new RealExecutedReturningAction<T>(Debugger.getResult(query, connection), deserializer);
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
                Debugger.execute(query, connection);
            }
        };
    }

    @Override
    public void createIfNotExits(Class<T> tclass) {
        List<String> arguments = new ArrayList<>();

        for (Field field : tclass.getDeclaredFields()) {
            WonsiColumn column = field.getAnnotation(WonsiColumn.class);
            arguments.add(column.name() + ' ' + ColumnUtil.get(field.getType()).convertToString(column.length()));
        }

        StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS `");
        query.append(tableName).append('`').append(" (");


        for (int i = 0; i < arguments.size(); i++) {
            query.append(arguments.get(i));
            if (arguments.size() != i + 1) query.append(", ");
        }

        query.append(')');

        Debugger.execute(query.toString(), connection);
    }
}
