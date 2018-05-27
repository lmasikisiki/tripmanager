package com.khwela.khwelacore;


import com.khwela.khwelacore.trips.Trip;
import com.khwela.khwelacore.trips.TripRequestedCommand;
import com.khwela.khwelacore.trips.TripRequestedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest

public class WhenTripRequested {

    private AggregateTestFixture<Trip> fixture;

    @Test
    public void ShouldRaiseTripRequestedEvent(){
        fixture= new AggregateTestFixture<>(Trip.class);

        Date tripDate = new Date();
        fixture.givenNoPriorActivity()
                .when(new TripRequestedCommand("124","Userid","From","Destination",tripDate))
                .expectEvents(new TripRequestedEvent("124","Userid","From","Destination",tripDate));
    }
}
