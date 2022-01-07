package aafanasyevaa.tests;

import aafanasyevaa.lombok.LombokUserData;

import static aafanasyevaa.specs.Specs.requestSpec;
import static aafanasyevaa.specs.Specs.responseSpec;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static io.restassured.RestAssured.given;

public class ReqresTest {

    @Test
    @DisplayName("LIST <RESOURCE>")
    public void getResourceList() {
        given()
                .spec(requestSpec)
                .when()
                .get("/unknown")
                .then()
                .spec(responseSpec)
                .log().all()
                .body("data.findAll{it.id}.size", is(6));
    }

    @Test
    @DisplayName("SINGLE USER")
    public void SingleUserTest() {

        String email = "janet.weaver@reqres.in";

        LombokUserData data =
                given()
                        .spec(requestSpec)
                        .when()
                        .get("/users/2")
                        .then()
                        .spec(responseSpec)
                        .log().body()
                        .extract().as(LombokUserData.class);

        assertEquals(email, data.getUser().getEmail());
    }
}
