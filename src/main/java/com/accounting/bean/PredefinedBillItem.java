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
@Table(name = "predefined_bill_item")
public class PredefinedBillItem implements Serializable {

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
    @Column(name = "cp_cpV",columnDefinition = "DECIMAL(15,2)")
    private Double cpcpV;
    @Size(max = 30)
    @Column(name = "cpcpv_amount")
    private String cpcpvAmount;
    @Column(name = "discount",columnDefinition = "DECIMAL(15,2)")
    private Double discount=0.0;
    @Column(name = "ep_or_epv",columnDefinition = "DECIMAL(15,2)")
    private Double epOrEpv;
    @Column(name = "invoice_no")
    private Integer invoiceNo;
    @Size(max = 150)
    @Column(name = "item_code")
    private String itemCode;
    @Size(max = 50)
    @Column(name = "item_name")
    private String itemName;
    @Size(max = 11)
    @Column(name = "margin")
    private String margin;
    @Size(max = 30)
    @Column(name = "mcmca")
    private String mcmca;
    @Size(max = 30)
    @Column(name = "mcmca_amount")
    private String mcmcaAmount;
    @Size(max = 150)
    @Column(name = "name_of_the_item")
    private String nameOfTheItem;
    @Column(name = "predefined_bill_id")
    private Integer predefinedBillId;
    @Column(name = "qty",columnDefinition = "DECIMAL(15,2)")
    private Double qty;
    @Column(name = "quantity",columnDefinition = "DECIMAL(15,2)")
    private Double quantity;
    @Column(name = "rate",columnDefinition = "DECIMAL(15,2)")
    private Double rate;
    @Size(max = 30)
    @Column(name = "tax")
    private String tax;
    @Column(name = "unit")
    private Integer unit;
    @Column(name = "vat",columnDefinition = "DECIMAL(15,2)")
    private Double vat;
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
    public PredefinedBillItem() {
    }

    public PredefinedBillItem(Integer id) {
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

    public Double getCpcpV() {
        return cpcpV;
    }

    public void setCpcpV(Double cpcpV) {
        this.cpcpV = cpcpV;
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

    public Double getEpOrEpv() {
        return epOrEpv;
    }

    public void setEpOrEpv(Double epOrEpv) {
        this.epOrEpv = epOrEpv;
    }

    public Integer getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Integer invoiceNo) {
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

    public String getNameOfTheItem() {
        return nameOfTheItem;
    }

    public void setNameOfTheItem(String nameOfTheItem) {
        this.nameOfTheItem = nameOfTheItem;
    }

    public Integer getPredefinedBillId() {
        return predefinedBillId;
    }

    public void setPredefinedBillId(Integer predefinedBillId) {
        this.predefinedBillId = predefinedBillId;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
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
        if (!(object instanceof PredefinedBillItem)) {
            return false;
        }
        PredefinedBillItem other = (PredefinedBillItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.PredefinedBillItem[ id=" + id + " ]";
    }
    
}
