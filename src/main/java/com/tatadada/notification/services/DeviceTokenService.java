package com.tatadada.notification.services;

import com.tatadada.notification.dto.DeviceTokenDTO;

import java.util.Collection;

/**
 * Created by mbogheti on 29/03/15.
 */
public interface DeviceTokenService {
    DeviceTokenDTO create(DeviceTokenDTO deviceToken);

    Collection<DeviceTokenDTO> findAll();
}
