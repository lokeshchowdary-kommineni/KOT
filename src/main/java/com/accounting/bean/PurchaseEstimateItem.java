/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shine
 */
@Entity
@Table(name = "purchase_estimate_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseEstimateItem.findAll", query = "SELECT p FROM PurchaseEstimateItem p")
    , @NamedQuery(name = "PurchaseEstimateItem.findById", query = "SELECT p FROM PurchaseEstimateItem p WHERE p.id = :id")
    , @NamedQuery(name = "PurchaseEstimateItem.findByAmount", query = "SELECT p FROM PurchaseEstimateItem p WHERE p.amount = :amount")
    , @NamedQuery(name = "PurchaseEstimateItem.findByCgst", query = "SELECT p FROM PurchaseEstimateItem p WHERE p.cgst = :cgst")
    , @NamedQuery(name = "PurchaseEstimateItem.findByItemCode", query = "SELECT p FROM PurchaseEstimateItem p WHERE p.itemCode = :itemCode")
    , @NamedQuery(name = "PurchaseEstimateItem.findByNameOfTheItem", query = "SELECT p FROM PurchaseEstimateItem p WHERE p.nameOfTheItem = :nameOfTheItem")
    , @NamedQuery(name = "PurchaseEstimateItem.findByPurchaseEstimateId", query = "SELECT p FROM PurchaseEstimateItem p WHERE p.purchaseEstimateId = :purchaseEstimateId")
    , @NamedQuery(name = "PurchaseEstimateItem.findByQty", query = "SELECT p FROM PurchaseEstimateItem p WHERE p.qty = :qty")
    , @NamedQuery(name = "PurchaseEstimateItem.findByTpRate", query = "SELECT p FROM PurchaseEstimateItem p WHERE p.tpRate = :tpRate")
    , @NamedQuery(name = "PurchaseEstimateItem.findByUnit", query = "SELECT p FROM PurchaseEstimateItem p WHERE p.unit = :unit")
    , @NamedQuery(name = "PurchaseEstimateItem.findByVat", query = "SELECT p FROM PurchaseEstimateItem p WHERE p.vat = :vat")
    , @NamedQuery(name = "PurchaseEstimateItem.findByTax", query = "SELECT p FROM PurchaseEstimateItem p WHERE p.tax = :tax")
    , @NamedQuery(name = "PurchaseEstimateItem.findByPeId", query = "SELECT p FROM PurchaseEstimateItem p WHERE p.peId = :peId")
    , @NamedQuery(name = "PurchaseEstimateItem.findByTaxCgst", query = "SELECT p FROM PurchaseEstimateItem p WHERE p.taxCgst = :taxCgst")
    , @NamedQuery(name = "PurchaseEstimateItem.findByTaxSgst", query = "SELECT p FROM PurchaseEstimateItem p WHERE p.taxSgst = :taxSgst")})
public class PurchaseEstimateItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount",columnDefinition = "DECIMAL(15,2)")
    private Double amount;
    @Column(name = "cgst",columnDefinition = "DECIMAL(15,2)")
    private Double cgst;
    @Column(name = "igst",columnDefinition = "DECIMAL(15,2)")
    private Double igst;
    @Size(max = 150)
    @Column(name = "item_code")
    private String itemCode;
    @Size(max = 150)
    @Column(name = "name_of_the_item")
    private String nameOfTheItem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "purchase_estimate_id")
    private String purchaseEstimateId;
    @Column(name = "qty",columnDefinition = "DECIMAL(15,2)")
    private Double qty;
    @Column(name = "tp_rate",columnDefinition = "DECIMAL(15,2)")
    private Double tpRate;
    @Column(name = "unit",columnDefinition = "DECIMAL(15,2)")
    private Double unit;
    @Column(name = "vat",columnDefinition = "DECIMAL(15,2)")
    private Double vat;
    @Column(name = "tax",columnDefinition = "DECIMAL(15,2)")
    private Double tax;

    public Double getIgst() {
        return igst;
    }

    public void setIgst(Double igst) {
        this.igst = igst;
    }

    public Double getTaxIgst() {
        return taxIgst;
    }

    public void setTaxIgst(Double taxIgst) {
        this.taxIgst = taxIgst;
    }
    @Basic(optional = false)
    @NotNull
    @Column(name = "pe_id")
    private int peId;
    @Column(name = "tax_cgst",columnDefinition = "DECIMAL(15,2)")
    private Double taxCgst;
    @Column(name = "tax_sgst",columnDefinition = "DECIMAL(15,2)")
    private Double taxSgst;
     @Column(name = "tax_igst",columnDefinition = "DECIMAL(15,2)")
    private Double taxIgst;

    public PurchaseEstimateItem() {
    }

    public PurchaseEstimateItem(Integer id) {
        this.id = id;
    }

    public PurchaseEstimateItem(Integer id, String purchaseEstimateId, int peId) {
        this.id = id;
        this.purchaseEstimateId = purchaseEstimateId;
        this.peId = peId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCgst() {
        return cgst;
    }

    public void setCgst(Double cgst) {
        this.cgst = cgst;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getNameOfTheItem() {
        return nameOfTheItem;
    }

    public void setNameOfTheItem(String nameOfTheItem) {
        this.nameOfTheItem = nameOfTheItem;
    }

    public String getPurchaseEstimateId() {
        return purchaseEstimateId;
    }

    public void setPurchaseEstimateId(String purchaseEstimateId) {
        this.purchaseEstimateId = purchaseEstimateId;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getTpRate() {
        return tpRate;
    }

    public void setTpRate(Double tpRate) {
        this.tpRate = tpRate;
    }

    public Double getUnit() {
        return unit;
    }

    public void setUnit(Double unit) {
        this.unit = unit;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public int getPeId() {
        return peId;
    }

    public void setPeId(int peId) {
        this.peId = peId;
    }

    public Double getTaxCgst() {
        return taxCgst;
    }

    public void setTaxCgst(Double taxCgst) {
        this.taxCgst = taxCgst;
    }

    public Double getTaxSgst() {
        return taxSgst;
    }

    public void setTaxSgst(Double taxSgst) {
        this.taxSgst = taxSgst;
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
        if (!(object instanceof PurchaseEstimateItem)) {
            return false;
        }
        PurchaseEstimateItem other = (PurchaseEstimateItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.PurchaseEstimateItem[ id=" + id + " ]";
    }
    
}
