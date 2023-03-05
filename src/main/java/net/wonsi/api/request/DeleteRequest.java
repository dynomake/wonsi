package net.wonsi.api.request;

import net.wonsi.util.Condition;

public interface DeleteRequest extends Request {

    DeleteRequest where(Condition condition);
    DeleteRequest limit(int limit);


}
