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
@Table(name = "voucher_contra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoucherContra.findAll", query = "SELECT v FROM VoucherContra v")
    , @NamedQuery(name = "VoucherContra.findByVouchId", query = "SELECT v FROM VoucherContra v WHERE v.vouchId = :vouchId")
    , @NamedQuery(name = "VoucherContra.findByVouchCashDate", query = "SELECT v FROM VoucherContra v WHERE v.vouchCashDate = :vouchCashDate")
    , @NamedQuery(name = "VoucherContra.findByVouchType", query = "SELECT v FROM VoucherContra v WHERE v.vouchType = :vouchType")
    , @NamedQuery(name = "VoucherContra.findByVouchAmount", query = "SELECT v FROM VoucherContra v WHERE v.vouchAmount = :vouchAmount")
    , @NamedQuery(name = "VoucherContra.findByVouchFrom", query = "SELECT v FROM VoucherContra v WHERE v.vouchFrom = :vouchFrom")
    , @NamedQuery(name = "VoucherContra.findByVouchBalFrom", query = "SELECT v FROM VoucherContra v WHERE v.vouchBalFrom = :vouchBalFrom")
    , @NamedQuery(name = "VoucherContra.findByVouchCashType", query = "SELECT v FROM VoucherContra v WHERE v.vouchCashType = :vouchCashType")
    , @NamedQuery(name = "VoucherContra.findByVouchBankName", query = "SELECT v FROM VoucherContra v WHERE v.vouchBankName = :vouchBankName")
    , @NamedQuery(name = "VoucherContra.findByVouchCheqNo", query = "SELECT v FROM VoucherContra v WHERE v.vouchCheqNo = :vouchCheqNo")
    , @NamedQuery(name = "VoucherContra.findByVouchBankDate", query = "SELECT v FROM VoucherContra v WHERE v.vouchBankDate = :vouchBankDate")
    , @NamedQuery(name = "VoucherContra.findByVouchTo", query = "SELECT v FROM VoucherContra v WHERE v.vouchTo = :vouchTo")
    , @NamedQuery(name = "VoucherContra.findByVouchBalTo", query = "SELECT v FROM VoucherContra v WHERE v.vouchBalTo = :vouchBalTo")
    , @NamedQuery(name = "VoucherContra.findByVouchCheqType", query = "SELECT v FROM VoucherContra v WHERE v.vouchCheqType = :vouchCheqType")
    , @NamedQuery(name = "VoucherContra.findByVouchNarration", query = "SELECT v FROM VoucherContra v WHERE v.vouchNarration = :vouchNarration")
    , @NamedQuery(name = "VoucherContra.findByCreatedOn", query = "SELECT v FROM VoucherContra v WHERE v.createdOn = :createdOn")
    , @NamedQuery(name = "VoucherContra.findByCreatedBy", query = "SELECT v FROM VoucherContra v WHERE v.createdBy = :createdBy")
    , @NamedQuery(name = "VoucherContra.findByModifiedOn", query = "SELECT v FROM VoucherContra v WHERE v.modifiedOn = :modifiedOn")
    , @NamedQuery(name = "VoucherContra.findByModifiedBy", query = "SELECT v FROM VoucherContra v WHERE v.modifiedBy = :modifiedBy")})
public class VoucherContra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vouch_id")
    private Integer vouchId;
    @Column(name = "vouch_cash_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date vouchCashDate;
    @Size(max = 20)
    @Column(name = "vouch_type")
    private String vouchType;
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
    @Size(max = 20)
    @Column(name = "vouch_bank_name")
    private String vouchBankName;
    @Size(max = 20)
    @Column(name = "vouch_cheq_no")
    private String  vouchCheqNo;
    @Column(name = "vouch_bank_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date vouchBankDate;  
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

    public VoucherContra() {
    }

    public VoucherContra(Integer vouchId) {
        this.vouchId = vouchId;
    }

    public Integer getVouchId() {
        return vouchId;
    }

    public void setVouchId(Integer vouchId) {
        this.vouchId = vouchId;
    }

    public Date getVouchCashDate() {
        return vouchCashDate;
    }

    public void setVouchCashDate(Date vouchCashDate) {
        this.vouchCashDate = vouchCashDate;
    }

    public String getVouchType() {
        return vouchType;
    }

    public void setVouchType(String vouchType) {
        this.vouchType = vouchType;
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

    public String getVouchBankName() {
        return vouchBankName;
    }

    public void setVouchBankName(String vouchBankName) {
        this.vouchBankName = vouchBankName;
    }

    public String getVouchCheqNo() {
        return vouchCheqNo;
    }

    public void setVouchCheqNo(String vouchCheqNo) {
        this.vouchCheqNo = vouchCheqNo;
    }

    public Date getVouchBankDate() {
        return vouchBankDate;
    }

    public void setVouchBankDate(Date vouchBankDate) {
        this.vouchBankDate = vouchBankDate;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vouchId != null ? vouchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoucherContra)) {
            return false;
        }
        VoucherContra other = (VoucherContra) object;
        if ((this.vouchId == null && other.vouchId != null) || (this.vouchId != null && !this.vouchId.equals(other.vouchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.VoucherContra[ vouchId=" + vouchId + " ]";
    }
    
}
