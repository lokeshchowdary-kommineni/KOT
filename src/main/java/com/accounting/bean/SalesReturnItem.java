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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shine
 */
@Entity
@Table(name = "sales_return_item")
public class SalesReturnItem implements Serializable {

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
    @Size(max = 30)
    @Column(name = "cpcpv_amopunt")
    private String cpcpvAmopunt;
    @Size(max = 30)
    @Column(name = "cpcpv_amount")
    private String cpcpvAmount;
    @Column(name = "discount",columnDefinition = "DECIMAL(15,2)")
    private Double discount;
    @Column(name = "ep_epV",columnDefinition = "DECIMAL(15,2)")
    private Double epepV;
    @Size(max = 255)
    @Column(name = "invoice_no")
    private String invoiceNo;
    @Size(max = 20)
    @Column(name = "item_code")
    private String itemCode;
    @Size(max = 40)
    @Column(name = "item_name")
    private String itemName;
    @Size(max = 20)
    @Column(name = "margin")
    private String margin;
    @Size(max = 30)
    @Column(name = "mcmca")
    private String mcmca;
    @Size(max = 30)
    @Column(name = "mcmca_amount")
    private String mcmcaAmount;
    @Column(name = "quantity",columnDefinition = "DECIMAL(15,2)")
    private Double quantity;
    @Column(name = "rate",columnDefinition = "DECIMAL(15,2)")
    private Double rate;
    @Column(name = "return_quantity",columnDefinition = "DECIMAL(15,2)")
    private Double returnQuantity;
    @Size(max = 30)
    @Column(name = "tax")
    private String tax;
    @Column(name = "unit")
    private Integer unit;
    @Column(name = "vat",columnDefinition = "DECIMAL(15,2)")
    private Double vat;
    @Column(name = "WithoutTax",columnDefinition = "DECIMAL(15,2)")
    private Double withoutTax;
    @Column(name = "tax_cgst",columnDefinition = "DECIMAL(15,2)")
    private Double taxCgst;
    @Column(name = "tax_sgst",columnDefinition = "DECIMAL(15,2)")
    private Double taxSgst;
    @Column(name = "tax_igst",columnDefinition = "DECIMAL(15,2)")
    private Double taxIgst;
    @Column(name = "igst",columnDefinition = "DECIMAL(15,2)")
    private Double igst;

    public Double getTaxIgst() {
        return taxIgst;
    }

    public void setTaxIgst(Double taxIgst) {
        this.taxIgst = taxIgst;
    }

    public Double getIgst() {
        return igst;
    }

    public void setIgst(Double igst) {
        this.igst = igst;
    }
    public SalesReturnItem() {
    }

    public SalesReturnItem(Integer id) {
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

    public String getCpcpvAmopunt() {
        return cpcpvAmopunt;
    }

    public void setCpcpvAmopunt(String cpcpvAmopunt) {
        this.cpcpvAmopunt = cpcpvAmopunt;
    }

    public String getCpcpvAmount() {
        return cpcpvAmount;
    }

    public void setCpcpvAmount(String cpcpvAmount) {
        this.cpcpvAmount = cpcpvAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getEpepV() {
        return epepV;
    }

    public void setEpepV(Double epepV) {
        this.epepV = epepV;
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

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getMcmca() {
        return mcmca;
    }

    public void setMcmca(String mcmca) {
        this.mcmca = mcmca;
    }

    public String getMcmcaAmount() {
        return mcmcaAmount;
    }

    public void setMcmcaAmount(String mcmcaAmount) {
        this.mcmcaAmount = mcmcaAmount;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(Double returnQuantity) {
        this.returnQuantity = returnQuantity;
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

    public Double getWithoutTax() {
        return withoutTax;
    }

    public void setWithoutTax(Double withoutTax) {
        this.withoutTax = withoutTax;
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
        if (!(object instanceof SalesReturnItem)) {
            return false;
        }
        SalesReturnItem other = (SalesReturnItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.SalesReturnItem[ id=" + id + " ]";
    }
    
}
