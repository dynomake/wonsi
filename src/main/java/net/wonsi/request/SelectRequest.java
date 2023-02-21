package net.wonsi.request;

import net.wonsi.table.WonsiRow;

import java.util.Collection;
import java.util.function.Predicate;

public interface SelectRequest<T> {

    SelectRequest<Collection<T>> all();

    SelectRequest<T> where(Predicate<WonsiRow> filter);
    SelectRequest<T> limit(int limit);

    T async();
    T sync();
}
