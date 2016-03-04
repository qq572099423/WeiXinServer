package com.weixinserver.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixinserver.entity.OpenInfo;
import com.weixinserver.model.StatisticData;
import com.weixinserver.repository.StatisticsRepository;
import com.weixinserver.service.StatisticsService;
@Service("statisticsService")
@Transactional
public class StatisticsServiceImpl implements StatisticsService{
  @Autowired
  private StatisticsRepository statisticsRepository;
  @Override
  public List<StatisticData> getMonthUsage(OpenInfo info, String startTime, String endTime) {
    return statisticsRepository.getMonthUsage(info, startTime, endTime);
  }
}
