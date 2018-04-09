package com.notebook.interceptor;

import com.notebook.utils.MD5;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


public class LoginInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //将cookie封装到map里
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        Cookie accessToken = cookieMap.get("accessToken");
        Cookie userInfo = cookieMap.get("userInfo");
        boolean legal = false;
        if (accessToken != null && userInfo != null) {
            //判断 accessToken 的合法性
            JSONObject jo = JSONObject.fromObject(URLDecoder.decode(userInfo.getValue(), "UTF-8"));
            String userName = (String) jo.get("userName");
            legal = accessToken.getValue().equals(MD5.get(userName));
        }
        if (!legal) {
            request.getRequestDispatcher("/notebook/login").forward(request, response);
        }
        return legal;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
