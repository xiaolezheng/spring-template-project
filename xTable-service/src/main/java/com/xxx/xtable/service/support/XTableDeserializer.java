package com.xxx.xtable.service.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.xxx.xtable.api.model.XTable;
import com.xxx.xtable.service.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XTableDeserializer extends JsonDeserializer<XTable> {
    @Override
    public XTable deserialize(JsonParser jp, DeserializationContext ctx) {
        XTable xTable = JsonUtil.deserialize(jp, XTable.class);
        return xTable;
    }
}
