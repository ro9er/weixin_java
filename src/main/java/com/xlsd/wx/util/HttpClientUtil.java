package com.xlsd.wx.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/*
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtil {
	private static String CHARSET = "utf-8";
	
	private static X509TrustManager tm = new X509TrustManager() {  
        @Override  
        public void checkClientTrusted(X509Certificate[] chain,  
                String authType) throws CertificateException {  
        }  
        @Override  
        public void checkServerTrusted(X509Certificate[] chain,  
                String authType) throws CertificateException {  
        }  
        @Override  
        public X509Certificate[] getAcceptedIssuers() {  
            return null;  
        }  
	};

	public static HttpResult postXml(String url,String xml, Integer timeout, boolean isHttps){
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		HttpResult result = new HttpResult();
		timeout = timeout == null?3000:timeout;
		try{
			if(isHttps){
				httpClient = createSSLClient();
			}else{
				httpClient = HttpClientPool.get();
			}
			httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			// Construct a string entity
			StringEntity entity = new StringEntity(xml);
			// Set XML entity
			httpPost.setEntity(entity);
			// Set content type of request header
			httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
			HttpResponse response = httpClient.execute(httpPost);
			doParseResult(response, result);
		}catch(Exception ex){
			ex.printStackTrace();
			result.setStatus(504);
		}finally{
			doRelease(isHttps, httpClient, httpPost);
		}
		return result;
	}
    
	public static HttpResult doPost(String url,Map<String,String> paraMap,Map<String,String> headerMap, Integer timeout){
		return doPost(url, paraMap, headerMap,timeout, false);
	}
	public static HttpResult doPost(String url,Map<String,String> paraMap,Map<String,String> headerMap, Integer timeout, boolean isHttps){
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		HttpResult result = new HttpResult();
		timeout = timeout == null?3000:timeout;
		try{
			if(isHttps){
				httpClient = createSSLClient();
			}else{
				httpClient = HttpClientPool.get();
			}
			httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			//设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator<Entry<String, String>> iterator = paraMap.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String,String> elem = iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
			}
			
			iterator = headerMap.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String,String> elem = (Entry<String, String>) iterator.next();
				httpPost.setHeader(elem.getKey(), elem.getValue());
			}
			if(list.size() > 0){
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,CHARSET);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			doParseResult(response, result);
		}catch(Exception ex){
			ex.printStackTrace();
			result.setStatus(504);
		}finally{
			doRelease(isHttps, httpClient, httpPost);
		}
		return result;
	}
	public static HttpResult doGet(String url,Map<String,String> paraMap,Map<String,String> headerMap, Integer timeout){
		return doGet(url, paraMap, headerMap, timeout, false);
	}
	public static HttpResult doGet(String url,Map<String,String> paraMap,Map<String,String> headerMap, Integer timeout, boolean isHttps){
		CloseableHttpClient httpClient = null;
		HttpGet httpGet = null;
		HttpResult result = new HttpResult();
		timeout = timeout == null?3000:timeout;
		try{
			if(isHttps){
				httpClient = createSSLClient();
			}else{
				httpClient = HttpClientPool.get();
			}
			//设置参数
			if(paraMap.size() != 0){
				url += "?";
			}
			Iterator<Entry<String, String>> iterator = paraMap.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String,String> elem =  iterator.next();
				url += elem.getKey() + "=" + URLEncoder.encode(elem.getValue(), CHARSET);
				url += "&";
			}
			url.substring(0,url.length() - 1);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();//设置请求和传输超时时间
			httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			
			iterator = headerMap.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String,String> elem = (Entry<String, String>) iterator.next();
				httpGet.setHeader(elem.getKey(), elem.getValue());
			}
			HttpResponse response = httpClient.execute(httpGet);
			doParseResult(response, result);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			doRelease(isHttps, httpClient, httpGet);
		}
		return result;
	}
	
	private static CloseableHttpClient createSSLClient(){
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
					// 信任所有
					public boolean isTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
						return true;
					}
				}).build();
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
				return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	private static void doParseResult(HttpResponse response, HttpResult result) throws IOException {
		if(response != null){
			result.setStatus(response.getStatusLine().getStatusCode());

			HttpEntity resEntity = response.getEntity();
			if(resEntity != null){
				result.setResultString(EntityUtils.toString(resEntity,CHARSET));
			}
		}
	}

	private static void doRelease(Boolean isHttps, CloseableHttpClient httpClient, HttpRequestBase requestBase){
		try {
			if(isHttps){
				httpClient.close();
			}
			requestBase.releaseConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}