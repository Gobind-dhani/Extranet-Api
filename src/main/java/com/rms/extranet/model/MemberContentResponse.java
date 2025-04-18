package com.rms.extranet.model;

import java.util.List;

public class MemberContentResponse {
    private String timestamp;
    private String versionNo;
    private List<String> responseCode;
    private String responseDesc;
    private List<ContentListItem> data;

    // Getters and Setters
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersionNo() {
        return versionNo;
    }
    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public List<String> getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(List<String> responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }
    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public List<ContentListItem> getData() {
        return data;
    }
    public void setData(List<ContentListItem> data) {
        this.data = data;
    }
}
