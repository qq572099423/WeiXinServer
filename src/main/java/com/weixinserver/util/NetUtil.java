package com.weixinserver.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weixinserver.model.CONST;
import com.weixinserver.model.AccessToken;
import com.weixinserver.model.OAuth2AccessToken;
import com.weixinserver.model.WechatProperty;

public class NetUtil {

  /**
   * @Title: getAccessToken
   * @Description: 获取AccessToken
   * @param @return 设定文件
   * @return AccessToken 返回类型
   * @throws
   */
  public static AccessToken getAccessToken() {
    try {
      WechatProperty props = WechatProperty.getProperties();
      return getAccessToken(props.getAppId(), props.getAppSecret());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * @Title: getAccessToken
   * @Description: 获取AccessToken
   * @param @param appId
   * @param @param appSecret
   * @param @return 设定文件
   * @return AccessToken 返回类型
   * @throws
   */
  public static AccessToken getAccessToken(String appId, String appSecret) {
    try {
      String url = String.format(CONST.API.API_GET_ACCESS_TOKEN, appId, appSecret);
      URL u = new URL(url);
      URLConnection connection = u.openConnection();
      connection.connect();
      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String access_token = "";
      String line = null;
      while (null != (line = reader.readLine())) {
        access_token += line;
      }
      connection.getInputStream().close();
      Gson gson = new GsonBuilder().disableHtmlEscaping().create();
      AccessToken accessToken = gson.fromJson(access_token, AccessToken.class);
      accessToken.setStartTime(new Date().getTime());
      return accessToken;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * @Title: getOAuth2AccessToken
   * @Description: 获取网页AccessToken
   * @param @param code
   * @param @return 设定文件
   * @return OAuth2AccessToken 返回类型
   * @throws
   */
  public static OAuth2AccessToken getOAuth2AccessToken(String code) {
    try {
      WechatProperty props = WechatProperty.getProperties();
      return getOAuth2AccessToken(props.getAppId(), props.getAppSecret(), code);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * @Title: getOAuth2AccessToken
   * @Description: 获取网页AccessToken
   * @param @param appId
   * @param @param appSecret
   * @param @param code
   * @param @return 设定文件
   * @return OAuth2AccessToken 返回类型
   * @throws
   */
  public static OAuth2AccessToken getOAuth2AccessToken(String appId, String appSecret, String code) {
    try {
      String url = String.format(CONST.API.API_GET_ACCESS_TOKEN_BY_CODE, appId, appSecret, code);
      URL u = new URL(url);
      URLConnection connection = u.openConnection();
      connection.connect();
      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String access_token = "";
      String line = null;
      while (null != (line = reader.readLine())) {
        access_token += line;
      }
      connection.getInputStream().close();
      Gson gson = new GsonBuilder().disableHtmlEscaping().create();
      OAuth2AccessToken oAuth2AccessToken = gson.fromJson(access_token, OAuth2AccessToken.class);
      oAuth2AccessToken.setStartTime(new Date().getTime());
      return oAuth2AccessToken;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 发起https请求并获取结果
   * 
   * @param requestUrl 请求地址
   * @param requestMethod 请求方式（GET、POST）
   * @param outputStr 提交的数据
   * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
   */
  public static <T> T httpRequest(String requestUrl, String requestMethod, String outputStr, Class<T> clazz) {
    StringBuffer buffer = new StringBuffer();
    try {
      // 创建SSLContext对象，并使用我们指定的信任管理器初始化
      TrustManager[] tm = {new MyX509TrustManager()};
      SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
      sslContext.init(null, tm, new java.security.SecureRandom());
      // 从上述SSLContext对象中得到SSLSocketFactory对象
      SSLSocketFactory ssf = sslContext.getSocketFactory();

      URL url = new URL(requestUrl);
      HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
      httpUrlConn.setSSLSocketFactory(ssf);

      httpUrlConn.setDoOutput(true);
      httpUrlConn.setDoInput(true);
      httpUrlConn.setUseCaches(false);
      // 设置请求方式（GET/POST）
      httpUrlConn.setRequestMethod(requestMethod);

      if ("GET".equalsIgnoreCase(requestMethod)) httpUrlConn.connect();

      // 当有数据需要提交时
      if (null != outputStr) {
        OutputStream outputStream = httpUrlConn.getOutputStream();
        // 注意编码格式，防止中文乱码
        outputStream.write(outputStr.getBytes("UTF-8"));
        outputStream.close();
      }

      // 将返回的输入流转换成字符串
      InputStream inputStream = httpUrlConn.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

      String str = null;
      while ((str = bufferedReader.readLine()) != null) {
        buffer.append(str);
      }
      bufferedReader.close();
      inputStreamReader.close();
      // 释放资源
      inputStream.close();
      inputStream = null;
      httpUrlConn.disconnect();
      return new Gson().fromJson(buffer.toString(), clazz);
    } catch (ConnectException ce) {
      System.err.println("Weixin server connection timed out.");
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return null;
  }
}
