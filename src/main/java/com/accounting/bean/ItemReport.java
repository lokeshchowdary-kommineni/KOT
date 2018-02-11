/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.bean;

import java.io.Serializable;
import java.math.BigInteger;
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
 * @author shinelogics
 */
@Entity
@Table(name = "item_report")
@XmlRootElement

public class ItemReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "item_group_id")
    private Integer itemGroupId;
  
    @Column(name = "closing_quantity",columnDefinition = "DECIMAL(15,2)")
    private Double closingQuantity;
    @Column(name = "closing_value",columnDefinition = "DECIMAL(15,2)")
    private Double closingValue;
    @Column(name = "in_quantity",columnDefinition = "DECIMAL(15,2)")
    private Double inQuantity;
    @Column(name = "in_value",columnDefinition = "DECIMAL(15,2)")
    private Double inValue;

    @Column(name = "item_id")
    private Integer itemId;
    @Size(max = 100)
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "out_quantity",columnDefinition = "DECIMAL(15,2)")
    private Double outQuantity;
    @Column(name = "out_value",columnDefinition = "DECIMAL(15,2)")
    private Double outValue;
    @Column(name = "transaction_date")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    @Column(name = "transaction_id")
    private String transactionId;
    @Size(max = 200)
    @Column(name = "transaction_name")
    private String transactionName;
    @Size(max = 50)
    @Column(name = "transaction_type")
    private String transactionType;

    public ItemReport() {
    }

    public ItemReport(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getClosingQuantity() {
        return closingQuantity;
    }

    public void setClosingQuantity(Double closingQuantity) {
        this.closingQuantity = closingQuantity;
    }

    public Double getClosingValue() {
        return closingValue;
    }

    public void setClosingValue(Double closingValue) {
        this.closingValue = closingValue;
    }

    public Double getInQuantity() {
        return inQuantity;
    }

    public void setInQuantity(Double inQuantity) {
        this.inQuantity = inQuantity;
    }

    public Double getInValue() {
        return inValue;
    }

    public void setInValue(Double inValue) {
        this.inValue = inValue;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getOutQuantity() {
        return outQuantity;
    }

    public void setOutQuantity(Double outQuantity) {
        this.outQuantity = outQuantity;
    }

    public Double getOutValue() {
        return outValue;
    }

    public void setOutValue(Double outValue) {
        this.outValue = outValue;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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
        if (!(object instanceof ItemReport)) {
            return false;
        }
        ItemReport other = (ItemReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.ItemReport[ id=" + id + " ]";
    }

   

    public Integer getItemGroupId() {
        return itemGroupId;
    }

    public void setItemGroupId(Integer itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

   


}
