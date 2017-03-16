package com.xlsd.wx.util;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * Created by xh on 2016/10/31.
 */
public class HttpClientPool {

    private static PoolingHttpClientConnectionManager cm;

    private static CloseableHttpClient client;

    static {
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(500);
        cm.setDefaultMaxPerRoute(200);
        client = HttpClients.custom().setMaxConnPerRoute(200).setConnectionManager(cm).build();
    }

    private HttpClientPool() {
    }

    public static CloseableHttpClient get() {
        return client;
    }
}
