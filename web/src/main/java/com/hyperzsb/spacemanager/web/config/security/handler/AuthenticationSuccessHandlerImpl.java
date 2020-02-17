package com.hyperzsb.spacemanager.web.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyperzsb.spacemanager.web.domain.ResponseBean;
import com.hyperzsb.spacemanager.web.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        User user = (User) authentication.getPrincipal();
        user.setPassword(null);
        ResponseBean ok = ResponseBean.ok("登录成功!", user);
        String string = new ObjectMapper().writeValueAsString(ok);
        out.write(string);
        out.flush();
        out.close();
    }

}
