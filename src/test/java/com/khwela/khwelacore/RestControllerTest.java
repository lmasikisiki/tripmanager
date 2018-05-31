package com.khwela.khwelacore;

import com.google.gson.Gson;
import com.khwela.khwelacore.commands.OfferTripCommand;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;


//@RunWith(SpringRunner.class)//
//@SpringBootTestootTest
public class RestControllerTest {


    @Before
    public void name() {
        RestAssured.baseURI = "http://localhost:8080/";

    }

    @Test
    public void Post_NewTripOffer_ShouldReturn() {
        Gson gson = new Gson();
        with()
                .contentType("application/json")
                .body(gson.toJson(new OfferTripCommand(UUID.randomUUID().toString(),
                        "Lizo",
                        "Bizana",
                        "Flagstaff",
                        3,
                        LocalDate.now())))
                .when()
                .post("trip/offer")
                .then()
                .assertThat()
                .body("$", equalTo(""));

    }


}
