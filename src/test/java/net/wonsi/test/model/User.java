package net.wonsi.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.wonsi.api.mapping.Table;
import net.wonsi.api.mapping.WonsiColumn;
import net.wonsi.api.mapping.WonsiPrimary;

import java.sql.ResultSet;

@Setter
@Getter
@AllArgsConstructor

// wonsi requied annotation's
@Table("app_users")
public class User {

    @WonsiPrimary
    @WonsiColumn(name = "identifier")
    private long identifier;

    @WonsiColumn(name = "vk")
    private String vkLink;

    @WonsiColumn(name = "tg")
    private String tgId;

    public static User deserialize(ResultSet data) {
        try {
            return new User(data.getInt("identifier"), data.getString("vk"), data.getString("tg"));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
