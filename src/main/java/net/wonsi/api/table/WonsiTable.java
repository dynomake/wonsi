package net.wonsi.api.table;

import lombok.NonNull;
import net.wonsi.api.request.DeleteRequest;
import net.wonsi.api.request.InsertRequest;
import net.wonsi.api.request.Request;
import net.wonsi.api.request.SelectRequest;
import net.wonsi.api.result.ExecutedReturningAction;

public interface WonsiTable<T> {

    SelectRequest<T> select();
    InsertRequest<T> insert();
    DeleteRequest delete();

    ExecutedReturningAction<T> customSelect(@NonNull String query);
    Request customQuery(@NonNull String query);

    void createIfNotExits(Class<T> tclass);

}
