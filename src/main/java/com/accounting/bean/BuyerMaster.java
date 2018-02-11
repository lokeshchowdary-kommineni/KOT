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
 * @author SHINELOGICS
 */
@Entity
@Table(name = "buyer_master")
@XmlRootElement
public class BuyerMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_buyer")
    private Integer idBuyer;
    @Size(max = 255)
    @Column(name = "customer_address")
    private String customerAddress;
    @Size(max = 20)
    @Column(name = "customer_status")
    private String customerStatus;
    @Size(max = 100)
    @Column(name = "customer_name")
    private String customerName;
    @Size(max = 30)
    @Column(name = "customer_state")
    private String customerState;
    @Size(max = 15)
    @Column(name = "customer_mobile")
    private String customerMobile;
    
    private String email;
    private String gender;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BuyerMaster() {
    }

    public BuyerMaster(Integer idBuyer) {
        this.idBuyer = idBuyer;
    }

    public Integer getIdBuyer() {
        return idBuyer;
    }

    public void setIdBuyer(Integer idBuyer) {
        this.idBuyer = idBuyer;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBuyer != null ? idBuyer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BuyerMaster)) {
            return false;
        }
        BuyerMaster other = (BuyerMaster) object;
        if ((this.idBuyer == null && other.idBuyer != null) || (this.idBuyer != null && !this.idBuyer.equals(other.idBuyer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.BuyerMaster[ idBuyer=" + idBuyer + " ]";
    }
    
}
