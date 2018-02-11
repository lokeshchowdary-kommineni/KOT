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
@Table(name = "account_group_master")
@XmlRootElement

public class AccountGroupMaster implements Serializable {

    @Column(name = "predefined",updatable=false)
    private Integer predefined;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_account")
    private Integer idAccount;
    
    @Size(max = 50)
    @NotEmpty(message = "AccountGroup Name is mandatory")
    @Column(name = "account_group_name")
    private String accountGroupName;
    @Column(name = "nature_of_group")
    private String natureOfGroup;
    @Column(name = "under_group")
    private Integer underGroup;

    public Integer getUnderGroup() {
        return underGroup;
    }

    public void setUnderGroup(Integer underGroup) {
        this.underGroup = underGroup;
    }

    public AccountGroupMaster() {
    }

    public AccountGroupMaster(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public AccountGroupMaster(Integer idAccount, Date createdOn, Date modifiedOn) {
        this.idAccount = idAccount;
        
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public String getAccountGroupName() {
        return accountGroupName;
    }

    public void setAccountGroupName(String accountGroupName) {
        this.accountGroupName = accountGroupName;
    }

    public String getNatureOfGroup() {
        return natureOfGroup;
    }

    public void setNatureOfGroup(String natureOfGroup) {
        this.natureOfGroup = natureOfGroup;
    }

  


   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccount != null ? idAccount.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountGroupMaster)) {
            return false;
        }
        AccountGroupMaster other = (AccountGroupMaster) object;
        if ((this.idAccount == null && other.idAccount != null) || (this.idAccount != null && !this.idAccount.equals(other.idAccount))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.AccountGroupMaster[ idAccount=" + idAccount + " ]";
    }

    public Integer getPredefined() {
        return predefined;
    }

    public void setPredefined(Integer predefined) {
        this.predefined = predefined;
    }
    
}
