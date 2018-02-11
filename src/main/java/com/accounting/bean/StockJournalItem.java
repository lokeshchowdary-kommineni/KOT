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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MR
 */
@Entity
@Table(name = "stock_journal_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StockJournalItem.findAll", query = "SELECT s FROM StockJournalItem s")
    , @NamedQuery(name = "StockJournalItem.findById", query = "SELECT s FROM StockJournalItem s WHERE s.id = :id")
    , @NamedQuery(name = "StockJournalItem.findByAmount", query = "SELECT s FROM StockJournalItem s WHERE s.amount = :amount")
    , @NamedQuery(name = "StockJournalItem.findByCgst", query = "SELECT s FROM StockJournalItem s WHERE s.cgst = :cgst")
    , @NamedQuery(name = "StockJournalItem.findByEp", query = "SELECT s FROM StockJournalItem s WHERE s.ep = :ep")
    , @NamedQuery(name = "StockJournalItem.findByItemCode", query = "SELECT s FROM StockJournalItem s WHERE s.itemCode = :itemCode")
    , @NamedQuery(name = "StockJournalItem.findByNameOfTheItem", query = "SELECT s FROM StockJournalItem s WHERE s.nameOfTheItem = :nameOfTheItem")
    , @NamedQuery(name = "StockJournalItem.findByQty", query = "SELECT s FROM StockJournalItem s WHERE s.qty = :qty")
    , @NamedQuery(name = "StockJournalItem.findByRate", query = "SELECT s FROM StockJournalItem s WHERE s.rate = :rate")
    , @NamedQuery(name = "StockJournalItem.findByStockJournalId", query = "SELECT s FROM StockJournalItem s WHERE s.stockJournalId = :stockJournalId")
    , @NamedQuery(name = "StockJournalItem.findByUnit", query = "SELECT s FROM StockJournalItem s WHERE s.unit = :unit")
    , @NamedQuery(name = "StockJournalItem.findByVat", query = "SELECT s FROM StockJournalItem s WHERE s.vat = :vat")
    , @NamedQuery(name = "StockJournalItem.findByStockjournalType", query = "SELECT s FROM StockJournalItem s WHERE s.stockjournalType = :stockjournalType")
    , @NamedQuery(name = "StockJournalItem.findByCp", query = "SELECT s FROM StockJournalItem s WHERE s.cp = :cp")})
public class StockJournalItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount",columnDefinition = "DECIMAL(15,2)")
    private Double amount;
    @Column(name = "cgst",columnDefinition = "DECIMAL(15,2)")
    private Double cgst;
    @Column(name = "ep",columnDefinition = "DECIMAL(15,2)")
    private Double ep;
    @Size(max = 150)
    @Column(name = "item_code")
    private String itemCode;
    @Size(max = 150)
    @Column(name = "name_of_the_item")
    private String nameOfTheItem;
    @Column(name = "qty",columnDefinition = "DECIMAL(15,2)")
    private Double qty;
    @Column(name = "rate",columnDefinition = "DECIMAL(15,2)")
    private Double rate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock_journal_id")
    private int stockJournalId;
    @Column(name = "unit",columnDefinition = "DECIMAL(15,2)")
    private Double unit;
    @Column(name = "vat",columnDefinition = "DECIMAL(15,2)")
    private Double vat;
    @Size(max = 100)
    @Column(name = "stockjournal_type")
    private String stockjournalType;
    @Column(name = "cp",columnDefinition = "DECIMAL(15,2)")
    private Double cp;

    public StockJournalItem() {
    }

    public StockJournalItem(Integer id) {
        this.id = id;
    }

    public StockJournalItem(Integer id, int stockJournalId) {
        this.id = id;
        this.stockJournalId = stockJournalId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCgst() {
        return cgst;
    }

    public void setCgst(Double cgst) {
        this.cgst = cgst;
    }

    public Double getEp() {
        return ep;
    }

    public void setEp(Double ep) {
        this.ep = ep;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getNameOfTheItem() {
        return nameOfTheItem;
    }

    public void setNameOfTheItem(String nameOfTheItem) {
        this.nameOfTheItem = nameOfTheItem;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public int getStockJournalId() {
        return stockJournalId;
    }

    public void setStockJournalId(int stockJournalId) {
        this.stockJournalId = stockJournalId;
    }

    public Double getUnit() {
        return unit;
    }

    public void setUnit(Double unit) {
        this.unit = unit;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public String getStockjournalType() {
        return stockjournalType;
    }

    public void setStockjournalType(String stockjournalType) {
        this.stockjournalType = stockjournalType;
    }

    public Double getCp() {
        return cp;
    }

    public void setCp(Double cp) {
        this.cp = cp;
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
        if (!(object instanceof StockJournalItem)) {
            return false;
        }
        StockJournalItem other = (StockJournalItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.StockJournalItem[ id=" + id + " ]";
    }
    
}
