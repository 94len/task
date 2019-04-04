package com.erafollower.task.util.response;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 返回实体
 * @author jaybril-pro
 * @date 2019-01-01
 * @param <T>
 */
public class ResponseModel<T> implements Serializable {
    private static final long serialVersionUID = -1241360949457314497L;
    private int status;
    private T result;
    private String message;
    private boolean success=false;

    public ResponseModel() {

        RequestAttributes requestAttributes=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if(requestAttributes!=null){
            HttpServletResponse response =((ServletRequestAttributes) requestAttributes).getResponse();
            response.setCharacterEncoding("UTF-8");
        }
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "status=" + status +
                ", result=" + result +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
