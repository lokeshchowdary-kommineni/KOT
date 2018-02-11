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
@Table(name = "sales_bill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesBill.findAll", query = "SELECT s FROM SalesBill s")
    , @NamedQuery(name = "SalesBill.findById", query = "SELECT s FROM SalesBill s WHERE s.id = :id")
    , @NamedQuery(name = "SalesBill.findByCancledbill", query = "SELECT s FROM SalesBill s WHERE s.cancledbill = :cancledbill")
    , @NamedQuery(name = "SalesBill.findByCap", query = "SELECT s FROM SalesBill s WHERE s.cap = :cap")
    , @NamedQuery(name = "SalesBill.findByInvoiceNo", query = "SELECT s FROM SalesBill s WHERE s.invoiceNo = :invoiceNo")
    , @NamedQuery(name = "SalesBill.findByItemCode", query = "SELECT s FROM SalesBill s WHERE s.itemCode = :itemCode")
    , @NamedQuery(name = "SalesBill.findByItemName", query = "SELECT s FROM SalesBill s WHERE s.itemName = :itemName")
    , @NamedQuery(name = "SalesBill.findByKotIds", query = "SELECT s FROM SalesBill s WHERE s.kotIds = :kotIds")
    , @NamedQuery(name = "SalesBill.findByKotNos", query = "SELECT s FROM SalesBill s WHERE s.kotNos = :kotNos")
    , @NamedQuery(name = "SalesBill.findByQuantity", query = "SELECT s FROM SalesBill s WHERE s.quantity = :quantity")
    , @NamedQuery(name = "SalesBill.findByRate", query = "SELECT s FROM SalesBill s WHERE s.rate = :rate")
    , @NamedQuery(name = "SalesBill.findBySalesdate", query = "SELECT s FROM SalesBill s WHERE s.salesdate = :salesdate")
    , @NamedQuery(name = "SalesBill.findByTableName", query = "SELECT s FROM SalesBill s WHERE s.tableName = :tableName")
    , @NamedQuery(name = "SalesBill.findByTaxCgst", query = "SELECT s FROM SalesBill s WHERE s.taxCgst = :taxCgst")
    , @NamedQuery(name = "SalesBill.findByTaxSgst", query = "SELECT s FROM SalesBill s WHERE s.taxSgst = :taxSgst")
    , @NamedQuery(name = "SalesBill.findByUnit", query = "SELECT s FROM SalesBill s WHERE s.unit = :unit")
    , @NamedQuery(name = "SalesBill.findByVap", query = "SELECT s FROM SalesBill s WHERE s.vap = :vap")
    , @NamedQuery(name = "SalesBill.findByWaiterId", query = "SELECT s FROM SalesBill s WHERE s.waiterId = :waiterId")})
public class SalesBill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 20)
    @Column(name = "cancledbill")
    private String cancledbill;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cap")
    private BigDecimal cap;
    @Size(max = 255)
    @Column(name = "invoice_no")
    private String invoiceNo;
    @Size(max = 20)
    @Column(name = "item_code")
    private String itemCode;
    @Size(max = 50)
    @Column(name = "item_name")
    private String itemName;
    @Size(max = 100)
    @Column(name = "kot_ids")
    private String kotIds;
    @Size(max = 100)
    @Column(name = "kot_nos")
    private String kotNos;
    @Column(name = "quantity")
    private Double quantity;
    @Column(name = "rate")
    private BigDecimal rate;
    @Size(max = 100)
    @Column(name = "salesdate")
    private String salesdate;
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

    public SalesBill() {
    }

    public SalesBill(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCancledbill() {
        return cancledbill;
    }

    public void setCancledbill(String cancledbill) {
        this.cancledbill = cancledbill;
    }

    public BigDecimal getCap() {
        return cap;
    }

    public void setCap(BigDecimal cap) {
        this.cap = cap;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
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

    public String getKotIds() {
        return kotIds;
    }

    public void setKotIds(String kotIds) {
        this.kotIds = kotIds;
    }

    public String getKotNos() {
        return kotNos;
    }

    public void setKotNos(String kotNos) {
        this.kotNos = kotNos;
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

    public String getSalesdate() {
        return salesdate;
    }

    public void setSalesdate(String salesdate) {
        this.salesdate = salesdate;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalesBill)) {
            return false;
        }
        SalesBill other = (SalesBill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.SalesBill[ id=" + id + " ]";
    }
    
}
