package com.zmy.blog.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: zengmy
 * @description: 常用的方法
 * @date 2020-09-22
 */
public class MyUtils {

    /**
     * @Author zengmy
     * @Despcription 获得IP地址
     * @Date 2020/9/22 18:21
     * @param * @param null
     * @return
     **/
    public static String getInAddr(HttpServletRequest request, HttpServletResponse response) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null ||ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
            ipAddress = request.getRemoteAddr();
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','，分割
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }

        return ipAddress;
    }
}
