package com.xxx.xtable.api;

import com.xxx.xtable.api.model.XTableData;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface XTableDataClient extends Client {
    @RequestLine("POST /api/xtable/data/save")
    @Headers({"Content-Type: application/json", "Authorization: {accessToken}"})
    Result<XTableData> save(@Param("accessToken") String accessToken, XTableData xTableData);

    @RequestLine("PUT /api/xtable/data/delete/{id}")
    @Headers({"Content-Type: application/json", "Authorization: {accessToken}"})
    Result<XTableData> delete(@Param("accessToken") String accessToken, @Param("id") String id);

    @RequestLine("GET /api/xtable/data/find/moduleType/{moduleType}/{relateId}")
    @Headers({"Content-Type: application/json", "Authorization: {accessToken}"})
    Result<XTableData> find(@Param("accessToken") String accessToken, @Param("moduleType") Integer moduleType, @Param("relateId") Long relateId);

    @RequestLine("GET /api/xtable/data/find/xTableId/{xTableId}/{relateId}")
    @Headers({"Content-Type: application/json", "Authorization: {accessToken}"})
    Result<XTableData> find(@Param("accessToken") String accessToken, @Param("xTableId") String xTableId, @Param("relateId") Long relateId);

    @RequestLine("GET /api/xtable/data/find/xTableId/{xTableId}")
    @Headers({"Content-Type: application/json", "Authorization: {accessToken}"})
    Result<List<XTableData>> find(@Param("accessToken") String accessToken, @Param("xTableId") String xTableId);
}
