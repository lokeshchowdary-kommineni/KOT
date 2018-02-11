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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MR
 */
@Entity
@Table(name = "sale_invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SaleInvoice.findAll", query = "SELECT s FROM SaleInvoice s")
    , @NamedQuery(name = "SaleInvoice.findByInvoiceNo", query = "SELECT s FROM SaleInvoice s WHERE s.invoiceNo = :invoiceNo")
    , @NamedQuery(name = "SaleInvoice.findByAdditionalcharges", query = "SELECT s FROM SaleInvoice s WHERE s.additionalcharges = :additionalcharges")
    , @NamedQuery(name = "SaleInvoice.findByAudittotalamount", query = "SELECT s FROM SaleInvoice s WHERE s.audittotalamount = :audittotalamount")
    , @NamedQuery(name = "SaleInvoice.findByCAtotal", query = "SELECT s FROM SaleInvoice s WHERE s.cAtotal = :cAtotal")
    , @NamedQuery(name = "SaleInvoice.findByCCtotal", query = "SELECT s FROM SaleInvoice s WHERE s.cCtotal = :cCtotal")
    , @NamedQuery(name = "SaleInvoice.findByCancledbill", query = "SELECT s FROM SaleInvoice s WHERE s.cancledbill = :cancledbill")
    , @NamedQuery(name = "SaleInvoice.findByCgstamount", query = "SELECT s FROM SaleInvoice s WHERE s.cgstamount = :cgstamount")
    , @NamedQuery(name = "SaleInvoice.findByCustomertotalamount", query = "SELECT s FROM SaleInvoice s WHERE s.customertotalamount = :customertotalamount")
    , @NamedQuery(name = "SaleInvoice.findByDiscount", query = "SELECT s FROM SaleInvoice s WHERE s.discount = :discount")
    , @NamedQuery(name = "SaleInvoice.findByGrandtotal", query = "SELECT s FROM SaleInvoice s WHERE s.grandtotal = :grandtotal")
    , @NamedQuery(name = "SaleInvoice.findByNettotal", query = "SELECT s FROM SaleInvoice s WHERE s.nettotal = :nettotal")
    , @NamedQuery(name = "SaleInvoice.findByPayementmode", query = "SELECT s FROM SaleInvoice s WHERE s.payementmode = :payementmode")
    , @NamedQuery(name = "SaleInvoice.findByRoundoff", query = "SELECT s FROM SaleInvoice s WHERE s.roundoff = :roundoff")
    , @NamedQuery(name = "SaleInvoice.findBySdate", query = "SELECT s FROM SaleInvoice s WHERE s.sdate = :sdate")
    , @NamedQuery(name = "SaleInvoice.findBySgstamount", query = "SELECT s FROM SaleInvoice s WHERE s.sgstamount = :sgstamount")
    , @NamedQuery(name = "SaleInvoice.findByWaiterName", query = "SELECT s FROM SaleInvoice s WHERE s.waiterName = :waiterName")
    , @NamedQuery(name = "SaleInvoice.findByRgb", query = "SELECT s FROM SaleInvoice s WHERE s.rgb = :rgb")})
public class SaleInvoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "invoice_no")
    private String invoiceNo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "additionalcharges")
    private BigDecimal additionalcharges;
    @Column(name = "audittotalamount")
    private BigDecimal audittotalamount;
    @Column(name = "C_A_total")
    private BigDecimal cAtotal;
    @Column(name = "C_C_total")
    private BigDecimal cCtotal;
    @Size(max = 20)
    @Column(name = "cancledbill")
    private String cancledbill;
    @Column(name = "cgstamount")
    private BigDecimal cgstamount;
    @Column(name = "customertotalamount")
    private BigDecimal customertotalamount;
    @Column(name = "discount")
    private BigDecimal discount;
    @Column(name = "grandtotal")
    private BigDecimal grandtotal;
    @Column(name = "nettotal")
    private BigDecimal nettotal;
    @Size(max = 30)
    @Column(name = "payementmode")
    private String payementmode;
    @Column(name = "roundoff")
    private BigDecimal roundoff;
    @Size(max = 30)
    @Column(name = "sdate")
    private String sdate;
    @Column(name = "sgstamount")
    private BigDecimal sgstamount;
    @Size(max = 50)
    @Column(name = "waiter_name")
    private String waiterName;
    @Size(max = 20)
    @Column(name = "rgb")
    private String rgb;
    private String cashAmount;
    private String cardAmount;


    public String getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(String cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(String cardAmount) {
        this.cardAmount = cardAmount;
    }
  
    public SaleInvoice() {
    }

    public SaleInvoice(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public BigDecimal getAdditionalcharges() {
        return additionalcharges;
    }

    public void setAdditionalcharges(BigDecimal additionalcharges) {
        this.additionalcharges = additionalcharges;
    }

    public BigDecimal getAudittotalamount() {
        return audittotalamount;
    }

    public void setAudittotalamount(BigDecimal audittotalamount) {
        this.audittotalamount = audittotalamount;
    }

    public BigDecimal getCAtotal() {
        return cAtotal;
    }

    public void setCAtotal(BigDecimal cAtotal) {
        this.cAtotal = cAtotal;
    }

    public BigDecimal getCCtotal() {
        return cCtotal;
    }

    public void setCCtotal(BigDecimal cCtotal) {
        this.cCtotal = cCtotal;
    }

    public String getCancledbill() {
        return cancledbill;
    }

    public void setCancledbill(String cancledbill) {
        this.cancledbill = cancledbill;
    }

    public BigDecimal getCgstamount() {
        return cgstamount;
    }

    public void setCgstamount(BigDecimal cgstamount) {
        this.cgstamount = cgstamount;
    }

    public BigDecimal getCustomertotalamount() {
        return customertotalamount;
    }

    public void setCustomertotalamount(BigDecimal customertotalamount) {
        this.customertotalamount = customertotalamount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(BigDecimal grandtotal) {
        this.grandtotal = grandtotal;
    }

    public BigDecimal getNettotal() {
        return nettotal;
    }

    public void setNettotal(BigDecimal nettotal) {
        this.nettotal = nettotal;
    }

    public String getPayementmode() {
        return payementmode;
    }

    public void setPayementmode(String payementmode) {
        this.payementmode = payementmode;
    }

    public BigDecimal getRoundoff() {
        return roundoff;
    }

    public void setRoundoff(BigDecimal roundoff) {
        this.roundoff = roundoff;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public BigDecimal getSgstamount() {
        return sgstamount;
    }

    public void setSgstamount(BigDecimal sgstamount) {
        this.sgstamount = sgstamount;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
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
        if (!(object instanceof SaleInvoice)) {
            return false;
        }
        SaleInvoice other = (SaleInvoice) object;
        if ((this.invoiceNo == null && other.invoiceNo != null) || (this.invoiceNo != null && !this.invoiceNo.equals(other.invoiceNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.SaleInvoice[ invoiceNo=" + invoiceNo + " ]";
    }
    
}
