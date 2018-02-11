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
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "stock_journal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StockJournal.findAll", query = "SELECT s FROM StockJournal s"),
    @NamedQuery(name = "StockJournal.findByStockJournalId", query = "SELECT s FROM StockJournal s WHERE s.stockJournalId = :stockJournalId"),
    @NamedQuery(name = "StockJournal.findByDate", query = "SELECT s FROM StockJournal s WHERE s.date = :date"),
    @NamedQuery(name = "StockJournal.findByItemCode", query = "SELECT s FROM StockJournal s WHERE s.itemCode = :itemCode"),
    @NamedQuery(name = "StockJournal.findByNameOfTheItem", query = "SELECT s FROM StockJournal s WHERE s.nameOfTheItem = :nameOfTheItem"),
    @NamedQuery(name = "StockJournal.findByQty", query = "SELECT s FROM StockJournal s WHERE s.qty = :qty"),
    @NamedQuery(name = "StockJournal.findByRate", query = "SELECT s FROM StockJournal s WHERE s.rate = :rate"),
    @NamedQuery(name = "StockJournal.findByTotalAmount", query = "SELECT s FROM StockJournal s WHERE s.totalAmount = :totalAmount"),
    @NamedQuery(name = "StockJournal.findByUnit", query = "SELECT s FROM StockJournal s WHERE s.unit = :unit"),
    @NamedQuery(name = "StockJournal.findByVoucherNo", query = "SELECT s FROM StockJournal s WHERE s.voucherNo = :voucherNo"),
    @NamedQuery(name = "StockJournal.findByAmount", query = "SELECT s FROM StockJournal s WHERE s.amount = :amount"),
    @NamedQuery(name = "StockJournal.findByCgst", query = "SELECT s FROM StockJournal s WHERE s.cgst = :cgst"),
    @NamedQuery(name = "StockJournal.findByEp", query = "SELECT s FROM StockJournal s WHERE s.ep = :ep"),
    @NamedQuery(name = "StockJournal.findById", query = "SELECT s FROM StockJournal s WHERE s.id = :id"),
    @NamedQuery(name = "StockJournal.findByTotalCostComp", query = "SELECT s FROM StockJournal s WHERE s.totalCostComp = :totalCostComp"),
    @NamedQuery(name = "StockJournal.findByValuAdditiion", query = "SELECT s FROM StockJournal s WHERE s.valuAdditiion = :valuAdditiion"),
    @NamedQuery(name = "StockJournal.findByValueOfFinishGood", query = "SELECT s FROM StockJournal s WHERE s.valueOfFinishGood = :valueOfFinishGood"),
    @NamedQuery(name = "StockJournal.findByVat", query = "SELECT s FROM StockJournal s WHERE s.vat = :vat"),
    @NamedQuery(name = "StockJournal.findByFdate", query = "SELECT s FROM StockJournal s WHERE s.fdate = :fdate")})
public class StockJournal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stock_journal_id")
    private Integer stockJournalId;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy") 
    private Date date;
    @Size(max = 50)
    @Column(name = "item_code")
    private String itemCode;
    @Size(max = 50)
    @Column(name = "name_of_the_item")
    private String nameOfTheItem;
    @Column(name = "qty")
    private Integer qty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rate",columnDefinition = "DECIMAL(15,2)")
    private Double rate;
    @Column(name = "total_amount",columnDefinition = "DECIMAL(15,2)")
    private Double totalAmount;
    @Column(name = "unit")
    private Integer unit;
    @Size(max = 50)
    @Column(name = "voucher_no")
    private String voucherNo;
    @Column(name = "amount",columnDefinition = "DECIMAL(15,2)")
    private Double amount;
    @Column(name = "cgst",columnDefinition = "DECIMAL(15,2)")
    private Double cgst;
    @Column(name = "ep",columnDefinition = "DECIMAL(15,2)")
    private Double ep;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;
    @Column(name = "total_cost_comp",columnDefinition = "DECIMAL(15,2)")
    private Double totalCostComp;
    @Column(name = "valu_additiion",columnDefinition = "DECIMAL(15,2)")
    private Double valuAdditiion=0.0;
    @Column(name = "value_of_finish_good",columnDefinition = "DECIMAL(15,2)")
    private Double valueOfFinishGood=0.0;
    @Column(name = "vat",columnDefinition = "DECIMAL(15,2)")
    private Double vat;
    @Column(name = "fdate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy") 
    private Date fdate;

    public StockJournal() {
    }

    public StockJournal(Integer stockJournalId) {
        this.stockJournalId = stockJournalId;
    }

    public StockJournal(Integer stockJournalId, int id) {
        this.stockJournalId = stockJournalId;
        this.id = id;
    }

    public Integer getStockJournalId() {
        return stockJournalId;
    }

    public void setStockJournalId(Integer stockJournalId) {
        this.stockJournalId = stockJournalId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTotalCostComp() {
        return totalCostComp;
    }

    public void setTotalCostComp(Double totalCostComp) {
        this.totalCostComp = totalCostComp;
    }

    public Double getValuAdditiion() {
        return valuAdditiion;
    }

    public void setValuAdditiion(Double valuAdditiion) {
        this.valuAdditiion = valuAdditiion;
    }

    public Double getValueOfFinishGood() {
        return valueOfFinishGood;
    }

    public void setValueOfFinishGood(Double valueOfFinishGood) {
        this.valueOfFinishGood = valueOfFinishGood;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stockJournalId != null ? stockJournalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StockJournal)) {
            return false;
        }
        StockJournal other = (StockJournal) object;
        if ((this.stockJournalId == null && other.stockJournalId != null) || (this.stockJournalId != null && !this.stockJournalId.equals(other.stockJournalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.StockJournal[ stockJournalId=" + stockJournalId + " ]";
    }
    
}
