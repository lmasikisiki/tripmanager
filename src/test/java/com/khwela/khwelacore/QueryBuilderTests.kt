package com.khwela.khwelacore

import com.khwela.khwelacore.commons.QueryBuilder.getQueryParts
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.junit.Test
import java.time.LocalDate
import java.util.*

class QueryBuilderTests {

    @Test
    fun Given_OnlyDestination_ShouldReturn_DestinationParts() {
        var filters = HashMap<String, Any>()
        filters["destination"] = "Point A"
        var result = getQueryParts(filters)
        assertThat(result, contains("destination = Point A"))

    }

    @Test
    fun Given_OnlyDestinationAndNumberOfSeats_ShouldReturn_OnlyDestinationAndNumber() {
        var filters = HashMap<String, Any>()
        filters["destination"] = "Point A"
        filters["numberOfSeats"] = 3;
        var result = getQueryParts(filters)
        assertThat(result, containsInAnyOrder("destination = Point A", "numberOfSeats = 3"))
    }

    @Test
    fun Given_OnlyDestinationAndTripDateandNumberOfSeats_ShouldReturn_Only3Parts() {
        val localDate = LocalDate.now();
        var filters = HashMap<String, Any>();
        filters["destination"] = "Point A";
        filters["numberOfSeats"] = 3;
        filters["tripDate"] = localDate;
        var result = getQueryParts(filters)
        assertThat(result, containsInAnyOrder("destination = Point A",
                "numberOfSeats = 3",
                "tripDate = $localDate"))
    }
}

