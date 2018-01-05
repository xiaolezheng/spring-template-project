package com.xxx.xtable.service.support;

import com.google.common.collect.Maps;
import com.xxx.xtable.service.exception.ServiceException;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * 请求上下文传递
 */
public class ContextHolder {

    private static final ThreadLocal<Map<Class, Object>> contextHolder = ThreadLocal.withInitial(() -> Maps.newHashMap());


    public static void set(Class clazz, Object data) {
        contextHolder.get().put(clazz, data);
    }

    public static Object getDataOrNull(Class clazz) {
        return contextHolder.get().get(clazz);
    }

    public static <T> T getData(Class<T> clazz) {
        Object result = contextHolder.get().get(clazz);
        if (result != null) {
            return (T) result;
        }

        throw new ServiceException(String.format("not find value for key from context, key:%s", clazz.getName()));
    }

    public static <T> T remove(Class<T> clazz) {
        Object result = contextHolder.get().get(clazz);
        if (result != null) {
            contextHolder.get().remove(clazz);
            return (T) result;
        }

        throw new ServiceException(String.format("not find value for key from context, key:%s", clazz.getName()));
    }

    public static void clear() {
        Map map = contextHolder.get();
        if (MapUtils.isNotEmpty(map)) {
            map.clear();
        }

        contextHolder.remove();
    }
}
