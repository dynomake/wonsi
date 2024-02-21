package net.wonsi.proxy.request;

import net.wonsi.api.request.DeleteRequest;
import net.wonsi.util.Condition;
import net.wonsi.util.ExecutorUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.CompletableFuture;

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
            ExecutorUtil.execute("DELETE FROM " + tableName + " WHERE " + condition + " LIMIT " + limit, connection);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
