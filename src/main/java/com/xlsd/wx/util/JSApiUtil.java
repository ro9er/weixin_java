package com.xlsd.wx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/25 0025.
 *
 * JSAPI支付实现类
 * 该类实现了从微信公众平台获取code、通过code获取openid和access_token、
 * 生成jsapi支付js接口所需的参数、生成获取共享收货地址所需的参数
 *
 * 该类是微信支付提供的样例程序，商户可根据自己的需求修改，或者使用lib中的api自行开发
 *
 * @author luoyuanqing
 *
 */
@Component
public class JSApiUtil {

    @Value("${wx.appid}")
    private String appId;

    @Value("${wx.appsecret}")
    private String appSecret;

    /**
     *
     * 通过跳转获取用户的openid，跳转流程如下：
     * 1、设置自己需要调回的url及其其他参数，跳转到微信服务器https://open.weixin.qq.com/connect/oauth2/authorize
     * 2、微信服务处理完成之后会跳转回用户redirect_uri地址，此时会带上一些参数，如：code
     *
     * @return 用户的openid
     */
    public String getOpenId(HttpServletRequest request) {
        //通过code获得openid
        if (request.getParameter("code") == null){
            //触发微信返回code码
            String baseUrl = null;
            try {
                baseUrl = URLEncoder.encode(request.getRequestURL().toString() + request.getQueryString(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String url  = createOauthUrlForCode(baseUrl);
            return "redirect:" + url;
        } else {
            String code = request.getParameter("code");
            String openId = getOpenidFromMp(code);
            return openId;
        }
    }

    /**
     *
     * 通过code从工作平台获取openid机器access_token
     * @param String $code 微信跳转回来带上的code
     *
     * @return openid
     */
    public String getOpenidFromMp(String code)
    {
        String url = createOauthUrlForOpenid(code);
        HttpResult result = HttpClientUtil.doGet(url, new HashMap<String, String>(), new HashMap<String, String>(), 5000, true);
        if(result.getStatus().equals(200)){
            JSONObject data = JSON.parseObject(result.getResultString());
            return data.getString("openid");
        }else{
            return null;
        }
    }

    /**
     *
     * 拼接签名字符串
     * @param Map 参数map
     *
     * @return 返回已经拼接好的字符串
     */
    private String toUrlParams(Map<String, String> paramsMap)
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = paramsMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            if(!entry.getKey().equals("sign")){
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            }
        }
        String result = sb.toString();
        return result.length() > 0?result.substring(0, result.length() - 1): result;
    }

    /**
     *
     * 构造获取code的url连接
     * @param String $redirectUrl 微信服务器回跳的url，需要url编码
     *
     * @return 返回构造好的url
     */
    private String createOauthUrlForCode(String redirectUrl) {
        Map<String, String> paramMap = new LinkedHashMap<>();
        paramMap.put("appid", appId);
        paramMap.put("redirect_uri", redirectUrl);
        paramMap.put("response_type", "code");
        paramMap.put("scope", "snsapi_base");
        paramMap.put("state", "STATE#wechat_redirect");
        String bizString = toUrlParams(paramMap);
        return "https://open.weixin.qq.com/connect/oauth2/authorize?" + bizString;
    }
    /**
     *
     * 构造获取open和access_toke的url地址
     * @param String code，微信跳转带回的code
     *
     * @return 请求的url
     */
    private String createOauthUrlForOpenid(String code) {
        Map<String, String> paramMap = new LinkedHashMap<>();
        paramMap.put("appid", appId);
        paramMap.put("secret", appSecret);
        paramMap.put("code", code);
        paramMap.put("grant_type", "authorization_code");
        String bizString = toUrlParams(paramMap);
        return "https://api.weixin.qq.com/sns/oauth2/access_token?" + bizString;
    }

}
