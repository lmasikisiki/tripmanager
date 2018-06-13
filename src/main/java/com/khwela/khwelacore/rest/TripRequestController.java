package com.khwela.khwelacore.rest;

import com.google.gson.Gson;
import com.khwela.khwelacore.commands.DirectRequestCommand;
import com.khwela.khwelacore.commands.RequestTripCommand;
import com.khwela.khwelacore.models.TripRequest;
import com.khwela.khwelacore.repositories.TripRepository;
import com.khwela.khwelacore.repositories.TripRequestRepository;
import com.khwela.khwelacore.services.RequestService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@RestController
 @SuppressWarnings("unused")
public class TripRequestController {

    private final CommandGateway commandGateway;
    private final TripRequestRepository tripRequestRepository;
    private final TripRepository tripRepository;
    private final RequestService requestService;

    private final Gson jsonConverter;

    public TripRequestController(CommandGateway commandGateway, TripRepository tripRepository, TripRequestRepository tripRequestRepository, RequestService requestService, Gson jsonConverter) {
        this.commandGateway = commandGateway;
        this.tripRepository = tripRepository;
        this.tripRequestRepository = tripRequestRepository;
        this.requestService = requestService;
        this.jsonConverter = jsonConverter;
    }

    @PostMapping("/trip/request/{tripId}")
    public CompletableFuture<Object> requestTrip(@RequestBody String tripRequest, @PathVariable(name = "tripId") String tripId) {
        Assert.hasLength(tripId, "Failed to get trip Id from the path");
        Assert.hasLength(tripRequest, "Failed to get tripRequest data");
        DirectRequestCommand directRequestCommand= jsonConverter
                .fromJson(tripRequest, DirectRequestCommand.class);
        directRequestCommand.setTripRequestId(UUID.randomUUID().toString());
        directRequestCommand.setTripId(tripId);
         return commandGateway.send(directRequestCommand);
    }

    @PostMapping("/trip/request/")
    public CompletableFuture<Object> requestTrip(@RequestBody String tripRequest) {
        System.out.println(tripRequest+" \n request recieved...");
        RequestTripCommand requestTripCommand = jsonConverter
                .fromJson(tripRequest, RequestTripCommand.class);
        requestTripCommand.setTripRequestId(UUID.randomUUID().toString());
        return commandGateway.send(requestTripCommand);
    }

    @GetMapping("/trip/request")
    public List<TripRequest> requestTrip() {
        return tripRequestRepository.findAll();
    }
}