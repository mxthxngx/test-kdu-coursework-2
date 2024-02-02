package com.assessment.assessmenttwo.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name="inventory")
@RequiredArgsConstructor
public class InventoryDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToOne
    ProductDTO productDTO;
}
