package com.weixinserver.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.weixinserver.entity.OpenInfo;
import com.weixinserver.model.StatisticData;
import com.weixinserver.repository.StatisticsRepository;
@Repository("statisticsRepository")
public class StatisticsRepositoryImpl implements StatisticsRepository {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<StatisticData> getMonthUsage(OpenInfo info, String startTime, String endTime) {
    Session session = sessionFactory.getCurrentSession();
    String sql = " SELECT DISTINCT m.amm_code,e.data - s.data FROM (SELECT * FROM bindmap WHERE openid = :openid) m ";
    sql += "LEFT JOIN (SELECT * FROM ammeterdata WHERE date = :startTime) s ON m.amm_code = s.amm_code ";
    sql += "LEFT JOIN (SELECT * FROM ammeterdata WHERE date = :endTime) e ON s.amm_code = e.amm_code ";
    SQLQuery query = session.createSQLQuery(sql);
    @SuppressWarnings("unchecked")
    List<Object[]> list = query.setString("openid", info.getOpenid()).setString("startTime", startTime).setString("endTime", endTime).list();
    if (list == null || list.size() == 0) {
      return null;
    }
    List<StatisticData> resultList = new ArrayList<StatisticData>();
    for (Object[] row : list) {
      StatisticData data = new StatisticData();
      data.setAmm_code(row[0].toString());
      if (row[1] == null) {
        data.setData("无数据");
      } else {
        data.setData(row[1].toString());
      }
      resultList.add(data);
    }
    return resultList;
  }

}
