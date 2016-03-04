package com.weixinserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixinserver.repository.BindRepository;
import com.weixinserver.service.BindService;

@Service("BindService")
@Transactional
public class BindServiceImpl implements BindService{

  @Autowired
  private BindRepository bindRepository;
  
  @Override
  public void unBindAmmeter(String openId, String amm_code) {
    bindRepository.unBindAmmeter(openId, amm_code);
  }
  
}
