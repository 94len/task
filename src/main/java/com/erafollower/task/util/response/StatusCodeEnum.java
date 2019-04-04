package com.erafollower.task.util.response;

/**
 * 自定义状态码枚举
 *
 * @author jaybril
 * @date 2019-03-17 11:00
 **/
public enum  StatusCodeEnum {
    /**
     * 系统未知错误
     */
    SYSTEM_ERROR(-1,"系统错误"),
    /**
     * 未登录
     */
    NO_LOGIN(-999,"请先登录"),
    /**
     * 非法的token
     */
    BAD_TOKEN(-2,"非法的token"),


    ;

    private Integer status;
    private String message;

    StatusCodeEnum(Integer status,String message){
        this.status=status;
        this.message=message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
