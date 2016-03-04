package com.weixinserver.model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WechatProperty {
  private String encodingAesKey;
  private String token;
  private String timestamp;
  private String appId;
  private String appSecret;
  private static WechatProperty me;

  public String getAppSecret() {
    return appSecret;
  }

  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret;
  }

  public String getEncodingAesKey() {
    return encodingAesKey;
  }

  public void setEncodingAesKey(String encodingAesKey) {
    this.encodingAesKey = encodingAesKey;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public static WechatProperty getProperties() {
    if (null == me) {
      @SuppressWarnings("resource")
      ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-*.xml");
      me = (WechatProperty) ctx.getBean("wechatProps");
    }
    return me;
  }

}
