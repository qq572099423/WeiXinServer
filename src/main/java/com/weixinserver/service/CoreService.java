package com.weixinserver.service;

import com.weixinserver.entity.OpenInfo;


public interface CoreService {
  String processRequest(String msg);

  OpenInfo updateOpenInfo(OpenInfo user);
}
