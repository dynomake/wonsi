package net.wonsi.proxy.request;

import com.google.common.base.Joiner;
import lombok.val;
import net.wonsi.api.request.InsertRequest;
import net.wonsi.util.ExecutorUtil;
import net.wonsi.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class RealInsertRequest<T> implements InsertRequest<T> {

    private final Connection connection;
    private final String tableName;
    private String condition;
    private int limit;
    private boolean fixDuplicate;
    private Map<String, Object> data;
    private final String primaryKeyName;

    public RealInsertRequest(Connection connection, String tableName, String primaryKeyName) {
        this.connection = connection;
        this.tableName = tableName;
        this.primaryKeyName = primaryKeyName;
        condition = "1";
        limit = 10;

    }

    @Override
    public InsertRequest<T> data(Consumer<Map<String, Object>> insertData) {
        Map<String, Object> map = new HashMap<>();
        insertData.accept(map);

        this.data = map;

        return this;
    }

    @Override
    public InsertRequest<T> updateOnDuplicate() {
        this.fixDuplicate = true;
        return this;
    }

    @Override
    public InsertRequest<T> limit(int limit) {
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
            val mapWithoutPrimary = new HashMap<>(data);
            if (fixDuplicate) {
                mapWithoutPrimary.remove(primaryKeyName);
            }
            val end = (fixDuplicate ? " ON DUPLICATE KEY UPDATE " + Joiner.on("= ?,").join(mapWithoutPrimary.keySet()) + " = ?" : "");
            val query = "INSERT INTO " + tableName + " (" + Joiner.on(",").join(data.keySet()) + ") VALUES (" + StringUtil.repeat(",?", data.size()).replaceFirst(",", "")+ ")" + end;
//            System.out.println(query);
            PreparedStatement statement = connection.prepareStatement(query);

            int currentArgument = 1;

            for (Object o : data.values()) {
                statement.setObject(currentArgument, o);
                currentArgument++;
            }

            if (fixDuplicate){
//                currentArgument = currentArgument-2;
                for (Object o : mapWithoutPrimary.values()) {
                    statement.setObject(currentArgument, o);
                    currentArgument++;
                }
            }

            ExecutorUtil.execute(statement, connection);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
