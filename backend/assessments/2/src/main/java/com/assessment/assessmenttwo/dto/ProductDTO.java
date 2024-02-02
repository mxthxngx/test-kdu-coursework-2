package com.assessment.assessmenttwo.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

//import java.util.int;

@Data
@Entity
@RequiredArgsConstructor
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double price;
    private String description;
    private String name;

}
