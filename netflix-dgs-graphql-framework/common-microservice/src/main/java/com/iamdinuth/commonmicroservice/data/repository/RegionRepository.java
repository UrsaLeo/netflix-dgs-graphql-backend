package com.iamdinuth.commonmicroservice.data.repository;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.iamdinuth.commonmicroservice.data.entity.Country;
import com.iamdinuth.commonmicroservice.data.entity.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RegionRepository extends EntityGraphJpaRepository<Region, UUID> {

    List<Region> findAllByCountry_Code(String code, EntityGraph entityGraph);
    @Query("SELECT r FROM Region r WHERE r.country.code=:code")
    List<Region> findRegionsForCountry(String code, EntityGraph entityGraph);

}
