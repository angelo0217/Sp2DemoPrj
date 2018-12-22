package com.demo.basic.vo;

import java.io.Serializable;

/**
 * 系統API標準Response
 * 因為系統整合Redis 若未實作Serializable會報下面錯誤
 * 所以被泛型吃進來的都需要實作Serializable
 * org.springframework.data.redis.serializer.SerializationException: Cannot serialize; nested exception is org.springframework.core.serializer.support.SerializationFailedException:
 *
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class Response<T> implements Serializable {
    private int code;
    private String msg;
    private String path;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
