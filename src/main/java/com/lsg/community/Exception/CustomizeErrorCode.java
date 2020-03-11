package com.lsg.community.Exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND(2001,"找不到该问题，要不换个试试！！！"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复！！！"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试！！！"),
    SYS_ERROR(2004,"先让服务君缓下，稍等一会！！！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在！！！"),
    COMMENT_NOY_FOUND(2006,"找不到该评论，要不换个试试！！！"),
    COMMENT_IS_EMPTY(2007,"输入问题不能为空！！！"),
    READ_NOTIFICATION_FAIL(2008,"兄弟你这是读别人的信息！！！"),
    NOTIFICATION_NOT_FOUND(2009,"消息不见了！！！")
    ;


    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code,String message){
        this.message=message;
        this.code=code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
