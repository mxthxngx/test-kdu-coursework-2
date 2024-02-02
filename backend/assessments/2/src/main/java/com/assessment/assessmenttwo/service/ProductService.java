package com.assessment.assessmenttwo.service;

import com.assessment.assessmenttwo.dao.ProductDAO;
import com.assessment.assessmenttwo.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO;
public ProductDTO update(UUID id, int quantity)
{
   Optional<ProductDTO> productDTO= productDAO.findById(id);
   ProductDTO p = productDTO.get();
   if(p.getQuantity()-quantity<0)
   {
       p.setQuantity(p.getQuantity()+quantity+100);
   }
   p.setQuantity(p.getQuantity()-quantity);
   productDAO.save(p);
   return p;
}

}
