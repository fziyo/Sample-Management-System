package com.fziyo.sms.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class User implements UserDetails, Serializable {
    private Integer id;
    private String username;
    @JsonIgnore
    private String pwd;
    private String name;
    private String tel;
    private String email;
    private Integer gender;
    private Integer accountNonExpired;
    private Integer credentialsNonExpired;
    private Integer accountNonLocked;
    private Integer accountEnabled;
    
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private Integer createBy;
    
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private LocalDateTime editTime;
    private Integer editedBy;
    
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;
    
    private String teamName;
    private String roleName;
    
    @JsonIgnore
    private List<Permission> permissionList;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        permissionList.forEach(permission -> {
            authorities.add(new SimpleGrantedAuthority(permission.getCode()));
        });
        
        return authorities;
    }
    
    @JsonIgnore
    @Override
    public String getPassword() {
        return this.pwd;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired == 1;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked == 1;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired == 1;
    }
    
    @Override
    public boolean isEnabled() {
        return this.accountEnabled == 1;
    }
}
