package com.weixinserver.model;

public class RequestResult {
  private int errcode;
  private String errmsg;

  public int getErrcode() {
    return errcode;
  }

  public void setErrcode(int errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }

  @Override
  public String toString() {
    return "RequestResult [errcode=" + errcode + ", errmsg=" + errmsg + "]";
  }

}
