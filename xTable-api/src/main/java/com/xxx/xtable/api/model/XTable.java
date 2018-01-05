package com.xxx.xtable.api.model;


import com.google.common.base.Preconditions;
import com.xxx.xtable.api.support.Check;
import com.xxx.xtable.api.support.Formatter;
import com.xxx.xtable.api.support.MetaClass;
import com.xxx.xtable.api.support.ModuleType;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class XTable extends Base implements Serializable, Check, Formatter {
    private static final long serialVersionUID = 2593927519477236618L;
    public static final String CollectionName = MetaClass.XTableMeta;
    /**
     * 关联模块
     * {@link ModuleType}.
     */
    private Integer moduleType;
    /**
     * 存储展示顺序,存储fieldId
     */
    private List<String> fieldSort;

    /**
     * 分段列表
     */
    private List<XSegment> xSegmentList;

    /**
     * 表单定义历史快照
     */
    private List<XTable> snapshotList;

    public XTable() {
        super();
        this.setSnapshotList(new ArrayList<>());
        this.setFieldSort(new ArrayList<>());
    }

    @Override
    public void check() {
        Preconditions.checkArgument(moduleType != null && moduleType.intValue() > 0, "字段moduleType值非法");
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(xSegmentList), "字段xSegmentList不能为空");
        if (CollectionUtils.isNotEmpty(xSegmentList)) {
            xSegmentList.forEach(xSegment -> xSegment.check());
        }
    }

    @Override
    public void format() {
        if (CollectionUtils.isNotEmpty(xSegmentList)) {
            xSegmentList.forEach(xSegment -> xSegment.format());
        }
    }
}
