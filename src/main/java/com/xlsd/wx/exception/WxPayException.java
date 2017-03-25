package com.xlsd.wx.exception;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class WxPayException extends Exception {
    private String msg;
    public WxPayException(String msg){
        this.msg = msg;
    }

}
