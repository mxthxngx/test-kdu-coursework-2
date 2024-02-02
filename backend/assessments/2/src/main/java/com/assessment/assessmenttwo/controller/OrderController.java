package com.assessment.assessmenttwo.controller;

import com.assessment.assessmenttwo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import java.util.int;

@RestController
@Slf4j
public class OrderController {
    OrderService orderService;
    @Autowired
    OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }
    @GetMapping("/order/process")
    public String process(@RequestParam int shoppingID)
    {try {
        double amt = orderService.process(shoppingID);
        return "Bill is: "+amt;
    }
    catch (Exception e)
    {
        log.error("Exception: "+e);
        throw e;
    }
    }
}
