package com.khwela.khwelacore;


import com.khwela.khwelacore.models.TripRecord;
import com.khwela.khwelacore.trips.Trip;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jdbc.JdbcEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EntityScan({
        "org.axonframework.eventsourcing.eventstore.jpa",
        "org.axonframework.eventhandling.saga.repository.jpa",
        "org.axonframework.eventhandling.tokenstore.jpa",
        "com.khwela.khwelacore",
        "com.khwela.khwelacore.models"
        })
public class ApplicationConfig {

    

//   @Bean
   public EventStorageEngine eventStores() throws SQLException {
       return new InMemoryEventStorageEngine();
     //  new JdbcEventStorageEngine()
    }

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
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
      /*  transactionManager.setEntityManagerFactory(
                dbEntityManager().getObject());*/
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
