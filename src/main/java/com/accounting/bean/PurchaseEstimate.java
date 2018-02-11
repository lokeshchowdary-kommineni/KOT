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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author RajRasu
 */
@Entity
@Table(name = "purchase_estimate")
public class PurchaseEstimate implements Serializable {

    private static final long serialVersionUID = 1L;
//    
    @Id
    @GenericGenerator(name="seq_id", strategy="com.accounting.idGeneration.PurchaseEstimate")
  @GeneratedValue(generator="seq_id") 
    @Column(name = "purchase_estimate_id")
    private String purchaseEstimateId;

    public String getPurchaseEstimateId() {
        return purchaseEstimateId;
    }

    public void setPurchaseEstimateId(String purchaseEstimateId) {
        this.purchaseEstimateId = purchaseEstimateId;
    }
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount",columnDefinition = "DECIMAL(15,2)")
    private Double amount;
    @Column(name = "cgst",columnDefinition = "DECIMAL(15,2)")
    private Double cgst;

     @Column(name = "igst",columnDefinition = "DECIMAL(15,2)")
    private Double igst;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy") 

    private Date date;
    @Size(max = 50)
    @Column(name = "item_code")
    private String itemCode;
    @Size(max = 50)
    @Column(name = "name_of_the_item")
    private String nameOfTheItem;
    @Column(name = "qty",columnDefinition = "DECIMAL(15,2)")
    private Double qty;
    @Column(name = "total_amount",columnDefinition = "DECIMAL(15,2)")
    private Double totalAmount;
    @Column(name = "tp_rate",columnDefinition = "DECIMAL(15,2)")
    private Double tpRate;
    @Column(name = "unit",columnDefinition = "DECIMAL(15,2)")
    private Double unit;
    @Column(name = "vat",columnDefinition = "DECIMAL(15,2)")
    private Double vat;
    @Basic(optional = false)

    @Column(name = "id")
    private int id;
    @Column(name = "total_cgst",columnDefinition = "DECIMAL(15,2)")
    private Double totalCgst;
    @Column(name = "total_vat",columnDefinition = "DECIMAL(15,2)")
    private Double totalVat;
    @Size(max = 30)
    @Column(name = "pos")
    private String pos;
    @Size(max = 30)
    public String getIsIgst() {
        return isIgst;
    }

    public void setIsIgst(String isIgst) {
        this.isIgst = isIgst;
    }
    @Size(max = 30)
    @Column(name = "is_igst")
    private String isIgst;
     

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
   
    

    public PurchaseEstimate() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Double getIgst() {
        return igst;
    }

    public void setIgst(Double igst) {
        this.igst = igst;
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

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTotalCgst() {
        return totalCgst;
    }

    public void setTotalCgst(Double totalCgst) {
        this.totalCgst = totalCgst;
    }

    public Double getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(Double totalVat) {
        this.totalVat = totalVat;
    }

 

    
}
