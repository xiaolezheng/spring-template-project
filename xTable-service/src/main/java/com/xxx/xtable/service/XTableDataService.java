package com.xxx.xtable.service;

import com.xxx.xtable.api.model.XTableData;
import com.xxx.xtable.api.support.ModuleType;

import java.util.List;

public interface XTableDataService {
    XTableData save(XTableData xTableData);

    XTableData delete(Long tenantId, String id);

    List<XTableData> find(Long tenantId, ModuleType moduleType, List<Object> relateIdList);

    List<XTableData> find(Long tenantId, String xTableId, List<Object> relateIdList);

    List<XTableData> find(Long tenantId, String xTableId);

    boolean exists(Long tenantId, String xTableId);
}
