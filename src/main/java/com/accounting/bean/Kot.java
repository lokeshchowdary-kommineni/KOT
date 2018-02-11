/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MR
 */
@Entity
@Table(name = "kot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kot.findAll", query = "SELECT k FROM Kot k")
    , @NamedQuery(name = "Kot.findByKotNo", query = "SELECT k FROM Kot k WHERE k.kotNo = :kotNo")
    , @NamedQuery(name = "Kot.findByKotTimestamp", query = "SELECT k FROM Kot k WHERE k.kotTimestamp = :kotTimestamp")
    , @NamedQuery(name = "Kot.findByTableName", query = "SELECT k FROM Kot k WHERE k.tableName = :tableName")
    , @NamedQuery(name = "Kot.findByTotalCgst", query = "SELECT k FROM Kot k WHERE k.totalCgst = :totalCgst")
    , @NamedQuery(name = "Kot.findByTotalKotvalue", query = "SELECT k FROM Kot k WHERE k.totalKotvalue = :totalKotvalue")
    , @NamedQuery(name = "Kot.findByTotalSgst", query = "SELECT k FROM Kot k WHERE k.totalSgst = :totalSgst")
    , @NamedQuery(name = "Kot.findByWaiterId", query = "SELECT k FROM Kot k WHERE k.waiterId = :waiterId")
    , @NamedQuery(name = "Kot.findById", query = "SELECT k FROM Kot k WHERE k.id = :id")})
public class Kot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    @Column(name = "kot_no")
    private String kotNo;
    @Column(name = "kot_timestamp",nullable=false,updatable=false)
    private Date kotTimestamp;
    @Size(max = 20)
    @Column(name = "table_name")
    private String tableName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_cgst")
    private BigDecimal totalCgst;
    @Column(name = "total_kotvalue")
    private BigDecimal totalKotvalue;
    @Column(name = "total_sgst")
    private BigDecimal totalSgst;
    @Size(max = 50)
    @Column(name = "waiter_id")
    private String waiterId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    public Kot() {
    }

    public Kot(Long id) {
        this.id = id;
    }

    public String getKotNo() {
        return kotNo;
    }

    public void setKotNo(String kotNo) {
        this.kotNo = kotNo;
    }

    public Date getKotTimestamp() {
        return kotTimestamp;
    }

    public void setKotTimestamp(Date kotTimestamp) {
        this.kotTimestamp = kotTimestamp;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public BigDecimal getTotalCgst() {
        return totalCgst;
    }

    public void setTotalCgst(BigDecimal totalCgst) {
        this.totalCgst = totalCgst;
    }

    public BigDecimal getTotalKotvalue() {
        return totalKotvalue;
    }

    public void setTotalKotvalue(BigDecimal totalKotvalue) {
        this.totalKotvalue = totalKotvalue;
    }

    public BigDecimal getTotalSgst() {
        return totalSgst;
    }

    public void setTotalSgst(BigDecimal totalSgst) {
        this.totalSgst = totalSgst;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kot)) {
            return false;
        }
        Kot other = (Kot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.Kot[ id=" + id + " ]";
    }
    
}
