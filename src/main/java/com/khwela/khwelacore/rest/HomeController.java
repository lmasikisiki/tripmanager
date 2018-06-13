package com.khwela.khwelacore.rest;


import com.google.gson.Gson;
import com.khwela.khwelacore.commands.OfferTripCommand;
import com.khwela.khwelacore.models.TripRecord;
import com.khwela.khwelacore.repositories.TripRepository;
import com.khwela.khwelacore.repositories.TripRequestRepository;
import com.khwela.khwelacore.services.OfferService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
 @SuppressWarnings("unused")
public class HomeController {

    private final CommandGateway commandGateway;
    private final TripRequestRepository tripRequestRepository;
    private final TripRepository tripRepository;
    private final Gson jsonConverter;
    private final OfferService offerService;

    public HomeController(CommandGateway commandGateway, TripRepository tripRepository, TripRequestRepository tripRequestRepository, Gson jsonConverter, OfferService offerService) {
        this.commandGateway = commandGateway;
        this.tripRepository = tripRepository;
        this.tripRequestRepository = tripRequestRepository;
        this.jsonConverter = jsonConverter;
        this.offerService = offerService;
    }

    @PostMapping("/trip")
    @Consumes("application/json")
    public @ResponseBody CompletableFuture<Object> offerTrip(@RequestBody String requestBody) {
        Assert.hasLength(requestBody, "supplied request body is empty");
        OfferTripCommand tripOffer = jsonConverter.fromJson(requestBody, OfferTripCommand.class);
        tripOffer.setTripId(UUID.randomUUID().toString());
        return commandGateway.send(tripOffer);
    }

    @GetMapping("/trip")
    public @ResponseBody List<TripRecord> getTrips() {
         return tripRepository.findAll();
    }

    @GetMapping("/trip/{tripId}")
    public @ResponseBody Optional<TripRecord> getTrips(@PathVariable(name = "tripId") String tripId) {
        return tripRepository.findById(tripId);
    }

    @PostMapping("/trip/available")
    @Consumes("application/json")
    public @ResponseBody List<TripRecord> availableTrips(@RequestBody Map<String, String> filters) {
         return this.offerService.getAvailableTrips(filters);
    }
}
