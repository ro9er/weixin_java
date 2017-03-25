package com.xlsd.wx.model.pay;
import com.xlsd.wx.exception.WxPayException;
import com.xlsd.wx.util.MessageUtil;
import com.xlsd.wx.util.SignUtil;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class WxPayDataBase {

    protected Map<String, String> values = new HashMap();

    /**
     * 设置签名，详见签名生成算法
     * @param string $value
     **/
    public String setSign(String key)
    {
        String sign = makeSign(key);
        values.put("sign", sign);
        return sign;
    }

    /**
     * 获取签名，详见签名生成算法的值
     * @return 值
     **/
    public String getSign()
    {
        return values.get("sign");
    }

    /**
     * 判断签名，详见签名生成算法是否存在
     * @return true 或 false
     **/
    public boolean isSignSet()
    {
        return values.containsKey("sign");
    }

    /**
     * 输出xml字符
     * @throws WxPayException
     **/
    public String trimoXml() throws WxPayException {
        if(this.values == null || this.values.size() == 0 )
        {
            throw new WxPayException("数组数据异常！");
        }

        StringBuilder xml = new StringBuilder("<xml>");
        Iterator iterator = this.values.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            if(StringUtils.isNumeric(entry.getValue())){
                xml.append("<").append(entry.getKey()).append(">")
                        .append(entry.getValue().toString()).append("</").append(entry.getKey()).append(">");
            }else{
                xml.append("<").append(entry.getKey()).append(">").append("<![CDATA[")
                        .append(entry.getValue().toString()).append("]]></").append(entry.getKey()).append(">");
            }
        }
        xml.append("</xml>");
        return xml.toString();
    }

    /**
     * 将xml转为array
     * @param string $xml
     * @throws WxPayException
     */
    public Map<String, String> fromXml(String xml) throws WxPayException {
        if(StringUtils.isEmpty(xml)){
            throw new WxPayException("xml数据异常！");
        }
        values = MessageUtil.xmlToMap(xml);
        return values;
    }

    /**
     * 格式化参数格式化成url参数
     */
    public String toUrlParams() {
        StringBuilder sb = new StringBuilder();
        Iterator it = values.entrySet().iterator();
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
     * 生成签名
     * @return 签名，本函数不覆盖sign成员变量，如要设置签名需要调用SetSign方法赋值
     */
    public String makeSign(String key)
    {
        //签名步骤一：按字典序排序参数
        String paramStr = SignUtil.getParamString(values);
        //签名步骤二：在string后加入KEY
        paramStr = paramStr + "&key=" + key;
        //签名步骤三：MD5加密
        String md5 = SignUtil.md5StrEncode(paramStr);
        //签名步骤四：所有字符转为大写
        return md5.toUpperCase();
    }

    /**
     * 获取设置的值
     */
    public Map<String, String> getValues() {
        return values;
    }
}
