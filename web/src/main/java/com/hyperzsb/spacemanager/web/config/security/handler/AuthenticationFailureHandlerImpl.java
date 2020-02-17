package com.hyperzsb.spacemanager.web.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyperzsb.spacemanager.web.domain.ResponseBean;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ResponseBean responseBean = ResponseBean.error("登录失败!");
        if (exception instanceof LockedException) {
            responseBean.setMsg("账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            responseBean.setMsg("密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            responseBean.setMsg("账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            responseBean.setMsg("账户被禁用，请联系管理员!");
        } else if (exception instanceof BadCredentialsException) {
            responseBean.setMsg("用户名或者密码输入错误，请重新输入!");
        }
        out.write(new ObjectMapper().writeValueAsString(responseBean));
        out.flush();
        out.close();
    }

}
