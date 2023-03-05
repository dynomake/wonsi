package net.wonsi.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {

    public String repeat(@NonNull String s, int repeatCount) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < repeatCount; i++)
            builder.append(s);

        return builder.toString();
    }
}
