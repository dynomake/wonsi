package net.wonsi.column.type;

public class DateType implements ColumnType {

    @Override
    public String convertToString(int length) {
        return "date";
    }
}
