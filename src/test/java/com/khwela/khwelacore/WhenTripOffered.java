package com.khwela.khwelacore;


import com.khwela.khwelacore.trips.Trip;
import com.khwela.khwelacore.trips.TripOfferedCommand;
import com.khwela.khwelacore.trips.TripOfferedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.axonframework.test.aggregate.AggregateTestFixture;

@SpringBootTest
public class WhenTripOffered {

    private AggregateTestFixture<Trip> fixture;

    @Test
    public  void shouldRaiseTripOfferedEvent(){
        fixture = new AggregateTestFixture<Trip>(Trip.class);

        fixture.givenNoPriorActivity()
                .when(new TripOfferedCommand("123","Lizo","Durban","Flagstaff"))
                .expectEvents(new TripOfferedEvent("123","Lizo","Durban","Flagstaff"));

    }
}
