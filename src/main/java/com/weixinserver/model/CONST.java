package com.weixinserver.model;

public class CONST {
  public class API {
    // 获得Access_Token
    public static final String API_GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    // 设置自定义菜单
    public static final String API_SET_CUSTOM_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
    // 根据用户openId和全局Access_token获取用户信息
    public static final String API_GET_USERINFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
    // 微信网页oauth2静默授权API
    public static final String API_SNSAPI_BASE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    // 获取网页认证的Access_Token
    public static final String API_GET_ACCESS_TOKEN_BY_CODE = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
  }
  public class TEXT{
    public static final String TXT_WELCOME_WORDS = "亲爱的%s\r\n感谢您关注我们！\r\n在这里您可以执行如下操作：\r\n●绑定电表\r\n●解绑电表\r\n●查询用电量\r\n请点击下方的菜单选择您需要的操作！";
  }
}
