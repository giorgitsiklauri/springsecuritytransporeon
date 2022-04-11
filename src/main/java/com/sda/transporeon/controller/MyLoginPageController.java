package com.sda.transporeon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyLoginPageController {

    @GetMapping("/myloginpage")
    public String getLoginPage() {
        return "myloginpage";
    }


}
