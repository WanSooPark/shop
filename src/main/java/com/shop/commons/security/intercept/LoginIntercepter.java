package com.shop.commons.security.intercept;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String loginId = (String) request.getSession()
                .getAttribute("userId");
        if (StringUtils.hasLength(loginId)) {
            return true;
        } else {
            response.sendRedirect("/login/");
            return false;
        }
    }
}

