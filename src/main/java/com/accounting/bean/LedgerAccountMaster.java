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
import org.springframework.format.annotation.DateTimeFormat;


/**
 *
 * @author MR
 */
@Entity
@Table(name = "ledger_account_master")


public class LedgerAccountMaster implements Serializable {
    @Column(name = "predefined",updatable=false)
    private Integer predefined;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ledger")
    private Integer idLedger;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Size(max = 11,message = "Cell no should have 10 digits")
    @Column(name = "cell_no")
    private String cellNo;
    
    @Size(max = 50)
    @Column(name = "name_of_ledger")
    private String nameOfLedger;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "opening_amount",columnDefinition = "DECIMAL(15,2)")
    private Double openingAmount=0.0;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(name = "opening_date")
    @Temporal(TemporalType.DATE)
    private Date openingDate;
    @Size(max = 10)
    @Column(name = "opening_type")
    private String openingType;
    @Size(max = 10)
    @Column(name = "pan_no")
    private String panNo;
    @Column(name = "under_group")
    private int underGroup;
    
    @Size(max = 20)
    @Column(name = "r_id")
    private String rId;
    @Size(max = 20)
    @Column(name = "type")
    private String type;
    @Size(max = 20)
    @Column(name = "tin")
    private String tin;
    @Size(max = 20)
    @Column(name = "b_type")
    private String bType;

    public LedgerAccountMaster() {
    }

    public LedgerAccountMaster(Integer idLedger) {
        this.idLedger = idLedger;
    }

    public Integer getIdLedger() {
        return idLedger;
    }

    public void setIdLedger(Integer idLedger) {
        this.idLedger = idLedger;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getNameOfLedger() {
        return nameOfLedger;
    }

    public void setNameOfLedger(String nameOfLedger) {
        this.nameOfLedger = nameOfLedger;
    }

    public Double getOpeningAmount() {
        return openingAmount;
    }

    public void setOpeningAmount(Double openingAmount) {
        this.openingAmount = openingAmount;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public String getOpeningType() {
        return openingType;
    }

    public void setOpeningType(String openingType) {
        this.openingType = openingType;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public int getUnderGroup() {
        return underGroup;
    }

    public void setUnderGroup(int underGroup) {
        this.underGroup = underGroup;
    }

  

    public String getRId() {
        return rId;
    }

    public void setRId(String rId) {
        this.rId = rId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getBType() {
        return bType;
    }

    public void setBType(String bType) {
        this.bType = bType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLedger != null ? idLedger.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LedgerAccountMaster)) {
            return false;
        }
        LedgerAccountMaster other = (LedgerAccountMaster) object;
        if ((this.idLedger == null && other.idLedger != null) || (this.idLedger != null && !this.idLedger.equals(other.idLedger))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.LedgerAccountMaster[ idLedger=" + idLedger + " ]";
    }

    public Integer getPredefined() {
        return predefined;
    }

    public void setPredefined(Integer predefined) {
        this.predefined = predefined;
    }

   
    
}
