package com.crell.core.util;

import com.crell.common.model.User;
import com.crell.core.constant.Context;
import com.crell.core.expection.InvalidUserException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component("SystemUtil")
public class SystemUtil {
    
    public static User getUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = (User)request.getSession().getAttribute(Context.USER_SESSION_KEY);
        if(user != null) return user;

        String authorization = request.getHeader("Authorization");
        String usertoken = SystemUtil.getToken(authorization);
//        user = (User) CacheUtils.get(usertoken);
//        if(user == null) throw new InvalidUserException("");

        request.getSession().setAttribute(Context.USER_SESSION_KEY,user);
        return user;
    }

    public static String getToken(String authorization){
        String usertoken = "";
        String[] parts = authorization.split(" ");
        if(parts.length == 2){
            String scheme = parts[0];
            String credentials = parts[1];
            if("Bearer".equals(scheme)){
                usertoken = credentials;
            }
        }
        return usertoken;
    }
}
