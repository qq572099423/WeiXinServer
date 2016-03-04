package com.weixinserver.service;

import java.util.List;

import com.weixinserver.entity.OpenInfo;
import com.weixinserver.model.StatisticData;

public interface StatisticsService {
  List<StatisticData> getMonthUsage(OpenInfo info, String startTime, String endTime);
}
