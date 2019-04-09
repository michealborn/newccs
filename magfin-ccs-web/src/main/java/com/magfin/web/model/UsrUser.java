package com.magfin.web.model;

import java.math.BigDecimal;

public class UsrUser {
    private String userCode;

    private String userName;

    private String password;

    private BigDecimal moneyTest;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public BigDecimal getMoneyTest() {
        return moneyTest;
    }

    public void setMoneyTest(BigDecimal moneyTest) {
        this.moneyTest = moneyTest;
    }
}