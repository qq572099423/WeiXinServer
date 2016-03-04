package com.weixinserver.model;

/**
 * @ClassName: AccessToken
 * @Description: 调用API:API_GET_ACCESS_TOKEN返回的JSON对应的实体类 access_token:获取到的凭证
 * @author WangPeng
 * @date 2016年1月27日 下午4:56:25
 */
public class OAuth2AccessToken extends RequestResult {
  private String access_token;
  private long expires_in;
  private String refresh_token;
  private String openid;
  private String scope;
  private String unionid;
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

  public String getRefresh_token() {
    return refresh_token;
  }

  public void setRefresh_token(String refresh_token) {
    this.refresh_token = refresh_token;
  }

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getUnionid() {
    return unionid;
  }

  public void setUnionid(String unionid) {
    this.unionid = unionid;
  }

  public long getStartTime() {
    return startTime;
  }

  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }

}
