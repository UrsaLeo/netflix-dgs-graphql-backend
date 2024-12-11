package com.iamdinuth.commonmicroservice.data.types;

import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.UUID;

public class ClientIdInput {
    private UUID id;

    public ClientIdInput() {
    }

    public ClientIdInput(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ClientIdInput{" + "id='" + id + "'" +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.iamdinuth.commonmicroservice.data.types.ClientIdInput that = (com.iamdinuth.commonmicroservice.data.types.ClientIdInput) o;
        return java.util.Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }

    public static com.iamdinuth.commonmicroservice.data.types.ClientIdInput.Builder newBuilder(
    ) {
        return new com.iamdinuth.commonmicroservice.data.types.ClientIdInput.Builder();
    }

    public static class Builder {
        private UUID id;

        public com.iamdinuth.commonmicroservice.data.types.ClientIdInput build() {
            com.iamdinuth.commonmicroservice.data.types.ClientIdInput result = new com.iamdinuth.commonmicroservice.data.types.ClientIdInput();
            result.id = this.id;
            return result;
        }

        public com.iamdinuth.commonmicroservice.data.types.ClientIdInput.Builder id(
                UUID id) {
            this.id = id;
            return this;
        }
    }
}
