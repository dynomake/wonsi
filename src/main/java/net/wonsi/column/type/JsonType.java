package net.wonsi.column.type;

public class JsonType implements ColumnType {

    @Override
    public String convertToString(int length) {
        return "json";
    }
}