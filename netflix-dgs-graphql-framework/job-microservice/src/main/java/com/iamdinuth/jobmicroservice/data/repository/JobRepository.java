package com.iamdinuth.jobmicroservice.data.repository;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.iamdinuth.jobmicroservice.data.entity.Job;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobRepository extends EntityGraphJpaRepository<Job, UUID> {
    List<Job> findAll(EntityGraph entityGraph);
}
