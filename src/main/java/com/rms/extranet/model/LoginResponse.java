package com.rms.extranet.model;

import java.util.List;

public class LoginResponse {
    private String memberCode;
    private List<String> responseCode;
    private String loginId;
    private String token;
    private String status;

    // Getters and Setters
    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public List<String> getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(List<String> responseCode) {
        this.responseCode = responseCode;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
