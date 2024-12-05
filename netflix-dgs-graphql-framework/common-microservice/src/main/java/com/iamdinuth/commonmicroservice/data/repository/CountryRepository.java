package com.iamdinuth.commonmicroservice.data.repository;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.iamdinuth.commonmicroservice.data.entity.Country;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CountryRepository extends EntityGraphJpaRepository<Country, String> {
    List<Country> findAll(EntityGraph entityGraph);
    Optional<Country> findByCode(String Code, EntityGraph entityGraph);

    Optional<Country> findCountryByRegions_Id(UUID id, EntityGraph entityGraph);
}
