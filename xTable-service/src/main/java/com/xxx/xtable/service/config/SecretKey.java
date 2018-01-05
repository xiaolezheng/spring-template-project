package com.xxx.xtable.service.config;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SecretKey implements Serializable {
    private static final long serialVersionUID = -1127988676544463275L;
    private String secretKey;
}
