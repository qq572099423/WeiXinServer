package com.weixinserver.repository;

public interface BindRepository {
  void unBindAmmeter(String openId, String amm_code);
}
