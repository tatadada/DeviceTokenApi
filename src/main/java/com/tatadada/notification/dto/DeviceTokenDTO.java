package com.tatadada.notification.dto;

import com.google.common.base.Strings;
import com.tatadada.notification.model.DeviceToken;

public class DeviceTokenDTO {

    private String deviceToken;
    private String applicationIdentifier;
    private String tatadadaDeviceToken;

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

    public static DeviceTokenDTO fromEntity(DeviceToken deviceToken) {
        return new DeviceTokenDTO(deviceToken.getDeviceToken(), deviceToken.getApplicationIdentifier(), deviceToken.getTatadadaDeviceToken());
    }
}
