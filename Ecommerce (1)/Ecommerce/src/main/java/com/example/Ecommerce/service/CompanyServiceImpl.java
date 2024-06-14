package com.example.Ecommerce.service;

import org.springframework.stereotype.Service;

import com.example.Ecommerce.entity.Company;
import com.example.Ecommerce.model.CompanyModel;
import com.example.Ecommerce.repository.CompanyRepository;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CompanyServiceImpl implements CompanyService 
{
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyModel> getAllCompany() 
    {
        log.info("Fetching All Brands!!!");
        //Fetch Brands
        List<Company> companyList = companyRepository.findAll();
        //now use stream operator to map with Response
        List<CompanyModel> companyResponses = companyList.stream()
                .map(this::convertToBrandResponse)
                .collect(Collectors.toList());
        log.info("Fetched All Brands!!!");
        return companyResponses;
    }

    private CompanyModel convertToBrandResponse(Company company) {
        return CompanyModel.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
    }
}
