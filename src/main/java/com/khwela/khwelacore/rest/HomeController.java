package com.khwela.khwelacore.rest;


import com.khwela.khwelacore.models.TripRecord;
import com.khwela.khwelacore.repositories.TripRepository;
import com.khwela.khwelacore.trips.TripOfferedCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class HomeController {

    private final CommandGateway commandGateway;
    private TripRepository tripRepository;
    public HomeController(CommandGateway commandGateway, TripRepository tripRepository){
        this.commandGateway= commandGateway;
        this.tripRepository=tripRepository;
    }

    @GetMapping("/home")
    public  String Home(){
        commandGateway.send(new TripOfferedCommand(UUID.randomUUID().toString(),"Lizo","Flagstaff","Durban"));
        return "Hello World";
    }

    @GetMapping("/list")
    public List<TripRecord> get(){
        return  this.tripRepository.findAll();
    }

}
