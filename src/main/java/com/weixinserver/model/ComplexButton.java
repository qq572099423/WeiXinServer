package com.weixinserver.model;

/**
 * @ClassName: ComplexButton
 * @Description: 复杂按钮（父按钮）
 * @author WangPeng
 * @date 2016年1月25日 下午4:15:12
 */
public class ComplexButton extends Button {
  private Button[] sub_button;

  public Button[] getSub_button() {
    return sub_button;
  }

  public void setSub_button(Button[] sub_button) {
    this.sub_button = sub_button;
  }
}
