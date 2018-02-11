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
 * @author MR
 */
@Entity
@Table(name = "access_control")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccessControl.findAll", query = "SELECT a FROM AccessControl a"),
    @NamedQuery(name = "AccessControl.findByAccessid", query = "SELECT a FROM AccessControl a WHERE a.accessid = :accessid"),
    @NamedQuery(name = "AccessControl.findByModuleName", query = "SELECT a FROM AccessControl a WHERE a.moduleName = :moduleName"),
    @NamedQuery(name = "AccessControl.findByCreatedon", query = "SELECT a FROM AccessControl a WHERE a.createdon = :createdon")})
public class AccessControl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Access_id")
    private Integer accessid;
    @Size(max = 255)
    @Column(name = "Module_Name")
    private String moduleName;
    @Column(name = "Created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;

    public AccessControl() {
    }

    public AccessControl(Integer accessid) {
        this.accessid = accessid;
    }

    public Integer getAccessid() {
        return accessid;
    }

    public void setAccessid(Integer accessid) {
        this.accessid = accessid;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accessid != null ? accessid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessControl)) {
            return false;
        }
        AccessControl other = (AccessControl) object;
        if ((this.accessid == null && other.accessid != null) || (this.accessid != null && !this.accessid.equals(other.accessid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.AccessControl[ accessid=" + accessid + " ]";
    }
    
}
