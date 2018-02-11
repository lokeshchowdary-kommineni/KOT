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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SHINELOGICS
 */
@Entity
@Table(name = "tax_structure")
public class TaxStructure implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "igst",columnDefinition = "DECIMAL(15,2)")
    private double igst;

    public double getIgst() {
        return igst;
    }

    public void setIgst(double igst) {
        this.igst = igst;
    }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size( max = 6)
    @Column(name = "composit_limit")
    private String compositLimit;
 
    @Basic(optional = false)
    @NotNull
    @Size( max = 6)
    @Column(name = "threshold_limit")
    private String thresholdLimit;
  

    public TaxStructure() {
    }

    public TaxStructure(Integer id) {
        this.id = id;
    }

   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    public String getCompositLimit() {
        return compositLimit;
    }

    public void setCompositLimit(String compositLimit) {
        this.compositLimit = compositLimit;
    }

  
    public String getThresholdLimit() {
        return thresholdLimit;
    }

    public void setThresholdLimit(String thresholdLimit) {
        this.thresholdLimit = thresholdLimit;
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
        if (!(object instanceof TaxStructure)) {
            return false;
        }
        TaxStructure other = (TaxStructure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.TaxStructure[ id=" + id + " ]";
    }
    
}
