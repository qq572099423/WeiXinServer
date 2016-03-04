package com.weixinserver.controller.wap;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixinserver.entity.Ammeter;
import com.weixinserver.entity.OpenInfo;
import com.weixinserver.service.BindService;
import com.weixinserver.service.CommonService;
import com.weixinserver.service.CoreService;
import com.weixinserver.util.CommonUtil;

@Controller
@RequestMapping(value = "/wap")
public class BindController {
  private @Autowired CommonService commonService;
  private @Autowired CoreService coreService;
  private @Autowired BindService bindService;

  @RequestMapping(value = "/showAmmeterList", method = RequestMethod.GET)
  public ModelMap showAmmeterList(String code, String state, HttpSession session) {
    ModelMap map = new ModelMap();
    String openID = CommonUtil.getOpenID(code, session, coreService);
    OpenInfo info = commonService.findByWhere("where u.openid = '" + openID + "'", OpenInfo.class);
    map.put("info", info);
    return map;
  }

  @RequestMapping(value = "/bindAmmeter", method = RequestMethod.GET)
  public void bindAmmeter(String code, String state, HttpSession session) {
    CommonUtil.getOpenID(code, session, coreService);
  }

  @RequestMapping(value = "/unbindAmmeter", method = RequestMethod.GET)
  public ModelMap unbindAmmeter(String code, String state, HttpSession session) {
    ModelMap map = new ModelMap();
    String openID = CommonUtil.getOpenID(code, session, coreService);
    OpenInfo info = commonService.findByWhere("where u.openid = '" + openID + "'", OpenInfo.class);
    map.put("info", info);
    return map;
  }

  @RequestMapping(value = "/commitBindAmmeters", method = RequestMethod.POST)
  public @ResponseBody ModelMap commitBindAmmeters(String codes, HttpSession session) {
    ModelMap map = new ModelMap();
    map.put("hasSuccess", false);
    try {
      if (codes == null || "".equals(codes)) {
        map.put("msg", "请输入要绑定的表号，多个请用逗号分隔");
        return map;
      }
      codes = codes.replace("，", ",");
      String arr[] = codes.split(",");
      OpenInfo user = (OpenInfo) session.getAttribute("OPENINFO");
      OpenInfo info = commonService.findByWhere("where u.openid = '" + user.getOpenid() + "'", OpenInfo.class);
      Collection<String> myCodes = new ArrayList<String>();
      for (Ammeter amm : user.getAmmeters()) {
        myCodes.add(amm.getCode().toUpperCase());
      }
      for (String code : arr) {
        Ammeter amm = commonService.findByCode(code, Ammeter.class);
        if (null == amm) {
          map.put("msg", code + "不是有效的表号。");
          return map;
        }
        if (myCodes.contains(code.toUpperCase())) {
          map.put("msg", "您已绑定过" + code + "，请勿重复绑定。");
          return map;
        }
        info.getAmmeters().add(amm);
      }
      commonService.update(info);
      map.remove("hasSuccess");
      map.put("hasSuccess", true);
      return map;
    }catch(NonUniqueObjectException ne){
      map.put("msg", "请勿重复绑定。");
      return map;
    } catch (Exception e) {
      map.put("msg", "系统错误。");
      return map;
    }
  }

  @RequestMapping(value = "/commitUnBindAmmeters", method = RequestMethod.POST)
  public @ResponseBody ModelMap commitUnBindAmmeters(String codes, HttpSession session) {
    ModelMap map = new ModelMap();
    map.put("hasSuccess", false);
    try {
      if (StringUtils.isEmpty(codes)) {
        map.put("msg", "请选择您要解绑的电表。");
        return map;
      }
      OpenInfo user = (OpenInfo) session.getAttribute("OPENINFO");
      user = commonService.findByWhere("where u.openid = '" + user.getOpenid() + "'", OpenInfo.class);
      String[] amm_codes = codes.split(",");
      for (String amm_code : amm_codes) {
        if (StringUtils.isEmpty(amm_code)) continue;

        bindService.unBindAmmeter(user.getOpenid(), amm_code);
      }
      map.remove("hasSuccess");
      map.put("hasSuccess", true);
      return map;
    } catch (Exception e) {
      map.put("msg", "系统错误。");
      return map;
    }
  }
}
