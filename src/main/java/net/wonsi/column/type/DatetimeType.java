package net.wonsi.column.type;

public class DatetimeType implements ColumnType {

    @Override
    public String convertToString(int length) {
        return "datetime";
    }
}
