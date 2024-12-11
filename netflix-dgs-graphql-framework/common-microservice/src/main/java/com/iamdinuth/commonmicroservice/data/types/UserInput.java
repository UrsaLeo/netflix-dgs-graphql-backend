package com.iamdinuth.commonmicroservice.data.types;

import com.iamdinuth.commonmicroservice.data.types.ClientIdInput;

import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.UUID;

public class UserInput {
    private UUID userId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private ClientIdInput client;

    public UserInput() {
    }

    public UserInput(UUID userId, String firstName, String lastName, String email, String phone,
                     ClientIdInput client) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.client = client;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ClientIdInput getClient() {
        return client;
    }

    public void setClient(ClientIdInput client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "UserInput{" + "userId='" + userId + "'," +"firstName='" + firstName + "'," +"lastName='" + lastName + "'," +"email='" + email + "'," +"phone='" + phone + "'," +"client='" + client + "'" +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.iamdinuth.commonmicroservice.data.types.UserInput that = (com.iamdinuth.commonmicroservice.data.types.UserInput) o;
        return java.util.Objects.equals(userId, that.userId) &&
                java.util.Objects.equals(firstName, that.firstName) &&
                java.util.Objects.equals(lastName, that.lastName) &&
                java.util.Objects.equals(email, that.email) &&
                java.util.Objects.equals(phone, that.phone) &&
                java.util.Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(userId, firstName, lastName, email, phone, client);
    }

    public static com.iamdinuth.commonmicroservice.data.types.UserInput.Builder newBuilder(
    ) {
        return new com.iamdinuth.commonmicroservice.data.types.UserInput.Builder();
    }

    public static class Builder {
        private UUID userId;

        private String firstName;

        private String lastName;

        private String email;

        private String phone;

        private ClientIdInput client;

        public com.iamdinuth.commonmicroservice.data.types.UserInput build() {
            com.iamdinuth.commonmicroservice.data.types.UserInput result = new com.iamdinuth.commonmicroservice.data.types.UserInput();
            result.userId = this.userId;
            result.firstName = this.firstName;
            result.lastName = this.lastName;
            result.email = this.email;
            result.phone = this.phone;
            result.client = this.client;
            return result;
        }

        public com.iamdinuth.commonmicroservice.data.types.UserInput.Builder userId(
                UUID userId) {
            this.userId = userId;
            return this;
        }

        public com.iamdinuth.commonmicroservice.data.types.UserInput.Builder firstName(
                String firstName) {
            this.firstName = firstName;
            return this;
        }

        public com.iamdinuth.commonmicroservice.data.types.UserInput.Builder lastName(
                String lastName) {
            this.lastName = lastName;
            return this;
        }

        public com.iamdinuth.commonmicroservice.data.types.UserInput.Builder email(
                String email) {
            this.email = email;
            return this;
        }

        public com.iamdinuth.commonmicroservice.data.types.UserInput.Builder phone(
                String phone) {
            this.phone = phone;
            return this;
        }

        public com.iamdinuth.commonmicroservice.data.types.UserInput.Builder client(
                ClientIdInput client) {
            this.client = client;
            return this;
        }
    }
}
