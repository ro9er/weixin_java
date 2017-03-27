package com.xlsd.wx.util;

import com.alibaba.fastjson.JSON;
import com.xlsd.wx.exception.WxPayException;
import com.xlsd.wx.model.pay.WxPayJsApiPay;
import com.xlsd.wx.model.pay.WxPayResults;
import com.xlsd.wx.model.pay.WxPayUnifiedOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
@Component
public class JSApiPayUtil {

    @Value("${wx.appid}")
    private String appId;

    @Value("${wx.appsecret}")
    private String appSecret;

    @Value("${wx.key}")
    private String key;

    @Value("${wx.mcid}")
    private String mcId;

    @Value("${wx.paynotifyurl}")
    private String payNotifyUrl;

    /**
     *
     * 统一下单，WxPayUnifiedOrder中out_trade_no、body、total_fee、trade_type必填
     * appid、mchid、spbill_create_ip、nonce_str不需要填入
     * @param WxPayUnifiedOrder $inputObj
     * @param int $timeOut
     * @throws WxPayException
     * @return 成功时返回，其他抛异常
     */
    public WxPayResults unifiedOrder(WxPayUnifiedOrder inputObj, HttpServletRequest request) throws WxPayException {
        String url  = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        //检测必填参数
        if(!inputObj.isOut_trade_noSet()) {
            throw new WxPayException("缺少统一支付接口必填参数out_trade_no！");
        }else if(!inputObj.isBodySet()){
            throw new WxPayException("缺少统一支付接口必填参数body！");
        }else if(!inputObj.isTotal_feeSet()) {
            throw new WxPayException("缺少统一支付接口必填参数total_fee！");
        }else if(!inputObj.isTrade_typeSet()) {
            throw new WxPayException("缺少统一支付接口必填参数trade_type！");
        }

        //关联参数
        if(inputObj.getTrade_type().equals("JSAPI") && !inputObj.isOpenidSet()){
            throw new WxPayException("统一支付接口中，缺少必填参数openid！trade_type为JSAPI时，openid为必填参数！");
        }
        if(inputObj.getTrade_type().equals("NATIVE") && !inputObj.isProduct_idSet()){
            throw new WxPayException("统一支付接口中，缺少必填参数product_id！trade_type为JSAPI时，product_id为必填参数！");
        }

        //异步通知url未设置，则使用配置文件中的url
        if(!inputObj.isNotify_urlSet()){
            inputObj.setNotify_url(payNotifyUrl);//异步通知url
        }

        inputObj.setAppid(appId);//公众账号ID
        inputObj.setMchId(mcId);//商户号
        inputObj.setSpbill_create_ip(request.getRemoteAddr());//终端ip
        //inputObj.SetSpbill_create_ip("1.1.1.1");
        inputObj.setNonceStr(getNonceStr(null));//随机字符串

        //签名
        inputObj.setSign(key);
        String xml = inputObj.toXml();

        HttpResult response = HttpClientUtil.postXml(url, xml, 6000, true);
        if(response.getStatus().equals(200)){
            WxPayResults results = WxPayResults.init(xml, key);
            return results;
        }else{
            throw new WxPayException("获取统一下单请求错误");
        }
    }


    /**
     *
     * 获取jsapi支付的参数
     * @param array $UnifiedOrderResult 统一支付接口返回的数据
     * @throws WxPayException
     *
     * @return json数据，可直接填入js函数作为参数
     */
    public String getJsApiParameters(WxPayResults results) throws WxPayException {
        if(!results.getValues().containsKey("appid")
                ||!results.getValues().containsKey("prepay_id")
                ||results.getValues().get("prepay_id").equals("")){
            throw new WxPayException("参数错误");
        }

        WxPayJsApiPay jsApi = new WxPayJsApiPay();
        jsApi.setAppid(results.getValues().get("appid"));
        Date now = new Date();
        jsApi.setTimeStamp(now.getTime()+"");
        jsApi.setNonceStr(JSApiPayUtil.getNonceStr(null));
        jsApi.setPackage("prepay_id=" + results.getValues().get("prepay_id"));
        jsApi.setSignType("MD5");
        jsApi.setPaySign(jsApi.makeSign(key));
        String parameter = JSON.toJSONString(jsApi.getValues());
        return parameter;
    }

    /**
     *
     * 产生随机字符串，不长于32位
     * @param int $length
     * @return 产生的随机字符串
     */
    public static String getNonceStr(Integer length) {
        length = length == null?32:length;
        Random random = new Random();
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb  = new StringBuilder();
        for (int i = 0; i < length; i++ )  {
            sb.append(chars.charAt(random.nextInt(length)));
        }
        return sb.toString();
    }

}
