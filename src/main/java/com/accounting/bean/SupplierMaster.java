/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.bean;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author SHINELOGICS
 */
@Entity
@Table(name = "supplier_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierMaster.findAll", query = "SELECT s FROM SupplierMaster s"),
    @NamedQuery(name = "SupplierMaster.findByIdSupplier", query = "SELECT s FROM SupplierMaster s WHERE s.idSupplier = :idSupplier"),
    @NamedQuery(name = "SupplierMaster.findBySupplierAddress", query = "SELECT s FROM SupplierMaster s WHERE s.supplierAddress = :supplierAddress"),
    @NamedQuery(name = "SupplierMaster.findBySupplierMobile", query = "SELECT s FROM SupplierMaster s WHERE s.supplierMobile = :supplierMobile"),
    @NamedQuery(name = "SupplierMaster.findBySupplierCity", query = "SELECT s FROM SupplierMaster s WHERE s.supplierCity = :supplierCity"),
    @NamedQuery(name = "SupplierMaster.findBySupplierState", query = "SELECT s FROM SupplierMaster s WHERE s.supplierState = :supplierState"),
    @NamedQuery(name = "SupplierMaster.findBySupplierCode", query = "SELECT s FROM SupplierMaster s WHERE s.supplierCode = :supplierCode"),
    @NamedQuery(name = "SupplierMaster.findBySupplierName", query = "SELECT s FROM SupplierMaster s WHERE s.supplierName = :supplierName"),
    @NamedQuery(name = "SupplierMaster.findBySupplierStatus", query = "SELECT s FROM SupplierMaster s WHERE s.supplierStatus = :supplierStatus")})
public class SupplierMaster implements Serializable {

    @OneToMany(mappedBy = "supplierId")
    private Collection<Purchase> purchaseCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_supplier")
    private Integer idSupplier;
    @Size(max = 255)
    @Column(name = "supplier_address")
    private String supplierAddress;
    @Size(max = 15)
    @Column(name = "supplier_mobile")
    private String supplierMobile;
    @Size(max = 50)
    @Column(name = "supplier_city")
    private String supplierCity;
    @Size(max = 50)
    @Column(name = "supplier_state")
    private String supplierState;
    @Size(max = 50)
    @Column(name = "supplier_code")
    private String supplierCode;
    @Size(max = 100)
    @Column(name = "supplier_name")
    private String supplierName;
    @Size(max = 50)
    @Column(name = "supplier_status")
    private String supplierStatus;

    public SupplierMaster() {
    }

    public SupplierMaster(Integer idSupplier) {
        this.idSupplier = idSupplier;
    }

    public Integer getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(Integer idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierMobile() {
        return supplierMobile;
    }

    public void setSupplierMobile(String supplierMobile) {
        this.supplierMobile = supplierMobile;
    }

    public String getSupplierCity() {
        return supplierCity;
    }

    public void setSupplierCity(String supplierCity) {
        this.supplierCity = supplierCity;
    }

    public String getSupplierState() {
        return supplierState;
    }

    public void setSupplierState(String supplierState) {
        this.supplierState = supplierState;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(String supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSupplier != null ? idSupplier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierMaster)) {
            return false;
        }
        SupplierMaster other = (SupplierMaster) object;
        if ((this.idSupplier == null && other.idSupplier != null) || (this.idSupplier != null && !this.idSupplier.equals(other.idSupplier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.SupplierMaster[ idSupplier=" + idSupplier + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Purchase> getPurchaseCollection() {
        return purchaseCollection;
    }

    public void setPurchaseCollection(Collection<Purchase> purchaseCollection) {
        this.purchaseCollection = purchaseCollection;
    }
    
}
