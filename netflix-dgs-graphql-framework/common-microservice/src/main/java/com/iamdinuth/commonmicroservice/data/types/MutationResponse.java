package com.iamdinuth.commonmicroservice.data.types;

public class MutationResponse {
    private boolean success;

    private String message;

    private int statusCode;

    public MutationResponse() {
    }

    public MutationResponse(boolean success, String message, int statusCode) {
        this.success = success;
        this.message = message;
        this.statusCode = statusCode;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "MutationResponse{" + "success='" + success + "'," +"message='" + message + "'," +"statusCode='" + statusCode + "'" +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.iamdinuth.commonmicroservice.data.types.MutationResponse that = (com.iamdinuth.commonmicroservice.data.types.MutationResponse) o;
        return success == that.success &&
                java.util.Objects.equals(message, that.message) &&
                statusCode == that.statusCode;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(success, message, statusCode);
    }

    public static com.iamdinuth.commonmicroservice.data.types.MutationResponse.Builder newBuilder(
    ) {
        return new com.iamdinuth.commonmicroservice.data.types.MutationResponse.Builder();
    }

    public static class Builder {
        private boolean success;

        private String message;

        private int statusCode;

        public com.iamdinuth.commonmicroservice.data.types.MutationResponse build() {
            com.iamdinuth.commonmicroservice.data.types.MutationResponse result = new com.iamdinuth.commonmicroservice.data.types.MutationResponse();
            result.success = this.success;
            result.message = this.message;
            result.statusCode = this.statusCode;
            return result;
        }

        public com.iamdinuth.commonmicroservice.data.types.MutationResponse.Builder success(
                boolean success) {
            this.success = success;
            return this;
        }

        public com.iamdinuth.commonmicroservice.data.types.MutationResponse.Builder message(
                String message) {
            this.message = message;
            return this;
        }

        public com.iamdinuth.commonmicroservice.data.types.MutationResponse.Builder statusCode(
                int statusCode) {
            this.statusCode = statusCode;
            return this;
        }
    }
}
