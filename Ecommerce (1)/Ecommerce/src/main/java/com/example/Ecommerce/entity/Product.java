package com.example.Ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;

    @Column(name="ProductName")
    private String productName;

    @Column(name="Price")
    private Double price;

    @Column(name="Rating")
    private Double rating;

    @Column(name="Discount")
    private Double discount;

    @Column(name="Availability")
    private Boolean availability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CompanyId", referencedColumnName = "Id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CategoryId", referencedColumnName = "Id")
    private Category category;
}
