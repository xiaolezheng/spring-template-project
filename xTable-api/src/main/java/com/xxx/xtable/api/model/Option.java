package com.xxx.xtable.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Option implements Serializable {
    private static final long serialVersionUID = 834239102632715796L;
    private String name; // 展示名称,不能为空
    private Integer value;   // 选项值,必须为数字,多个不能重复,不能为空
    private Integer sort;    // 排序顺序

    public Option() {
    }
}
