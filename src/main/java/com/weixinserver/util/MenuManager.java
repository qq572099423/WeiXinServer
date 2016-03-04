package com.weixinserver.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.gson.GsonBuilder;
import com.weixinserver.model.Button;
import com.weixinserver.model.CONST;
import com.weixinserver.model.ComplexButton;
import com.weixinserver.model.Menu;
import com.weixinserver.model.RequestResult;
import com.weixinserver.model.ViewButton;

public class MenuManager {
  // 菜单创建（POST） 限100（次/天）
  public static void main(String[] args) {
    // 调用接口创建菜单
    createMenu(getMenu(), AccessTokenHolder.getAccessToken());
  }

  /**
   * @Title: createMenu
   * @Description: 创建菜单
   * @param @param menu
   * @param @param accessToken 设定文件
   * @return void 返回类型
   * @throws
   */
  public static void createMenu(Menu menu, String accessToken) {
    // 将菜单对象转换成json字符串
    String jsonMenu = new GsonBuilder().disableHtmlEscaping().create().toJson(menu);
    String url = String.format(CONST.API.API_SET_CUSTOM_MENU, accessToken);
    RequestResult rr = NetUtil.httpRequest(url, "POST", jsonMenu, RequestResult.class);
    System.out.println(rr.toString());
  }

  /**
   * @Title: getMenu
   * @Description: 组装菜单数据
   * @param @return 设定文件
   * @return Menu 返回类型
   * @throws
   */
  private static Menu getMenu() {
    String api;
    String url;
    String appID = "wx481dec316d333d7b";
    api = CONST.API.API_SNSAPI_BASE;
    url = String.format(api, appID, "http://enayo-lx.xicp.net/WeiXinServer/wap/bindAmmeter");
    ViewButton btn11 = new ViewButton();
    btn11.setName("绑定电表");
    btn11.setType("view");
    btn11.setUrl(url);
    url = String.format(api, appID, "http://enayo-lx.xicp.net/WeiXinServer/wap/unbindAmmeter");
    ViewButton btn12 = new ViewButton();
    btn12.setName("解绑电表");
    btn12.setType("view");
    btn12.setUrl(url);
    url = String.format(api, appID, "http://enayo-lx.xicp.net/WeiXinServer/wap/showAmmeterList");
    ViewButton btn13 = new ViewButton();
    btn13.setName("我的电表");
    btn13.setType("view");
    btn13.setUrl(url);
    ComplexButton mainBtn1 = new ComplexButton();
    mainBtn1.setName("管理电表");
    mainBtn1.setSub_button(new Button[] {btn11, btn12, btn13});

    url = String.format(api, appID, "http://enayo-lx.xicp.net/WeiXinServer/wap/showMonthUsage");
    ViewButton btn21 = new ViewButton();
    btn21.setName("查询月用量");
    btn21.setType("view");
    btn21.setUrl(url);
    // CommonButton btn22 = new CommonButton();
    // btn22.setName("查询所有");
    // btn22.setType("click");
    // btn22.setKey("22");
    ComplexButton mainBtn2 = new ComplexButton();
    mainBtn2.setName("查询用量");
    mainBtn2.setSub_button(new Button[] {btn21});

    /**
     * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
     */
    Menu menu = new Menu();
    menu.setButton(new Button[] {mainBtn1, mainBtn2});

    return menu;
  }
}
