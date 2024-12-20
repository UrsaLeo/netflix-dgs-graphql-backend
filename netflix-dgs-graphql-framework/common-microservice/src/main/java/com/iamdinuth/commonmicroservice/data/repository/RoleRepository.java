package com.iamdinuth.commonmicroservice.data.repository;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.iamdinuth.commonmicroservice.data.entity.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends EntityGraphJpaRepository<Role, UUID> {
    Optional<Role> findById(UUID roleId, EntityGraph entityGraph);

    Optional<Role> findByDescription(String description);
}
