package com.lsg.community.enums;

public enum NotificationStatusEnum {
    UNREND(0),
    READ(1)
    ;

    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
