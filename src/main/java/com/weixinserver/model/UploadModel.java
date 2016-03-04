package com.weixinserver.model;

import java.util.List;

public class UploadModel {
  private String proCode;
  private String username;
  private String password;

  private List<UploadData> dataList;


  public List<UploadData> getDataList() {
    return dataList;
  }

  public void setDataList(List<UploadData> dataList) {
    this.dataList = dataList;
  }

  public String getProCode() {
    return proCode;
  }

  public void setProCode(String proCode) {
    this.proCode = proCode;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


}
