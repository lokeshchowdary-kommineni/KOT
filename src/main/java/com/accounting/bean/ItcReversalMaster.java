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
import org.hibernate.validator.constraints.NotEmpty;

/**
 *   
 * @author shinelogics
 */
@Entity
@Table(name = "itc_reversal_master")
@XmlRootElement

public class ItcReversalMaster implements Serializable {

    @Column(name = "predefined",updatable=false)
    private Integer predefined;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_itc")
    private Integer idItc;
  
    @Size(max = 50)
    @NotEmpty(message = "Please enter Category Name")
    @Column(name = "category_name")
    private String categoryName;
    
    @Column(name = "under_category")
    private Integer underCategory;
    @Size(max = 30)
    @Column(name = "sec_code")
    private String secCode;
    @Size(max = 255)
    @Column(name = "descriptions")
    private String descriptions;
    

    public ItcReversalMaster() {
    }

    public ItcReversalMaster(Integer idItc) {
        this.idItc = idItc;
    }

    public ItcReversalMaster(Integer idItc, Date createdOn, Date modifiedOn) {
        this.idItc = idItc;
      
    }

    public Integer getIdItc() {
        return idItc;
    }

    public void setIdItc(Integer idItc) {
        this.idItc = idItc;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getUnderCategory() {
        return underCategory;
    }

    public void setUnderCategory(Integer underCategory) {
        this.underCategory = underCategory;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItc != null ? idItc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItcReversalMaster)) {
            return false;
        }
        ItcReversalMaster other = (ItcReversalMaster) object;
        if ((this.idItc == null && other.idItc != null) || (this.idItc != null && !this.idItc.equals(other.idItc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.ItcReversalMaster[ idItc=" + idItc + " ]";
    }

    public Integer getPredefined() {
        return predefined;
    }

    public void setPredefined(Integer predefined) {
        this.predefined = predefined;
    }
    
}
