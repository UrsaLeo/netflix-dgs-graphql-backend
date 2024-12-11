package com.iamdinuth.commonmicroservice.data.types;

import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.UUID;

public class ClientInput {
    private UUID id;

    private String name;

    private String address;

    public ClientInput() {
    }

    public ClientInput(UUID id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ClientInput{" + "id='" + id + "'," +"name='" + name + "'," +"address='" + address + "'" +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.iamdinuth.commonmicroservice.data.types.ClientInput that = (com.iamdinuth.commonmicroservice.data.types.ClientInput) o;
        return java.util.Objects.equals(id, that.id) &&
                java.util.Objects.equals(name, that.name) &&
                java.util.Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name, address);
    }

    public static com.iamdinuth.commonmicroservice.data.types.ClientInput.Builder newBuilder(
    ) {
        return new com.iamdinuth.commonmicroservice.data.types.ClientInput.Builder();
    }

    public static class Builder {
        private UUID id;

        private String name;

        private String address;

        public com.iamdinuth.commonmicroservice.data.types.ClientInput build() {
            com.iamdinuth.commonmicroservice.data.types.ClientInput result = new com.iamdinuth.commonmicroservice.data.types.ClientInput();
            result.id = this.id;
            result.name = this.name;
            result.address = this.address;
            return result;
        }

        public com.iamdinuth.commonmicroservice.data.types.ClientInput.Builder id(
                UUID id) {
            this.id = id;
            return this;
        }

        public com.iamdinuth.commonmicroservice.data.types.ClientInput.Builder name(
                String name) {
            this.name = name;
            return this;
        }

        public com.iamdinuth.commonmicroservice.data.types.ClientInput.Builder address(
                String address) {
            this.address = address;
            return this;
        }
    }
}
