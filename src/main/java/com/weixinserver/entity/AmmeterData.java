package com.weixinserver.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @ClassName: AmmeterData
 * @Description:
 * @author WangPeng
 * @date 2016年1月25日 下午3:03:47
 */
@Entity
public class AmmeterData implements Serializable {
  /**
   * @Fields serialVersionUID :
   */
  private static final long serialVersionUID = 6387628949298243607L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "amm_code", referencedColumnName = "code")
  private Ammeter ammeter;
  @Column
  private Date date;
  @Column
  private BigDecimal data;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Ammeter getAmmeter() {
    return ammeter;
  }

  public void setAmmeter(Ammeter ammeter) {
    this.ammeter = ammeter;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public BigDecimal getData() {
    return data;
  }

  public void setData(BigDecimal data) {
    this.data = data;
  }
}
