package com.whkj.spikes.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class Ips {

    private static final String CLIENT_IP = "CLIENT_IP";

    public static String getRealIP(HttpServletRequest req) {
        String ip = (String) req.getAttribute(CLIENT_IP);
        if (ip == null) {
            ip = getFirstNonBlankHeader(req, "X-Real-IP", "x-real-ip",
                    "X-Forwarded-For", "x-forwarded-for");
            if (ip == null) {
                ip = req.getRemoteAddr();
                if (ip == null) {
                    ip = "";
                }
            }
            int idx = ip.indexOf(",");
            ip = (idx != -1) ? ip.substring(0, idx).trim() : ip;
            req.setAttribute(CLIENT_IP, ip);
        }
        return ip;
    }

    private static String getFirstNonBlankHeader(HttpServletRequest req,
                                                 String... headerNames) {
        if (req == null) {
            return null;
        }
        for (String name : headerNames) {
            String value = req.getHeader(name);
            if (StringUtils.isNotBlank(value)) {
                return value;
            }
        }
        return null;
    }
}
