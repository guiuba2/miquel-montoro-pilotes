package com.jagaad.pilotes.controllers;


import com.jagaad.pilotes.model.Client;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientControllerIT {

    Client client = new Client();
    private int orderId;
    @LocalServerPort
    private int randomPort;

    @BeforeAll
    public void setUpTest() {
        //    System.out.println(randomPort);
        RestAssured.port = randomPort;
        client.setEmail("phil@hotmail.com");
        client.setPhoneNumber("5511912365412");
        client.setFirstName("Phillip");
        client.setLastName("Angus");
        client.setPassword("01041964");
    }


    @Test
    @Order(1)
    void whenRegisterThenCheckIsRegistered() {

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE) //ContentType.TEXT
                .body(client)
                .post("/api/register")
                .then()
                //    .statusCode(HttpStatus.CREATED.value())
                //  .assertThat()
                .statusCode(200);
        //     .body("message", Matchers.equalTo("Client registered successfully"));
             /*   .assertThat()
                .extract().response().body().prettyPrint().matches("Client registered successfully")*/
        ;
        //       .body("message", Matchers.equalTo("Client registered successfully"));

    }

    @Test
    @Order(2)
    void whenFindClientThenCheckResult() {

        RestAssured.given()
                .when()
                .auth().basic("admin@admin.com", "admin")
                //   .contentType(MediaType.APPLICATION_JSON)
                .get("/api/findClient/{name}", client.getFirstName())
                .then()
                .assertThat()
                .statusCode(200)
                .body("phoneNumber[0]", Matchers.equalTo(client.getPhoneNumber()));

        //   .extract().response().body().prettyPrint();

    }

    @Test
    @Order(3)
    void whenPlaceOrderThenCheckStatus() {
        orderId = RestAssured.given()
                .when()
                .param("phoneNumber", client.getPhoneNumber())
                .param("deliveryAddress", "1234 NW Bobcat Lane")
                .param("numberOfPilotes", 15)
                .post("/api/placeOrder/")  //client.getPhoneNumber() "5511912365412", 15
                .then()
                .statusCode(200)
                .body("numberOfPilotes", Matchers.equalTo(15))
                .body("deliveryAddress", Matchers.equalTo("1234 NW Bobcat Lane"))
                .extract()
                .path("orderId");
                //.response().body().prettyPeek().prettyPrint();

    }

    @Test
    @Order(4)
    void whenUpdateOrderCheckStatus() {
        RestAssured.given()
                .when()
                .param("deliveryAddress", "500 5TH AVE")
                .param("numberOfPilotes", 10)
                .put("/api/updateOrder/{orderId}", orderId)
                .then()
                .statusCode(200)
                .body("numberOfPilotes", Matchers.equalTo(10))
                .body("deliveryAddress", Matchers.equalTo("500 5TH AVE"));

    }



    @Test
    @Order(5)
    void whenDeleteClientCheckIsDeleted() {
        RestAssured.given()
                .when()

                .delete("/api/deleteClient/{phoneNumber}", client.getPhoneNumber()) //  "5511912365412"
                .then()
                //       .statusCode(200);

                .statusCode(HttpStatus.OK.value());

        //       .extract().response().body().prettyPrint().);

        //      .body("message", Matchers.equalTo("Client deleted successfully"));

    }


}