package com.xxx.xtable.api.support;

public enum ModuleType implements EnumOf {
    UNKnown(0), SecurityEmployee(1);

    private int value;

    ModuleType(int value) {
        this.value = value;
    }

    public static ModuleType valueOf(int value) {
        for (ModuleType type : values()) {
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
