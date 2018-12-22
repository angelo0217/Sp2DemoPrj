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
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.exMsg = exMsg;
    }

    private int errCode;
    private String errMsg;
    private String exMsg;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getExMsg() {
        return exMsg;
    }

    public void setExMsg(String exMsg) {
        this.exMsg = exMsg;
    }
}
