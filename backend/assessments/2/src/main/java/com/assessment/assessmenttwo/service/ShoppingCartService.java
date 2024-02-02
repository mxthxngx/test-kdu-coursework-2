package com.assessment.assessmenttwo.service;

import com.assessment.assessmenttwo.dao.ShoppingCartDAO;
import com.assessment.assessmenttwo.dto.InventoryDTO;
import com.assessment.assessmenttwo.dto.ProductDTO;
import com.assessment.assessmenttwo.dto.ShoppingCartDTO;
import com.assessment.assessmenttwo.dto.UserDTO;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class ShoppingCartService {
    ShoppingCartDAO shoppingCartDAO;
    ProductService productService;

    List<ShoppingCartDTO> getShoppingCartByUserID(int UserID)
    {
        try
        {
            return shoppingCartDAO.findUserByUserId(UserID);
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    @Autowired
    ShoppingCartService(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }
    @SneakyThrows
    @Transactional
    public void addProduct(int productID, int quantity, int userID)
    {
      try {
          ShoppingCartDTO shoppingCartDTO = shoppingCartDAO.findProductById(productID,userID);
          if (shoppingCartDTO != null) {

              shoppingCartDTO.setQuantity(shoppingCartDTO.getQuantity() + quantity);
          } else {

              shoppingCartDTO = new ShoppingCartDTO();
              ProductDTO productDTO = new ProductDTO();
              productDTO.setId(productID);
              shoppingCartDTO.setProductDTO(productDTO);
              shoppingCartDTO.setQuantity(quantity);
              UserDTO user  = new UserDTO();
              user.setId(userID);
              shoppingCartDTO.setUser(user);
              save(shoppingCartDTO);
          }
      }
      catch (Exception e)
      {
          log.error("Exception in adding to cart: "+e);
          throw e;
      }
    }
    @Transactional
    public void removeProduct(int productId, int quantityToRemove,int userID) {
        try {

            ShoppingCartDTO shoppingCartDTO = shoppingCartDAO.findProductById(productId,userID);

            if (shoppingCartDTO != null) {
                int currentQuantity = shoppingCartDTO.getQuantity();
                if (quantityToRemove >= currentQuantity) {

                    shoppingCartDAO.delete(shoppingCartDTO);
                } else {

                    shoppingCartDTO.setQuantity(currentQuantity - quantityToRemove);
                    shoppingCartDAO.save(shoppingCartDTO);
                }
            } else {

                throw new RuntimeException("Product not found in the shopping cart");
            }
        } catch (Exception e) {

          throw e;
        }
    }
    public void save(ShoppingCartDTO shoppingCartDTO)
    {
        try {
            if (shoppingCartDTO.getProductDTO() != null) {

                shoppingCartDAO.save(shoppingCartDTO);
            } else {
                throw new Exception("Product not identified");
            }
        } catch (Exception e) {
            log.error("Error occurred while saving shopping cart: {}", e.getMessage());
        }
    }

//    public void updateQuantity(int qty,int productID) throws NoSuchFieldException {
//        ShoppingCartDTO shoppingCartDTO= shoppingCartDAO.findProductById(productID);
//        if(shoppingCartDTO.isPresent()) {
//            ShoppingCartDTO shoppingCartDTO1 = shoppingCartDTO.get();
//            shoppingCartDTO1.setQuantity(shoppingCartDTO1.getQuantity()+qty);
//            if(shoppingCartDTO1.getQuantity()<=0)
//            {
//                shoppingCartDAO.delete(shoppingCartDTO1);
//            }
//            else {
//
//                save(shoppingCartDTO1);
//            }
//            log.info("updated");
//        }
//        else {
//            log.error("Product not found!");
//            throw new NoSuchFieldException("No such product");
//        }
//    }
public List<ShoppingCartDTO> view(int userID) throws NoSuchFieldException {try {
    return shoppingCartDAO.findUserByUserId(userID);
}
catch (Exception e)
{
    throw new NoSuchFieldException("User ID does not have shopping cart");
}

}
}


