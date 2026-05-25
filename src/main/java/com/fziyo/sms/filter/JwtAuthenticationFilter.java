package com.fziyo.sms.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import com.fziyo.sms.common.Result;
import com.fziyo.sms.config.SecurityConfig;
import com.fziyo.sms.constants.ConfigConstants;
import com.fziyo.sms.model.entity.User;
import io.netty.util.Constant;
import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String requestUri = request.getRequestURI();
        if (requestUri.equals("/user/login")) {
            filterChain.doFilter(request, response);
        } else {
            String token = request.getHeader("token");
            if (!StringUtils.hasText(token)) {
                Result<String> result = new Result<>(901, "Empty token", null);
                response.getWriter().write(JSONUtil.toJsonStr(result));
            } else {
                boolean authenticated = false;
                try {
                    authenticated = JWTUtil.verify(token, ConfigConstants.SECRET.getBytes());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (!authenticated) {
                    Result<String> result = new Result<>(901, "Invalid token", null);
                    response.getWriter().write(JSONUtil.toJsonStr(result));
                } else {
                    JSONObject playloads = JWTUtil.parseToken(token).getPayloads();
                    String userJSON = playloads.getStr("user", String.valueOf(String.class));
                    User user = JSONUtil.toBean(userJSON, User.class);
                    Integer userId = user.getId();

                    String redisToken = (String) redisTemplate.opsForHash().get(ConfigConstants.REDIS_TOKEN_KEY, String.valueOf(userId));
                    if (!token.equals(redisToken)) {
                        Result<String> result = new Result<>(903, "Incorrect token", null);
                        response.getWriter().write(JSONUtil.toJsonStr(result));
                    } else {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        filterChain.doFilter(request, response);
                    }
                }
            }
        }
        
        
    }
}
