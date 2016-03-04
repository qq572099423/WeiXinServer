package com.weixinserver.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName: Project
 * @Description:
 * @author WangPeng
 * @date 2016年1月25日 下午3:03:35
 */
@Entity
public class Project implements Serializable {
  /**
   * @Fields serialVersionUID :
   */
  private static final long serialVersionUID = 5592802051191610148L;
  @Id
  private String code;
  @Column
  private String name;
  @Column
  private String basicName;
  @Column
  private String basicPwd;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBasicName() {
    return basicName;
  }

  public void setBasicName(String basicName) {
    this.basicName = basicName;
  }

  public String getBasicPwd() {
    return basicPwd;
  }

  public void setBasicPwd(String basicPwd) {
    this.basicPwd = basicPwd;
  }

}
