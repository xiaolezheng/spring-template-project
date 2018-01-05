package com.xxx.xtable.api.model;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.xxx.xtable.api.support.Check;
import com.xxx.xtable.api.support.Formatter;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class XSegment implements Serializable, Check, Formatter {
    private static final long serialVersionUID = -7007654768066978283L;
    private String id;
    private String name;
    private int sort;         //排序字段
    private Date createTime;
    private Date updateTime;

    /**
     * 字段列表
     */
    private List<XField> fieldList;

    public XSegment() {
    }

    @Override
    public void check() {
        Preconditions.checkArgument(StringUtils.isNotEmpty(id), "字段XSegment.id不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(name), "字段XSegment.name不能为空");
        if (CollectionUtils.isNotEmpty(fieldList)) {
            Set<String> fieldIdSet = Sets.newHashSetWithExpectedSize(fieldList.size());
            Set<String> fieldNameSet = Sets.newHashSetWithExpectedSize(fieldList.size());
            fieldList.forEach(field -> {
                field.check();

                if (fieldIdSet.contains(field.getId())) {
                    throw new RuntimeException(String.format("类别[%s]字段id出现重复,重复Field[%s-%s]", name, field.getId(), field.getName()));
                }
                if (fieldNameSet.contains(field.getName())) {
                    throw new RuntimeException(String.format("类别[%s]name出现重复,重复Field[%s-%s]", name, field.getId(), field.getName()));
                }

                fieldIdSet.add(field.getId());
                fieldNameSet.add(field.getName());
            });
        }
    }

    @Override
    public void format() {
        if (this.createTime == null) {
            setCreateTime(new Date());
        }
        setUpdateTime(new Date());
        if (CollectionUtils.isNotEmpty(fieldList)) {
            fieldList.forEach(field -> field.format());
        }
    }
}
