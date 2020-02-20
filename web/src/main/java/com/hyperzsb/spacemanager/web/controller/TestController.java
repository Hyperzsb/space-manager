package com.hyperzsb.spacemanager.web.controller;

import com.hyperzsb.spacemanager.web.domain.ResponseBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/public")
    public ResponseBean getPublic() {
        return ResponseBean.ok("Get public succeeded!");
    }

    @PostMapping("/public")
    public ResponseBean postPublic() {
        return ResponseBean.ok("Post public succeeded!");
    }

    @GetMapping("/user/common")
    public ResponseBean getCommon() {
        return ResponseBean.ok("Get common succeeded!");
    }

    @PostMapping("/user/common")
    public ResponseBean postCommon() {
        return ResponseBean.ok("Post common succeeded!");
    }

    @GetMapping("/user/admin")
    public ResponseBean getAdmin() {
        return ResponseBean.ok("Get admin succeeded!");
    }

    @PostMapping("/user/admin")
    public ResponseBean postAdmin() {
        return ResponseBean.ok("Post admin succeeded!");
    }
}
