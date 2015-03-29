package com.tatadada.notification.services;

import com.tatadada.notification.dto.DeviceTokenDTO;
import com.tatadada.notification.model.DeviceToken;
import com.tatadada.notification.model.api.DeviceTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

/**
 * Created by mbogheti on 29/03/15.
 */
@Service
public class DeviceTokenServiceImpl  implements DeviceTokenService{

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceTokenServiceImpl.class);

    private final DeviceTokenRepository deviceTokenRepository;

    @Autowired
    public DeviceTokenServiceImpl(DeviceTokenRepository deviceTokenRepository) {
        this.deviceTokenRepository = deviceTokenRepository;
    }

    @Override
    public DeviceTokenDTO create(DeviceTokenDTO deviceToken) {

        LOGGER.info("Creating a new DeviceToken entry with information: {}", deviceToken);

        DeviceToken persisted = DeviceToken.getBuilder()
                .deviceToken(deviceToken.getDeviceToken())
                .applicationIdentifier(deviceToken.getApplicationIdentifier())
                .tatadadaDeviceToken(deviceToken.getTatadadaDeviceToken())
                .build();

        persisted = deviceTokenRepository.save(persisted);
        LOGGER.info("Created a new todo entry with information: {}", persisted);

        return fromEntity(persisted);

    }

    @Override
    public Collection<DeviceTokenDTO> findAll() {
        LOGGER.info("Finding all DeviceToken entries.");

        Collection<DeviceToken> deviceTokens = deviceTokenRepository.findAll();

        LOGGER.info("Found {} DeviceToken entries", deviceTokens.size());

        return fromBeanCollection(deviceTokens);
    }

    private DeviceTokenDTO fromEntity(DeviceToken deviceToken) {
        return new DeviceTokenDTO(deviceToken.getId(), deviceToken.getDeviceToken(), deviceToken.getApplicationIdentifier(), deviceToken.getTatadadaDeviceToken());
    }

    private Collection<DeviceTokenDTO> fromBeanCollection(Collection<DeviceToken> channels) {
        return channels.stream()
                .map(this::fromEntity)
                .collect(toList());
    }
}
