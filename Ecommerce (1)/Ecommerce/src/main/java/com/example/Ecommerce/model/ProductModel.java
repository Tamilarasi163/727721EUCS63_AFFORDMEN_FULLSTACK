package com.example.Ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductModel {
    private Integer id;
    private String productName;
    private Double price;
    private Double rating;
    private Double discount;
    private Boolean availability;
    private Integer companyId;
    private String companyName;  
    private String categoryName; 
}
