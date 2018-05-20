package com.khwela.khwelacore;

import com.khwela.khwelacore.enums.TripStatus;
import com.khwela.khwelacore.trips.Trip;
import com.khwela.khwelacore.trips.TripCancelledCommand;
import com.khwela.khwelacore.trips.TripCancelledEvent;
import com.khwela.khwelacore.trips.TripStatusChangedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class WhenTripCancelled {

    private AggregateTestFixture<Trip> fixture;

    @Test
    public void shouldlRaiseTripCancelledEvent(){
        fixture= new AggregateTestFixture<Trip>(Trip.class);
        fixture.givenNoPriorActivity()
                .when(new TripCancelledCommand("123","cancelledBy"))
                .expectEvents(new TripCancelledEvent("123","cancelledBy"),
                        new TripStatusChangedEvent("123",TripStatus.AVAILABLE, TripStatus.CANCELLED));
    }
}
