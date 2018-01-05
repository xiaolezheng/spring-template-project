package com.xxx.xtable.api;

import com.xxx.xtable.api.model.XTable;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface XTableMetaClient extends Client {
    @RequestLine("GET /api/xtable/meta/find/{moduleType}")
    @Headers({"Content-Type: application/json", "Authorization: {accessToken}"})
    Result<XTable> find(@Param("accessToken") String accessToken, @Param("moduleType") Integer moduleType);

    @RequestLine("GET /api/xtable/meta/find/id/{xTableId}")
    @Headers({"Content-Type: application/json", "Authorization: {accessToken}"})
    Result<XTable> find(@Param("accessToken") String accessToken, @Param("xTableId") String xTableId);
}
