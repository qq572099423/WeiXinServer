package com.weixinserver.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixinserver.repository.CommonRepository;
import com.weixinserver.service.CommonService;


@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {

  @Autowired
  protected CommonRepository repository;

  @Override
  public <T> T save(T t) {
    return this.repository.save(t);
  }

  @Override
  public <T> T update(T t) {
    return this.repository.update(t);
  }

  @Override
  public <T> T delete(T t) {
    return this.repository.delete(t);
  }

  @Override
  public <T> List<T> findAll(Class<T> clazz) {
    return this.repository.findAll(clazz);
  }


  @Override
  public <T> T findById(long id, Class<T> clazz) {
    return this.repository.findById(id, clazz);
  }


  @Override
  public <T> long getCount(Class<T> clazz) {
    return this.repository.getCount(clazz);
  }

  @Override
  public <T> List<T> getListByWhere(String where, Class<T> clazz) {
    return this.repository.getListByWhere(where, clazz);
  }

  @Override
  public <T> T findByCode(String code, Class<T> clazz) {
    return this.repository.findByCode(code, clazz);
  }

  @Override
  public <T> T findByWhere(String where, Class<T> clazz) {
    return this.repository.findByWhere(where, clazz);
  }

}
