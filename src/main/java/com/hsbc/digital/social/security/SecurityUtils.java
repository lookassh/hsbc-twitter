package com.hsbc.digital.social.security;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtils {
    public String currentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
