//package com.assessment.assessmenttwo.controller;
//
//import com.assessment.assessmenttwo.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//
//@RestController
//public class OrderController {
//    OrderService orderService;
//    @Autowired
//    OrderController(OrderService orderService)
//    {
//        this.orderService = orderService;
//    }
//    @GetMapping("/order/process")
//    public String process(@RequestParam UUID shoppingID)
//    {
//        orderService.process(shoppingID);
//    }
//}
