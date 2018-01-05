package com.xxx.xtable.api.model;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.xxx.xtable.api.support.Check;
import com.xxx.xtable.api.support.Formatter;
import com.xxx.xtable.api.support.XFieldDataType;
import com.xxx.xtable.api.support.XFieldType;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class XField implements Serializable, Check, Formatter {
    private static final long serialVersionUID = -4212804171372982406L;
    private String id;
    private String name;
    /**
     * {@link XFieldType}.
     */
    private int fieldType;      //字段类型
    /**
     * {@link XFieldDataType}.
     */
    private int dataType;       //字段数据类型
    private boolean hidden;   //是否隐藏
    private boolean required; //是否必填
    private int sort;         //排序字段
    private Date createTime;
    private Date updateTime;

    /**
     * 单选、多选配置选项
     * {@link XFieldType.SingleSelect,XFieldType.MultiSelect}
     */
    private List<Option> optionList;

    public XField() {
    }

    @Override
    public void check() {
        Preconditions.checkArgument(fieldType > 0, "字段fieldType值非法");
        Preconditions.checkArgument(dataType > 0, "字段dataType值非法");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "字段name值不能为空");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(id), "字段id值不能为空");


        if (fieldType == XFieldType.SingleSelect.value() ||
                fieldType == XFieldType.MultiSelect.value()) {
            Preconditions.checkArgument(CollectionUtils.isNotEmpty(getOptionList()), "字段optionList不能为空");
            Set<String> nameSet = Sets.newHashSet();
            Set<Integer> valueSet = Sets.newHashSet();

            getOptionList().forEach(option -> {
                if (nameSet.contains(option.getName())) {
                    throw new RuntimeException(String.format("字段option.name出现重复[%s]", option.getName()));
                }

                if (valueSet.contains(option.getValue())) {
                    throw new RuntimeException(String.format("字段option.value出现重复[%s]", option.getValue()));
                }

                nameSet.add(option.getName());
                valueSet.add(option.getValue());
            });
        }

    }

    @Override
    public void format() {
        if (this.createTime == null) {
            setCreateTime(new Date());
        }
        setUpdateTime(new Date());

        XFieldType fieldType = XFieldType.valueOf(this.getFieldType());
        setDataType(fieldType.dataType().value());
    }
}
