package com.assessment.assessmenttwo.service;

import com.assessment.assessmenttwo.dao.ShoppingCartDAO;
import com.assessment.assessmenttwo.dto.ProductDTO;
import com.assessment.assessmenttwo.dto.ShoppingCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class ShoppingCartService {
    ShoppingCartDAO shoppingCartDAO;
    ProductService productService;

    @Autowired
    ShoppingCartService(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }


    public void add(UUID uuid, int quanitty) {
        try {
            ProductDTO p = productService.update(uuid, quanitty);
            ShoppingCartDTO dto = new ShoppingCartDTO();
            dto.setProductDTO(p);
            if (dto.getProductDTO().getQuantity() == 0) {
                shoppingCartDAO.delete(dto);
            } else shoppingCartDAO.save(dto);
        } catch (Exception e) {
            throw e;
        }
    }

public ShoppingCartDTO view(UUID id)
{
   Optional<ShoppingCartDTO> shoppingCartDTO = shoppingCartDAO.findById(id);
  return shoppingCartDTO.get();

}
}


