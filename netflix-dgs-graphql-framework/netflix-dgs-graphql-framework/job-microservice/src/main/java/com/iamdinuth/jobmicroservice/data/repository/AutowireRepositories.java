package com.iamdinuth.jobmicroservice.data.repository;

import com.iamdinuth.jobmicroservice.data.util.EntityGraphBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class AutowireRepositories {
    @Autowired
    public JobRepository jobRepository;
    @Autowired
    public EntityGraphBuilder egb;
}
