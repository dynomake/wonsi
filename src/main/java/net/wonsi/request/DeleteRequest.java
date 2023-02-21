package net.wonsi.request;

import net.wonsi.table.WonsiRow;

import java.util.function.Predicate;

public interface DeleteRequest extends Request {

    DeleteRequest where(Predicate<WonsiRow> filter);
    DeleteRequest limit(int limit);


}
