package com.example.jdbc.service;

import com.example.jdbc.dao.AllDAO;
import com.example.jdbc.dto.AllDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AllService {
    AllDAO allDAO;
    AllService(AllDAO allDAO)
    {
        this.allDAO = allDAO;
    }
    /**
     * Adds all AllDTO objects to the database.
     *
     * @param  allDTO    the AllDTO object to be added
     * @return          true if the addition is successful, false otherwise
     */
  
    @Transactional
    public boolean addAll(AllDTO allDTO) {
        try {
            if(allDAO.addAllDAO(allDTO)!=0)
                return true;

        }
        catch (Exception e)
        {
            log.error("Exception at allDTO");
            throw e;
        }
        return false;
    }
}
