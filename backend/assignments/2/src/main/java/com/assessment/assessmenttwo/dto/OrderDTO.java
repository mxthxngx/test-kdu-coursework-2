package com.assessment.assessmenttwo.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name="orders")
public class OrderDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    Date date;
    int totalAmount;
    @OneToOne
    ShoppingCartDTO shoppingCartDTO;

}
