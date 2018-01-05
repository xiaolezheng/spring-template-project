package com.xxx.xtable.api.support;

public enum XFieldDataType implements EnumOf {
    UNKnown(0), StringType(1), NumberType(2), LongType(3), DoubleType(4),
    DateType(5), ListLongType(6), ListStringType(7);

    private int value;

    XFieldDataType(int value) {
        this.value = value;
    }

    public static XFieldDataType valueOf(int value) {
        for (XFieldDataType type : values()) {
            if (type.value() == value) {
                return type;
            }
        }

        return UNKnown;
    }

    @Override
    public int value() {
        return value;
    }
}
