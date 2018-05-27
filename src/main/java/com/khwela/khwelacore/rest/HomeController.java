package com.khwela.khwelacore.rest;


import com.khwela.khwelacore.models.TripRecord;
import com.khwela.khwelacore.models.TripRequest;
import com.khwela.khwelacore.repositories.TripRepository;
import com.khwela.khwelacore.repositories.TripRequestRepository;
import com.khwela.khwelacore.trips.TripCancelledCommand;
import com.khwela.khwelacore.trips.TripOfferedCommand;
import com.khwela.khwelacore.trips.TripRequestedCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class HomeController {

    private final CommandGateway commandGateway;
    private final TripRequestRepository tripRequestRepository;
    private final TripRepository tripRepository;

    public HomeController(CommandGateway commandGateway, TripRepository tripRepository, TripRequestRepository tripRequestRepository){
        this.commandGateway = commandGateway;
        this.tripRepository = tripRepository;
        this.tripRequestRepository  =  tripRequestRepository;
    }



    @PostMapping("/trip/offer")
    public String offerTrip(@RequestBody @ModelAttribute TripRecord tripRecord){

         return tripRecord.toString();
    }

    @GetMapping("/home")
    public  String Home(){
        String id= UUID.randomUUID().toString();
        commandGateway.send(new TripOfferedCommand(id,UUID.randomUUID().toString(),"Lizo","Flagstaff","Durban",3, new Date()));
        return "Hello World";
    }

    //Make it post
    @GetMapping("/cancel/{id}")
    public CompletableFuture<Object> update(@PathVariable(name = "id") String id){
        String ids= UUID.randomUUID().toString();
        return commandGateway.send(new TripCancelledCommand(ids,"",""));
    }

    @GetMapping("/new")
    public CompletableFuture<Object> makerequest(){
        String id= UUID.randomUUID().toString();
        return commandGateway.send(new TripRequestedCommand(id,"aa","ss", "ss",new Date()));
     }

    @GetMapping("/request")
    public List<TripRequest> requests(){
        return tripRequestRepository.findAll();

    }



    @GetMapping("/list")
    public List<TripRecord> get(){
        return  this.tripRepository.findAll();
    }

}
