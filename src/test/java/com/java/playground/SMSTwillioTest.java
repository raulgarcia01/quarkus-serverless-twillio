package com.java.playground;

import com.java.playground.resource.FreeMessage;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.given;


@QuarkusTest
public class SMSTwillioTest {
    @Test
    public void testSimpleSMS() throws Exception {
        FreeMessage payLoad = new FreeMessage("+18334890658", "+13233282603", "Simple SMS Message from Twillio Testing");
        given().contentType("application/json")
                .accept("application/json")
                .body(payLoad)
                .when().post("/api/messages/freeStyleSMS").then()
                .statusCode(200)
                .contentType("application/json")
                .body(containsString("queued"));
    }

    @Test
    public void testMultimediaSMS() {
        FreeMessage payLoad = new FreeMessage("+18334890658", "+13233282603", "Simple SMS Message from Twillio Testing", "https://th.bing.com/th/id/OIP.W0PPizn-HfWnLo8ML4fzfwAAAA?pid=ImgDet&rs=1");
        given().contentType("application/json")
                .accept("application/json")
                .body(payLoad)
                .when().post("/api/messages/freeStyleSMS").then()
                .statusCode(200)
                .contentType("application/json")
                .body(containsString("queued"));
    }

}
