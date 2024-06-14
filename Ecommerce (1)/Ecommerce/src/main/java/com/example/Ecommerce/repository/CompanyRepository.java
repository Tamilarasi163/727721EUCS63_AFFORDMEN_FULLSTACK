package com.example.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Ecommerce.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer>
{

}

