package com.weixinserver.model;

/**
 * @ClassName: AccessToken
 * @Description: 调用API:API_GET_ACCESS_TOKEN返回的JSON对应的实体类 access_token:获取到的凭证
 * @author WangPeng
 * @date 2016年1月27日 下午4:56:25
 */
public class AccessToken extends RequestResult {
  private String access_token;
  private long expires_in;
  private long startTime;

  public String getAccess_token() {
    return access_token;
  }

  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }

  public long getExpires_in() {
    return expires_in;
  }

  public void setExpires_in(long expires_in) {
    this.expires_in = expires_in;
  }

  public long getStartTime() {
    return startTime;
  }

  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }
}
