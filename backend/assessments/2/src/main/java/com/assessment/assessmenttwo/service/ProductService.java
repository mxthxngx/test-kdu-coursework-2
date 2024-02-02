package com.assessment.assessmenttwo.service;

import com.assessment.assessmenttwo.dao.ProductDAO;
import com.assessment.assessmenttwo.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
//import java.util.int;

@Service
public class ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        return productDAO.save(productDTO);
    }

    public ProductDTO getProduct(int productID) throws Exception {
      Optional<ProductDTO> productDTOTemp =  productDAO.findById(productID);
      if(productDTOTemp.isPresent())
      {
          return productDTOTemp.get();
      }
      else {
          throw new Exception("Product not found");
      }
    }
}