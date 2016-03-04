package com.weixinserver.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.weixinserver.repository.BindRepository;

@Repository("bindRepository")
public class BindRepositoryImpl implements BindRepository {
  @Autowired
  private SessionFactory sessionFactory;

  public void unBindAmmeter(String openId, String amm_code) {
    Session session = sessionFactory.getCurrentSession();
    session.createSQLQuery("delete from bindmap where openid=:openid and amm_code=:amm_code")
    .setString("openid", openId)
    .setString("amm_code", amm_code)
    .executeUpdate();
  }
}
