package com.iamdinuth.commonmicroservice.data.types;

import com.iamdinuth.commonmicroservice.data.types.ClientIdInput;

import java.lang.Double;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.UUID;

public class TwinInput {
    private UUID twinId;

    private String twinName;

    private String description;

    private Double latitude;

    private Double longitude;

    private String uri;

    private com.iamdinuth.commonmicroservice.data.types.ClientIdInput client;

    public TwinInput() {
    }

    public TwinInput(UUID twinId, String twinName, String description, Double latitude,
                     Double longitude, String uri, com.iamdinuth.commonmicroservice.data.types.ClientIdInput client) {
        this.twinId = twinId;
        this.twinName = twinName;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.uri = uri;
        this.client = client;
    }

    public UUID getTwinId() {
        return twinId;
    }

    public void setTwinId(UUID twinId) {
        this.twinId = twinId;
    }

    public String getTwinName() {
        return twinName;
    }

    public void setTwinName(String twinName) {
        this.twinName = twinName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public com.iamdinuth.commonmicroservice.data.types.ClientIdInput getClient() {
        return client;
    }

    public void setClient(com.iamdinuth.commonmicroservice.data.types.ClientIdInput client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "TwinInput{" + "twinId='" + twinId + "'," +"twinName='" + twinName + "'," +"description='" + description + "'," +"latitude='" + latitude + "'," +"longitude='" + longitude + "'," +"uri='" + uri + "'," +"client='" + client + "'" +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.iamdinuth.commonmicroservice.data.types.TwinInput that = (com.iamdinuth.commonmicroservice.data.types.TwinInput) o;
        return java.util.Objects.equals(twinId, that.twinId) &&
                java.util.Objects.equals(twinName, that.twinName) &&
                java.util.Objects.equals(description, that.description) &&
                java.util.Objects.equals(latitude, that.latitude) &&
                java.util.Objects.equals(longitude, that.longitude) &&
                java.util.Objects.equals(uri, that.uri) &&
                java.util.Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(twinId, twinName, description, latitude, longitude, uri, client);
    }

    public static com.iamdinuth.commonmicroservice.data.types.TwinInput.Builder newBuilder(
    ) {
        return new com.iamdinuth.commonmicroservice.data.types.TwinInput.Builder();
    }

    public static class Builder {
        private UUID twinId;

        private String twinName;

        private String description;

        private Double latitude;

        private Double longitude;

        private String uri;

        private com.iamdinuth.commonmicroservice.data.types.ClientIdInput client;

        public com.iamdinuth.commonmicroservice.data.types.TwinInput build() {
            com.iamdinuth.commonmicroservice.data.types.TwinInput result = new com.iamdinuth.commonmicroservice.data.types.TwinInput();
            result.twinId = this.twinId;
            result.twinName = this.twinName;
            result.description = this.description;
            result.latitude = this.latitude;
            result.longitude = this.longitude;
            result.uri = this.uri;
            result.client = this.client;
            return result;
        }

        public com.iamdinuth.commonmicroservice.data.types.TwinInput.Builder twinId(
                UUID twinId) {
            this.twinId = twinId;
            return this;
        }

        public com.iamdinuth.commonmicroservice.data.types.TwinInput.Builder twinName(
                String twinName) {
            this.twinName = twinName;
            return this;
        }

        public com.iamdinuth.commonmicroservice.data.types.TwinInput.Builder description(
                String description) {
            this.description = description;
            return this;
        }

        public com.iamdinuth.commonmicroservice.data.types.TwinInput.Builder latitude(
                Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public com.iamdinuth.commonmicroservice.data.types.TwinInput.Builder longitude(
                Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public com.iamdinuth.commonmicroservice.data.types.TwinInput.Builder uri(
                String uri) {
            this.uri = uri;
            return this;
        }

        public com.iamdinuth.commonmicroservice.data.types.TwinInput.Builder client(
                ClientIdInput client) {
            this.client = client;
            return this;
        }
    }
}

