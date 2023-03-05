package net.wonsi.api.request;

import lombok.NonNull;
import net.wonsi.util.Condition;

import java.util.Map;
import java.util.function.Consumer;

public interface UpdateRequest extends Request {

    UpdateRequest where(Condition condition);

    UpdateRequest data(@NonNull Consumer<Map<String, Object>> insertData);

    UpdateRequest limit(int limit);

}
