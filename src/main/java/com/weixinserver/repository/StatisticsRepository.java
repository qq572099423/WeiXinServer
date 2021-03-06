package com.weixinserver.repository;

import java.util.List;

import com.weixinserver.entity.OpenInfo;
import com.weixinserver.model.StatisticData;

public interface StatisticsRepository {
  List<StatisticData> getMonthUsage(OpenInfo info, String startTime, String endTime);
}
