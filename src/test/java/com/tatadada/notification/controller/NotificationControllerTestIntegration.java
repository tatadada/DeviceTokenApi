package com.tatadada.notification.controller;

import com.google.common.net.MediaType;
import com.jayway.restassured.RestAssured;
import com.tatadada.notification.NotificationApplication;
import com.tatadada.notification.dto.DeviceTokenDTO;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.CREATED;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NotificationApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@ActiveProfiles("test")
public class NotificationControllerTestIntegration {


//    @Autowired
//    private ItemRepository repository;

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setUp() {
//        repository.deleteAll();
//        firstItem = repository.save(FIRST_ITEM);
//        secondItem = repository.save(SECOND_ITEM);
        RestAssured.port = serverPort;
    }


    @Test
    public void should_save_andSend_back_the_item() {
        DeviceTokenDTO dto = new DeviceTokenDTO("devicetoken", "applicationIdentifier", "tatadadaDeviceToken");
        given().request().body(dto).header("Content-Type", MediaType.JSON_UTF_8.toString()).when().
                put("/ios/devicetoken").
                then().
                statusCode(CREATED.value()).
                body("applicationIdentifier", Matchers.is("applicationIdentifier")).
                body("deviceToken", Matchers.is("devicetoken"));
    }

}
