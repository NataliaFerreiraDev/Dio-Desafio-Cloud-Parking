package br.com.dio.parking.controller;

import br.com.dio.parking.model.dto.ParkingCreateDTO;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest extends AbstractContainerBase {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest(){
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("license[0]", Matchers.equalTo("ABC-1234"));
    }

//    @Test
//    void whenFindAllThenCheckResult() {
//        RestAssured.given()
//                .auth().basic("user", "Dio@123456")
//                .when()
//                .get("/parking")
//                .then()
//                .statusCode(HttpStatus.OK.value());
//    }

    @Test
    void whenCreateThenCheckIsCreated() {
        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("AMARELO");
        createDTO.setLicense("ABC-1234");
        createDTO.setModel("BRASILIA");
        createDTO.setState("SP");

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("ABC-1234"))
                .body("color", Matchers.equalTo("AMARELO"))
                .body("model", Matchers.equalTo("BRASILIA"))
                .body("state", Matchers.equalTo("SP"));
    }

    @Test
    void findById() {
    }



    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void exit() {
    }
}