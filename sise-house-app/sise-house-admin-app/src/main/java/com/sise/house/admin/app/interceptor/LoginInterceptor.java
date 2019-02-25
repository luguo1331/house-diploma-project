package com.sise.house.admin.app.interceptor;

import com.sise.common.exception.MyException;
import com.sise.common.exception.ResultEnum;
import com.sise.house.admin.app.annotation.LoginRequired;
import com.sise.house.admin.app.support.TokenManager;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Description: 登陆拦截器
 * @Auther: 冲之
 * @Date: 2019/2/17 20:17
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    public static final String LOGIN_TOKEN_KEY = "X-House-Token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        boolean isLoginRequired = isAnnotationPresent(method, LoginRequired.class);
        if (isLoginRequired) {
            String token = request.getHeader(LOGIN_TOKEN_KEY);
            if (token == null || token.isEmpty() || TokenManager.getUserId(token) == null) {
                throw new MyException(ResultEnum.NEED_LOGIN);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    private boolean isAnnotationPresent(Method method, Class<? extends Annotation> annotationClass) {
        return method.getDeclaringClass().isAnnotationPresent(annotationClass) || method.isAnnotationPresent(annotationClass);
    }


}
