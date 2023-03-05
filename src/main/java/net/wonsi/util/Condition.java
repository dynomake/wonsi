package net.wonsi.util;


import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class Condition {

    private String condition;

    public static Condition is(@NonNull String column, @NonNull Object value) {
        return new Condition('`' + column + '`' + " = " + value);
    }

    public static Condition over(@NonNull String column, @NonNull Object value) {
        return new Condition('`' + column + '`' + " > " + value);
    }

    public static Condition below(@NonNull String column, @NonNull Object value) {
        return new Condition('`' + column + '`' + " < " + value);
    }

    public static Condition alwaysTrue() {
        return new Condition("1");
    }

    @Override
    public String toString() {
        return condition;
    }
}
