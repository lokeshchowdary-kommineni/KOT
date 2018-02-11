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
@Table(name = "waitermaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Waitermaster.findAll", query = "SELECT w FROM Waitermaster w")
    , @NamedQuery(name = "Waitermaster.findByWaiterId", query = "SELECT w FROM Waitermaster w WHERE w.waiterId = :waiterId")
    , @NamedQuery(name = "Waitermaster.findByWaiterName", query = "SELECT w FROM Waitermaster w WHERE w.waiterName = :waiterName")
    , @NamedQuery(name = "Waitermaster.findByMobileNo", query = "SELECT w FROM Waitermaster w WHERE w.mobileNo = :mobileNo")
    , @NamedQuery(name = "Waitermaster.findByAddress", query = "SELECT w FROM Waitermaster w WHERE w.address = :address")
    , @NamedQuery(name = "Waitermaster.findByGender", query = "SELECT w FROM Waitermaster w WHERE w.gender = :gender")
    , @NamedQuery(name = "Waitermaster.findById", query = "SELECT w FROM Waitermaster w WHERE w.id = :id")
    , @NamedQuery(name = "Waitermaster.findByFromSerial", query = "SELECT w FROM Waitermaster w WHERE w.fromSerial = :fromSerial")
    , @NamedQuery(name = "Waitermaster.findByToSerial", query = "SELECT w FROM Waitermaster w WHERE w.toSerial = :toSerial")})
public class Waitermaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "waiter_id")
    private String waiterId;
    @Size(max = 50)
    @Column(name = "waiter_name")
    private String waiterName;
    @Size(max = 11)
    @Column(name = "mobile_no")
    private String mobileNo;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Size(max = 10)
    @Column(name = "gender")
    private String gender;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "from_serial")
    private String fromSerial;
    @Size(max = 50)
    @Column(name = "to_serial")
    private String toSerial;

    public Waitermaster() {
    }

    public Waitermaster(Integer id) {
        this.id = id;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromSerial() {
        return fromSerial;
    }

    public void setFromSerial(String fromSerial) {
        this.fromSerial = fromSerial;
    }

    public String getToSerial() {
        return toSerial;
    }

    public void setToSerial(String toSerial) {
        this.toSerial = toSerial;
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
        if (!(object instanceof Waitermaster)) {
            return false;
        }
        Waitermaster other = (Waitermaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.Waitermaster[ id=" + id + " ]";
    }
    
}
