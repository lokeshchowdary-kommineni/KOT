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
 * @author MR
 */
@Entity
@Table(name = "sales_estimate")
public class SalesEstimate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name="seq_id", strategy="com.accounting.idGeneration.SalesEstimate")
    @GeneratedValue(generator="seq_id") 
    @Column(name = "invoice_no")
    private String invoiceNo;
    @Column(name = "add_less",columnDefinition = "DECIMAL(15,2)")
    private Double addLess;
    @Column(name = "assess_value",columnDefinition = "DECIMAL(15,2)")
    private Double assessValue;
    @Column(name = "buyer_id")
    private Integer buyerId;
    @Size(max = 30)
    @Column(name = "cash_buyer")
    private String cashBuyer;
    @Size(max = 30)
    @Column(name = "category")
    private String category;
    @Basic(optional = false)
    
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
     @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date date;
    @Column(name = "invoice_amount",columnDefinition = "DECIMAL(15,2)")
    private Double invoiceAmount;
    @Column(name = "mca",columnDefinition = "DECIMAL(15,2)")
    private Double mca;
    @Size(max = 10)
    @Column(name = "mediator_id")
    private String mediatorId;
    @Size(max = 20)
    @Column(name = "mode")
    private String mode;
    @Size(max = 30)
    @Column(name = "name_of_buyer")
    private String nameOfBuyer;
    @Size(max = 30)
    @Column(name = "name_of_mediator")
    private String nameOfMediator;
    @Size(max = 100)
    @Column(name = "narration")
    private String narration;
    @Size(max = 10)
    @Column(name = "tax_percentage")
    private String taxPercentage;
    @Size(max = 20)
    @Column(name = "tin_no")
    private String tinNo;
    @Column(name = "total_cgst",columnDefinition = "DECIMAL(15,2)")
    private Double totalCgst;
    @Column(name = "total_vat",columnDefinition = "DECIMAL(15,2)")
    private Double totalVat;
    @Column(name = "vaa",columnDefinition = "DECIMAL(15,2)")
    private Double vaa;
    @Size(max = 50)
    @Column(name = "status")
    private String status;
    @Column(name = "total_igst",columnDefinition = "DECIMAL(15,2)")
    private Double totalIgst;
    @Size(max = 30)
    @Column(name = "is_igst")
    private String isIgst;
    @Size(max = 30)
    @Column(name = "pos")
    private String pos;
    public Double getTotalIgst() {
        return totalIgst;
    }

    public void setTotalIgst(Double totalIgst) {
        this.totalIgst = totalIgst;
    }

    public String getIsIgst() {
        return isIgst;
    }

    public void setIsIgst(String isIgst) {
        this.isIgst = isIgst;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }    
    public SalesEstimate() {
    }

    public SalesEstimate(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public SalesEstimate(String invoiceNo, Date date) {
        this.invoiceNo = invoiceNo;
        this.date = date;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Double getAddLess() {
        return addLess;
    }

    public void setAddLess(Double addLess) {
        this.addLess = addLess;
    }

    public Double getAssessValue() {
        return assessValue;
    }

    public void setAssessValue(Double assessValue) {
        this.assessValue = assessValue;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getCashBuyer() {
        return cashBuyer;
    }

    public void setCashBuyer(String cashBuyer) {
        this.cashBuyer = cashBuyer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Double getMca() {
        return mca;
    }

    public void setMca(Double mca) {
        this.mca = mca;
    }

    public String getMediatorId() {
        return mediatorId;
    }

    public void setMediatorId(String mediatorId) {
        this.mediatorId = mediatorId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getNameOfBuyer() {
        return nameOfBuyer;
    }

    public void setNameOfBuyer(String nameOfBuyer) {
        this.nameOfBuyer = nameOfBuyer;
    }

    public String getNameOfMediator() {
        return nameOfMediator;
    }

    public void setNameOfMediator(String nameOfMediator) {
        this.nameOfMediator = nameOfMediator;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(String taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
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

    public Double getVaa() {
        return vaa;
    }

    public void setVaa(Double vaa) {
        this.vaa = vaa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceNo != null ? invoiceNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalesEstimate)) {
            return false;
        }
        SalesEstimate other = (SalesEstimate) object;
        if ((this.invoiceNo == null && other.invoiceNo != null) || (this.invoiceNo != null && !this.invoiceNo.equals(other.invoiceNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.SalesEstimate[ invoiceNo=" + invoiceNo + " ]";
    }
    
}
