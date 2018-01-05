package com.xxx.xtable.service;

import com.google.common.base.Preconditions;
import com.xxx.xtable.api.model.XTableData;
import com.xxx.xtable.api.support.ModuleType;
import com.xxx.xtable.api.support.Status;
import com.xxx.xtable.service.support.LogProfiling;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Slf4j
@Service
public class XTableDataServiceBean implements XTableDataService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    XTableDataHelper xTableDataHelper;

    @LogProfiling
    @Override
    public XTableData save(XTableData xTableData) {
        if(StringUtils.isEmpty(xTableData.getId())){
            return insert(xTableData);
        }

        return update(xTableData);
    }

    @LogProfiling
    @Override
    public XTableData delete(Long tenantId, String id) {
        Query query = new Query(where("tenantId").is(tenantId).and("id").is(id));
        XTableData xTableData = mongoTemplate.findOne(query, XTableData.class, XTableData.CollectionName);
        mongoTemplate.updateFirst(query, Update.update("status", Status.Delete.value()), XTableData.CollectionName);
        return xTableData;
    }

    @LogProfiling
    @Override
    public List<XTableData> find(Long tenantId, ModuleType moduleType, List<Object> relateIdList) {
        Preconditions.checkNotNull(tenantId, "参数tenantId不能为空");
        Preconditions.checkNotNull(moduleType, "参数moduleType不能为空");
        Preconditions.checkArgument(moduleType != ModuleType.UNKnown, "参数moduleType值非法");
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(relateIdList), "参数relateIdList不能为空");

        Query query = new Query(where("tenantId").is(tenantId).and("moduleType").is(moduleType.value()).and("relatedId").in(relateIdList));
        return mongoTemplate.find(query, XTableData.class, XTableData.CollectionName);
    }

    @LogProfiling
    @Override
    public List<XTableData> find(Long tenantId, String xTableId, List<Object> relateIdList) {
        Preconditions.checkNotNull(tenantId, "参数tenantId不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(xTableId), "参数xTableId不能为空");
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(relateIdList), "参数relateIdList不能为空");

        Query query = new Query(where("tenantId").is(tenantId).and("xTableId").is(xTableId).and("relatedId").in(relateIdList));
        return mongoTemplate.find(query, XTableData.class, XTableData.CollectionName);
    }

    @LogProfiling
    @Override
    public List<XTableData> find(Long tenantId, String xTableId) {
        Preconditions.checkNotNull(tenantId, "参数tenantId不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(xTableId), "参数xTableId不能为空");

        Query query = new Query(where("tenantId").is(tenantId).and("xTableId").is(xTableId));
        return mongoTemplate.find(query, XTableData.class, XTableData.CollectionName);
    }

    @LogProfiling
    @Override
    public boolean exists(Long tenantId, String xTableId) {
        Preconditions.checkNotNull(tenantId, "参数tenantId不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(xTableId), "参数xTableId不能为空");

        Query query = new Query(where("tenantId").is(tenantId).and("xTableId").is(xTableId));
        XTableData xTableData = mongoTemplate.findOne(query, XTableData.class, XTableData.CollectionName);

        return xTableData != null;
    }

    private XTableData insert(XTableData xTableData) {
        xTableDataHelper.checkOnCreateData(xTableData);
        xTableDataHelper.packingOnCreateData(xTableData);

        mongoTemplate.remove(new Query(where("tenantId").is(xTableData.getTenantId()).and("moduleType").is(xTableData.getModuleType()).and("relatedId").is(xTableData.getRelatedId())),
                XTableData.class,XTableData.CollectionName);
        mongoTemplate.insert(xTableData, XTableData.CollectionName);
        return xTableData;
    }


    private XTableData update(XTableData xTableData) {
        xTableDataHelper.checkOnUpdateData(xTableData);
        xTableDataHelper.packingOnUpdateData(xTableData);

        mongoTemplate.save(xTableData, XTableData.CollectionName);
        return xTableData;
    }
}
