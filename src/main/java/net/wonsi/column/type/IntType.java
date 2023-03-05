package net.wonsi.column.type;

public class IntType implements ColumnType {

    @Override
    public String convertToString(int length) {
        if (length <= 5) return "tinyint";
        if (length <= 6) return "mediumint";
        if (length < 10) return "int";
        else return "bigint";
    }
}
