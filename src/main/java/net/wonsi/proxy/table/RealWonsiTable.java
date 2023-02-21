package net.wonsi.proxy.table;

import net.wonsi.request.DeleteRequest;
import net.wonsi.request.InsertRequest;
import net.wonsi.request.SelectRequest;
import net.wonsi.table.WonsiTable;

public class RealWonsiTable<T> implements WonsiTable<T> {
    /**
     * @return
     */
    @Override
    public SelectRequest<T> select() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public InsertRequest insert() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public DeleteRequest delete() {
        return null;
    }
}
