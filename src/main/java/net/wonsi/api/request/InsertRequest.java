package net.wonsi.api.request;


import lombok.NonNull;

import java.util.Map;
import java.util.function.Consumer;

public interface InsertRequest<T> extends Request {

    InsertRequest<T> data(@NonNull Consumer<Map<String, Object>> insertData);
    InsertRequest<T> updateOnDuplicate();
    InsertRequest<T> limit(int limit);
}
