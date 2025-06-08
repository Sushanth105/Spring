package com.sushanth.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;


// class Model{
//     private String name;
//     private int age;

//     Model(String name,int age){
//         this.name = name;
//         this.age = age;
//     }

//     public String getName(){
//         return name;
//     }
//     public int getAge(){
//         return age;
//     }
//     public int getp(){
//         return 5;
//     }
// }

record Model(String name,int age) {
}

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

    @PostMapping("/datas")
    @ResponseBody
    public List<Model> data() {
        return List.of(
            new Model("Name1", 19),
            new Model("Name2", 20)
            );
    }

    @PostMapping("/send")
    @ResponseBody
    public List<Model> postMethodName(@RequestBody List<Model> models) {
        System.out.println(models);
        System.out.println("Name : "+models.get(0).name()+" Age : "+models.get(0).age());
        return models;
    }
    
}