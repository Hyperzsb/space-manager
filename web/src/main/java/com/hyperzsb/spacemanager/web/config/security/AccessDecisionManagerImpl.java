package com.hyperzsb.spacemanager.web.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class AccessDecisionManagerImpl implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null || configAttributes.size() <= 0) {
            return;
        } else {
            String needRole;
            for (Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext(); ) {
                needRole = iter.next().getAttribute();
                for (GrantedAuthority grantedAuthority : authentication.getAuthorities())
                    if (needRole.trim().equals(grantedAuthority.getAuthority().trim()))
                        return;
            }
            throw new AccessDeniedException("当前访问没有权限");
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
