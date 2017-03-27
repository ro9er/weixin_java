package com.xlsd.wx.model.pay;

/**
 * Created by Administrator on 2017/3/27 0027.
 */
/**
 *
 * 统一下单输入对象
 * @author widyhu
 *
 */
public class WxPayUnifiedOrder extends WxPayDataBase {
    /**
     * 设置微信分配的公众账号ID
     * @param String value 
     **/
    public void setAppid(String value){
        this.values.put("appid", value);
    }
    /**
     * 获取微信分配的公众账号ID的值
     * @return 值
     **/
    public String getAppid(){
        return this.values.get("appid");
    }
    /**
     * 判断微信分配的公众账号ID是否存在
     * @return true 或 false
     **/
    public boolean isAppidSet()
    {
        return this.values.containsKey("appid");
    }


    /**
     * 设置微信支付分配的商户号
     * @param String value 
     **/
    public void setMchId(String value)
    {
        this.values.put("mch_id", value);
    }
    /**
     * 获取微信支付分配的商户号的值
     * @return 值
     **/
    public String getMchId()
    {
        return this.values.get("mch_id");
    }
    /**
     * 判断微信支付分配的商户号是否存在
     * @return true 或 false
     **/
    public boolean isMchIdSet()
    {
        return this.values.containsKey("mch_id");
    }


    /**
     * 设置微信支付分配的终端设备号，商户自定义
     * @param String value
     **/
    public void setDeviceInfo(String value)
    {
        this.values.put("device_info", value);
    }
    /**
     * 获取微信支付分配的终端设备号，商户自定义的值
     * @return 值
     **/
    public String getDeviceInfo()
    {
        return this.values.get("device_info");
    }
    /**
     * 判断微信支付分配的终端设备号，商户自定义是否存在
     * @return true 或 false
     **/
    public boolean isDeviceInfoSet()
    {
        return this.values.containsKey("device_info");
    }


    /**
     * 设置随机字符串，不长于32位。推荐随机数生成算法
     * @param String value
     **/
    public void setNonceStr(String value)
    {
        this.values.put("nonce_str", value);
    }
    /**
     * 获取随机字符串，不长于32位。推荐随机数生成算法的值
     * @return 值
     **/
    public String getNonceStr()
    {
        return this.values.get("nonce_str");
    }
    /**
     * 判断随机字符串，不长于32位。推荐随机数生成算法是否存在
     * @return true 或 false
     **/
    public boolean isNonceStrSet()
    {
        return this.values.containsKey("nonce_str");
    }

    /**
     * 设置商品或支付单简要描述
     * @param String value
     **/
    public void setBody(String value)
    {
        this.values.put("body", value);
    }
    /**
     * 获取商品或支付单简要描述的值
     * @return 值
     **/
    public String getBody()
    {
        return this.values.get("body");
    }
    /**
     * 判断商品或支付单简要描述是否存在
     * @return true 或 false
     **/
    public boolean isBodySet()
    {
        return this.values.containsKey("body");
    }


    /**
     * 设置商品名称明细列表
     * @param String value
     **/
    public void setDetail(String value)
    {
        this.values.put("detail", value);
    }
    /**
     * 获取商品名称明细列表的值
     * @return 值
     **/
    public String getDetail()
    {
        return this.values.get("detail");
    }
    /**
     * 判断商品名称明细列表是否存在
     * @return true 或 false
     **/
    public boolean isDetailSet()
    {
        return this.values.containsKey("detail");
    }


    /**
     * 设置附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     * @param String value
     **/
    public void setAttach(String value)
    {
        this.values.put("attach", value);
    }
    /**
     * 获取附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据的值
     * @return 值
     **/
    public String getAttach()
    {
        return this.values.get("attach");
    }
    /**
     * 判断附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据是否存在
     * @return true 或 false
     **/
    public boolean isAttachSet()
    {
        return this.values.containsKey("attach");
    }


    /**
     * 设置商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
     * @param String value
     **/
    public void setOut_trade_no(String value)
    {
        this.values.put("out_trade_no", value);
    }
    /**
     * 获取商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号的值
     * @return 值
     **/
    public String getOut_trade_no()
    {
        return this.values.get("out_trade_no");
    }
    /**
     * 判断商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号是否存在
     * @return true 或 false
     **/
    public boolean isOut_trade_noSet()
    {
        return this.values.containsKey("out_trade_no");
    }


    /**
     * 设置符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     * @param String value
     **/
    public void setFee_type(String value)
    {
        this.values.put("fee_type", value);
    }
    /**
     * 获取符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型的值
     * @return 值
     **/
    public String getFee_type()
    {
        return this.values.get("fee_type");
    }
    /**
     * 判断符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型是否存在
     * @return true 或 false
     **/
    public boolean isFee_typeSet()
    {
        return this.values.containsKey("fee_type");
    }


    /**
     * 设置订单总金额，只能为整数，详见支付金额
     * @param String value
     **/
    public void setTotal_fee(String value)
    {
        this.values.put("total_fee", value);
    }
    /**
     * 获取订单总金额，只能为整数，详见支付金额的值
     * @return 值
     **/
    public String getTotal_fee()
    {
        return this.values.get("total_fee");
    }
    /**
     * 判断订单总金额，只能为整数，详见支付金额是否存在
     * @return true 或 false
     **/
    public boolean isTotal_feeSet()
    {
        return this.values.containsKey("total_fee");
    }


    /**
     * 设置APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
     * @param String value
     **/
    public void setSpbill_create_ip(String value)
    {
        this.values.put("spbill_create_ip", value);
    }
    /**
     * 获取APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。的值
     * @return 值
     **/
    public String getSpbill_create_ip()
    {
        return this.values.get("spbill_create_ip");
    }
    /**
     * 判断APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。是否存在
     * @return true 或 false
     **/
    public boolean isSpbill_create_ipSet()
    {
        return this.values.containsKey("spbill_create_ip");
    }


    /**
     * 设置订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     * @param String value
     **/
    public void setTime_start(String value)
    {
        this.values.put("time_start", value);
    }
    /**
     * 获取订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则的值
     * @return 值
     **/
    public String getTime_start()
    {
        return this.values.get("time_start");
    }
    /**
     * 判断订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则是否存在
     * @return true 或 false
     **/
    public boolean isTime_startSet()
    {
        return this.values.containsKey("time_start");
    }


    /**
     * 设置订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则
     * @param String value
     **/
    public void setTime_expire(String value)
    {
        this.values.put("time_expire", value);
    }
    /**
     * 获取订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则的值
     * @return 值
     **/
    public String getTime_expire()
    {
        return this.values.get("time_expire");
    }
    /**
     * 判断订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则是否存在
     * @return true 或 false
     **/
    public boolean isTime_expireSet()
    {
        return this.values.containsKey("time_expire");
    }


    /**
     * 设置商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
     * @param String value
     **/
    public void setGoods_tag(String value)
    {
        this.values.put("goods_tag", value);
    }
    /**
     * 获取商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠的值
     * @return 值
     **/
    public String getGoods_tag()
    {
        return this.values.get("goods_tag");
    }
    /**
     * 判断商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠是否存在
     * @return true 或 false
     **/
    public boolean isGoods_tagSet()
    {
        return this.values.containsKey("goods_tag");
    }


    /**
     * 设置接收微信支付异步通知回调地址
     * @param String value
     **/
    public void setNotify_url(String value)
    {
        this.values.put("notify_url", value);
    }
    /**
     * 获取接收微信支付异步通知回调地址的值
     * @return 值
     **/
    public String getNotify_url()
    {
        return this.values.get("notify_url");
    }
    /**
     * 判断接收微信支付异步通知回调地址是否存在
     * @return true 或 false
     **/
    public boolean isNotify_urlSet()
    {
        return this.values.containsKey("notify_url");
    }


    /**
     * 设置取值如下：JSAPI，NATIVE，APP，详细说明见参数规定
     * @param String value
     **/
    public void setTrade_type(String value)
    {
        this.values.put("trade_type", value);
    }
    /**
     * 获取取值如下：JSAPI，NATIVE，APP，详细说明见参数规定的值
     * @return 值
     **/
    public String getTrade_type()
    {
        return this.values.get("trade_type");
    }
    /**
     * 判断取值如下：JSAPI，NATIVE，APP，详细说明见参数规定是否存在
     * @return true 或 false
     **/
    public boolean isTrade_typeSet()
    {
        return this.values.containsKey("trade_type");
    }


    /**
     * 设置trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
     * @param String value
     **/
    public void setProduct_id(String value)
    {
        this.values.put("product_id", value);
    }
    /**
     * 获取trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。的值
     * @return 值
     **/
    public String getProduct_id()
    {
        return this.values.get("product_id");
    }
    /**
     * 判断trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。是否存在
     * @return true 或 false
     **/
    public boolean isProduct_idSet()
    {
        return this.values.containsKey("product_id");
    }


    /**
     * 设置trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid。
     * @param String value
     **/
    public void setOpenid(String value)
    {
        this.values.put("openid", value);
    }
    /**
     * 获取trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid。 的值
     * @return 值
     **/
    public String getOpenid()
    {
        return this.values.get("openid");
    }
    /**
     * 判断trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid。 是否存在
     * @return true 或 false
     **/
    public boolean isOpenidSet()
    {
        return this.values.containsKey("openid");
    }
}
