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
@Table(name = "sales_return")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesReturn.findAll", query = "SELECT s FROM SalesReturn s")
    , @NamedQuery(name = "SalesReturn.findById", query = "SELECT s FROM SalesReturn s WHERE s.id = :id")
    , @NamedQuery(name = "SalesReturn.findByActualMca", query = "SELECT s FROM SalesReturn s WHERE s.actualMca = :actualMca")
    , @NamedQuery(name = "SalesReturn.findByAddLess", query = "SELECT s FROM SalesReturn s WHERE s.addLess = :addLess")
    , @NamedQuery(name = "SalesReturn.findByAmount", query = "SELECT s FROM SalesReturn s WHERE s.amount = :amount")
    , @NamedQuery(name = "SalesReturn.findByAssessValue", query = "SELECT s FROM SalesReturn s WHERE s.assessValue = :assessValue")
    , @NamedQuery(name = "SalesReturn.findByBuyerBalance", query = "SELECT s FROM SalesReturn s WHERE s.buyerBalance = :buyerBalance")
    , @NamedQuery(name = "SalesReturn.findByBuyerId", query = "SELECT s FROM SalesReturn s WHERE s.buyerId = :buyerId")
    , @NamedQuery(name = "SalesReturn.findByCashBuyer", query = "SELECT s FROM SalesReturn s WHERE s.cashBuyer = :cashBuyer")
    , @NamedQuery(name = "SalesReturn.findByCategory", query = "SELECT s FROM SalesReturn s WHERE s.category = :category")
    , @NamedQuery(name = "SalesReturn.findByCgst", query = "SELECT s FROM SalesReturn s WHERE s.cgst = :cgst")
    , @NamedQuery(name = "SalesReturn.findByCpcpV", query = "SELECT s FROM SalesReturn s WHERE s.cpcpV = :cpcpV")
    , @NamedQuery(name = "SalesReturn.findByDate", query = "SELECT s FROM SalesReturn s WHERE s.date = :date")
    , @NamedQuery(name = "SalesReturn.findByDiscount", query = "SELECT s FROM SalesReturn s WHERE s.discount = :discount")
    , @NamedQuery(name = "SalesReturn.findByInvoiceAmount", query = "SELECT s FROM SalesReturn s WHERE s.invoiceAmount = :invoiceAmount")
    , @NamedQuery(name = "SalesReturn.findByInvoiceNo", query = "SELECT s FROM SalesReturn s WHERE s.invoiceNo = :invoiceNo")
    , @NamedQuery(name = "SalesReturn.findByItemCode", query = "SELECT s FROM SalesReturn s WHERE s.itemCode = :itemCode")
    , @NamedQuery(name = "SalesReturn.findByItemName", query = "SELECT s FROM SalesReturn s WHERE s.itemName = :itemName")
    , @NamedQuery(name = "SalesReturn.findByMargin", query = "SELECT s FROM SalesReturn s WHERE s.margin = :margin")
    , @NamedQuery(name = "SalesReturn.findByMca", query = "SELECT s FROM SalesReturn s WHERE s.mca = :mca")
    , @NamedQuery(name = "SalesReturn.findByMediatorBalance", query = "SELECT s FROM SalesReturn s WHERE s.mediatorBalance = :mediatorBalance")
    , @NamedQuery(name = "SalesReturn.findByMediatorId", query = "SELECT s FROM SalesReturn s WHERE s.mediatorId = :mediatorId")
    , @NamedQuery(name = "SalesReturn.findByMode", query = "SELECT s FROM SalesReturn s WHERE s.mode = :mode")
    , @NamedQuery(name = "SalesReturn.findByNameOfBuyer", query = "SELECT s FROM SalesReturn s WHERE s.nameOfBuyer = :nameOfBuyer")
    , @NamedQuery(name = "SalesReturn.findByNameOfMediator", query = "SELECT s FROM SalesReturn s WHERE s.nameOfMediator = :nameOfMediator")
    , @NamedQuery(name = "SalesReturn.findByNarration", query = "SELECT s FROM SalesReturn s WHERE s.narration = :narration")
    , @NamedQuery(name = "SalesReturn.findByQuantity", query = "SELECT s FROM SalesReturn s WHERE s.quantity = :quantity")
    , @NamedQuery(name = "SalesReturn.findByRate", query = "SELECT s FROM SalesReturn s WHERE s.rate = :rate")
    , @NamedQuery(name = "SalesReturn.findByReturnDate", query = "SELECT s FROM SalesReturn s WHERE s.returnDate = :returnDate")
    , @NamedQuery(name = "SalesReturn.findByTinNo", query = "SELECT s FROM SalesReturn s WHERE s.tinNo = :tinNo")
    , @NamedQuery(name = "SalesReturn.findByTotalCgst", query = "SELECT s FROM SalesReturn s WHERE s.totalCgst = :totalCgst")
    , @NamedQuery(name = "SalesReturn.findByTotalVat", query = "SELECT s FROM SalesReturn s WHERE s.totalVat = :totalVat")
    , @NamedQuery(name = "SalesReturn.findByUnit", query = "SELECT s FROM SalesReturn s WHERE s.unit = :unit")
    , @NamedQuery(name = "SalesReturn.findByVaa", query = "SELECT s FROM SalesReturn s WHERE s.vaa = :vaa")
    , @NamedQuery(name = "SalesReturn.findByVat", query = "SELECT s FROM SalesReturn s WHERE s.vat = :vat")
    , @NamedQuery(name = "SalesReturn.findByWorkSite", query = "SELECT s FROM SalesReturn s WHERE s.workSite = :workSite")
    , @NamedQuery(name = "SalesReturn.findByWithoutTax", query = "SELECT s FROM SalesReturn s WHERE s.withoutTax = :withoutTax")
    , @NamedQuery(name = "SalesReturn.findByAuthid", query = "SELECT s FROM SalesReturn s WHERE s.authid = :authid")})
public class SalesReturn implements Serializable {

    private static final long serialVersionUID = 1L;
     @Id
    @GenericGenerator(name="seq_id", strategy="com.accounting.idGeneration.SalesReturn")
    @GeneratedValue(generator="seq_id") 
    @Column(name = "id")
    private String id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "actual_mca",columnDefinition = "DECIMAL(15,2)")
    private Double actualMca;
    @Column(name = "add_less",columnDefinition = "DECIMAL(15,2)")
    private Double addLess;
    @Column(name = "amount",columnDefinition = "DECIMAL(15,2)")
    private Double amount;
    @Column(name = "assess_value",columnDefinition = "DECIMAL(15,2)")
    private Double assessValue;
    @Column(name = "buyer_balance",columnDefinition = "DECIMAL(15,2)")
    private Double buyerBalance;
    @Size(max = 10)
    @Column(name = "buyer_id")
    private String buyerId;
    @Size(max = 30)
    @Column(name = "cash_buyer")
    private String cashBuyer;
    @Size(max = 30)
    @Column(name = "category")
    private String category;
    @Column(name = "cgst",columnDefinition = "DECIMAL(15,2)")
    private Double cgst;
    @Column(name = "cp_cpV",columnDefinition = "DECIMAL(15,2)")
    private Double cpcpV;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy") 
    private Date date;
    @Column(name = "discount",columnDefinition = "DECIMAL(15,2)")
    private Double discount;
    @Column(name = "invoice_amount",columnDefinition = "DECIMAL(15,2)")
    private Double invoiceAmount;
    @Size(max = 255)
    @Column(name = "invoice_no")
    private String invoiceNo;
    @Size(max = 20)
    @Column(name = "item_code")
    private String itemCode;
    @Size(max = 40)
    @Column(name = "item_name")
    private String itemName;
    @Size(max = 10)
    @Column(name = "margin")
    private String margin;
    @Column(name = "mca",columnDefinition = "DECIMAL(15,2)")
    private Double mca;
    @Column(name = "mediator_balance",columnDefinition = "DECIMAL(15,2)")
    private Double mediatorBalance;
    @Size(max = 10)
    @Column(name = "mediator_id")
    private String mediatorId;
    @Size(max = 20)
    @Column(name = "mode")
    private String mode;
    @Size(max = 30)
    @Column(name = "name_of_buyer")
    private String nameOfBuyer;
    @Size(max = 30)
    @Column(name = "name_of_mediator")
    private String nameOfMediator;
    @Size(max = 100)
    @Column(name = "narration")
    private String narration;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "rate",columnDefinition = "DECIMAL(15,2)")
    private Double rate;
    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy") 
    private Date returnDate;
    @Size(max = 20)
    @Column(name = "tin_no")
    private String tinNo;
    @Column(name = "total_cgst",columnDefinition = "DECIMAL(15,2)")
    private Double totalCgst;
    @Column(name = "total_vat",columnDefinition = "DECIMAL(15,2)")
    private Double totalVat;
    @Column(name = "unit")
    private Integer unit;
    @Column(name = "vaa",columnDefinition = "DECIMAL(15,2)")
    private Double vaa;
    @Column(name = "vat",columnDefinition = "DECIMAL(15,2)")
    private Double vat;
    @Size(max = 30)
    @Column(name = "work_site")
    private String workSite;
    @Column(name = "withoutTax",columnDefinition = "DECIMAL(15,2)")
    private Double withoutTax;
    @Basic(optional = false)
    @Column(name = "Authid")
    private int authid;
    
    @Size(max = 10)
    @Column(name = "mediator_type")
    private String mediatorType;
    @Size(max = 10)
    @Column(name = "buyer_type")
    private String buyerType;
    @Column(name = "total_igst",columnDefinition = "DECIMAL(15,2)")
    private Double totalIgst;
    @Size(max = 30)  
    @Column(name = "pos")
    private String pos;

    public Double getTotalIgst() {
        return totalIgst;
    }

    public void setTotalIgst(Double totalIgst) {
        this.totalIgst = totalIgst;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
    public String getMediatorType() {
        return mediatorType;
    }

    public void setMediatorType(String mediatorType) {
        this.mediatorType = mediatorType;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public SalesReturn() {
    }

    public SalesReturn(String id) {
        this.id = id;
    }

    public SalesReturn(String id, Date returnDate, int authid) {
        this.id = id;
        this.returnDate = returnDate;
        this.authid = authid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getActualMca() {
        return actualMca;
    }

    public void setActualMca(Double actualMca) {
        this.actualMca = actualMca;
    }

    public Double getAddLess() {
        return addLess;
    }

    public void setAddLess(Double addLess) {
        this.addLess = addLess;
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

    public Double getBuyerBalance() {
        return buyerBalance;
    }

    public void setBuyerBalance(Double buyerBalance) {
        this.buyerBalance = buyerBalance;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getCashBuyer() {
        return cashBuyer;
    }

    public void setCashBuyer(String cashBuyer) {
        this.cashBuyer = cashBuyer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getCgst() {
        return cgst;
    }

    public void setCgst(Double cgst) {
        this.cgst = cgst;
    }

    public Double getCpcpV() {
        return cpcpV;
    }

    public void setCpcpV(Double cpcpV) {
        this.cpcpV = cpcpV;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public Double getMca() {
        return mca;
    }

    public void setMca(Double mca) {
        this.mca = mca;
    }

    public Double getMediatorBalance() {
        return mediatorBalance;
    }

    public void setMediatorBalance(Double mediatorBalance) {
        this.mediatorBalance = mediatorBalance;
    }

    public String getMediatorId() {
        return mediatorId;
    }

    public void setMediatorId(String mediatorId) {
        this.mediatorId = mediatorId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getNameOfBuyer() {
        return nameOfBuyer;
    }

    public void setNameOfBuyer(String nameOfBuyer) {
        this.nameOfBuyer = nameOfBuyer;
    }

    public String getNameOfMediator() {
        return nameOfMediator;
    }

    public void setNameOfMediator(String nameOfMediator) {
        this.nameOfMediator = nameOfMediator;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
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

    public Double getVaa() {
        return vaa;
    }

    public void setVaa(Double vaa) {
        this.vaa = vaa;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public String getWorkSite() {
        return workSite;
    }

    public void setWorkSite(String workSite) {
        this.workSite = workSite;
    }

    public Double getWithoutTax() {
        return withoutTax;
    }

    public void setWithoutTax(Double withoutTax) {
        this.withoutTax = withoutTax;
    }

    public int getAuthid() {
        return authid;
    }

    public void setAuthid(int authid) {
        this.authid = authid;
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
        if (!(object instanceof SalesReturn)) {
            return false;
        }
        SalesReturn other = (SalesReturn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.SalesReturn[ id=" + id + " ]";
    }
    
}
