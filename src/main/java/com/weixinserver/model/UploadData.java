package com.weixinserver.model;

import java.math.BigDecimal;

public class UploadData {
  private String amm_code;
  private String amm_name;
  private String date;
  private BigDecimal data;

  public String getAmm_code() {
    return amm_code;
  }

  public void setAmm_code(String amm_code) {
    this.amm_code = amm_code;
  }

  public String getAmm_name() {
    return amm_name;
  }

  public void setAmm_name(String amm_name) {
    this.amm_name = amm_name;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public BigDecimal getData() {
    return data;
  }

  public void setData(BigDecimal data) {
    this.data = data;
  }

}
