package net.wonsi.column.type;

import java.util.Map;

public class VarcharType implements ColumnType {

    @Override
    public String convertToString(int length) {
        return "varchar(" + length + ')';
    }
}
