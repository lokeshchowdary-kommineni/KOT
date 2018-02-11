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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MR
 */
@Entity
@Table(name = "access_control_master_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccessControlMasterTable.findAll", query = "SELECT a FROM AccessControlMasterTable a"),
    @NamedQuery(name = "AccessControlMasterTable.findByAccessControlid", query = "SELECT a FROM AccessControlMasterTable a WHERE a.accessControlid = :accessControlid"),
    @NamedQuery(name = "AccessControlMasterTable.findByCreatevalue", query = "SELECT a FROM AccessControlMasterTable a WHERE a.createvalue = :createvalue"),
    @NamedQuery(name = "AccessControlMasterTable.findByDeletevalue", query = "SELECT a FROM AccessControlMasterTable a WHERE a.deletevalue = :deletevalue"),
    @NamedQuery(name = "AccessControlMasterTable.findByEditvalue", query = "SELECT a FROM AccessControlMasterTable a WHERE a.editvalue = :editvalue"),
    @NamedQuery(name = "AccessControlMasterTable.findByGroupid", query = "SELECT a FROM AccessControlMasterTable a WHERE a.groupid = :groupid"),
    @NamedQuery(name = "AccessControlMasterTable.findByModuleName", query = "SELECT a FROM AccessControlMasterTable a WHERE a.moduleName = :moduleName"),
    @NamedQuery(name = "AccessControlMasterTable.findByViewvalue", query = "SELECT a FROM AccessControlMasterTable a WHERE a.viewvalue = :viewvalue")})
public class AccessControlMasterTable implements Serializable {

    @Column(name = "Access_id")
    private Integer accessid;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AccessControl_id")
    private Integer accessControlid;
    
    @Size(max = 10)
    @Column(name = "Create_value")
    private String createvalue;
    @Size(max = 10)
    @Column(name = "Delete_value")
    private String deletevalue;
    @Size(max = 10)
    @Column(name = "Edit_value")
    private String editvalue;
    @Column(name = "Group_id")
    private Integer groupid;
    @Size(max = 255)
    @Column(name = "Module_Name")
    private String moduleName;
    @Size(max = 10)
    @Column(name = "View_value")
    private String viewvalue;

    public Integer getAccessid() {
        return accessid;
    }

    public void setAccessid(Integer accessid) {
        this.accessid = accessid;
    }

    public AccessControlMasterTable() {
    }

    public AccessControlMasterTable(Integer accessControlid) {
        this.accessControlid = accessControlid;
    }

    public Integer getAccessControlid() {
        return accessControlid;
    }

    public void setAccessControlid(Integer accessControlid) {
        this.accessControlid = accessControlid;
    }

    


    public String getCreatevalue() {
        return createvalue;
    }

    public void setCreatevalue(String createvalue) {
        this.createvalue = createvalue;
    }

    public String getDeletevalue() {
        return deletevalue;
    }

    public void setDeletevalue(String deletevalue) {
        this.deletevalue = deletevalue;
    }

    public String getEditvalue() {
        return editvalue;
    }

    public void setEditvalue(String editvalue) {
        this.editvalue = editvalue;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getViewvalue() {
        return viewvalue;
    }

    public void setViewvalue(String viewvalue) {
        this.viewvalue = viewvalue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accessControlid != null ? accessControlid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessControlMasterTable)) {
            return false;
        }
        AccessControlMasterTable other = (AccessControlMasterTable) object;
        if ((this.accessControlid == null && other.accessControlid != null) || (this.accessControlid != null && !this.accessControlid.equals(other.accessControlid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.AccessControlMasterTable[ accessControlid=" + accessControlid + " ]";
    }

    
}
