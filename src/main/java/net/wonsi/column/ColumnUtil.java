package net.wonsi.column;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.wonsi.column.type.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class ColumnUtil {

    private static Map<Class<?>, ColumnType> TYPE_MAP;

    static {
        TYPE_MAP = new HashMap<>();
        TYPE_MAP.put(String.class, new VarcharType());
        TYPE_MAP.put(Boolean.class, new BooleanType());
        TYPE_MAP.put(boolean.class, new BooleanType());
        TYPE_MAP.put(char.class, new VarcharType());
        TYPE_MAP.put(Character.class, new VarcharType());
        TYPE_MAP.put(Date.class, new DateType());
        TYPE_MAP.put(Timestamp.class, new DatetimeType());
        TYPE_MAP.put(short.class, new IntType());
        TYPE_MAP.put(int.class, new IntType());
        TYPE_MAP.put(Integer.class, new IntType());
        TYPE_MAP.put(Long.class, new IntType());
        TYPE_MAP.put(Short.class, new IntType());
        TYPE_MAP.put(Number.class, new IntType());
        TYPE_MAP.put(Byte.class, new IntType());
        TYPE_MAP.put(long.class, new IntType());
        TYPE_MAP.put(byte.class, new IntType());
        TYPE_MAP.put(float.class, new FloatType());
        TYPE_MAP.put(double.class, new FloatType());
        TYPE_MAP.put(BigDecimal.class, new FloatType());
        TYPE_MAP.put(Collection.class, new JsonType());
    }

    public ColumnType get(@NonNull Class<?> javaType) {
        if (javaType.isAssignableFrom(Collection.class)) return new JsonType();

        return TYPE_MAP.getOrDefault(javaType, new VarcharType());
    }
}
