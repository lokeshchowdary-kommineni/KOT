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
@Table(name = "voucher_journal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoucherJournal.findAll", query = "SELECT v FROM VoucherJournal v")
    , @NamedQuery(name = "VoucherJournal.findByVouchId", query = "SELECT v FROM VoucherJournal v WHERE v.vouchId = :vouchId")
    , @NamedQuery(name = "VoucherJournal.findByVouchDate", query = "SELECT v FROM VoucherJournal v WHERE v.vouchDate = :vouchDate")
    , @NamedQuery(name = "VoucherJournal.findByVouchAmount", query = "SELECT v FROM VoucherJournal v WHERE v.vouchAmount = :vouchAmount")
    , @NamedQuery(name = "VoucherJournal.findByVouchFrom", query = "SELECT v FROM VoucherJournal v WHERE v.vouchFrom = :vouchFrom")
    , @NamedQuery(name = "VoucherJournal.findByVouchBalFrom", query = "SELECT v FROM VoucherJournal v WHERE v.vouchBalFrom = :vouchBalFrom")
    , @NamedQuery(name = "VoucherJournal.findByVouchCashType", query = "SELECT v FROM VoucherJournal v WHERE v.vouchCashType = :vouchCashType")
    , @NamedQuery(name = "VoucherJournal.findByVouchTo", query = "SELECT v FROM VoucherJournal v WHERE v.vouchTo = :vouchTo")
    , @NamedQuery(name = "VoucherJournal.findByVouchBalTo", query = "SELECT v FROM VoucherJournal v WHERE v.vouchBalTo = :vouchBalTo")
    , @NamedQuery(name = "VoucherJournal.findByVouchCheqType", query = "SELECT v FROM VoucherJournal v WHERE v.vouchCheqType = :vouchCheqType")
    , @NamedQuery(name = "VoucherJournal.findByVouchNarration", query = "SELECT v FROM VoucherJournal v WHERE v.vouchNarration = :vouchNarration")
    , @NamedQuery(name = "VoucherJournal.findByCreatedOn", query = "SELECT v FROM VoucherJournal v WHERE v.createdOn = :createdOn")
    , @NamedQuery(name = "VoucherJournal.findByCreatedBy", query = "SELECT v FROM VoucherJournal v WHERE v.createdBy = :createdBy")
    , @NamedQuery(name = "VoucherJournal.findByModifiedOn", query = "SELECT v FROM VoucherJournal v WHERE v.modifiedOn = :modifiedOn")
    , @NamedQuery(name = "VoucherJournal.findByModifiedBy", query = "SELECT v FROM VoucherJournal v WHERE v.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "VoucherJournal.findByVouchNo", query = "SELECT v FROM VoucherJournal v WHERE v.vouchNo = :vouchNo")})
public class VoucherJournal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vouch_id")
    private Integer vouchId;
    @Column(name = "vouch_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date vouchDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vouch_amount",columnDefinition = "DECIMAL(15,2)")
    private Double vouchAmount;   
    @Column(name = "vouch_from")
    private Integer vouchFrom;
    @Column(name = "vouch_bal_from",columnDefinition = "DECIMAL(15,2)")
    private Double vouchBalFrom;
    @Size(max = 20)
    @Column(name = "vouch_cash_type")
    private String vouchCashType;    
    @Column(name = "vouch_to")
    private Integer vouchTo;
    @Column(name = "vouch_bal_to",columnDefinition = "DECIMAL(15,2)")
    private Double vouchBalTo;
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
    @Column(name = "vouch_no")
    private String vouchNo;
      private String mode;
        private String modeOn;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getModeOn() {
        return modeOn;
    }

    public void setModeOn(String modeOn) {
        this.modeOn = modeOn;
    }

    public VoucherJournal() {
    }

    public VoucherJournal(Integer vouchId) {
        this.vouchId = vouchId;
    }

    public Integer getVouchId() {
        return vouchId;
    }

    public void setVouchId(Integer vouchId) {
        this.vouchId = vouchId;
    }

    public Date getVouchDate() {
        return vouchDate;
    }

    public void setVouchDate(Date vouchDate) {
        this.vouchDate = vouchDate;
    }

    public Double getVouchAmount() {
        return vouchAmount;
    }

    public void setVouchAmount(Double vouchAmount) {
        this.vouchAmount = vouchAmount;
    }

    public Integer getVouchFrom() {
        return vouchFrom;
    }

    public void setVouchFrom(Integer vouchFrom) {
        this.vouchFrom = vouchFrom;
    }

    public Double getVouchBalFrom() {
        return vouchBalFrom;
    }

    public void setVouchBalFrom(Double vouchBalFrom) {
        this.vouchBalFrom = vouchBalFrom;
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

    public Double getVouchBalTo() {
        return vouchBalTo;
    }

    public void setVouchBalTo(Double vouchBalTo) {
        this.vouchBalTo = vouchBalTo;
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

    public String getVouchNo() {
        return vouchNo;
    }

    public void setVouchNo(String vouchNo) {
        this.vouchNo = vouchNo;
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
        if (!(object instanceof VoucherJournal)) {
            return false;
        }
        VoucherJournal other = (VoucherJournal) object;
        if ((this.vouchId == null && other.vouchId != null) || (this.vouchId != null && !this.vouchId.equals(other.vouchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.VoucherJournal[ vouchId=" + vouchId + " ]";
    }
    
}
