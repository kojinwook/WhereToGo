package com.korea.WhereToGo.entity;

import lombok.Data;

@Data
public class UserStatus {
    private String username;
    private boolean online;

    public UserStatus(String username, boolean online) {
        this.username = username;
        this.online = online;
    }
}
