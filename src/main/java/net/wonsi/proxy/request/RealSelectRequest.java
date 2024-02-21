package net.wonsi.proxy.request;

import net.wonsi.api.request.SelectRequest;
import net.wonsi.api.result.ExecutedReturningAction;
import net.wonsi.proxy.result.RealExecutedReturningAction;
import net.wonsi.util.Condition;
import net.wonsi.util.ExecutorUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class RealSelectRequest<T> implements SelectRequest<T> {

    private final Connection connection;
    private final String tableName;
    private final Function<ResultSet, T> deserializer;
    private String condition;
    private int limit;

    public RealSelectRequest(Connection connection, String tableName, Function<ResultSet, T> deserializer) {
        this.connection = connection;
        this.tableName = tableName;
        this.deserializer = deserializer;
        condition = "1";
        limit = -1;
    }

    @Override
    public CompletableFuture<ExecutedReturningAction<T>> async() {
        return CompletableFuture.supplyAsync(this::sync);
    }

    @Override
    public ExecutedReturningAction<T> sync() {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM " + tableName + " WHERE " + condition + (limit > 0 ? " LIMIT " + limit : ""));

            return new RealExecutedReturningAction<T>(ExecutorUtil.getResult(statement, connection), deserializer);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public SelectRequest<T> where(Condition condition) {
        this.condition = condition.toString();
        return this;
    }

    @Override
    public SelectRequest<T> limit(int limit) {
        this.limit = limit;
        return this;
    }
}
