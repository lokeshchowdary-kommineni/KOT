/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MR
 */
@Entity
@Table(name = "temp_kot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TempKot.findAll", query = "SELECT t FROM TempKot t")
    , @NamedQuery(name = "TempKot.findById", query = "SELECT t FROM TempKot t WHERE t.id = :id")
    , @NamedQuery(name = "TempKot.findByAuditRate", query = "SELECT t FROM TempKot t WHERE t.auditRate = :auditRate")
    , @NamedQuery(name = "TempKot.findByCap", query = "SELECT t FROM TempKot t WHERE t.cap = :cap")
    , @NamedQuery(name = "TempKot.findByItemCode", query = "SELECT t FROM TempKot t WHERE t.itemCode = :itemCode")
    , @NamedQuery(name = "TempKot.findByItemName", query = "SELECT t FROM TempKot t WHERE t.itemName = :itemName")
    , @NamedQuery(name = "TempKot.findByKotNo", query = "SELECT t FROM TempKot t WHERE t.kotNo = :kotNo")
    , @NamedQuery(name = "TempKot.findByKotTimestamp", query = "SELECT t FROM TempKot t WHERE t.kotTimestamp = :kotTimestamp")
    , @NamedQuery(name = "TempKot.findByQuantity", query = "SELECT t FROM TempKot t WHERE t.quantity = :quantity")
    , @NamedQuery(name = "TempKot.findByRate", query = "SELECT t FROM TempKot t WHERE t.rate = :rate")
    , @NamedQuery(name = "TempKot.findByTableName", query = "SELECT t FROM TempKot t WHERE t.tableName = :tableName")
    , @NamedQuery(name = "TempKot.findByTaxCgst", query = "SELECT t FROM TempKot t WHERE t.taxCgst = :taxCgst")
    , @NamedQuery(name = "TempKot.findByTaxSgst", query = "SELECT t FROM TempKot t WHERE t.taxSgst = :taxSgst")
    , @NamedQuery(name = "TempKot.findByUnit", query = "SELECT t FROM TempKot t WHERE t.unit = :unit")
    , @NamedQuery(name = "TempKot.findByVap", query = "SELECT t FROM TempKot t WHERE t.vap = :vap")
    , @NamedQuery(name = "TempKot.findByWaiterId", query = "SELECT t FROM TempKot t WHERE t.waiterId = :waiterId")
    , @NamedQuery(name = "TempKot.findByKotid", query = "SELECT t FROM TempKot t WHERE t.kotid = :kotid")})
public class TempKot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "audit_rate")
    private BigDecimal auditRate;
    @Column(name = "cap")
    private BigDecimal cap;
    @Size(max = 20)
    @Column(name = "item_code")
    private String itemCode;
    @Size(max = 50)
    @Column(name = "item_name")
    private String itemName;
    @Size(max = 20)
    @Column(name = "kot_no")
    private String kotNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kot_timestamp",nullable=false,updatable=false)
    private Date kotTimestamp;
    @Column(name = "quantity")
    private Double quantity;
    @Column(name = "rate")
    private BigDecimal rate;
    @Size(max = 20)
    @Column(name = "table_name")
    private String tableName;
    @Column(name = "tax_cgst")
    private BigDecimal taxCgst;
    @Column(name = "tax_sgst")
    private BigDecimal taxSgst;
    @Size(max = 20)
    @Column(name = "unit")
    private String unit;
    @Column(name = "vap")
    private BigDecimal vap;
    @Size(max = 50)
    @Column(name = "waiter_id")
    private String waiterId;
    @Column(name = "kotid")
    private Long kotid;

    public TempKot() {
    }

    public TempKot(Long id) {
        this.id = id;
    }

    public TempKot(Long id, Date kotTimestamp) {
        this.id = id;
        this.kotTimestamp = kotTimestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAuditRate() {
        return auditRate;
    }

    public void setAuditRate(BigDecimal auditRate) {
        this.auditRate = auditRate;
    }

    public BigDecimal getCap() {
        return cap;
    }

    public void setCap(BigDecimal cap) {
        this.cap = cap;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public BigDecimal getTaxCgst() {
        return taxCgst;
    }

    public void setTaxCgst(BigDecimal taxCgst) {
        this.taxCgst = taxCgst;
    }

    public BigDecimal getTaxSgst() {
        return taxSgst;
    }

    public void setTaxSgst(BigDecimal taxSgst) {
        this.taxSgst = taxSgst;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getVap() {
        return vap;
    }

    public void setVap(BigDecimal vap) {
        this.vap = vap;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    public Long getKotid() {
        return kotid;
    }

    public void setKotid(Long kotid) {
        this.kotid = kotid;
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
        if (!(object instanceof TempKot)) {
            return false;
        }
        TempKot other = (TempKot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.TempKot[ id=" + id + " ]";
    }
    
}
