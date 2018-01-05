package com.xxx.xtable.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.xxx.xtable.api.model.XSegment;
import com.xxx.xtable.api.model.XTable;
import com.xxx.xtable.api.support.ModuleType;
import com.xxx.xtable.api.support.Status;
import com.xxx.xtable.service.exception.ServiceException;
import com.xxx.xtable.service.support.LogProfiling;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Slf4j
@Service
public class XTableServiceBean implements XTableService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    XTableHelper xTableHelper;

    @LogProfiling
    @Override
    public XTable create(XTable xTable) {
        xTableHelper.formatOnCreateXTable(xTable);
        xTableHelper.checkOnCreateXTable(xTable);
        xTableHelper.packingOnCreateXTable(xTable);
        /**
         * 同一公司同一模块业务只能存在一个表单
         */
        delete(xTable.getTenantId(), xTable.getModuleType());
        mongoTemplate.insert(xTable, XTable.CollectionName);
        return xTable;
    }

    @LogProfiling
    @Override
    public XTable update(XTable xTable) {
        xTableHelper.formatOnUpdateXTable(xTable);
        xTableHelper.checkOnUpdateXTable(xTable);
        xTableHelper.packingOnUpdateXTable(xTable);
        mongoTemplate.save(xTable, XTable.CollectionName);
        return xTable;
    }

    @LogProfiling
    @Override
    public XTable delete(Long tenantId, String xTableId, String segmentId) {
        Preconditions.checkNotNull(tenantId, "参数tenantId不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(xTableId), "参数xTableId不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(segmentId), "参数segmentId不能为空");

        XTable xTable = find(tenantId, xTableId);
        if (xTable == null) {
            throw new ServiceException(String.format("主表单不存在,公司ID[%s],表单ID[%s]", tenantId, xTableId));
        }

        if (CollectionUtils.isEmpty(xTable.getXSegmentList())) {
            throw new ServiceException(String.format("主表单所有类别已经被删除,公司ID[%s],表单ID[%s]", tenantId, xTableId));
        }

        if (xTable.getStatus() == Status.Delete.value()) {
            throw new ServiceException(String.format("表单已经被删除,公司ID[%s],表单ID[%s]", tenantId, xTableId));
        }

        List<XSegment> xSegmentList = Lists.newArrayList();
        xTable.getXSegmentList().forEach(xSegment -> {
            if (!StringUtils.equals(segmentId, xSegment.getId())) {
                xSegmentList.add(xSegment);
            }
        });

        if (CollectionUtils.isEmpty(xSegmentList)) {
            xTable.setStatus(Status.Delete.value());
        }

        mongoTemplate.save(xTable, XTable.CollectionName);
        return xTable;
    }

    @LogProfiling
    @Override
    public XTable find(Long tenantId, ModuleType moduleType) {
        Preconditions.checkNotNull(tenantId, "参数tenantId不能为空");
        Preconditions.checkNotNull(moduleType, "参数moduleType不能为空");
        Preconditions.checkArgument(moduleType != ModuleType.UNKnown, "参数moduleType值非法");

        Query query = new Query(where("tenantId").is(tenantId).and("moduleType").is(moduleType.value()));
        return mongoTemplate.findOne(query, XTable.class, XTable.CollectionName);
    }

    @LogProfiling
    @Override
    public XTable find(Long tenantId, String xTableId) {
        Preconditions.checkNotNull(tenantId, "参数tenantId不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(xTableId), "参数xTableId不能为空");

        Query query = new Query(where("tenantId").is(tenantId).and("id").is(xTableId));
        return mongoTemplate.findOne(query, XTable.class, XTable.CollectionName);
    }

    private void delete(Long tenantId, int moduleType) {
        Preconditions.checkNotNull(tenantId, "参数tenantId不能为空");
        Preconditions.checkArgument(moduleType > ModuleType.UNKnown.value(), "参数moduleType值非法");
        Query query = new Query(where("tenantId").is(tenantId).and("moduleType").is(moduleType));
        mongoTemplate.remove(query, XTable.CollectionName);
    }
}
