package com.beanlerning.beanlearning;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService {

    // @Autowired
    private MyFirstClass myFirstClass;

    //No nead of using @Autowired because it contains only one constructor
    public MyFirstService(MyFirstClass myFirstClass){
        this.myFirstClass = myFirstClass;
    }

    public String tellStory(){
        return myFirstClass.sayHello();
    }
}
