package com.xxx.xtable.service;

import com.xxx.xtable.api.model.Base;
import com.xxx.xtable.api.support.Status;
import com.xxx.xtable.service.support.ContextHolder;
import com.xxx.xtable.service.support.Principal;
import com.xxx.xtable.service.util.IdUtil;

import java.util.Date;

public interface BaseHelper {
    int INIT_VERSION = 1;

    default void setBaseData(Base base) {
        Principal principal = ContextHolder.getData(Principal.class);

        base.setId(IdUtil.id());
        base.setTenantId(principal.getTenantId());
        base.setCreateById(principal.getUserId());
        base.setUpdateById(principal.getUserId());
        if (base.getCreateTime() == null) {
            base.setCreateTime(new Date());
        }
        base.setUpdateTime(new Date());
        base.setVersion(INIT_VERSION);
        base.setStatus(Status.Normal.value());
    }
}
