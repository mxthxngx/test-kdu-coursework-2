package com.assessment.assessmenttwo.service;

import com.assessment.assessmenttwo.dao.InventoryDAO;
import com.assessment.assessmenttwo.dto.InventoryDTO;
import com.assessment.assessmenttwo.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {
    InventoryDAO inventoryDAO;
    public void save(InventoryDTO inventoryDTO)
    {
        inventoryDAO.save(inventoryDTO);
    }
    public void update(UUID uuid,InventoryDTO inventoryDTOmain)
    {
        try {
            Optional<InventoryDTO> inventoryDTO = inventoryDAO.findById(uuid);
            if (inventoryDTO.isPresent()) {
                InventoryDTO inventoryDTO1 = inventoryDTO.get();
                ProductDTO productDTO = inventoryDTO1.getProductDTO();
                productDTO.setName(inventoryDTOmain.getProductDTO().getName());
                productDTO.setPrice(inventoryDTOmain.getProductDTO().getPrice());
                productDTO.setDescription(inventoryDTOmain.getProductDTO().getDescription());
                inventoryDAO.save(inventoryDTO1);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    public void delete(UUID uuid)
    {
        try {
            inventoryDAO.deleteById(uuid);
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
