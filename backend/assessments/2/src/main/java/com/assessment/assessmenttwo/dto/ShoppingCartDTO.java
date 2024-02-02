package com.assessment.assessmenttwo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.Fetch;

import java.util.List;
//import java.util.int;

@Data
@Entity
@Table(name="shopping_cart")
@NoArgsConstructor

public class ShoppingCartDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductDTO productDTO;
    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private UserDTO user;



}
