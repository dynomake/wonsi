package net.wonsi.api.request;

import net.wonsi.util.Condition;

public interface SelectRequest<T> extends ReturningRequest<T> {

    SelectRequest<T> where(Condition condition);
    SelectRequest<T> limit(int limit);


}
