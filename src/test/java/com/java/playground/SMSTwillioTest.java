package com.java.playground;

import com.java.playground.resource.FreeMessage;
import com.java.playground.resource.LoadMessage;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.given;


@QuarkusTest
class SMSTwillioTest {
    @Test
    void testSimpleSMS() throws Exception {
        FreeMessage payLoad = new FreeMessage("+18334890658", "+13233282603", "Simple SMS Message from Twilio Testing");
        given().contentType("application/json")
                .accept("application/json")
                .body(payLoad)
                .when().post("/api/messages/freeStyleSMS").then()
                .statusCode(200)
                .contentType("application/json")
                .body(containsString("queued"));
    }

    @Test
    void testMultimediaSMS() {
        FreeMessage payLoad = new FreeMessage("+18334890658", "+13233282603", "Simple SMS Message from Twillio Testing", "https://campus.harmonytx.org/hsakaty-new/wp-content/uploads/sites/129/2020/08/Back-to-Class.jpg");
        given().contentType("application/json")
                .accept("application/json")
                .body(payLoad)
                .when().post("/api/messages/freeStyleSMS").then()
                .statusCode(200)
                .contentType("application/json")
                .body(containsString("queued"));
    }

    @Test
    void testTemplateSimpleSMS() throws Exception {
        LoadMessage payLoad = new LoadMessage("+18334890658", "+13233282603", "Raul Garcia", "Summer Camp");
        given().contentType("application/json")
                .accept("application/json")
                .body(payLoad)
                .when().post("/api/messages/templateStyleSMS").then()
                .statusCode(200)
                .contentType("application/json")
                .body(containsString("queued"));
    }

    @Test
    void testTemplateMultimediaSMS() {
        LoadMessage payLoad = new LoadMessage("+18334890658", "+13233282603", "Raul Garcia", "Important announcement");
        given().contentType("application/json")
                .accept("application/json")
                .body(payLoad)
                .when().post("/api/messages/templateStyleSMS").then()
                .statusCode(200)
                .contentType("application/json")
                .body(containsString("queued"));
    }

}
