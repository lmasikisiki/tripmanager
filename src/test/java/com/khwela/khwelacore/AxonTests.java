package com.khwela.khwelacore;

import com.khwela.khwelacore.aggregates.Requests;
import com.khwela.khwelacore.aggregates.Trip;
import com.khwela.khwelacore.commands.OfferTripCommand;
import com.khwela.khwelacore.commands.RequestTripCommand;
import com.khwela.khwelacore.enums.LuggageSize;
import com.khwela.khwelacore.events.TripOfferedEvent;
import com.khwela.khwelacore.events.TripRequestAssignedEvent;
import com.khwela.khwelacore.events.TripRequestedEvent;
import com.khwela.khwelacore.models.TripRequest;
import org.apache.tomcat.jni.Local;
import org.aspectj.lang.annotation.Before;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.axonframework.messaging.MessageHandler;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.axonframework.test.aggregate.TestExecutor;
import org.axonframework.test.matchers.FieldFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class AxonTests {


    private AggregateTestFixture<Trip> fixture;
    private AggregateTestFixture<Requests> frequestFixture;

    @BeforeEach
    public void setup() {
        fixture = new AggregateTestFixture<>(Trip.class);
        frequestFixture = new AggregateTestFixture<>(Requests.class);
    }

    @Test
    public void GivenOfferTripCommand_ShouldRaise_TripOfferedEvent() {
        LocalDate date = LocalDate.now();
        fixture.givenNoPriorActivity()
                .when(new OfferTripCommand("123456",
                        "Tester User",
                        "Point A",
                        "Point B",
                        3,
                        date))
                .expectEvents(new TripOfferedEvent("123456",
                        "Tester User",
                        "Point A",
                        "Point B",
                        3,
                        date))
                .expectSuccessfulHandlerExecution();
    }

    @Test
    void When_TripRequested_ShouldRaise_TripRequestedEvent() {
        frequestFixture.when(new RequestTripCommand("1234",
                "Lizo2345",
                "Point A",
                "Point B",
                LocalDate.now(),
                3,
                LuggageSize.SMALL))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new TripRequestedEvent("1234",
                        "Lizo2345",
                        "Point A",
                        "Point B",
                        LocalDate.now(),
                        3,
                        LuggageSize.SMALL))
                .expectSuccessfulHandlerExecution();
    }

}
