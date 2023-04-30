package com.hb.auth.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class JwtUtil {
    public static void assignJwtToCookie(String jwt, HttpServletResponse response){
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
