package com.weixinserver.controller.wap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weixinserver.entity.OpenInfo;
import com.weixinserver.model.StatisticData;
import com.weixinserver.service.CommonService;
import com.weixinserver.service.CoreService;
import com.weixinserver.service.StatisticsService;
import com.weixinserver.util.CommonUtil;

@Controller
@RequestMapping(value = "/wap")
public class StatisticsController {
  @Autowired
  private CoreService coreService;
  @Autowired
  private CommonService commonService;
  @Autowired
  private StatisticsService statisticsService;

  @RequestMapping(value = "/showMonthUsage", method = RequestMethod.GET)
  public ModelMap showMonthUsage(String code, String state, HttpSession session) {
    ModelMap map = new ModelMap();
    String openID = CommonUtil.getOpenID(code, session, coreService);
    OpenInfo info = commonService.findByWhere("where u.openid = '" + openID + "'", OpenInfo.class);
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    String endTime = sf.format(cal.getTime());
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.DAY_OF_MONTH, 1);
    String startTime = sf.format(cal.getTime());

    List<StatisticData> resultList = statisticsService.getMonthUsage(info, startTime, endTime);
    map.put("resultList", resultList);
    return map;
  }
}
