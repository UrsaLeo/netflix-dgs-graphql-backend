package com.iamdinuth.commonmicroservice.data.repository;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.iamdinuth.commonmicroservice.data.entity.Twin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TwinRepository extends EntityGraphJpaRepository<Twin, UUID> {

    List<Twin> findAll(EntityGraph entityGraph);

    @Query("SELECT t FROM Twin t WHERE t.client.id=:clientId")
    List<Twin> findTwinsForClient(String clientId, EntityGraph entityGraph);
}
