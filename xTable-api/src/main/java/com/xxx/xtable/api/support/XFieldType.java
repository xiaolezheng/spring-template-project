package com.xxx.xtable.api.support;

import com.google.common.collect.Maps;

import java.util.Map;

public enum XFieldType implements EnumOf {
    UNKnown(0),
    SingleText(1),
    MultiText(2),
    Number(3),
    Calendar(4),
    SingleSelect(5),
    MultiSelect(6);

    private int value;

    private static final Map<XFieldType, XFieldDataType> MAPPING = Maps.newHashMap();

    static {
        MAPPING.put(XFieldType.UNKnown, XFieldDataType.UNKnown);
        MAPPING.put(XFieldType.SingleText, XFieldDataType.StringType);
        MAPPING.put(XFieldType.MultiText, XFieldDataType.StringType);
        MAPPING.put(XFieldType.Number, XFieldDataType.NumberType);
        MAPPING.put(XFieldType.Calendar, XFieldDataType.DateType);
        MAPPING.put(XFieldType.SingleSelect, XFieldDataType.ListLongType);
        MAPPING.put(XFieldType.MultiSelect, XFieldDataType.ListLongType);
    }

    XFieldType(int value) {
        this.value = value;
    }

    public static XFieldType valueOf(int value) {
        for (XFieldType type : XFieldType.values()) {
            if (type.value() == value) {
                return type;
            }
        }

        return UNKnown;
    }

    public XFieldDataType dataType() {
        XFieldDataType dataType = MAPPING.get(this);
        if (dataType != null) {
            return dataType;
        }

        return XFieldDataType.UNKnown;
    }


    @Override
    public int value() {
        return value;
    }
}
