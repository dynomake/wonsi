package net.wonsi.table;

import lombok.NonNull;

public interface WonsiRow {

    String getValue(@NonNull String column);
}
