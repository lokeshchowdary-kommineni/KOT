/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MR
 */
@Entity
@Table(name = "tablemaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablemaster.findAll", query = "SELECT t FROM Tablemaster t")
    , @NamedQuery(name = "Tablemaster.findByTableName", query = "SELECT t FROM Tablemaster t WHERE t.tableName = :tableName")
    , @NamedQuery(name = "Tablemaster.findByTableCategory", query = "SELECT t FROM Tablemaster t WHERE t.tableCategory = :tableCategory")
    , @NamedQuery(name = "Tablemaster.findById", query = "SELECT t FROM Tablemaster t WHERE t.id = :id")
    , @NamedQuery(name = "Tablemaster.findByServiceCharges", query = "SELECT t FROM Tablemaster t WHERE t.serviceCharges = :serviceCharges")})
public class Tablemaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    @Column(name = "table_name")
    private String tableName;
    @Size(max = 30)
    @Column(name = "table_category")
    private String tableCategory;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "service_charges")
    private BigDecimal serviceCharges;

    public Tablemaster() {
    }

    public Tablemaster(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableCategory() {
        return tableCategory;
    }

    public void setTableCategory(String tableCategory) {
        this.tableCategory = tableCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(BigDecimal serviceCharges) {
        this.serviceCharges = serviceCharges;
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
        if (!(object instanceof Tablemaster)) {
            return false;
        }
        Tablemaster other = (Tablemaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.Tablemaster[ id=" + id + " ]";
    }
    
}
