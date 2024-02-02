package com.assessment.assessmenttwo.controller;

import com.assessment.assessmenttwo.dto.ShoppingCartDTO;
import com.assessment.assessmenttwo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ShoppingCartController {
    ShoppingCartService shoppingCartService;
    @Autowired
    ShoppingCartController(ShoppingCartService shoppingCartService)
    {
        this.shoppingCartService = shoppingCartService;
    }

@PostMapping("/shoppingcart/add")
        public String addToShoppingCart(@RequestParam UUID productID,@RequestParam int quantity)
{
    try {
        shoppingCartService.add(productID,quantity);
        return "Successfully added!";
    }
    catch (Exception e)
    {
        throw e;
    }
}

@DeleteMapping("/shoppingcart/remove")
    public String fromShoppingCart(@RequestParam UUID uuid, @RequestParam int quantity)
{
    try{
        shoppingCartService.add(uuid,-quantity);
        return "Successfully removed!";
    }
    catch (Exception e)
    {
        throw e;
    }
}

@GetMapping("shoppingcart/view")
    public ShoppingCartDTO viewShoppingCart(@RequestParam UUID id)
{
    try {
        return shoppingCartService.view(id);
    }
    catch (Exception e)
    {
        throw e;
    }
}

}
