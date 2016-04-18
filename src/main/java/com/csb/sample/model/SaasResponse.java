package com.csb.sample.model;

import java.io.Serializable;

public class SaasResponse implements Serializable {

    private static final long serialVersionUID = 1865085063455593141L;

    private String returnCode;
    private String message;
    private String accountId;
    private String userAccountId;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

}
