package com.lsg.community.Exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("找不到该问题，要不换个试试!!!");
    private String message;

    CustomizeErrorCode(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
