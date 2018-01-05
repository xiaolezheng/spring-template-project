package com.xxx.xtable.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.xxx.xtable.api.model.XTable;
import com.xxx.xtable.service.exception.ServiceException;
import com.xxx.xtable.service.support.ContextHolder;
import com.xxx.xtable.service.support.Principal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class XTableHelper implements BaseHelper {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    XTableService xTableService;

    @Autowired
    XTableDataService xTableDataService;


    public void formatOnCreateXTable(XTable xTable) {
        Preconditions.checkNotNull(xTable, "参数xTable不能为空");
        xTable.format();
    }

    public void checkOnCreateXTable(XTable xTable) {
        Preconditions.checkNotNull(xTable, "参数xTable不能为空");
        xTable.check();
    }

    public void formatOnUpdateXTable(XTable xTable) {
        Preconditions.checkNotNull(xTable, "参数xTable不能为空");
        xTable.format();
    }

    public void checkOnUpdateXTable(XTable xTable) {
        Preconditions.checkNotNull(xTable, "参数xTable不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(xTable.getId()), "主表单id字段不能为空");
        xTable.check();

        XTable dbXTable = xTableService.find(xTable.getTenantId(), xTable.getId());
        if (xTable.version() != dbXTable.version()) {
            throw new ServiceException(String.format("表单已经被其他人修改,公司ID[%s],主表单ID[%s]", xTable.getTenantId(), xTable.getId()));
        }
    }

    public void packingOnCreateXTable(XTable xTable) {
        setBaseData(xTable);
    }

    public void packingOnUpdateXTable(XTable xTable) {
        Principal principal = ContextHolder.getData(Principal.class);
        xTable.setUpdateById(principal.getUserId());
        xTable.setUpdateTime(new Date());

        /**
         * 没有数据则不进行版本号递增,避免配置阶段保存大量快照
         */
        boolean existsData = xTableDataService.exists(principal.getTenantId(), xTable.getId());
        if (!existsData) {
            return;
        }

        // 版本号递增
        increaseVersion(xTable);

        // 保存版本快照
        XTable lastXTable = xTableService.find(principal.getTenantId(), xTable.getId());
        if (CollectionUtils.isEmpty(xTable.getSnapshotList())) {
            xTable.setSnapshotList(Lists.newArrayList(lastXTable));
        } else {
            xTable.getSnapshotList().add(lastXTable);
        }
    }

    private void increaseVersion(XTable xTable) {
        xTable.setVersion(xTable.version() + 1);
    }
}
