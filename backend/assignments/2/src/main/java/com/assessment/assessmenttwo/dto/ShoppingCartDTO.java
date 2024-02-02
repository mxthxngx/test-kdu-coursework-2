package com.assessment.assessmenttwo.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Data
@Entity
@Table(name ="shoppingcart")
public class ShoppingCartDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int  id;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="product_id",referencedColumnName = "id")
    ProductDTO  productDTO;
    private int quantity;

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="user_id")
    private UserDTO user;



}
