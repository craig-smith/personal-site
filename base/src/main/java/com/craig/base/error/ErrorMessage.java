package com.craig.base.error;

/**
 * Created by Craig on 3/13/2016.
 */
public class ErrorMessage {

    private String errorType;
    private String errorMessage;

    public ErrorMessage(String errorType, String errorMessage) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
