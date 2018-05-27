package com.khwela.khwelacore;


import com.khwela.khwelacore.trips.Trip;
import com.khwela.khwelacore.trips.TripOfferedCommand;
import com.khwela.khwelacore.trips.TripOfferedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.axonframework.test.aggregate.AggregateTestFixture;

import java.util.Date;

@SpringBootTest
public class WhenTripOffered {

    private AggregateTestFixture<Trip> fixture;

    @Test
    public  void shouldRaiseTripOfferedEvent(){
        fixture = new AggregateTestFixture<Trip>(Trip.class);

        fixture.givenNoPriorActivity()
                .when(new TripOfferedCommand("234","123","Lizo","Durban","Flagstaff",3, new Date()))
                .expectEvents(new TripOfferedEvent("234","123","Lizo","Durban","Flagstaff",3, new Date()));

    }
}
