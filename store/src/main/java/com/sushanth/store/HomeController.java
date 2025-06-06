package com.sushanth.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;




@Controller
// @ResponseBody
public class HomeController {
    
    @Value("${spring.application.name}")
    private String appname;

    @RequestMapping("/")
    public String getMethodName() {
        System.out.println("App name : "+appname);
        return "index";
    }

    @PostMapping("/user")
    @ResponseBody
    public String user(@RequestParam String email , @RequestParam String password) {
        return "User Email : "+email+"\nUser Password : "+password;
    }

    @PostMapping("/user/{name}")
    @ResponseBody
    public String name(@PathVariable String name) {
        return "User Name : "+name;
    }
    
    
}