package net.wonsi.column.type;

public class FloatType implements ColumnType {

    @Override
    public String convertToString(int length) {
        return "float";
    }
}
