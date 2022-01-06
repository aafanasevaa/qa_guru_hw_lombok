package aafanasyevaa.tests;

import aafanasyevaa.lombok.token.Token;
import aafanasyevaa.lombok.token.TokenData;
import aafanasyevaa.lombok.user.LombokUserData;
import io.restassured.RestAssured;

import static aafanasyevaa.specs.Specs.requestSpec;
import static aafanasyevaa.specs.Specs.responseSpec;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
                .spec(requestSpec)
                .when()
                .get("/unknown")
                .then()
                .spec(responseSpec)
                .body("total", is(12));
    }

    @Test
    @DisplayName("LOGIN - SUCCESSFUL")
    public void SuccessfulLoginTest() {

        String expected_token = "QpwL5tke4Pnpja7X4";
        String email = "eve.holt@reqres.in";
        String password = "cityslicka";

        TokenData token =
        given()
                .spec(requestSpec)
                .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }")
                .when()
                .post("/login")
                .then()
                .spec(responseSpec)
                .log().body()
                .extract().as(TokenData.class);

        System.out.println(token.getToken());
    }

//    "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }"

}