package net.wonsi.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.wonsi.api.mapping.Table;
import net.wonsi.api.mapping.WonsiColumn;
import net.wonsi.api.mapping.WonsiPrimary;

import java.sql.ResultSet;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table("cars")
public class Car {

    @WonsiPrimary
    @WonsiColumn
    private long id;

    @WonsiColumn
    private String concern;

    @WonsiColumn
    private String model;

    public static Car deserialize(ResultSet data) {
        try {
            return new Car(data.getInt("id"), data.getString("concern"), data.getString("model"));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
