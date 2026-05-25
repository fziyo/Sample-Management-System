package com.fziyo.sms.handler;

import cn.hutool.jwt.JWTUtil;
import com.fziyo.sms.common.Result;
import com.fziyo.sms.constants.ConfigConstants;
import com.fziyo.sms.model.entity.User;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import cn.hutool.json.JSONUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.Map;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler
{
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // generate jwt toke
        User user = (User) authentication.getPrincipal();
        String userJSON = JSONUtil.toJsonStr(user);
        String token = JWTUtil.createToken(Map.of("user", userJSON), ConfigConstants.SECRET.getBytes());
        
        // store token to redis
        redisTemplate.opsForHash().put(ConfigConstants.REDIS_TOKEN_KEY, String.valueOf(user.getId()), token);
        
        Result<String> result = new Result<>(200, "Login Success", token);
        String responseJson = JSONUtil.toJsonStr(result);
        response.getWriter().write(responseJson);
    }
}
