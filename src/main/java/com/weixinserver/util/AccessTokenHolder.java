package com.weixinserver.util;

import java.util.Date;

import com.weixinserver.model.AccessToken;

/**
 * @ClassName: AccessTokenHolder
 * @Description: AccessToken保持器
 * @author WangPeng
 * @date 2016年1月27日 下午5:03:16
 */
public class AccessTokenHolder {
  /**
   * AccessToken的单例
   */
  private static AccessToken accessToken;
  /**
   * 假定的网络延迟（1分钟）
   */
  private static final long SPAN = 1000 * 60;

  public static String getAccessToken() {
    // 第一次获取
    if (null == accessToken) {
      accessToken = NetUtil.getAccessToken();
    } else {
      // 过期的场合重新获取
      if (new Date().getTime() - (accessToken.getStartTime() - SPAN) >= accessToken.getExpires_in()) {
        accessToken = NetUtil.getAccessToken();
      }
    }
    return accessToken.getAccess_token();
  }
}
