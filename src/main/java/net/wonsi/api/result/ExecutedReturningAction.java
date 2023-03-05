package net.wonsi.api.result;

import java.util.Collection;

public interface ExecutedReturningAction<T> {

    Collection<T> getAll();
    T findFirst();
    boolean isEmpty();
}
