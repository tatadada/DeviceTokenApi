package com.tatadada.notification.controller;

import com.tatadada.notification.dto.DeviceTokenDTO;
import com.tatadada.notification.services.DeviceTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mbogheti on 22/03/15.
 */
@RestController
public class NotificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

    private final DeviceTokenService deviceTokenService;

    @Autowired
    public NotificationController(DeviceTokenService deviceTokenService) {
        this.deviceTokenService = deviceTokenService;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/ios/devicetoken")
    public
    @ResponseBody
    ResponseEntity<DeviceTokenDTO> create(@RequestBody DeviceTokenDTO notification) {
        LOGGER.debug("%s", notification);

        if(!notification.isValid()) {
            return new ResponseEntity<>(notification, HttpStatus.BAD_REQUEST);
        }

        final DeviceTokenDTO deviceTokenDTO = deviceTokenService.create(notification);
        return new ResponseEntity<>(deviceTokenDTO, HttpStatus.CREATED);
    }
}
