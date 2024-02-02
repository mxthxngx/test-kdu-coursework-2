package com.assessment.assessmenttwo.service;

import com.assessment.assessmenttwo.dao.InventoryDAO;
import com.assessment.assessmenttwo.dto.InventoryDTO;
import com.assessment.assessmenttwo.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
//import java.util.int;

@Service
@Slf4j
public class InventoryService {
    @Autowired
            InventoryService(InventoryDAO inventoryDAO,ProductService productService)
    {
        this.inventoryDAO = inventoryDAO;
        this.productService = productService;
    }
    InventoryDAO inventoryDAO;
    ProductService productService;
    public void save(InventoryDTO inventoryDTO) {
        try {
            if (inventoryDTO.getProductDTO() != null) {
                ProductDTO savedProductDTO = productService.saveProduct(inventoryDTO.getProductDTO());
                inventoryDAO.save(inventoryDTO);
            } else {
                throw new Exception("Product not identified");
            }
        } catch (Exception e) {
            log.error("Error occurred while saving inventory: {}", e.getMessage());
        }
    }

    public void update(int uuid,InventoryDTO inventoryDTOmain)
    {
        try {
            Optional<InventoryDTO> inventoryDTO = inventoryDAO.findById(uuid);
            if (inventoryDTO.isPresent()) {
                InventoryDTO inventoryDTO1 = inventoryDTO.get();

                inventoryDTO1.getProductDTO().setName(inventoryDTOmain.getProductDTO().getName());
                inventoryDTO1.getProductDTO().setPrice(inventoryDTOmain.getProductDTO().getPrice());
                inventoryDTO1.getProductDTO().setDescription(inventoryDTOmain.getProductDTO().getDescription());
                inventoryDTO1.setQuantity(inventoryDTOmain.getQuantity());
                inventoryDAO.save(inventoryDTO1);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    public void delete(int uuid)
    {
        try {
            inventoryDAO.deleteById(uuid);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public InventoryDTO getByProductId(int productId)
    {
        try{
          InventoryDTO inventoryDTO = inventoryDAO.findByProductID(productId);
          return inventoryDTO;
        }
        catch (Exception e)
        {
            throw e;
        }
    }



    public void updateQuantity(int qty,int productID) throws Exception,NoSuchFieldException {
       Optional<InventoryDTO> inventoryDTO= inventoryDAO.findById(productID);
       if(inventoryDTO.isPresent()) {
          InventoryDTO inventoryDTO1 = inventoryDTO.get();
          inventoryDTO1.setQuantity(inventoryDTO1.getQuantity()+qty);
    log.info("updated");
            save(inventoryDTO1);
       }
       else {
           log.error("Product not found!");
           throw new NoSuchFieldException("No such product");
       }
    }
}
