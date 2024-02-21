package net.wonsi.api.table;

import lombok.NonNull;
import net.wonsi.api.request.*;
import net.wonsi.api.result.ExecutedReturningAction;

public interface WonsiTable<T> {

    SelectRequest<T> select();
    InsertRequest<T> insert();
    DeleteRequest delete();
    UpdateRequest update();

    String getName();
    ExecutedReturningAction<T> customSelect(@NonNull String query);
    Request customQuery(@NonNull String query);

    void createIfNotExits(Class<T> tclass);

}
