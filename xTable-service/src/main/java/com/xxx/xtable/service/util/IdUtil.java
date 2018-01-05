package com.xxx.xtable.service.util;

import org.bson.types.ObjectId;

public class IdUtil {
    public static String id() {
        return new ObjectId().toHexString();
    }
}
