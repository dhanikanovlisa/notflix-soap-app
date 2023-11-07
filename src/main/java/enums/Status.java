package enums;

public enum Status {
    PENDING("pending"),
    ACCEPTED("accepted"),
    REJECTED("rejected");

    private final String statusCode;

    Status(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public static Status fromStatusCode(String value) {
        for (Status status : values()) {
            if (status.getStatusCode().equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
    }
}