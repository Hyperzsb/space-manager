package com.hyperzsb.spacemanager.web.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyperzsb.spacemanager.web.domain.ResponseBean;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(401);
        PrintWriter out = response.getWriter();
        ResponseBean responseBean = ResponseBean.error("访问失败!");
        if (authException instanceof InsufficientAuthenticationException) {
            responseBean.setMsg("请求失败，请联系管理员!");
        }
        out.write(new ObjectMapper().writeValueAsString(responseBean));
        out.flush();
        out.close();
    }
}
