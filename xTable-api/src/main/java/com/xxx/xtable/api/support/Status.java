package com.xxx.xtable.api.support;

public enum Status implements EnumOf {
    UNKnown(0), Normal(1), Delete(2);

    private int value;

    Status(int value) {
        this.value = value;
    }

    public static Status valueOf(int value) {
        for (Status status : values()) {
            if (status.value() == value) {
                return status;
            }
        }

        return UNKnown;
    }

    @Override
    public int value() {
        return value;
    }
}
