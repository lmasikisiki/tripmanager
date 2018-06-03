package com.khwela.khwelacore.rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.khwela.khwelacore.commands.OfferTripCommand;
import com.khwela.khwelacore.commons.DateDeSerializerAdaper;
import com.khwela.khwelacore.commons.DateSerializerAdaper;
import com.khwela.khwelacore.models.TripRecord;
import com.khwela.khwelacore.repositories.TripRepository;
import com.khwela.khwelacore.repositories.TripRequestRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@SuppressWarnings("unused")
public class HomeController  {

    private final CommandGateway commandGateway;
    private final TripRequestRepository tripRequestRepository;
    private final TripRepository tripRepository;

    private final Gson jsonConverter;

    public HomeController(CommandGateway commandGateway, TripRepository tripRepository, TripRequestRepository tripRequestRepository, Gson jsonConverter) {
        this.commandGateway = commandGateway;
        this.tripRepository = tripRepository;
        this.tripRequestRepository = tripRequestRepository;
       this.jsonConverter= jsonConverter;
    }

    @PostMapping("/trip")
    public CompletableFuture<Object> offerTrip(@RequestBody String requestBody) {
        Assert.hasLength(requestBody, "supplied request body is empty");
        OfferTripCommand tripOffer = jsonConverter.fromJson(requestBody, OfferTripCommand.class);
        tripOffer.setTripId(UUID.randomUUID().toString());
        return commandGateway.send(tripOffer);
    }

    @GetMapping("/trip")
    public List<TripRecord> getTrips() {
        return tripRepository.findAll();
    }

    @GetMapping("/trip/{tripId}")
    public Optional<TripRecord> getTrips(@PathVariable(name = "tripId") String tripId) {
        return tripRepository.findById(tripId);
    }
}
