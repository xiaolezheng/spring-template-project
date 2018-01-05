package com.xxx.xtable.service.util;

import com.xxx.xtable.service.exception.ServiceException;
import com.xxx.xtable.service.support.Principal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public final class HttpUtil {
    /**
     * 这几个参数同账号token生成必须保持一致
     */
    private static final String ACCESS_TOKEN = "access_token";
    private static final String TENANT_ID = "tid";
    private static final String AID = "aid";

    /**
     * 获取Ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader(" x-forwarded-for ");
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" WL-Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    public static Principal fromRequest(HttpServletRequest request, String secretKey) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String accessToken = "";
        if (StringUtils.isNotEmpty(authHeader)) {
            String[] authArray = authHeader.split(" ");
            accessToken = authArray[1];
        } else {
            Cookie cookie = WebUtils.getCookie(request, ACCESS_TOKEN);
            if (cookie != null) {
                accessToken = cookie.getValue();
            }
        }

        if (StringUtils.isNotEmpty(accessToken)) {
            return fromAccessToken(accessToken, secretKey);
        }

        throw new SecurityException("token解析错误,请检查请求头是否包括access_token");
    }


    public static Principal fromAccessToken(String accessToken, String secretKey) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken).getBody();
            Object tenantId = claims.get(TENANT_ID);
            Object runAsId = claims.get(AID);

            return Principal.builder().tenantId(Long.parseLong(tenantId.toString())).userId(Long.parseLong(runAsId.toString())).build();
        } catch (Exception e) {
            log.error("token解析失败,token:{}", accessToken);
            throw new ServiceException("token解析失败", e);
        }
    }
}
