package com.lsg.community.enums;

public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论")
    ;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    private int type;
    private String name;

    NotificationTypeEnum(int status, String name) {
        this.type = status;
        this.name = name;
    }

    public static  String nameOfType(int type){
        for (NotificationTypeEnum value : NotificationTypeEnum.values()) {
            if (value.getType()==type){
                return value.getName();
            }
        }
        return "";
    }
}
