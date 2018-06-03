package com.khwela.khwelacore.rest;

import com.google.gson.Gson;
import com.khwela.khwelacore.commands.OfferTripCommand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class HomeControllerTest {


    private Gson gson= new Gson();
    @InjectMocks
    private  HomeController homeController;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc= MockMvcBuilders
                .standaloneSetup(homeController)
                .build();
    }


     public void offerTrip() throws Exception {
        Gson g = new Gson();
        mockMvc.perform(post("/trip")
                    .content(g.toJson(new OfferTripCommand("123","Liz"
                            ,"ll","aaa",3,LocalDate.now())))
                    .contentType("application/json0")
                    .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

    }

}