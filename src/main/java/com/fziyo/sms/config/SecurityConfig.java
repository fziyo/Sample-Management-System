package com.fziyo.sms.config;

import com.fziyo.sms.handler.AuthenticationFailureHandlerImpl;
import com.fziyo.sms.handler.AuthenticationSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
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
                   .sessionManagement((sessionManagement) -> {
                       sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                   })
                   .formLogin(formLogin -> {
                       formLogin
                           .loginProcessingUrl("/user/login")  // where SC get username and password
                           .successHandler(authenticationSuccessHandler)
                           .failureHandler(authenticationFailureHandler);
                       
                   })
                   .authorizeHttpRequests(authorizeRequests -> {
                       authorizeRequests
                           .anyRequest().authenticated();
                   })
                   .build();
    }
    
}
