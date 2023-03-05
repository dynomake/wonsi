package net.wonsi.column.type;

public class BooleanType implements ColumnType {

    @Override
    public String convertToString(int length) {
        return "boolean";
    }
}
