package com.erafollower.task.util.response;

import org.springframework.http.HttpStatus;


/**
 * 请求返回工具类
 * @author jaybril-pro
 * @date 2019-01-01
 */
public class ResponseHelper {

    public ResponseHelper() {
    }

    /**
     * 请求返回内容
     * @param result 返回给前端的vo
     * @param <T> 返回给前端的vo类型
     * @return ResponseModel
     */
    public static <T> ResponseModel<T> buildResponseModel(T result) {
        ResponseModel response = new ResponseModel();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setSuccess(true);
        response.setResult(result);
        return response;
    }


    /**
     * 请求的资源不存在
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseModel<T> NOT_FOUND(String message) {
        ResponseModel response = new ResponseModel();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(message);
        return response;
    }

    /**
     * 未登录
     * @param <T>
     * @return
     */
    public static <T> ResponseModel<T> noLogin() {
        ResponseModel response = new ResponseModel();
        response.setStatus(StatusCodeEnum.NO_LOGIN.getStatus());
        response.setMessage(StatusCodeEnum.NO_LOGIN.getMessage());
        return response;
    }

    /**
     * 未登录
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseModel<T> noLogin(String message) {
        ResponseModel response = new ResponseModel();
        response.setStatus(StatusCodeEnum.NO_LOGIN.getStatus());
        response.setMessage(message);
        return response;
    }
    /**
     *系统出错
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseModel<T> systemError(String message) {
        ResponseModel response = new ResponseModel();
        response.setStatus(StatusCodeEnum.SYSTEM_ERROR.getStatus());
        response.setMessage(message);
        return response;
    }
    /**
     * 系统出错
     * @return ResponseModel
     */
    public static ResponseModel systemError() {
        ResponseModel response = new ResponseModel();
        response.setStatus(StatusCodeEnum.SYSTEM_ERROR.getStatus());
        response.setMessage(StatusCodeEnum.SYSTEM_ERROR.getMessage());
        return response;
    }
}
