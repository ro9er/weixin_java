package com.xlsd.wx.model.pay;

/**
 *
 * 提交JSAPI输入对象
 * @author widyhu
 *
 */
public class WxPayJsApiPay extends WxPayDataBase
{
    /**
     * 设置微信分配的公众账号ID
     * @param string value 
     **/
    public void setAppid(String value)
    {
        this.values.put("appId", value);
    }
    /**
     * 获取微信分配的公众账号ID的值
     * @return 值
     **/
    public String getAppid()
    {
        return this.values.get("appId");
    }
    /**
     * 判断微信分配的公众账号ID是否存在
     * @return true 或 false
     **/
    public boolean isAppidSet()
    {
        return this.values.containsKey("appId");
    }


    /**
     * 设置支付时间戳
     * @param string value 
     **/
    public void setTimeStamp(String value)
    {
        this.values.put("timeStamp", value);
    }
    /**
     * 获取支付时间戳的值
     * @return 值
     **/
    public String getTimeStamp()
    {
        return this.values.get("timeStamp");
    }
    /**
     * 判断支付时间戳是否存在
     * @return true 或 false
     **/
    public boolean isTimeStampSet()
    {
        return this.values.containsKey("timeStamp");
    }

    /**
     * 随机字符串
     * @param string value 
     **/
    public void setNonceStr(String value)
    {
        this.values.put("nonceStr", value);
    }
    /**
     * 获取notify随机字符串值
     * @return 值
     **/
    public String getReturn_code()
    {
        return this.values.get("nonceStr");
    }
    /**
     * 判断随机字符串是否存在
     * @return true 或 false
     **/
    public boolean isReturn_codeSet()
    {
        return this.values.containsKey("nonceStr");
    }


    /**
     * 设置订单详情扩展字符串
     * @param string value 
     **/
    public void setPackage(String value)
    {
        this.values.put("package", value);
    }
    /**
     * 获取订单详情扩展字符串的值
     * @return 值
     **/
    public String getPackage()
    {
        return this.values.get("package");
    }
    /**
     * 判断订单详情扩展字符串是否存在
     * @return true 或 false
     **/
    public boolean isPackageSet()
    {
        return this.values.containsKey("package");
    }

    /**
     * 设置签名方式
     * @param string value 
     **/
    public void setSignType(String value)
    {
        this.values.put("signType", value);
    }
    /**
     * 获取签名方式
     * @return 值
     **/
    public String getSignType()
    {
        return this.values.get("signType");
    }
    /**
     * 判断签名方式是否存在
     * @return true 或 false
     **/
    public boolean isSignTypeSet()
    {
        return this.values.containsKey("signType");
    }

    /**
     * 设置签名方式
     * @param string value 
     **/
    public void setPaySign(String value)
    {
        this.values.put("paySign", value);
    }
    /**
     * 获取签名方式
     * @return 值
     **/
    public String getPaySign()
    {
        return this.values.get("paySign");
    }
    /**
     * 判断签名方式是否存在
     * @return true 或 false
     **/
    public boolean isPaySignSet()
    {
        return this.values.containsKey("paySign");
    }
}
