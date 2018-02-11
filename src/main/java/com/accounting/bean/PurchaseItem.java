/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.bean;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shine
 */
@Entity
@Table(name = "purchase_item")
@XmlRootElement
public class PurchaseItem implements Serializable {

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
    @Column(name = "ep",columnDefinition = "DECIMAL(15,2)")
    private Double ep;
    @Size(max = 255)
    @Column(name = "invoice_no")
    private String invoiceNo;
    @Size(max = 20)
    @Column(name = "item_code")
    private String itemCode;
    @Size(max = 150)
    @Column(name = "name_of_the_item")
    private String nameOfTheItem;
    @Column(name = "purchase_invoice_id")
    private Integer purchaseInvoiceId;
    @Column(name = "qty",columnDefinition = "DECIMAL(15,2)")
    private Double qty;
    @Column(name = "rate",columnDefinition = "DECIMAL(15,2)")
    private Double rate;
    @Size(max = 10)
    @Column(name = "tax")
    private String tax;

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
    @Column(name = "unit")
    private Integer unit;
    @Column(name = "vat",columnDefinition = "DECIMAL(15,2)")
    private Double vat;
     @Column(name = "igst",columnDefinition = "DECIMAL(15,2)")
    private Double igst;
    @Column(name = "tax_cgst",columnDefinition = "DECIMAL(15,2)")
    private Double taxCgst;
    @Column(name = "tax_sgst",columnDefinition = "DECIMAL(15,2)")
    private Double taxSgst;
    @Column(name = "tax_igst",columnDefinition = "DECIMAL(15,2)")
    private Double taxIgst;
    @Column(name = "price_cp_update")
    private Integer cpPriceUpdate;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PurchaseItem() {
    }

    public Integer getCpPriceUpdate() {
        return cpPriceUpdate;
    }

    public void setCpPriceUpdate(Integer cpPriceUpdate) {
        this.cpPriceUpdate = cpPriceUpdate;
    }

    public PurchaseItem(Integer id) {
        this.id = id;
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

    public Double getEp() {
        return ep;
    }

    public void setEp(Double ep) {
        this.ep = ep;
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

    public String getNameOfTheItem() {
        return nameOfTheItem;
    }

    public void setNameOfTheItem(String nameOfTheItem) {
        this.nameOfTheItem = nameOfTheItem;
    }

    public Integer getPurchaseInvoiceId() {
        return purchaseInvoiceId;
    }

    public void setPurchaseInvoiceId(Integer purchaseInvoiceId) {
        this.purchaseInvoiceId = purchaseInvoiceId;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
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
        if (!(object instanceof PurchaseItem)) {
            return false;
        }
        PurchaseItem other = (PurchaseItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.PurchaseItem[ id=" + id + " ]";
    }
    
}
