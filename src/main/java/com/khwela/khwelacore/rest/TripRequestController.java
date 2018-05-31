package com.khwela.khwelacore.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.khwela.khwelacore.commands.RequestTripCommand;
import com.khwela.khwelacore.commons.DateDeSerializerAdaper;
import com.khwela.khwelacore.commons.DateSerializerAdaper;
import com.khwela.khwelacore.models.TripRequest;
import com.khwela.khwelacore.repositories.TripRepository;
import com.khwela.khwelacore.repositories.TripRequestRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@RestController
@SuppressWarnings("unused")
 public class TripRequestController {

    private final CommandGateway commandGateway;
    private final TripRequestRepository tripRequestRepository;
    private final TripRepository tripRepository;

    private final Gson jsonConverter;

    public TripRequestController(CommandGateway commandGateway, TripRepository tripRepository, TripRequestRepository tripRequestRepository, Gson jsonConverter) {
        this.commandGateway = commandGateway;
        this.tripRepository = tripRepository;
        this.tripRequestRepository = tripRequestRepository;
        this.jsonConverter= jsonConverter;
    }

    @PostMapping("/trip/request")
    public CompletableFuture<Object> requestTrip(@RequestBody String tripRequest) {
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