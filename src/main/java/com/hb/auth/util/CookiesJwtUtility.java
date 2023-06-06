package com.hb.auth.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class CookiesJwtUtility {
    /**
     * Assign JWT to response after login
     * @param jwt the jwt provided by JwtProvider
     * @param response the HttpServletResponse provided by the controller
     */
    public static void assignJwtToCookies(String jwt, HttpServletResponse response){
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void removeJwtFromCookies(HttpServletResponse response){
        Cookie jwtCookie = new Cookie("jwt", null);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);
    }
}
