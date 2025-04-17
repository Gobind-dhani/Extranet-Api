package com.rms.extranet.model;



import java.util.List;

public class LogoutResponse {
    private List<String> responseCode;
    private String status;

    // Getters and Setters
    public List<String> getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(List<String> responseCode) {
        this.responseCode = responseCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

