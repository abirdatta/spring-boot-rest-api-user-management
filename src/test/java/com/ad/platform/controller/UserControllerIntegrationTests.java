package com.ad.platform.controller;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ad.platform.PropertyManagementApplication;
import com.ad.platform.model.User;
import com.ad.platform.model.UserType;
import com.google.gson.Gson;

import io.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = PropertyManagementApplication.class)
public class UserControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Test
    public void testBasicAuth() {
        given().auth().preemptive().basic("invalid", "invalid").when()
                .get(String.format("http://localhost:%s/user/list", port)).then().statusCode(401);
        given().auth().preemptive().basic("invalid", "invalid").when()
                .get(String.format("http://localhost:%s/user/roles", port)).then().statusCode(401);
    }

    @Test
    public void testUserList() {
        given().auth().preemptive().basic("abir", "password").when()
                .get(String.format("http://localhost:%s/user/list", port)).then().statusCode(200)
                .body(containsString("Abir"));
    }

    @Test
    public void testUserRoles() {
        given().auth().preemptive().basic("abir", "password").when()
                .get(String.format("http://localhost:%s/user/roles", port)).then().statusCode(200)
                .body(containsString("owner"));
    }

    @Test
    public void testAddUser() {

        UserType role = new UserType();
        role.setUserTypeId(1);

        User user = new User("Lionel", "", "Messi", "b@c.com", "password", "Barcelona", "Spain", role);
        Gson gson = new Gson();
        String jsonInString = gson.toJson(user);
        given().auth().preemptive().basic("abir", "password")
        .contentType("application/json")
        .body(jsonInString)
        .when()
        .post(String.format("http://localhost:%s/user/add", port)).then().statusCode(200);
    }
}
