package com.xxx.xtable.service;

import com.xxx.xtable.api.model.XTable;
import com.xxx.xtable.api.support.ModuleType;

public interface XTableService {
    XTable create(XTable xTable);

    XTable update(XTable xTable);

    XTable delete(Long tenantId, String xTableId, String segmentId);

    XTable find(Long tenantId, ModuleType moduleType);

    XTable find(Long tenantId, String xTableId);
}
