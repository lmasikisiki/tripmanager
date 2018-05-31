package com.khwela.khwelacore;

import com.khwela.khwelacore.aggregates.Trip;
import com.khwela.khwelacore.commands.OfferTripCommand;
import com.khwela.khwelacore.events.TripOfferedEvent;
import org.aspectj.lang.annotation.Before;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

@SpringBootTest
public class AxonTests {


    private AggregateTestFixture<Trip> fixture;

    @BeforeEach
    public void setup() {
        fixture = new AggregateTestFixture<>(Trip.class);
    }

    @Test
    public void GivenOfferTripCommand_ShouldRaise_TripOfferedEvent() {
        LocalDate date =  LocalDate.now();
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
}
