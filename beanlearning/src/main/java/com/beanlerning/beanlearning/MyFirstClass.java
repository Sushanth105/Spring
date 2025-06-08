package com.beanlerning.beanlearning;

import org.springframework.beans.factory.annotation.Value;

// import org.springframework.stereotype.Component;

// @Component
public class MyFirstClass {

    @Value("${java.version}")
    private String version;
    
    public String sayHello(){
        return "Hello!!, From the MyFirstClass : "+version;
    }
}
