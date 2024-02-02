package com.assessment.assessmenttwo.controller;

import com.assessment.assessmenttwo.dto.ShoppingCartDTO;
import com.assessment.assessmenttwo.service.ShoppingCartService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ShoppingCartController {
    ShoppingCartService shoppingCartService;
    @Autowired
    ShoppingCartController(ShoppingCartService shoppingCartService)
    {
        this.shoppingCartService = shoppingCartService;
    }

@PostMapping("/shoppingcart/add")
        public String addToShoppingCart(@RequestParam  String productID, @RequestParam int quantity, @RequestParam int userID) throws NoSuchFieldException {
    try {
        int prodID = Integer.parseInt(productID);
        shoppingCartService.addProduct(prodID,quantity,userID);
        return "Successfully added!";
    }
    catch (Exception e)
    {
        throw e;
    }
}

@DeleteMapping("/shoppingcart/remove")
    public String fromShoppingCart(@RequestParam @JsonDeserialize int productID, @RequestParam int quantity,@RequestParam int userID) throws NoSuchFieldException {
    try{
        shoppingCartService.removeProduct(productID,quantity,userID);
        return "Successfully removed!";
    }
    catch (Exception e)
    {
        throw e;
    }
}

@GetMapping("shoppingcart/view")
    public List<ShoppingCartDTO> viewShoppingCart(@RequestParam int userID) throws NoSuchFieldException {
    try {
        return shoppingCartService.view(userID);
    }
    catch (Exception e)
    {
        throw e;
    }
}

}
