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

/**
 *
 * @author shinelogics
 */
@Entity
@Table(name = "default_settings")
@XmlRootElement

public class DefaultSettings implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_setting")
    private Integer idSetting;   
    @Column(name = "category_item_master")
    private Integer categoryItemMaster; 
    @Column(name = "unit_item_master")
    private Integer unitItemMaster;
    @Size(max = 50)
    @Column(name = "margin_item_master")
    private String marginItemMaster;
    @Size(max = 50)
    @Column(name = "purchase_category")
    private String purchaseCategory;
    @Size(max = 50)
    @Column(name = "purchase_mode")
    private String purchaseMode;
    @Size(max = 50)
    @Column(name = "sales_category")
    private String salesCategory;
    @Size(max = 50)
    @Column(name = "sales_mode")
    private String salesMode;  
    @Column(name = "reversal_itc_category")
    private Integer reversalItcCategory;
    @Size(max = 50)
    @Column(name = "reversal_itc_mode")
    private String reversalItcMode;
    @Size(max = 50)
    @Column(name = "receipt_mode")
    private String receiptMode;
    @Size(max = 50)
    @Column(name = "payment_mode")
    private String paymentMode;
    @Column(name = "sales_serial_no")
    private Integer salesNumber=0;  

    public Integer getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(Integer salesNumber) {
        this.salesNumber = salesNumber;
    }
    private String editLimit;

    public String getEditLimit() {
        return editLimit;
    }

    public void setEditLimit(String editLimit) {
        this.editLimit = editLimit;
    }
     private String salesReturn="1";

    public String getSalesReturn() {
        return salesReturn;
    }

    public void setSalesReturn(String salesReturn) {
        this.salesReturn = salesReturn;
    }

    
    public DefaultSettings() {
    }

    public DefaultSettings(Integer idSetting) {
        this.idSetting = idSetting;
    }

    public DefaultSettings(Integer idSetting, Date createdOn, Date modifiedOn) {
        this.idSetting = idSetting;
        
    }

    public Integer getIdSetting() {
        return idSetting;
    }

    public void setIdSetting(Integer idSetting) {
        this.idSetting = idSetting;
    }

    public Integer getCategoryItemMaster() {
        return categoryItemMaster;
    }

    public void setCategoryItemMaster(Integer categoryItemMaster) {
        this.categoryItemMaster = categoryItemMaster;
    }

    public Integer getUnitItemMaster() {
        return unitItemMaster;
    }

    public void setUnitItemMaster(Integer unitItemMaster) {
        this.unitItemMaster = unitItemMaster;
    }

    public String getMarginItemMaster() {
        return marginItemMaster;
    }

    public void setMarginItemMaster(String marginItemMaster) {
        this.marginItemMaster = marginItemMaster;
    }

    public String getPurchaseCategory() {
        return purchaseCategory;
    }

    public void setPurchaseCategory(String purchaseCategory) {
        this.purchaseCategory = purchaseCategory;
    }

    public String getPurchaseMode() {
        return purchaseMode;
    }

    public void setPurchaseMode(String purchaseMode) {
        this.purchaseMode = purchaseMode;
    }

    public String getSalesCategory() {
        return salesCategory;
    }

    public void setSalesCategory(String salesCategory) {
        this.salesCategory = salesCategory;
    }

    public String getSalesMode() {
        return salesMode;
    }

    public void setSalesMode(String salesMode) {
        this.salesMode = salesMode;
    }

    public Integer getReversalItcCategory() {
        return reversalItcCategory;
    }

    public void setReversalItcCategory(Integer reversalItcCategory) {
        this.reversalItcCategory = reversalItcCategory;
    }

    public String getReversalItcMode() {
        return reversalItcMode;
    }

    public void setReversalItcMode(String reversalItcMode) {
        this.reversalItcMode = reversalItcMode;
    }

    public String getReceiptMode() {
        return receiptMode;
    }

    public void setReceiptMode(String receiptMode) {
        this.receiptMode = receiptMode;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSetting != null ? idSetting.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DefaultSettings)) {
            return false;
        }
        DefaultSettings other = (DefaultSettings) object;
        if ((this.idSetting == null && other.idSetting != null) || (this.idSetting != null && !this.idSetting.equals(other.idSetting))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.DefaultSettings[ idSetting=" + idSetting + " ]";
    }
    
}
