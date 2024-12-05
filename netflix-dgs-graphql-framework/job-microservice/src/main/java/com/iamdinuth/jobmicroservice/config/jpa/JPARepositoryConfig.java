package com.iamdinuth.jobmicroservice.config.jpa;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class,
        basePackages = {"${spring.jpa.properties.repository_package}"})
public class JPARepositoryConfig {
}
