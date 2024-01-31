package com.example.jdbc.controller;

import com.example.jdbc.dao.AllDAO;
import com.example.jdbc.dto.AllDTO;
import com.example.jdbc.service.AllService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AllUpdateController {
    AllService allService;
    @Autowired
    AllUpdateController(AllService allDAO)
    {
        this.allService = allDAO;
    }
    /**
     * A method to add all users.
     *
     * @param  allDTO   the DTO containing all users to be added
     * @return          an HttpEntity with a String indicating success or failure
     */
    @PostMapping("all/add")
    public HttpEntity<String> addAllUsers(@RequestBody AllDTO allDTO)
    {
        try {
            log.info(allDTO.toString());
            if( allService.addAll(allDTO))
                return ResponseEntity.ok("Successfully added");

            else  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to add shift type");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to add shift type");
        }

    }
}
