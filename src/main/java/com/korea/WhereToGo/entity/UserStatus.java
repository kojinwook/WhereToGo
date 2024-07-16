package com.korea.WhereToGo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStatus {
    private String username;
    private boolean online;

    public UserStatus(String username, boolean online) {
        this.username = username;
        this.online = online;
    }
}
