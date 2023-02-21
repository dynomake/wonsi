package net.wonsi.table;

import net.wonsi.request.DeleteRequest;
import net.wonsi.request.InsertRequest;
import net.wonsi.request.SelectRequest;

public interface WonsiTable<T> {

    SelectRequest<T> select();
    InsertRequest insert();
    DeleteRequest delete();

}
