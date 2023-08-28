package com.example.exam_board.entity.constant;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    public String value;
    UserRole(String value){
        this.value = value;
    }
}
