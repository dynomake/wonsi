package net.wonsi;

import lombok.NonNull;
import net.wonsi.database.WonsiDatabase;

/**
 * Wonsi Object Mapping Technology.
 * Created by suuft, at 20/02/2023 17:12
 */
public interface Wonsi {

    /**
     * Get or create (if not exits) database
     *
     * @param database - db name
     * @return - wonsi database handle
     */
    WonsiDatabase getDatabase(@NonNull String database);
}
