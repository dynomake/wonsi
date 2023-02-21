package net.wonsi.test.model;

import lombok.Getter;
import lombok.Setter;
import net.wonsi.serialization.SerializedSource;

@Entity
@Setter
@Getter
public class User {

    private Long identifier;

    public static void deserialize(SerializedSource data) {

    }

    public SerializedSource serialize() {

    }
}
