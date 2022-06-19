package com.example.onlineshop.entity.user;

import org.springframework.security.core.GrantedAuthority;

public enum ERole implements GrantedAuthority {
    ADMIN,
    MANAGER,
    CUSTOMER;

    @Override
    public String getAuthority() {
        return name();
    }


    @Override
    public String toString() {
        return super.toString();
    }
}



