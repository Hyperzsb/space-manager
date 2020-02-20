package com.hyperzsb.spacemanager.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class WebSecurityController {

    @GetMapping("/authentication")
    public Authentication isAuthenticated(Authentication authentication) {
        return authentication;
    }

}
