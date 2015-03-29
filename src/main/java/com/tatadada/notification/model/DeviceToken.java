package com.tatadada.notification.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by mbogheti on 29/03/15.
 */
@Entity(name = "device_token")
public final class DeviceToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String deviceToken;

    @NotNull
    private String applicationIdentifier;

    @NotNull
    private String tatadadaDeviceToken;

//    public DeviceToken(String deviceToken, String applicationIdentifier, String tatadadaDeviceToken) {
//        this.deviceToken = deviceToken;
//        this.applicationIdentifier = applicationIdentifier;
//        this.tatadadaDeviceToken = tatadadaDeviceToken;
//    }

    private DeviceToken(Builder builder) {
        this.deviceToken = builder.deviceToken;
        this.applicationIdentifier = builder.applicationIdentifier;
        this.tatadadaDeviceToken = builder.tatadadaDeviceToken;
    }

    public long getId() {
        return id;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public String getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public String getTatadadaDeviceToken() {
        return tatadadaDeviceToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceToken)) return false;

        DeviceToken that = (DeviceToken) o;

        if (id != that.id) return false;
        if (!deviceToken.equals(that.deviceToken)) return false;
        if (!applicationIdentifier.equals(that.applicationIdentifier)) return false;
        return tatadadaDeviceToken.equals(that.tatadadaDeviceToken);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + deviceToken.hashCode();
        result = 31 * result + applicationIdentifier.hashCode();
        result = 31 * result + tatadadaDeviceToken.hashCode();
        return result;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String deviceToken;
        private String applicationIdentifier;
        private String tatadadaDeviceToken;

        private Builder() {
        }

        public Builder deviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
            return this;
        }

        public Builder applicationIdentifier(String applicationIdentifier) {
            this.applicationIdentifier = applicationIdentifier;
            return this;
        }

        public Builder tatadadaDeviceToken(String tatadadaDeviceToken) {
            this.tatadadaDeviceToken = tatadadaDeviceToken;
            return this;
        }

        public DeviceToken build() {
            DeviceToken build = new DeviceToken(this);

            //TODO check the current bean's validity

            return build;
        }
    }


}
