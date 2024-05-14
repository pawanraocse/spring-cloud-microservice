package com.algo.microservices.orderservice;

import com.algo.microservices.orderservice.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.testcontainers.containers.MySQLContainer;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.3.0");

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mySQLContainer.start();
    }

    @Test
    void shouldCreateProduct() {
        String requestBody = """
            {
                 "skuCode": "Realme_Pro",
                 "price": 90,
                 "quantity": 100
             }
            """;
        InventoryClientStub.stubInventoryCall("Realme_Pro", 100);

        RestAssured.given()
            .contentType("application/json")
            .body(requestBody)
            .when().post("/api/order")
            .then().statusCode(201)
            .body(equalTo("Order placed successfully"));

    }

}
