package com.weixinserver.model;

/**
* @ClassName: ViewButton 
* @Description: 跳转按钮，点击后跳转到网页界面
* @author WangPeng
* @date 2016年1月27日 上午10:21:00
 */
public class ViewButton extends Button {
  private String type;
  private String url;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


}
