package com.tatadada.notification.dto;

import com.google.common.base.Strings;

public class DeviceTokenDTO {


    private Long id;
    private String deviceToken;
    private String applicationIdentifier;
    private String tatadadaDeviceToken;

    public DeviceTokenDTO(Long id, String deviceToken, String applicationIdentifier, String tatadadaDeviceToken) {
        this.deviceToken = deviceToken;
        this.applicationIdentifier = applicationIdentifier;
        this.tatadadaDeviceToken = tatadadaDeviceToken;
        this.id=id;
    }

    //TODO add a builder please
    public DeviceTokenDTO(String deviceToken, String applicationIdentifier, String tatadadaDeviceToken) {
        this.deviceToken = deviceToken;
        this.applicationIdentifier = applicationIdentifier;
        this.tatadadaDeviceToken = tatadadaDeviceToken;
    }

    public DeviceTokenDTO() {
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public void setApplicationIdentifier(String applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
    }

    public String getTatadadaDeviceToken() {
        return tatadadaDeviceToken;
    }

    public void setTatadadaDeviceToken(String tatadadaDeviceToken) {
        this.tatadadaDeviceToken = tatadadaDeviceToken;
    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "deviceToken='" + deviceToken + '\'' +
                ", applicationIdentifier='" + applicationIdentifier + '\'' +
                ", tatadadaDeviceToken='" + tatadadaDeviceToken + '\'' +
                '}';
    }

    public boolean isValid() {
        return !Strings.isNullOrEmpty(deviceToken) && !Strings.isNullOrEmpty(applicationIdentifier) && !Strings.isNullOrEmpty(tatadadaDeviceToken);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
