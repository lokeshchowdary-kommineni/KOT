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

/**
 *
 * @author SHINELOGICS
 */
@Entity
@Table(name = "receipt_voucher")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReceiptVoucher.findAll", query = "SELECT r FROM ReceiptVoucher r"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchId", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchId = :vouchId"),
    @NamedQuery(name = "ReceiptVoucher.findByAuthorisation", query = "SELECT r FROM ReceiptVoucher r WHERE r.authorisation = :authorisation"),
    @NamedQuery(name = "ReceiptVoucher.findByCreatedBy", query = "SELECT r FROM ReceiptVoucher r WHERE r.createdBy = :createdBy"),
    @NamedQuery(name = "ReceiptVoucher.findByCreatedOn", query = "SELECT r FROM ReceiptVoucher r WHERE r.createdOn = :createdOn"),
    @NamedQuery(name = "ReceiptVoucher.findByModifiedBy", query = "SELECT r FROM ReceiptVoucher r WHERE r.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "ReceiptVoucher.findByModifiedOn", query = "SELECT r FROM ReceiptVoucher r WHERE r.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchAmount", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchAmount = :vouchAmount"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchBal", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchBal = :vouchBal"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchCashDate", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchCashDate = :vouchCashDate"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchCashType", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchCashType = :vouchCashType"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchCheqBal", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchCheqBal = :vouchCheqBal"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchCheqDate", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchCheqDate = :vouchCheqDate"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchCheqNo", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchCheqNo = :vouchCheqNo"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchCheqType", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchCheqType = :vouchCheqType"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchFrom", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchFrom = :vouchFrom"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchMode", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchMode = :vouchMode"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchNameofBank", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchNameofBank = :vouchNameofBank"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchNarration", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchNarration = :vouchNarration"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchTo", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchTo = :vouchTo"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchType", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchType = :vouchType"),
    @NamedQuery(name = "ReceiptVoucher.findByVouchdepositTobank", query = "SELECT r FROM ReceiptVoucher r WHERE r.vouchdepositTobank = :vouchdepositTobank")})
public class ReceiptVoucher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vouch_id")
    private Integer vouchId;
    @Size(max = 20)
    @Column(name = "authorisation")
    private String authorisation;
    @Size(max = 20)
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Size(max = 20)
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "modified_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vouch_amount",columnDefinition = "DECIMAL(15,2)")
    private Double vouchAmount;
    @Column(name = "vouch_bal",columnDefinition = "DECIMAL(15,2)")
    private Double vouchBal;
    @Column(name = "vouch_cash_date")
    @Temporal(TemporalType.DATE)
    private Date vouchCashDate;
    @Size(max = 20)
    @Column(name = "vouch_cash_type")
    private String vouchCashType;
    @Column(name = "vouch_cheq_bal",columnDefinition = "DECIMAL(15,2)")
    private Double vouchCheqBal;
    @Column(name = "vouch_cheq_date")
    @Temporal(TemporalType.DATE)
    private Date vouchCheqDate;
    @Column(name = "vouch_cheq_no")
    private Integer vouchCheqNo;
    @Size(max = 20)
    @Column(name = "vouch_cheq_type")
    private String vouchCheqType;
    @Size(max = 20)
    @Column(name = "vouch_from")
    private String vouchFrom;
    @Size(max = 20)
    @Column(name = "vouch_mode")
    private String vouchMode;
    @Size(max = 50)
    @Column(name = "vouch_nameof_bank")
    private String vouchNameofBank;
    @Size(max = 250)
    @Column(name = "vouch_narration")
    private String vouchNarration;
    @Size(max = 20)
    @Column(name = "vouch_to")
    private String vouchTo;
    @Size(max = 20)
    @Column(name = "vouch_type")
    private String vouchType;
    @Size(max = 250)
    @Column(name = "vouch_depositTo_bank")
    private String vouchdepositTobank;

    public ReceiptVoucher() {
    }

    public ReceiptVoucher(Integer vouchId) {
        this.vouchId = vouchId;
    }

    public Integer getVouchId() {
        return vouchId;
    }

    public void setVouchId(Integer vouchId) {
        this.vouchId = vouchId;
    }

    public String getAuthorisation() {
        return authorisation;
    }

    public void setAuthorisation(String authorisation) {
        this.authorisation = authorisation;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Double getVouchAmount() {
        return vouchAmount;
    }

    public void setVouchAmount(Double vouchAmount) {
        this.vouchAmount = vouchAmount;
    }

    public Double getVouchBal() {
        return vouchBal;
    }

    public void setVouchBal(Double vouchBal) {
        this.vouchBal = vouchBal;
    }

    public Date getVouchCashDate() {
        return vouchCashDate;
    }

    public void setVouchCashDate(Date vouchCashDate) {
        this.vouchCashDate = vouchCashDate;
    }

    public String getVouchCashType() {
        return vouchCashType;
    }

    public void setVouchCashType(String vouchCashType) {
        this.vouchCashType = vouchCashType;
    }

    public Double getVouchCheqBal() {
        return vouchCheqBal;
    }

    public void setVouchCheqBal(Double vouchCheqBal) {
        this.vouchCheqBal = vouchCheqBal;
    }

    public Date getVouchCheqDate() {
        return vouchCheqDate;
    }

    public void setVouchCheqDate(Date vouchCheqDate) {
        this.vouchCheqDate = vouchCheqDate;
    }

    public Integer getVouchCheqNo() {
        return vouchCheqNo;
    }

    public void setVouchCheqNo(Integer vouchCheqNo) {
        this.vouchCheqNo = vouchCheqNo;
    }

    public String getVouchCheqType() {
        return vouchCheqType;
    }

    public void setVouchCheqType(String vouchCheqType) {
        this.vouchCheqType = vouchCheqType;
    }

    public String getVouchFrom() {
        return vouchFrom;
    }

    public void setVouchFrom(String vouchFrom) {
        this.vouchFrom = vouchFrom;
    }

    public String getVouchMode() {
        return vouchMode;
    }

    public void setVouchMode(String vouchMode) {
        this.vouchMode = vouchMode;
    }

    public String getVouchNameofBank() {
        return vouchNameofBank;
    }

    public void setVouchNameofBank(String vouchNameofBank) {
        this.vouchNameofBank = vouchNameofBank;
    }

    public String getVouchNarration() {
        return vouchNarration;
    }

    public void setVouchNarration(String vouchNarration) {
        this.vouchNarration = vouchNarration;
    }

    public String getVouchTo() {
        return vouchTo;
    }

    public void setVouchTo(String vouchTo) {
        this.vouchTo = vouchTo;
    }

    public String getVouchType() {
        return vouchType;
    }

    public void setVouchType(String vouchType) {
        this.vouchType = vouchType;
    }

    public String getVouchdepositTobank() {
        return vouchdepositTobank;
    }

    public void setVouchdepositTobank(String vouchdepositTobank) {
        this.vouchdepositTobank = vouchdepositTobank;
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
        if (!(object instanceof ReceiptVoucher)) {
            return false;
        }
        ReceiptVoucher other = (ReceiptVoucher) object;
        if ((this.vouchId == null && other.vouchId != null) || (this.vouchId != null && !this.vouchId.equals(other.vouchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.ReceiptVoucher[ vouchId=" + vouchId + " ]";
    }
    
}
