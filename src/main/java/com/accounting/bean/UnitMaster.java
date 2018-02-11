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
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author shinelogics
 */
@Entity
@Table(name = "unit_master")
@XmlRootElement

public class UnitMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_unit")
    private Integer idUnit;
    
    @NotNull(message = "Please enter your unit symbol")
   @Size(min=1,max=10, message="Size should not be more than 10")
    @Column(name = "unit_symbol")
    private String unitSymbol;
    
    @NotNull(message = "Please enter UnitName")
    @Size(max = 20,message="Size should not be more than 20")
    @Column(name = "unit_name")
    private String unitName;
    
    @NotNull(message = "Please enter digit ")
    @Column(name = "decimal_nos")
    @Range(min = 0, max = 4,message="This Field will Accept Only digit 0 to 4 ")
    private Integer decimalNos;
   
   
   
    public UnitMaster() {
    }

    public UnitMaster(Integer idUnit) {
        this.idUnit = idUnit;
    }

    public UnitMaster(Integer idUnit, Date createdOn, Date modifiedOn) {
        this.idUnit = idUnit;
        
    }

    public Integer getIdUnit() {
        return idUnit;
    }

    public void setIdUnit(Integer idUnit) {
        this.idUnit = idUnit;
    }

    public String getUnitSymbol() {
        return unitSymbol;
    }

    public void setUnitSymbol(String unitSymbol) {
        this.unitSymbol = unitSymbol;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getDecimalNos() {
        return decimalNos;
    }

    public void setDecimalNos(Integer decimalNos) {
        this.decimalNos = decimalNos;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnit != null ? idUnit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnitMaster)) {
            return false;
        }
        UnitMaster other = (UnitMaster) object;
        if ((this.idUnit == null && other.idUnit != null) || (this.idUnit != null && !this.idUnit.equals(other.idUnit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.UnitMaster[ idUnit=" + idUnit + " ]";
    }
    
}
