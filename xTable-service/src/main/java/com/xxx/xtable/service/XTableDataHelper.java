package com.xxx.xtable.service;

import com.google.common.base.Preconditions;
import com.xxx.xtable.api.model.XTableData;
import com.xxx.xtable.api.support.ModuleType;
import com.xxx.xtable.service.support.ContextHolder;
import com.xxx.xtable.service.support.Principal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class XTableDataHelper implements BaseHelper {
    @Autowired
    MongoTemplate mongoTemplate;

    public void checkOnCreateData(XTableData xTableData) {
        checkBaseData(xTableData);
    }

    public void checkOnUpdateData(XTableData xTableData) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(xTableData.getId()), "字段id不能为空");
        checkBaseData(xTableData);
    }

    public void packingOnCreateData(XTableData xTableData) {
        setBaseData(xTableData);
    }

    public void packingOnUpdateData(XTableData xTableData) {
        Principal principal = ContextHolder.getData(Principal.class);
        xTableData.setUpdateById(principal.getUserId());
        xTableData.setUpdateTime(new Date());
        increaseVersion(xTableData);
    }


    private void increaseVersion(XTableData xTableData) {
        xTableData.setVersion(xTableData.getVersion() + 1);
    }

    private void checkBaseData(XTableData xTableData) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(xTableData.getXTableId()), "字段xTableId不能为空");
        Preconditions.checkNotNull(xTableData.getXTableVersion(), "字段xTableVersion不能为空");
        Preconditions.checkNotNull(xTableData.getRelatedId(), "字段relateId不能为空");
        Preconditions.checkArgument(MapUtils.isNotEmpty(xTableData.getData()), "字段data不能为空");
        Preconditions.checkArgument(xTableData.getModuleType()!=null && xTableData.getModuleType()> ModuleType.UNKnown.value(),"字段moduleType值非法");
        //todo 后期需要增加每个字段的数据校验
    }
}
