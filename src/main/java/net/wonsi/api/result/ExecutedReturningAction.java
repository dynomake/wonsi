package net.wonsi.api.result;

import java.util.Collection;
import java.util.Optional;

public interface ExecutedReturningAction<T> {

    Collection<T> getAll();
    Optional<T> findFirst();
    boolean isEmpty();
}
