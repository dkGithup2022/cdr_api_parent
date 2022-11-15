package com.dk0124.cdr.api.endpoint.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.dk0124.cdr.persistence"})
@EnableJpaRepositories(basePackages={"com.dk0124.cdr.persistence"})
@EntityScan(basePackages = {"com.dk0124.cdr.persistence"})
public class PersistenceConfig {
}
