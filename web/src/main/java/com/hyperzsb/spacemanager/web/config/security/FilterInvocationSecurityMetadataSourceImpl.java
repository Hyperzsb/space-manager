package com.hyperzsb.spacemanager.web.config.security;

import com.hyperzsb.spacemanager.web.domain.Permission;
import com.hyperzsb.spacemanager.web.domain.Role;
import com.hyperzsb.spacemanager.web.service.PermissionService;
import com.hyperzsb.spacemanager.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    private static HashMap<String, Collection<ConfigAttribute>> map = null;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (map == null) {
            map = new HashMap<>(16);
            List<Role> roleList = roleService.getRoles();
            for (Role role : roleList) {
//                System.out.println("ROLE: " + role.toString());
                List<Permission> permissionList = permissionService.getPermissionsByRole(role);
                for (Permission permission : permissionList) {
//                    System.out.println("PERMISSION: " + permission.toString());
                    String url = permission.getUrl();
                    String roleName = role.getName();
                    ConfigAttribute newRole = new SecurityConfig(roleName);
                    if (map.containsKey(url)) {
                        map.get(url).add(newRole);
                    } else {
                        List<ConfigAttribute> list = new ArrayList<>();
                        list.add(newRole);
                        map.put(url, list);
                    }
                }
            }
        }
//        System.out.println(map.toString());
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
            String url = it.next();
            if (new AntPathRequestMatcher(url).matches(request))
                return map.get(url);
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
