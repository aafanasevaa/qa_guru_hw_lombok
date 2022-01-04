package aafanasyevaa.tests;

import io.restassured.RestAssured;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ReqresTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    @DisplayName("LIST <RESOURCE>")
    public void getResourceList() {
        given()
                .when()
                .get("api/unknown")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }


    @Test
    @DisplayName("LOGIN - SUCCESSFUL")
    public void SuccessfulLoginTest() {

        String expected_token = "QpwL5tke4Pnpja7X4";
        Response response =
        given()
                .contentType(JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }")
                .when()
                .post("api/login")
                .then()
                .statusCode(200)
                .extract().response();

    }

}