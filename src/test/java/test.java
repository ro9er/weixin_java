import com.sun.tools.internal.ws.processor.model.Message;
import com.xlsd.wx.model.TextMessage;
import com.xlsd.wx.util.HttpClientPool;
import com.xlsd.wx.util.HttpClientUtil;
import com.xlsd.wx.util.HttpResult;
import com.xlsd.wx.util.MessageUtil;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
public class test {

    public static void main(String[] args){
        /*
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResult result = new HttpResult();
        int timeout = 3000;
        try{

            httpClient = HttpClientPool.get();
            httpPost = new HttpPost("http://120.77.247.81/wx");
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();//设置请求和传输超时时间
            httpPost.setConfig(requestConfig);
            StringEntity myEntity = new StringEntity("<html>你好啊啊</html>", "utf-8");
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(myEntity);
            HttpResponse response = httpClient.execute(httpPost);
        }catch(Exception ex){
            ex.printStackTrace();
            result.setStatus(504);
        }finally {
        }
        */

        TextMessage message = new TextMessage();
        message.setContent("123");
        message.setFromUserName("321");
        message.setMsgType("<123>");
        message.setToUserName("!@#");
        message.setCreateTime(System.currentTimeMillis());
        System.out.println(MessageUtil.textMessageToXML(message));

        String xmlStr = "<xml><ToUserName><![CDATA[gh_5f0f1b64fb83]]></ToUserName>\n" +
                "<FromUserName><![CDATA[o0JIxswSr7H2_8NKifBfG649ZUxw]]></FromUserName>\n" +
                "<CreateTime>1489672547</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[哈哈]]></Content>\n" +
                "<MsgId>6398094871529524792</MsgId>\n" +
                "</xml>";
        Map<String,String> map = MessageUtil.xmlToMap(xmlStr);
        for(String key : map.keySet()){
            System.out.println(key + ":" + map.get(key));
        }
    }
}
