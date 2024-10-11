package com.example.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDTO {

    @JsonProperty("accountId")
    private Integer accountId;

    @JsonProperty("username")
    private String username;

    public AccountDTO() {
    }

    public AccountDTO(Integer accountId, String username) {
        this.accountId = accountId;
        this.username = username;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}



