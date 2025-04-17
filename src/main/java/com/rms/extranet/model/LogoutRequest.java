package com.rms.extranet.model;


public class LogoutRequest {
    private String memberCode;
    private String loginId;

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
}

