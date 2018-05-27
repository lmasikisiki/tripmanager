package com.khwela.khwelacore;

import com.khwela.khwelacore.trips.Trip;
import com.khwela.khwelacore.trips.TripUpdatedCommand;
import com.khwela.khwelacore.trips.TripUpdatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WhenUpdatingATrip {

    private AggregateTestFixture<Trip> fixture;

    @Test
    public void ShouldRaiseTripUpdateEvent(){
        fixture =  new AggregateTestFixture<>(Trip.class);
        fixture.givenNoPriorActivity()
        .when(new TripUpdatedCommand("234","123","Lizo","Durban","Flagstaff"))
        .expectEvents(new TripUpdatedEvent("234","123","Lizo","Durban","Flagstaff"));
    }

}
