package com.xxx.xtable.api;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.Map;

public interface DebugClient extends Client {
    @RequestLine("POST /api/xtable/debug.json")
    @Headers({"Content-Type: application/json", "Authorization: {accessToken}"})
    Result post(@Param("accessToken") String accessToken, Map map);


    @RequestLine("GET /api/xtable/debug.json")
    @Headers({"Content-Type: application/json", "Authorization: {accessToken}"})
    Result get(@Param("accessToken") String accessToken);
}
