package com.iamdinuth.commonmicroservice.data.repository;

import com.iamdinuth.commonmicroservice.data.util.EntityGraphBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class AutowireRepositories {
    @Autowired
    public CountryRepository countryRepository;
    @Autowired
    public RegionRepository regionRepository;
    @Autowired
    public EntityGraphBuilder egb;
}