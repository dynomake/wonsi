package net.wonsi.proxy.request;

import com.google.common.base.Joiner;
import net.wonsi.api.request.UpdateRequest;
import net.wonsi.util.Condition;
import net.wonsi.util.ExecutorUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class RealUpdateRequest implements UpdateRequest {

    private final Connection connection;
    private final String tableName;
    private String condition;
    private int limit;
    private Map<String, Object> data;

    public RealUpdateRequest(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
        condition = "0";
        limit = 10;
    }


    @Override
    public CompletableFuture<Void> async() {
        return CompletableFuture.runAsync(this::sync);
    }

    @Override
    public void sync() {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE " + tableName + Joiner.on("= ?,").join(data.keySet()) + " = ? LIMIT = ?");

            int currentArgument = 1;

            for (Object o : data.values()) {
                statement.setObject(currentArgument, o);
                currentArgument++;
            }

            statement.setObject(currentArgument, limit);

            ExecutorUtil.execute(statement, connection);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public UpdateRequest where(Condition condition) {
        this.condition = condition.toString();
        return this;
    }

    @Override
    public UpdateRequest data(Consumer<Map<String, Object>> insertData) {
        data = new HashMap<>();
        insertData.accept(data);

        return this;
    }

    @Override
    public UpdateRequest limit(int limit) {
        this.limit = limit;
        return this;
    }
}
