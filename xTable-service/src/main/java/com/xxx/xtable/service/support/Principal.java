package com.xxx.xtable.service.support;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 当前登录主体
 */
@Data
@Builder
public class Principal implements Serializable {
    private static final long serialVersionUID = -3520320606110404042L;
    private Long tenantId;
    private Long userId;
}
