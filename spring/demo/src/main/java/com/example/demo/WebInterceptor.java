package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;


@Component
public class WebInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            if (request.getHeader("Authorization") == null){
                throw new AccessDeniedException("Not Authorization");
            }
        }
        catch (AccessDeniedException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
            System.out.println(request.getHeader("Authorization"));
        return true;
    }
}
