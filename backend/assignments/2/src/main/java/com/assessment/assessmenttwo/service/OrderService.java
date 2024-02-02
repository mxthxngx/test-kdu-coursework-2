package com.assessment.assessmenttwo.service;

import com.assessment.assessmenttwo.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    OrderDAO orderDAO;
    ShoppingCartService shoppingCartService;
    @Autowired
    OrderService(OrderDAO orderDAO,ShoppingCartService shoppingCartService)
    {
        this.orderDAO =orderDAO;
        this.shoppingCartService = shoppingCartService;
    }

}
