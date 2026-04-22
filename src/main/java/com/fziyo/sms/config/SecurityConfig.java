package com.fziyo.sms.config;

import com.fziyo.sms.handler.AuthenticationFailureHandlerImpl;
import com.fziyo.sms.handler.AuthenticationSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig {
    
    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandlerImpl authenticationFailureHandler;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                   .csrf(AbstractHttpConfigurer::disable)
                   .formLogin(formLogin -> {
                       formLogin
                           .loginProcessingUrl("/user/login")  // SC框架去哪里接收登录参数
                           .successHandler(authenticationSuccessHandler)
                           .failureHandler(authenticationFailureHandler);
                       
                   })
                   // 把默认的拦截所有接口访问的登录检查行为，再加回来。
                   .authorizeHttpRequests(authorizeRequests -> {
                       // 任何请求都需要认证
                       authorizeRequests
                           // 其他路径都需要认证登录
                           .anyRequest().authenticated();
                   })
                   .build();
    }
    
}
