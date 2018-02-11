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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author MR
 */
@Entity
@Table(name = "voucher_receipt_and_payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoucherReceiptAndPayment.findAll", query = "SELECT v FROM VoucherReceiptAndPayment v")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchId", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchId = :vouchId")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchType", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchType = :vouchType")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchCashDate", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchCashDate = :vouchCashDate")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchMode", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchMode = :vouchMode")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchFrom", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchFrom = :vouchFrom")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchBal", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchBal = :vouchBal")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchCashType", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchCashType = :vouchCashType")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchTo", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchTo = :vouchTo")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchAmount", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchAmount = :vouchAmount")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchCheqNo", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchCheqNo = :vouchCheqNo")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchCheqDate", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchCheqDate = :vouchCheqDate")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchNameofBank", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchNameofBank = :vouchNameofBank")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchdepositTobank", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchdepositTobank = :vouchdepositTobank")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchCheqBal", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchCheqBal = :vouchCheqBal")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchCheqType", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchCheqType = :vouchCheqType")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByVouchNarration", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.vouchNarration = :vouchNarration")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByCreatedOn", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.createdOn = :createdOn")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByCreatedBy", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.createdBy = :createdBy")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByModifiedOn", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.modifiedOn = :modifiedOn")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByModifiedBy", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "VoucherReceiptAndPayment.findByAuthorisation", query = "SELECT v FROM VoucherReceiptAndPayment v WHERE v.authorisation = :authorisation")})
public class VoucherReceiptAndPayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vouch_id")
    private Integer vouchId;
    @Size(max = 20)
    @Column(name = "vouch_type")
    private String vouchType;
    @Column(name = "vouch_cash_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy") 
    private Date vouchCashDate;
    @Size(max = 20)
    @Column(name = "vouch_mode")
    private String vouchMode;  
    @Column(name = "vouch_from")
    private Integer vouchFrom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vouch_bal",columnDefinition = "DECIMAL(15,2)")
    private Double vouchBal;
    @Size(max = 20)
    @Column(name = "vouch_cash_type")
    private String vouchCashType;   
    @Column(name = "vouch_to")
    private Integer vouchTo;
    @Column(name = "vouch_amount",columnDefinition = "DECIMAL(15,2)")
    private Double vouchAmount;
     @Size(max = 20)
    @Column(name = "vouch_cheq_no")
    private String vouchCheqNo;
    @Column(name = "vouch_cheq_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date vouchCheqDate;
    @Size(max = 50)
    @Column(name = "vouch_nameof_bank")
    private String vouchNameofBank;   
    @Column(name = "vouch_depositTo_bank")
    private Integer vouchdepositTobank;
    @Column(name = "vouch_cheq_bal",columnDefinition = "DECIMAL(15,2)")
    private Double vouchCheqBal;
    @Size(max = 20)
    @Column(name = "vouch_cheq_type")
    private String vouchCheqType;
    @Size(max = 250)
    @Column(name = "vouch_narration")
    private String vouchNarration;
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date createdOn;
    @Size(max = 20)
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modified_on")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date modifiedOn;
    @Size(max = 20)
    @Column(name = "modified_by")
    private String modifiedBy;
    @Size(max = 20)
    @Column(name = "authorisation")
    private String authorisation;

    public VoucherReceiptAndPayment() {
    }

    public VoucherReceiptAndPayment(Integer vouchId) {
        this.vouchId = vouchId;
    }

    public Integer getVouchId() {
        return vouchId;
    }

    public void setVouchId(Integer vouchId) {
        this.vouchId = vouchId;
    }

    public String getVouchType() {
        return vouchType;
    }

    public void setVouchType(String vouchType) {
        this.vouchType = vouchType;
    }

    public Date getVouchCashDate() {
        return vouchCashDate;
    }

    public void setVouchCashDate(Date vouchCashDate) {
        this.vouchCashDate = vouchCashDate;
    }

    public String getVouchMode() {
        return vouchMode;
    }

    public void setVouchMode(String vouchMode) {
        this.vouchMode = vouchMode;
    }

    public Integer getVouchFrom() {
        return vouchFrom;
    }

    public void setVouchFrom(Integer vouchFrom) {
        this.vouchFrom = vouchFrom;
    }

    public Double getVouchBal() {
        return vouchBal;
    }

    public void setVouchBal(Double vouchBal) {
        this.vouchBal = vouchBal;
    }

    public String getVouchCashType() {
        return vouchCashType;
    }

    public void setVouchCashType(String vouchCashType) {
        this.vouchCashType = vouchCashType;
    }

    public Integer getVouchTo() {
        return vouchTo;
    }

    public void setVouchTo(Integer vouchTo) {
        this.vouchTo = vouchTo;
    }

    public Double getVouchAmount() {
        return vouchAmount;
    }

    public void setVouchAmount(Double vouchAmount) {
        this.vouchAmount = vouchAmount;
    }

    public String getVouchCheqNo() {
        return vouchCheqNo;
    }

    public void setVouchCheqNo(String vouchCheqNo) {
        this.vouchCheqNo = vouchCheqNo;
    }

    public Date getVouchCheqDate() {
        return vouchCheqDate;
    }

    public void setVouchCheqDate(Date vouchCheqDate) {
        this.vouchCheqDate = vouchCheqDate;
    }

    public String getVouchNameofBank() {
        return vouchNameofBank;
    }

    public void setVouchNameofBank(String vouchNameofBank) {
        this.vouchNameofBank = vouchNameofBank;
    }

    public Integer getVouchdepositTobank() {
        return vouchdepositTobank;
    }

    public void setVouchdepositTobank(Integer vouchdepositTobank) {
        this.vouchdepositTobank = vouchdepositTobank;
    }

    public Double getVouchCheqBal() {
        return vouchCheqBal;
    }

    public void setVouchCheqBal(Double vouchCheqBal) {
        this.vouchCheqBal = vouchCheqBal;
    }

    public String getVouchCheqType() {
        return vouchCheqType;
    }

    public void setVouchCheqType(String vouchCheqType) {
        this.vouchCheqType = vouchCheqType;
    }

    public String getVouchNarration() {
        return vouchNarration;
    }

    public void setVouchNarration(String vouchNarration) {
        this.vouchNarration = vouchNarration;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getAuthorisation() {
        return authorisation;
    }

    public void setAuthorisation(String authorisation) {
        this.authorisation = authorisation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vouchId != null ? vouchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoucherReceiptAndPayment)) {
            return false;
        }
        VoucherReceiptAndPayment other = (VoucherReceiptAndPayment) object;
        if ((this.vouchId == null && other.vouchId != null) || (this.vouchId != null && !this.vouchId.equals(other.vouchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.VoucherReceiptAndPayment[ vouchId=" + vouchId + " ]";
    }
    
}
