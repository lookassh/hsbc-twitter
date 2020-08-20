package com.hsbc.digital.social.service;

import com.hsbc.digital.social.web.api.model.NewPost;
import io.restassured.filter.log.LogDetail;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.ValidatableMockMvcResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest
public class DefaultPostServiceTest {
    @Autowired
    private WebApplicationContext context;

    private final RequestPostProcessor bobUser = httpBasic("bob", "123456");
    private final RequestPostProcessor aliceUser = httpBasic("alice", "123456");
    private final RequestPostProcessor johnUser = httpBasic("john", "123456");

    @BeforeEach
    public void init() {
        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    @Test
    void successfullyAddPostTest() {
        addPost(aliceUser, new NewPost("Sunday", "What a wonderful world"))
                .statusCode(200)
                .body("createdBy", is("alice"))
                .body("title", is("Sunday"))
                .body("body", is("What a wonderful world"));
    }

    @Test
    void bodyTooLongAddPostTest() {
        addPost(aliceUser, new NewPost("Sunday", "Sunday is the day of the week between Saturday and Monday. Sunday is a day of rest in most Western countries, and a part of the weekend. In some Eastern countries such as Israel Sunday is a weekday."))
                .statusCode(400);
    }

    @Test
    void titleLongAddPostTest() {
        addPost(aliceUser, new NewPost("Sunday Sunday Sunday Sunday Sunday Sunday Sunday", "Sunday is the day of the week"))
                .statusCode(400);
    }

    @Test
    void simpleIntegrationTest() {
        addPost(johnUser, new NewPost("Monday", "Monday body"));
        addPost(johnUser, new NewPost("Tuesday", "Tuesday body"));
        addPost(johnUser, new NewPost("Wednesday", "Wednesday body"));
        addPost(bobUser, new NewPost("Thursday", "Thursday body"));
        addPost(bobUser, new NewPost("Friday", "Friday body"));

        getWall(johnUser)
                .statusCode(200)
                .body("data.createdBy", everyItem(is("john")))
                .body("data.title", hasItems("Monday", "Tuesday", "Wednesday"))
                .body("data.body", hasItems("Monday body", "Tuesday body", "Wednesday body"))
                .body("pageInfo.page", is(0))
                .body("pageInfo.size", is(50))
                .body("pageInfo.total", is(3));

        getTimeline(johnUser)
                .statusCode(200)
                .body("data", hasSize(0))
                .body("pageInfo.page", is(0))
                .body("pageInfo.size", is(50))
                .body("pageInfo.total", is(0));

        follow(johnUser, "bob").statusCode(200);

        getTimeline(johnUser)
                .body("data.createdBy", everyItem(is("bob")))
                .body("data.title", hasItems("Thursday", "Friday"))
                .body("data.body", hasItems("Thursday body", "Friday body"))
                .body("pageInfo.page", is(0))
                .body("pageInfo.size", is(50))
                .body("pageInfo.total", is(2));

        getTimeline(bobUser)
                .statusCode(200)
                .body("data.createdBy", hasSize(0))
                .body("pageInfo.page", is(0))
                .body("pageInfo.size", is(50))
                .body("pageInfo.total", is(0));
    }

    private ValidatableMockMvcResponse addPost(RequestPostProcessor user, NewPost newPost) {
        return RestAssuredMockMvc
                .given()
                .auth()
                .with(user)
                .body(newPost)
                .contentType(JSON)
                .when()
                .post("/api/v1/posts")
                .then();
    }

    private ValidatableMockMvcResponse getWall(RequestPostProcessor user) {
        return RestAssuredMockMvc
                .given()
                .auth()
                .with(user)
                .contentType(JSON)
                .when()
                .get("/api/v1/wall")
                .then();
    }

    private ValidatableMockMvcResponse getTimeline(RequestPostProcessor user) {
        return RestAssuredMockMvc
                .given()
                .auth()
                .with(user)
                .contentType(JSON)
                .when()
                .get("/api/v1/timeline")
                .then();
    }

    private ValidatableMockMvcResponse follow(RequestPostProcessor user, String userName) {
        return RestAssuredMockMvc
                .given()
                .auth()
                .with(user)
                .contentType(JSON)
                .when()
                .post("/api/v1/follow/{userId}", userName)
                .then();
    }
}