package com.labbooking.config;

import com.labbooking.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                Claims claims = JwtUtil.parseToken(token);
                request.setAttribute("userId", Long.valueOf(claims.getSubject()));
                request.setAttribute("role", claims.get("role"));
                return true;
            } catch (Exception e) {
                response.setStatus(401);
                return false;
            }
        }
        String uri = request.getRequestURI();
        if (uri.contains("/login") || uri.contains("/register")) return true;
        response.setStatus(401);
        return false;
    }
}