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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author shine
 */
@Entity
@Table(name = "reversal_of_itc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReversalOfItc.findAll", query = "SELECT r FROM ReversalOfItc r")
    , @NamedQuery(name = "ReversalOfItc.findByReversalOdItcId", query = "SELECT r FROM ReversalOfItc r WHERE r.reversalOdItcId = :reversalOdItcId")
    , @NamedQuery(name = "ReversalOfItc.findByAddOrLess", query = "SELECT r FROM ReversalOfItc r WHERE r.addOrLess = :addOrLess")
    , @NamedQuery(name = "ReversalOfItc.findByAmount", query = "SELECT r FROM ReversalOfItc r WHERE r.amount = :amount")
    , @NamedQuery(name = "ReversalOfItc.findByAssessValue", query = "SELECT r FROM ReversalOfItc r WHERE r.assessValue = :assessValue")
    , @NamedQuery(name = "ReversalOfItc.findByBalance", query = "SELECT r FROM ReversalOfItc r WHERE r.balance = :balance")
    , @NamedQuery(name = "ReversalOfItc.findByBillNo", query = "SELECT r FROM ReversalOfItc r WHERE r.billNo = :billNo")
    , @NamedQuery(name = "ReversalOfItc.findByCashSupplier", query = "SELECT r FROM ReversalOfItc r WHERE r.cashSupplier = :cashSupplier")
    , @NamedQuery(name = "ReversalOfItc.findByCategoryForReversal", query = "SELECT r FROM ReversalOfItc r WHERE r.categoryForReversal = :categoryForReversal")
    , @NamedQuery(name = "ReversalOfItc.findByCgst", query = "SELECT r FROM ReversalOfItc r WHERE r.cgst = :cgst")
    , @NamedQuery(name = "ReversalOfItc.findByDate", query = "SELECT r FROM ReversalOfItc r WHERE r.date = :date")
    , @NamedQuery(name = "ReversalOfItc.findByDebtAmount", query = "SELECT r FROM ReversalOfItc r WHERE r.debtAmount = :debtAmount")
    , @NamedQuery(name = "ReversalOfItc.findByEp", query = "SELECT r FROM ReversalOfItc r WHERE r.ep = :ep")
    , @NamedQuery(name = "ReversalOfItc.findById", query = "SELECT r FROM ReversalOfItc r WHERE r.id = :id")
    , @NamedQuery(name = "ReversalOfItc.findByItemCode", query = "SELECT r FROM ReversalOfItc r WHERE r.itemCode = :itemCode")
    , @NamedQuery(name = "ReversalOfItc.findByLedgerAccount", query = "SELECT r FROM ReversalOfItc r WHERE r.ledgerAccount = :ledgerAccount")
    , @NamedQuery(name = "ReversalOfItc.findByMode", query = "SELECT r FROM ReversalOfItc r WHERE r.mode = :mode")
    , @NamedQuery(name = "ReversalOfItc.findByNameOfTheItem", query = "SELECT r FROM ReversalOfItc r WHERE r.nameOfTheItem = :nameOfTheItem")
    , @NamedQuery(name = "ReversalOfItc.findByQty", query = "SELECT r FROM ReversalOfItc r WHERE r.qty = :qty")
    , @NamedQuery(name = "ReversalOfItc.findByReturnDate", query = "SELECT r FROM ReversalOfItc r WHERE r.returnDate = :returnDate")
    , @NamedQuery(name = "ReversalOfItc.findBySupplierId", query = "SELECT r FROM ReversalOfItc r WHERE r.supplierId = :supplierId")
    , @NamedQuery(name = "ReversalOfItc.findByTinNo", query = "SELECT r FROM ReversalOfItc r WHERE r.tinNo = :tinNo")
    , @NamedQuery(name = "ReversalOfItc.findByTotalAmount", query = "SELECT r FROM ReversalOfItc r WHERE r.totalAmount = :totalAmount")
    , @NamedQuery(name = "ReversalOfItc.findByTotalCgst", query = "SELECT r FROM ReversalOfItc r WHERE r.totalCgst = :totalCgst")
    , @NamedQuery(name = "ReversalOfItc.findByTotalVat", query = "SELECT r FROM ReversalOfItc r WHERE r.totalVat = :totalVat")
    , @NamedQuery(name = "ReversalOfItc.findByUnit", query = "SELECT r FROM ReversalOfItc r WHERE r.unit = :unit")
    , @NamedQuery(name = "ReversalOfItc.findByVat", query = "SELECT r FROM ReversalOfItc r WHERE r.vat = :vat")
    , @NamedQuery(name = "ReversalOfItc.findByAuthid", query = "SELECT r FROM ReversalOfItc r WHERE r.authid = :authid")})
public class ReversalOfItc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name="seq_id", strategy="com.accounting.idGeneration.ReversalOfItc")
    @GeneratedValue(generator="seq_id") 
    @Column(name = "reversal_od_itc_id")
    private String reversalOdItcId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "add_or_less",columnDefinition = "DECIMAL(15,2)")
    private Double addOrLess;
    @Column(name = "amount",columnDefinition = "DECIMAL(15,2)")
    private Double amount;
    @Column(name = "assess_value",columnDefinition = "DECIMAL(15,2)")
    private Double assessValue;
    @Column(name = "balance",columnDefinition = "DECIMAL(15,2)")
    private Double balance;
    @Size(max = 50)
    @Column(name = "bill_no")
    private String billNo;
    @Size(max = 50)
    @Column(name = "cash_supplier")
    private String cashSupplier;
    
    @Column(name = "category_for_reversal")
    private Integer categoryForReversal;
    @Column(name = "cgst",columnDefinition = "DECIMAL(15,2)")
    private Double cgst;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy") 
    private Date date;
    @Column(name = "debt_amount",columnDefinition = "DECIMAL(15,2)")
    private Double debtAmount;
    @Column(name = "ep",columnDefinition = "DECIMAL(15,2)")
    private Double ep;
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "item_code")
    private String itemCode;
    @Size(max = 50)
    @Column(name = "ledger_account")
    private String ledgerAccount;
    @Size(max = 50)
    @Column(name = "mode")
    private String mode;
    @Size(max = 50)
    @Column(name = "name_of_the_item")
    private String nameOfTheItem;
    @Column(name = "qty")
    private Integer qty;
    @Basic(optional = false)

    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy") 
    private Date returnDate;

    @Column(name = "supplier_id")
    private Integer supplierId;
    @Size(max = 255)
    @Column(name = "tin_no")
    private String tinNo;
    @Column(name = "total_amount",columnDefinition = "DECIMAL(15,2)")
    private Double totalAmount;
    @Column(name = "total_cgst",columnDefinition = "DECIMAL(15,2)")
    private Double totalCgst;
    @Column(name = "total_vat",columnDefinition = "DECIMAL(15,2)")
    private Double totalVat;
    @Column(name = "total_igst",columnDefinition = "DECIMAL(15,2)")
    private Double totalIgst;
    @Column(name = "unit")
    private Integer unit;
    @Column(name = "vat",columnDefinition = "DECIMAL(15,2)")
    private Double vat;
    @Basic(optional = false)
    @Column(name = "Authid")
    private int authid;
    @Size(max = 20)
    @Column(name = "supplier_type")
    private String supplierType;

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public ReversalOfItc() {
    }

    public ReversalOfItc(String reversalOdItcId) {
        this.reversalOdItcId = reversalOdItcId;
    }

    public ReversalOfItc(String reversalOdItcId, Date returnDate, int authid) {
        this.reversalOdItcId = reversalOdItcId;
        this.returnDate = returnDate;
        this.authid = authid;
    }

    public String getReversalOdItcId() {
        return reversalOdItcId;
    }

    public void setReversalOdItcId(String reversalOdItcId) {
        this.reversalOdItcId = reversalOdItcId;
    }

    public Double getAddOrLess() {
        return addOrLess;
    }

    public void setAddOrLess(Double addOrLess) {
        this.addOrLess = addOrLess;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAssessValue() {
        return assessValue;
    }

    public void setAssessValue(Double assessValue) {
        this.assessValue = assessValue;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getCashSupplier() {
        return cashSupplier;
    }

    public void setCashSupplier(String cashSupplier) {
        this.cashSupplier = cashSupplier;
    }

    public Integer getCategoryForReversal() {
        return categoryForReversal;
    }

    public void setCategoryForReversal(Integer categoryForReversal) {
        this.categoryForReversal = categoryForReversal;
    }

    public Double getCgst() {
        return cgst;
    }

    public void setCgst(Double cgst) {
        this.cgst = cgst;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(Double debtAmount) {
        this.debtAmount = debtAmount;
    }

    public Double getEp() {
        return ep;
    }

    public void setEp(Double ep) {
        this.ep = ep;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getLedgerAccount() {
        return ledgerAccount;
    }

    public void setLedgerAccount(String ledgerAccount) {
        this.ledgerAccount = ledgerAccount;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
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

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalCgst() {
        return totalCgst;
    }

    public void setTotalCgst(Double totalCgst) {
        this.totalCgst = totalCgst;
    }

    public Double getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(Double totalVat) {
        this.totalVat = totalVat;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public int getAuthid() {
        return authid;
    }

    public void setAuthid(int authid) {
        this.authid = authid;
    }
    
    public Double getTotalIgst() {
        return totalIgst;
    }

    public void setTotalIgst(Double totalIgst) {
        this.totalIgst = totalIgst;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reversalOdItcId != null ? reversalOdItcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReversalOfItc)) {
            return false;
        }
        ReversalOfItc other = (ReversalOfItc) object;
        if ((this.reversalOdItcId == null && other.reversalOdItcId != null) || (this.reversalOdItcId != null && !this.reversalOdItcId.equals(other.reversalOdItcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.ReversalOfItc[ reversalOdItcId=" + reversalOdItcId + " ]";
    }
    
}
