package com.sushanth.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServer {
    private PaymentService paymentService;

    public OrderServer(){

    }

    @Autowired
    public OrderServer(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    // public void setPaymentService(PaymentService paymentService){
    //     this.paymentService = paymentService;
    // }

    public void placeOrder(double amount){
            paymentService.processPayment(amount);
    }
}
