package tests.api;


import data.*;
import data.Error;
import data.resources.ColorResource;
import data.resources.UserResource;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;

import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static specification.Specification.*;

public class APITests {

    @Test()
    public void testUniqueAvatars() {
        installSpec(requestSpec(),responseSpec());

        UserResource userResource = given()
                .when()
                .get("/api/users?page=2")
                .then()
                .log().body()
                .extract().body().as(UserResource.class);
        Assert.assertEquals(userResource.getData().stream().
                distinct().
                count(),
                userResource.getData().size());

        deleteSpec();
    }

    @Test
    public void testAuthUser(){
        installSpec(requestSpec(),responseSpec());

        User user = new User("eve.holt@reqres.in", "cityslicka");
        AuthUser authUser = given()
                .body(user)
                .when()
                .post("/api/login")
                .then()
                .log().body()
                .extract().body().as(AuthUser.class);
        Assert.assertNotNull(authUser.getToken());

        deleteSpec();
    }

    @Test
    public void testAuthUserWithoutPassword(){
        installSpec(requestSpec(),responseSpecFailure());
        User user = new User("eve.holt@reqres.in");
        Error error = given()
                .body(user)
                .when()
                .post("/api/login")
                .then()
                .log().body()
                .extract().body().as(Error.class);
        Assert.assertEquals(error.getError(), "Missing password");

        deleteSpec();
    }

    @Test()
    public void testSortByYear() {
        installSpec(requestSpec(),responseSpec());

        ColorResource resource = given()
                .when()
                .get("/api/unknown")
                .then()
                .log().body()
                .extract().body().as(ColorResource.class);
        Assert.assertEquals(resource.getData(),
                resource.getData().stream().
                sorted(Comparator.comparing(Color::getYear)).
                collect(Collectors.toList()));

        deleteSpec();
    }
}
