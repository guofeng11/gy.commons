package gy.commons.dto;

import java.io.Serializable;

/**
 * @ClassName ResultMessage.java
 * @Author guofeng
 * @Description 返回请求包装后信息
 * @Version 1.0.0
 * @Date 2020年05月08日 13:48:00
 */
public class ResultMessage<E> implements Serializable {
    private static final long serialVersionUID = 7249477025009657288L;

    //返回类型的枚举
    public enum ResultType{
        SUCCESS(0),UNAUTHORIZED(1),FAIL(2),EXCEPTION(3),ARGSVALIDATION(4),REFRESHTOKEN(5);

        private int code;
        private ResultType(int code){
            this.code=code;
        }

        public int getCode() {
            return code;
        }
    }
    //结果标识
    private int result;
    //消息
    private String message;
    //数据
    private E data;

    /**
     * 构造方法
     * @param result 返回枚举ResultType类型
     * @param message 返回说明信息 当E 为String类型时 不能省略
     * @param data 请求返回的数据
     */
    public ResultMessage(ResultType result, String message, E data) {
        this.result = result.getCode();
        this.message = message;
        this.data = data;
    }

    public ResultMessage(ResultType result, String message) {
        this(result,message,null);
    }
    public ResultMessage(ResultType result,E data) {
        this(result,result.name(),data);
    }
    public ResultMessage(ResultType result) {
        this(result,result.name(),null);
    }
    public ResultMessage(){}
    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultMessage{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
