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
@Table(name = "predefined_bill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PredefinedBill.findAll", query = "SELECT p FROM PredefinedBill p")
    , @NamedQuery(name = "PredefinedBill.findByPredefinedBillId", query = "SELECT p FROM PredefinedBill p WHERE p.predefinedBillId = :predefinedBillId")
    , @NamedQuery(name = "PredefinedBill.findByAddLess", query = "SELECT p FROM PredefinedBill p WHERE p.addLess = :addLess")
    , @NamedQuery(name = "PredefinedBill.findByAddOrLess", query = "SELECT p FROM PredefinedBill p WHERE p.addOrLess = :addOrLess")
    , @NamedQuery(name = "PredefinedBill.findByAmount", query = "SELECT p FROM PredefinedBill p WHERE p.amount = :amount")
    , @NamedQuery(name = "PredefinedBill.findByAssessValue", query = "SELECT p FROM PredefinedBill p WHERE p.assessValue = :assessValue")
    , @NamedQuery(name = "PredefinedBill.findByBillName", query = "SELECT p FROM PredefinedBill p WHERE p.billName = :billName")
    , @NamedQuery(name = "PredefinedBill.findByCategory", query = "SELECT p FROM PredefinedBill p WHERE p.category = :category")
    , @NamedQuery(name = "PredefinedBill.findByCgst", query = "SELECT p FROM PredefinedBill p WHERE p.cgst = :cgst")
    , @NamedQuery(name = "PredefinedBill.findByDiscount", query = "SELECT p FROM PredefinedBill p WHERE p.discount = :discount")
    , @NamedQuery(name = "PredefinedBill.findByEpOrEpv", query = "SELECT p FROM PredefinedBill p WHERE p.epOrEpv = :epOrEpv")
    , @NamedQuery(name = "PredefinedBill.findById", query = "SELECT p FROM PredefinedBill p WHERE p.id = :id")
    , @NamedQuery(name = "PredefinedBill.findByInvoiceAmount", query = "SELECT p FROM PredefinedBill p WHERE p.invoiceAmount = :invoiceAmount")
    , @NamedQuery(name = "PredefinedBill.findByInvoiceNo", query = "SELECT p FROM PredefinedBill p WHERE p.invoiceNo = :invoiceNo")
    , @NamedQuery(name = "PredefinedBill.findByItemCode", query = "SELECT p FROM PredefinedBill p WHERE p.itemCode = :itemCode")
    , @NamedQuery(name = "PredefinedBill.findByMargin", query = "SELECT p FROM PredefinedBill p WHERE p.margin = :margin")
    , @NamedQuery(name = "PredefinedBill.findByMca", query = "SELECT p FROM PredefinedBill p WHERE p.mca = :mca")
    , @NamedQuery(name = "PredefinedBill.findByNameOfBill", query = "SELECT p FROM PredefinedBill p WHERE p.nameOfBill = :nameOfBill")
    , @NamedQuery(name = "PredefinedBill.findByNameOfTheItem", query = "SELECT p FROM PredefinedBill p WHERE p.nameOfTheItem = :nameOfTheItem")
    , @NamedQuery(name = "PredefinedBill.findByNarration", query = "SELECT p FROM PredefinedBill p WHERE p.narration = :narration")
    , @NamedQuery(name = "PredefinedBill.findByQty", query = "SELECT p FROM PredefinedBill p WHERE p.qty = :qty")
    , @NamedQuery(name = "PredefinedBill.findByRate", query = "SELECT p FROM PredefinedBill p WHERE p.rate = :rate")
    , @NamedQuery(name = "PredefinedBill.findByTotalCgst", query = "SELECT p FROM PredefinedBill p WHERE p.totalCgst = :totalCgst")
    , @NamedQuery(name = "PredefinedBill.findByTotalVat", query = "SELECT p FROM PredefinedBill p WHERE p.totalVat = :totalVat")
    , @NamedQuery(name = "PredefinedBill.findByUnit", query = "SELECT p FROM PredefinedBill p WHERE p.unit = :unit")
    , @NamedQuery(name = "PredefinedBill.findByVaa", query = "SELECT p FROM PredefinedBill p WHERE p.vaa = :vaa")
    , @NamedQuery(name = "PredefinedBill.findByVat", query = "SELECT p FROM PredefinedBill p WHERE p.vat = :vat")
    , @NamedQuery(name = "PredefinedBill.findByTaxPercentage", query = "SELECT p FROM PredefinedBill p WHERE p.taxPercentage = :taxPercentage")})
public class PredefinedBill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "predefined_bill_id")
    private Integer predefinedBillId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "add_less",columnDefinition = "DECIMAL(15,2)")
    private Double addLess;
    @Column(name = "add_or_less",columnDefinition = "DECIMAL(15,2)")
    private Double addOrLess;
    @Column(name = "amount",columnDefinition = "DECIMAL(15,2)")
    private Double amount;
    @Column(name = "assess_value",columnDefinition = "DECIMAL(15,2)")
    private Double assessValue;
    @Size(max = 50)
    @Column(name = "bill_name")
    private String billName;
    @Size(max = 50)
    @Column(name = "category")
    private String category;
    @Column(name = "cgst",columnDefinition = "DECIMAL(15,2)")
    private Double cgst;
    @Column(name = "discount")
    private Integer discount;
    @Column(name = "ep_or_epv",columnDefinition = "DECIMAL(15,2)")
    private Double epOrEpv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;
    @Column(name = "invoice_amount",columnDefinition = "DECIMAL(15,2)")
    private Double invoiceAmount;
    @Column(name = "invoice_no")
    private Integer invoiceNo;
    @Size(max = 50)
    @Column(name = "item_code")
    private String itemCode;
    @Column(name = "margin")
    private Integer margin;
    @Column(name = "mca",columnDefinition = "DECIMAL(15,2)")
    private Double mca;
    @Size(max = 50)
    @Column(name = "name_of_bill")
    private String nameOfBill;
    @Size(max = 50)
    @Column(name = "name_of_the_item")
    private String nameOfTheItem;
    @Size(max = 100)
    @Column(name = "narration")
    private String narration;
    @Column(name = "qty")
    private Integer qty;
    @Column(name = "rate",columnDefinition = "DECIMAL(15,2)")
    private Double rate;
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
    @Size(max = 10)
    @Column(name = "tax_percentage")
    private String taxPercentage;
    @Column(name = "total_igst",columnDefinition = "DECIMAL(15,2)")
    private Double totalIgst;
    @Size(max = 30)
    @Column(name = "is_igst")
    private String isIgst;
    @Size(max = 30)
    @Column(name = "pos")
    private String pos;

    public Double getTotalIgst() {
        return totalIgst;
    }

    public void setTotalIgst(Double totalIgst) {
        this.totalIgst = totalIgst;
    }

    public String getIsIgst() {
        return isIgst;
    }

    public void setIsIgst(String isIgst) {
        this.isIgst = isIgst;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
    

    public PredefinedBill() {
    }

    public PredefinedBill(Integer predefinedBillId) {
        this.predefinedBillId = predefinedBillId;
    }

    public PredefinedBill(Integer predefinedBillId, int id) {
        this.predefinedBillId = predefinedBillId;
        this.id = id;
    }

    public Integer getPredefinedBillId() {
        return predefinedBillId;
    }

    public void setPredefinedBillId(Integer predefinedBillId) {
        this.predefinedBillId = predefinedBillId;
    }

    public Double getAddLess() {
        return addLess;
    }

    public void setAddLess(Double addLess) {
        this.addLess = addLess;
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

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getEpOrEpv() {
        return epOrEpv;
    }

    public void setEpOrEpv(Double epOrEpv) {
        this.epOrEpv = epOrEpv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Integer getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Integer invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    public Double getMca() {
        return mca;
    }

    public void setMca(Double mca) {
        this.mca = mca;
    }

    public String getNameOfBill() {
        return nameOfBill;
    }

    public void setNameOfBill(String nameOfBill) {
        this.nameOfBill = nameOfBill;
    }

    public String getNameOfTheItem() {
        return nameOfTheItem;
    }

    public void setNameOfTheItem(String nameOfTheItem) {
        this.nameOfTheItem = nameOfTheItem;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
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

    public String getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(String taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (predefinedBillId != null ? predefinedBillId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PredefinedBill)) {
            return false;
        }
        PredefinedBill other = (PredefinedBill) object;
        if ((this.predefinedBillId == null && other.predefinedBillId != null) || (this.predefinedBillId != null && !this.predefinedBillId.equals(other.predefinedBillId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.PredefinedBill[ predefinedBillId=" + predefinedBillId + " ]";
    }
    
}
