package com.pxh.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntPredicate;

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
		OutputStream os = null;
		InputStream is = null;
		//数据验证
		if(null != urlPath && !"".equals(urlPath)){
			try {
				URL url = new URL(urlPath);
				HttpURLConnection connection = (HttpURLConnection)url.openConnection();
				connection.setConnectTimeout(connectTimeout);
				connection.setRequestMethod("GET");
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setReadTimeout(30*1000);
				connection.setRequestProperty("contentType", "utf-8");
				connection.setRequestProperty("Content-type", "text/html");
				connection.connect();
				//获取输入流  提交数据
				if(null != json && !"".equals(json)){
					os  = connection.getOutputStream();
					os.write(json.getBytes());
					os.flush();
					os.close();
				}
				//获取输出流  将结果返回
				is = connection.getInputStream();
				byte[] bys = new byte[1024];
				int potion = 0;
				while(0 < is.available()){
//					is.read(bys, off, len)
				}
			} catch (MalformedURLException e) {
				maps.put("flag", true);
				maps.put("msg", "get请求创建路径时候失败:"+e.getMessage());
			} catch (IOException e) {
				maps.put("flag", true);
				maps.put("msg", "get请求创建链接时候失败:"+e.getMessage());
			}finally {
				if(os != null){try {os.close();} catch (IOException e) {}os = null;}
				if(is != null){try {is.close();} catch (IOException e) {} is = null;}
			}
		}else{
			maps.put("flag", true);
			maps.put("msg", "get请求路径为空");
		}
		
		return gson.toJson(maps);
	}

}
