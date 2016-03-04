package com.weixinserver.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class OpenInfo implements Serializable {
  /**
   * @Fields serialVersionUID :
   */
  private static final long serialVersionUID = 4057763454137190206L;
  @Id
  @Column
  private String openid;
  @Column
  private int subscribe;
  @Column
  private String nickname;
  @Column
  private int sex;
  @Column
  private String language;
  @Column
  private String city;
  @Column
  private String province;
  @Column
  private String country;
  @Column
  private String headimgurl;
  @Column
  private long subscribe_time;
  @Column
  private String unionid;
  @Column
  private String remark;
  @Column
  private int groupid;
  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
  @JoinTable(name = "BindMap", joinColumns = {@JoinColumn(name = "openid", referencedColumnName = "openid", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "amm_code", referencedColumnName = "code", nullable = false, updatable = false)})
  private List<Ammeter> ammeters = new ArrayList<Ammeter>();

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public int getSubscribe() {
    return subscribe;
  }

  public void setSubscribe(int subscribe) {
    this.subscribe = subscribe;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getHeadimgurl() {
    return headimgurl;
  }

  public void setHeadimgurl(String headimgurl) {
    this.headimgurl = headimgurl;
  }

  public long getSubscribe_time() {
    return subscribe_time;
  }

  public void setSubscribe_time(long subscribe_time) {
    this.subscribe_time = subscribe_time;
  }

  public String getUnionid() {
    return unionid;
  }

  public void setUnionid(String unionid) {
    this.unionid = unionid;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public int getGroupid() {
    return groupid;
  }

  public void setGroupid(int groupid) {
    this.groupid = groupid;
  }

  public List<Ammeter> getAmmeters() {
    return ammeters;
  }

  public void setAmmeters(List<Ammeter> ammeter) {
    this.ammeters = ammeter;
  }


}
