package com.iamdinuth.commonmicroservice.data.repository;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.iamdinuth.commonmicroservice.data.entity.ClientTwinAccess;
import com.iamdinuth.commonmicroservice.data.entity.Role;
import com.iamdinuth.commonmicroservice.data.entity.Twin;
import com.iamdinuth.commonmicroservice.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientTwinAccessRepository  extends JpaRepository<ClientTwinAccess, UUID> {
    @Query("SELECT c FROM ClientTwinAccess c WHERE c.twin.id=:twinId")
    List<ClientTwinAccess> findAssinedUsersForTwin(UUID twinId, EntityGraph entityGraph);

    Optional<ClientTwinAccess> findById(UUID id, EntityGraph entityGraph);

    boolean existsByTwinAndUserAndRole(Twin twin, User user, Role role);
}
