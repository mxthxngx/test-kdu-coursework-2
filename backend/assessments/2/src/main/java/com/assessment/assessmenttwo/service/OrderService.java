package com.assessment.assessmenttwo.service;

import com.assessment.assessmenttwo.dao.OrderDAO;
import com.assessment.assessmenttwo.dto.InventoryDTO;
import com.assessment.assessmenttwo.dto.ProductDTO;
import com.assessment.assessmenttwo.dto.ShoppingCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import java.util.int;

@Service
public class OrderService {
    OrderDAO orderDAO;
    InventoryService inventoryService;
    ShoppingCartService shoppingCartService;
    @Autowired
    OrderService(OrderDAO orderDAO,ShoppingCartService shoppingCartService,InventoryService inventoryService)
    {
        this.orderDAO =orderDAO;
        this.shoppingCartService = shoppingCartService;
        this.inventoryService = inventoryService;
    }

    public double process(int userID)
    {
      List<ShoppingCartDTO> shoppingCartDTOList= shoppingCartService.getShoppingCartByUserID(userID);
      double totalAmount = 0;
      for(var element : shoppingCartDTOList)
      {
          int productID = element.getProductDTO().getId();
          int askedQty = element.getQuantity();
          InventoryDTO inventoryDTO = inventoryService.getByProductId(productID);
          int availableQty = inventoryDTO.getQuantity();
          if(availableQty>askedQty)
          {
              // Restock
              inventoryDTO.setQuantity(availableQty+askedQty);
          }
          int updatedQty = inventoryDTO.getQuantity()-askedQty;
          inventoryDTO.setQuantity(updatedQty);
          totalAmount+=askedQty*inventoryDTO.getProductDTO().getPrice();
          inventoryService.save(inventoryDTO);
          shoppingCartService.removeProduct(productID,askedQty,userID);
      }
      return totalAmount;
    }

}
