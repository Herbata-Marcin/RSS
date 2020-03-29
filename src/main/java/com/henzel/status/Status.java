package com.henzel.status;

public enum Status {
    
    ERROR("Error"),
    OK("OK");
    
    private String message;

    Status(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}