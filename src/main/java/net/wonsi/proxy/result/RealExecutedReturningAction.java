package net.wonsi.proxy.result;

import lombok.NonNull;
import net.wonsi.api.result.ExecutedReturningAction;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

public class RealExecutedReturningAction<T> implements ExecutedReturningAction<T> {

    private final Collection<T> response;

    public RealExecutedReturningAction(@NonNull ResultSet resultSet, @NonNull Function<ResultSet, T> deserializer) {
        this.response = new ArrayList<>();

        try {

            while (resultSet.next()) {
                response.add(deserializer.apply(resultSet));
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    @Override
    public Collection<T> getAll() {
        return response;
    }

    @Override
    public Optional<T> findFirst() {
        return response.stream().findFirst();
    }

    @Override
    public boolean isEmpty() {
        return response.isEmpty();
    }
}
