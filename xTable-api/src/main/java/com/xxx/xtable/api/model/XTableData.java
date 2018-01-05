package com.xxx.xtable.api.model;

import com.xxx.xtable.api.support.MetaClass;
import com.xxx.xtable.api.support.ModuleType;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class XTableData extends Base implements Serializable {
    private static final long serialVersionUID = -8202636936186027892L;
    public static final String CollectionName = MetaClass.XTableDataMeta;

    /**
     * 关联外部数据ID,一般为外部主表的主键ID
     */
    private Long relatedId;
    /**
     * 表单ID
     */
    private String xTableId;
    /**
     * 表单对应的版本号
     */
    private Integer xTableVersion;
    /**
     * 关联模块
     * {@link ModuleType}.
     */
    private Integer moduleType;

    /**
     * 结构 segId ->map(fieldId -> data), 二级结构
     */
    private Map<String, Map<String, Object>> data;

    public XTableData() {
        super();
    }
}
