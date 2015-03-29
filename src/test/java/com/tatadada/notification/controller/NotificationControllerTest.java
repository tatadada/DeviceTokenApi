package com.tatadada.notification.controller;

import com.tatadada.notification.TatadadaBaseTest;
import com.tatadada.notification.dto.DeviceTokenDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


public class NotificationControllerTest extends TatadadaBaseTest {
    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void create() throws Exception {
        mockMvc.perform(put("/ios/devicetoken")
                .content(this.json(getNotificationDtoWithData()))
                .contentType(contentType))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createWithWrongJson() throws Exception {
        mockMvc.perform(put("/ios/devicetoken")
                .content(this.json(new DeviceTokenDTO()))
                .contentType(contentType))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void should_return_400() throws Exception {
        mockMvc.perform(put("/ios/devicetoken")
                .contentType(contentType))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    private DeviceTokenDTO getNotificationDtoWithData() {
        final DeviceTokenDTO deviceTokenDTO = new DeviceTokenDTO();
        deviceTokenDTO.setApplicationIdentifier("ApplicationIdentifier");
        deviceTokenDTO.setDeviceToken("DeviceToken");
        deviceTokenDTO.setTatadadaDeviceToken("tatadadaDeviceToken");

        return deviceTokenDTO;
    }


    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }


}