package com.weixinserver.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Ammeter implements Serializable {
  /**
   * @Fields serialVersionUID :
   */
  private static final long serialVersionUID = -2695026067674947797L;
  @Id
  @Column
  private String code;
  @Column
  private String name;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "pro_code", referencedColumnName = "code")
  private Project project;
  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "ammeters")
  private List<OpenInfo> openInfos;

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

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  @Override
  public String toString() {
    return "Ammeter [code=" + code + ", name=" + name + ", project=" + project + "]";
  }

  public List<OpenInfo> getOpenInfos() {
    return openInfos;
  }

  public void setOpenInfos(List<OpenInfo> openInfos) {
    this.openInfos = openInfos;
  }
}
