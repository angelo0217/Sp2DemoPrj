package com.demo.basic.exception;
/**
 *
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class DemoException  extends  Exception{

    public DemoException(int errCode, String errMsg, String exMsg){
        this.code = errCode;
        this.msg = errMsg;
        this.exMsg = exMsg;
    }

    private int code;
    private String msg;
    private String exMsg;

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

    public String getExMsg() {
        return exMsg;
    }

    public void setExMsg(String exMsg) {
        this.exMsg = exMsg;
    }
}
