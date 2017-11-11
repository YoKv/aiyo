package net; /**
 * www.mbaobao.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void addCookie(HttpServletResponse resp, Cookie cookie) {
        cookie.setPath("/");
        resp.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest req, String name) {
        if (req == null || req.getCookies() == null || req.getCookies().length == 0) {
            return null;
        }
        for (Cookie cookie : req.getCookies()) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }

        return null;
    }

    public static String getCookieValue(HttpServletRequest req, String name) {
        if (req == null || req.getCookies() == null || req.getCookies().length == 0) {
            return null;
        }
        for (Cookie cookie : req.getCookies()) {
            if (name.equals(cookie.getName())) {
                if (cookie != null)
                    return cookie.getValue();
            }
        }
        return null;
    }

    public static void addCookieValue(HttpServletResponse resp, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        if (value == null) {
            cookie.setMaxAge(0);
        }
        cookie.setPath("/");
        resp.addCookie(cookie);


    }

    // 将 base 进行 BASE64 编码
    public static String getBASE64(String s) {
        if (s == null) return null;
        return (new BASE64Encoder()).encode(s.getBytes());
    }

    // 将 BASE64 编码的字符串 base 进行解码
    public static String getFromBASE64(String s) {
        if (s == null) return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }

}
