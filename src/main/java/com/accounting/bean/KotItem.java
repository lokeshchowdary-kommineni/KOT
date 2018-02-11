/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MR
 */
@Entity
@Table(name = "kot_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KotItem.findAll", query = "SELECT k FROM KotItem k")
    , @NamedQuery(name = "KotItem.findById", query = "SELECT k FROM KotItem k WHERE k.id = :id")
    , @NamedQuery(name = "KotItem.findByKotNo", query = "SELECT k FROM KotItem k WHERE k.kotNo = :kotNo")
    , @NamedQuery(name = "KotItem.findByCap", query = "SELECT k FROM KotItem k WHERE k.cap = :cap")
    , @NamedQuery(name = "KotItem.findByItemCode", query = "SELECT k FROM KotItem k WHERE k.itemCode = :itemCode")
    , @NamedQuery(name = "KotItem.findByItemName", query = "SELECT k FROM KotItem k WHERE k.itemName = :itemName")
    , @NamedQuery(name = "KotItem.findByQuantity", query = "SELECT k FROM KotItem k WHERE k.quantity = :quantity")
    , @NamedQuery(name = "KotItem.findByTaxCgst", query = "SELECT k FROM KotItem k WHERE k.taxCgst = :taxCgst")
    , @NamedQuery(name = "KotItem.findByTaxSgst", query = "SELECT k FROM KotItem k WHERE k.taxSgst = :taxSgst")
    , @NamedQuery(name = "KotItem.findByUnit", query = "SELECT k FROM KotItem k WHERE k.unit = :unit")
    , @NamedQuery(name = "KotItem.findByVap", query = "SELECT k FROM KotItem k WHERE k.vap = :vap")
    , @NamedQuery(name = "KotItem.findByRate", query = "SELECT k FROM KotItem k WHERE k.rate = :rate")
    , @NamedQuery(name = "KotItem.findByCgstPercent", query = "SELECT k FROM KotItem k WHERE k.cgstPercent = :cgstPercent")
    , @NamedQuery(name = "KotItem.findBySgstPercent", query = "SELECT k FROM KotItem k WHERE k.sgstPercent = :sgstPercent")
    , @NamedQuery(name = "KotItem.findByAuditRate", query = "SELECT k FROM KotItem k WHERE k.auditRate = :auditRate")})
public class KotItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 20)
    @Column(name = "kot_no")
    private String kotNo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cap")
    private BigDecimal cap;
    @Size(max = 20)
    @Column(name = "item_code")
    private String itemCode;
    @Size(max = 50)
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "quantity")
    private Double quantity;
    @Column(name = "tax_cgst")
    private BigDecimal taxCgst;
    @Column(name = "tax_sgst")
    private BigDecimal taxSgst;
    @Size(max = 20)
    @Column(name = "unit")
    private String unit;
    @Column(name = "vap")
    private BigDecimal vap;
    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "cgst_percent")
    private Double cgstPercent;
    @Column(name = "sgst_percent")
    private Double sgstPercent;
    @Column(name = "audit_rate")
    private BigDecimal auditRate;
    @Column(name = "kotid")
    private Long kotid;

    public KotItem() {
    }

    public KotItem(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKotNo() {
        return kotNo;
    }

    public void setKotNo(String kotNo) {
        this.kotNo = kotNo;
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Double getCgstPercent() {
        return cgstPercent;
    }

    public void setCgstPercent(Double cgstPercent) {
        this.cgstPercent = cgstPercent;
    }

    public Double getSgstPercent() {
        return sgstPercent;
    }

    public void setSgstPercent(Double sgstPercent) {
        this.sgstPercent = sgstPercent;
    }

    public BigDecimal getAuditRate() {
        return auditRate;
    }

    public void setAuditRate(BigDecimal auditRate) {
        this.auditRate = auditRate;
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
        if (!(object instanceof KotItem)) {
            return false;
        }
        KotItem other = (KotItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.KotItem[ id=" + id + " ]";
    }
    
}
