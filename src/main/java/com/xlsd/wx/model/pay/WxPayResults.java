package com.xlsd.wx.model.pay;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

import com.xlsd.wx.exception.WxPayException;

import java.util.Map;

/**
 *
 * 接口调用结果类
 * @author widyhu
 *
 */
public class WxPayResults extends WxPayDataBase
{
    /**
     *
     * 检测签名
     */
    public boolean checkSgn(String key) throws WxPayException {
        //fix异常
        if(this.isSignSet()){
            throw new WxPayException("签名错误！");
        }

        String sign = this.makeSign(key);
        if(this.getSign().equals(sign)){
            return true;
        }
        throw new WxPayException("签名错误！");
    }

    /**
     *
     * 使用数组初始化
     * @param array $array
     */
    public void fromArray(Map<String, String> array)
    {
        this.values = array;
    }

    /**
     *
     * 使用数组初始化对象
     * @param array $array
     * @param 是否检测签名 $noCheckSign
     */
    public static WxPayResults initFromArray(Map<String, String> array, Boolean noCheckSign, String key) throws WxPayException {
        noCheckSign = noCheckSign == null?false:noCheckSign;
        WxPayResults result = new WxPayResults();
        result.fromArray(array);
        if(!noCheckSign){
            result.checkSgn(key);
        }
        return result;
    }

    /**
     *
     * 设置参数
     * @param string $key
     * @param string $value
     */
    public void SetData(String key, String value)
    {
        this.values.put(key, value);
    }

    /**
     * 将xml转为array
     * @param string $xml
     * @throws WxPayException
     */
    public static WxPayResults init(String xml, String key) throws WxPayException {
        WxPayResults result = new WxPayResults();
        result.fromXml(xml);
        //fix bug 2015-06-29
        /*
        if(['return_code'] != 'SUCCESS'){
            return $obj->GetValues();
        }
        */
        result.checkSgn(key);
        return result;
    }
}

