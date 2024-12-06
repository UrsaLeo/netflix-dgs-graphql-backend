package com.iamdinuth.commonmicroservice.data.repository;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.iamdinuth.commonmicroservice.data.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends EntityGraphJpaRepository<User, UUID> {
    List<User> findAll(EntityGraph entityGraph);

    @Query("SELECT u FROM User u WHERE u.client.id=:clientId")
    List<User> findUsersForClient(String clientId, EntityGraph entityGraph);
}
