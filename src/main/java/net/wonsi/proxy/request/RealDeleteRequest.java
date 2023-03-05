package net.wonsi.proxy.request;

import net.wonsi.api.request.DeleteRequest;
import net.wonsi.proxy.result.RealExecutedReturningAction;
import net.wonsi.util.Condition;
import net.wonsi.util.Debugger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class RealDeleteRequest implements DeleteRequest {

    private final Connection connection;
    private final String tableName;
    private String condition;
    private int limit;

    public RealDeleteRequest(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
        condition = "0";
        limit = 1;
    }

    @Override
    public DeleteRequest where(Condition condition) {
        this.condition = condition.toString();
        return this;
    }

    @Override
    public DeleteRequest limit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public CompletableFuture<Void> async() {
        return CompletableFuture.runAsync(this::sync);
    }

    @Override
    public void sync() {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM " + tableName + " WHERE " + condition + " LIMIT ?");

            statement.setInt(1, limit);

            Debugger.execute(statement, connection);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
