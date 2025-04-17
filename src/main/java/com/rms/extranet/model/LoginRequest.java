package com.rms.extranet.model;


public class LoginRequest {
    private String memberCode;
    private String loginId;
    private String password; // This should be AES-256 encrypted

    // Getters and Setters
    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

