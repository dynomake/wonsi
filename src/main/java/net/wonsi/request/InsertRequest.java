package net.wonsi.request;

import net.wonsi.serialization.SerializedSource;

public interface InsertRequest extends Request {

    InsertRequest data(SerializedSource serializedSource);
    InsertRequest updateOnDuplicate();
}
