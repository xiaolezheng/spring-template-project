package com.xxx.xtable.api.model;

import com.xxx.xtable.api.support.Version;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Base implements Serializable, Version {
    private static final long serialVersionUID = -908277790333777937L;
    private String id;
    private Long tenantId;
    private Date createTime;
    private Date updateTime;
    private Long createById;
    private Long updateById;
    private int status;
    private Integer version;

    @Override
    public Integer version() {
        return version;
    }


    public Base() {
    }
}
