package com.magfin.web.model;

public class UsrResource {
    private String resCode;

    private String resName;

    private String resUrl;

    private String parentResCode;

    private String resType;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode == null ? null : resCode.trim();
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName == null ? null : resName.trim();
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl == null ? null : resUrl.trim();
    }

    public String getParentResCode() {
        return parentResCode;
    }

    public void setParentResCode(String parentResCode) {
        this.parentResCode = parentResCode == null ? null : parentResCode.trim();
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType == null ? null : resType.trim();
    }
}