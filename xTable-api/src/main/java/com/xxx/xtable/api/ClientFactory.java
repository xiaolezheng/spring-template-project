package com.xxx.xtable.api;

import com.xxx.xtable.api.support.ClientNotInitException;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;

import java.util.HashMap;
import java.util.Map;

public class ClientFactory {
    public static final String LOCAL_HOST = "http://127.0.0.1:9999";
    public static final String DEV_HOST = "https://dev01.xxx.me";
    public static final String TEST_HOST = "https://test01.xxx.me";
    public static final String PROD_HOST = "https://www.xxx.com";
    private static String HOST = LOCAL_HOST;


    private static final Map<Class, Object> CLIENT_MAP = new HashMap<>(3);
    private static volatile boolean init = false;

    private ClientFactory() {
    }

    private static void build(Class<? extends Client> tClass) {
        Object instance = Feign.builder().client(new OkHttpClient()).
                encoder(new JacksonEncoder()).
                decoder(new JacksonDecoder()).
                logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.BASIC).target(tClass, HOST);
        CLIENT_MAP.put(tClass, instance);
    }

    /**
     * 初始化
     *
     * @param host 对应环境的HOST地址
     */
    public static void init(String host) {
        if (!init) {
            synchronized (ClientFactory.class) {
                if (!init) {
                    HOST = host;
                    build(DebugClient.class);
                    build(XTableDataClient.class);
                    build(XTableMetaClient.class);
                    init = true;
                    System.out.println("ClientFactory init finished!");
                }
            }
        }
    }

    public static <T> T get(Class<T> tClass) {
        if (!init) {
            throw new ClientNotInitException("没有初始化,请先执行ClientFactory.init(host)方法进行初始化操作");
        }

        return (T) CLIENT_MAP.get(tClass);
    }

}
