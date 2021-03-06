package com.khwela.khwelacore;


import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@EntityScan({
        "org.axonframework.eventsourcing.eventstore.jpa",
        "org.axonframework.eventhandling.saga.repository.jpa",
        "org.axonframework.eventhandling.tokenstore.jpa",
        "com.khwela.khwelacore",
        "com.khwela.khwelacore.models"
        })
public class ApplicationConfig {

    @Bean
   public EntityManagerProvider entityManagerProvider(){
          return new ContainerManagedEntityManagerProvider();

   }

   @Bean
   public DataSource dataSource(){
        return DataSourceBuilder.create()
                .username("sa")
                .password("")
                .url("jdbc:h2:mem:example-app;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")
                .driverClassName("org.h2.Driver")
                .build();

   }

    @Bean(name="transactionManager")
    public PlatformTransactionManager dbTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        return transactionManager;
    }

    @Bean
    public SpringTransactionManager springTransactionManager(){
        return new SpringTransactionManager(dbTransactionManager());
    }

    @Bean
    public JpaEventStorageEngine jpaEventStorageEngine(){
       return new JpaEventStorageEngine(entityManagerProvider(),springTransactionManager());
    }
}
