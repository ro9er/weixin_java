package com.xlsd.wx.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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


}
