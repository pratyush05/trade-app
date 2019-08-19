package com.example.pratyush.response;

public class ResponseStatus {
    private int statusCode;
    private String statusMessage;

    public static final ResponseStatus OK = new ResponseStatus(
            200,
            "OK"
    );
    public static final ResponseStatus CREATED = new ResponseStatus(
            201,
            "Created"
    );
    public static final ResponseStatus NO_CONTENT = new ResponseStatus(
            204,
            "No Content"
    );
    public static final ResponseStatus NOT_MODIFIED = new ResponseStatus(
            304,
            "Not Modified"
    );
    public static final ResponseStatus BAD_REQUEST = new ResponseStatus(
            400,
            "Bad Request"
    );
    public static final ResponseStatus UNAUTHORIZED = new ResponseStatus(
            401,
            "Unauthorized"
    );
    public static final ResponseStatus FORBIDDEN = new ResponseStatus(
            403,
            "Forbidden"
    );
    public static final ResponseStatus NOT_FOUND = new ResponseStatus(
            404,
            "Not Found"
    );
    public static final ResponseStatus CONFLICT = new ResponseStatus(
            409,
            "Conflict"
    );
    public static final ResponseStatus INTERNAL_SERVER_ERROR = new ResponseStatus(
            500,
            "Internal Server Error"
    );
    public static final ResponseStatus NOT_IMPLEMENTED = new ResponseStatus(
            501,
            "Not Implemented"
    );

    private ResponseStatus(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

}
