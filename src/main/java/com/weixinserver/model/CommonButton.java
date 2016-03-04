package com.weixinserver.model;

/**
 * @ClassName: CommonButton
 * @Description: 普通按钮（子按钮）
 * @author WangPeng
 * @date 2016年1月25日 下午4:14:28
 */
public class CommonButton extends Button{
  private String type;
  private String key;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
