package com.pxh.wechat;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * http 请求
 * @author pxh
 *2017-09-16
 */
public class HttpRequest {
	private Gson gson;
	
	/**
	 * 无参数构造器  创建gson对象
	 */
	public HttpRequest(){
		gson = new Gson();
	}
	/**
	 * http get请求
	 * @param urlPath 请求路径
	 * @param connectTimeout 超时时间
	 * @param json 提交的json数据
	 * @return
	 */
	public  String get(String urlPath, int connectTimeout, String json){
		Map<String,Object> maps = new HashMap<String,Object>();
		//数据验证
		if(null != urlPath && !"".equals(urlPath)){
			try {
				URL url = new URL(urlPath);
				HttpURLConnection connection = (HttpURLConnection)url.openConnection();
				connection.setConnectTimeout(connectTimeout);
				connection.setRequestMethod("GET");
				
			} catch (MalformedURLException e) {
				maps.put("flag", true);
				maps.put("msg", "get请求创建路径时候失败:"+e.getMessage());
			} catch (IOException e) {
				maps.put("flag", true);
				maps.put("msg", "get请求创建链接时候失败:"+e.getMessage());
			}
		}else{
			maps.put("flag", true);
			maps.put("msg", "get请求路径为空");
		}
		
		return gson.toJson(maps);
	}

}
