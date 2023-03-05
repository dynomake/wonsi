package net.wonsi;

import lombok.experimental.UtilityClass;
import net.wonsi.api.Wonsi;
import net.wonsi.proxy.RealWonsi;

import java.sql.Connection;

/**
 * Wonsi Object Mapping Technology.
 * Created by suuft, at 20/02/2023 17:12
 */
@UtilityClass
public class WonsiFactory {

    /**
     * Creates a new Wonsi instance. To work successfully, you need to
     * already have a connection to the SQL-database.
     *
     * @param connection - sql connection
     * @return - wonsi instance
     */
    public Wonsi createInstance(Connection connection) {
        return new RealWonsi(connection);
    }
}
