/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.bean;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author MR
 */
@Entity
@Table(name = "ccode_master")
@XmlRootElement

public class CcodeMaster implements Serializable {

    @Column(name = "tax_rate",columnDefinition = "DECIMAL(15,2)")
    private Double taxRate;
    @Column(name = "igst_rate")
    private Double igstRate;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    @Column(name = "cgst_rate")
    private Double cgstRate;
    @Column(name = "sgst_rate")
    private Double sgstRate;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ccode_id")
    private Integer ccodeId;
    @Size(max = 10)
    @Column(name = "ccode")
    private String ccode;
    @Size(max = 100)
    @Column(name = "category")
    private String category;
    

    public CcodeMaster() {
    }

    public CcodeMaster(Integer ccodeId) {
        this.ccodeId = ccodeId;
    }

    public Integer getCcodeId() {
        return ccodeId;
    }

    public void setCcodeId(Integer ccodeId) {
        this.ccodeId = ccodeId;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ccodeId != null ? ccodeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CcodeMaster)) {
            return false;
        }
        CcodeMaster other = (CcodeMaster) object;
        if ((this.ccodeId == null && other.ccodeId != null) || (this.ccodeId != null && !this.ccodeId.equals(other.ccodeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.CcodeMaster[ ccodeId=" + ccodeId + " ]";
    }

    

    public Double getCgstRate() {
        return cgstRate;
    }

    public void setCgstRate(Double cgstRate) {
        this.cgstRate = cgstRate;
    }

    public Double getSgstRate() {
        return sgstRate;
    }

    public void setSgstRate(Double sgstRate) {
        this.sgstRate = sgstRate;
    }

    

    public Double getIgstRate() {
        return igstRate;
    }

    public void setIgstRate(Double igstRate) {
        this.igstRate = igstRate;
    }
    
}
