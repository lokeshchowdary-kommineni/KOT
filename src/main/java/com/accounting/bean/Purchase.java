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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="purchase")
public class Purchase
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GenericGenerator(name="seq_id", strategy="com.accounting.idGeneration.Purchase")
  @GeneratedValue(generator="seq_id")
  @Size(min=1, max=255)
  @Column(name="invoice_no")
  private String invoiceNo;
  @Column(name="add_or_less", columnDefinition="DECIMAL(15,2)")
  private Double addOrLess = Double.valueOf(0.0D);
  @Column(name="amount", columnDefinition="DECIMAL(15,2)")
  private Double amount;
  @Column(name="assess_value", columnDefinition="DECIMAL(15,2)")
  private Double assessValue;
  @Size(max=50)
  @Column(name="cash_supplier")
  private String cashSupplier;
  @Size(max=50)
  @Column(name="category")
  private String category;
  @Column(name="current_balance", columnDefinition="DECIMAL(15,2)")
  private Double currentBalance;
  @Column(name="date")
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern="dd/MM/yyyy")
  private Date date;
  @Column(name="def_addless", columnDefinition="DECIMAL(15,2)")
  private Double defAddless;
  @Column(name="def_cgst", columnDefinition="DECIMAL(15,2)")
  private Double defCgst;
  @Column(name="def_igst", columnDefinition="DECIMAL(15,2)")
  private Double defIgst;
  @Column(name="def_vat", columnDefinition="DECIMAL(15,2)")
  private Double defVat;
  @Column(name="ep", columnDefinition="DECIMAL(15,2)")
  private Double ep;
  @Column(name="id")
  private Integer id;
  @Column(name="invoice_amount", columnDefinition="DECIMAL(15,2)")
  private Double invoiceAmount;
  @Size(max=50)
  @Column(name="item_code")
  private String itemCode;
  @Size(max=50)
  @Column(name="mode")
  private String mode;
  @Size(max=50)
  @Column(name="name_of_supplier")
  private String nameOfSupplier;
  @Size(max=50)
  @Column(name="name_of_the_item")
  private String nameOfTheItem;
  @Size(max=255)
  @Column(name="narration")
  private String narration;
  @Basic(optional=false)
  @Size(min=1, max=50)
  @Column(name="purchase_invoice_id")
  private String purchaseInvoiceId;
  @Column(name="qty")
  private Integer qty;
  @Column(name="rate", columnDefinition="DECIMAL(15,2)")
  private Double rate;
  @Column(name="supplier_id")
  private Integer supplierId;
  @Size(max=20)
  @Column(name="supplier_type")
  private String supplierType;
  @Size(max=20)
  @Column(name="tin_no")
  private String tinNo;
  @Column(name="total_amount", columnDefinition="DECIMAL(15,2)")
  private Double totalAmount;
  @Column(name="total_cgst", columnDefinition="DECIMAL(15,2)")
  private Double totalCgst;
  @Column(name="total_invoice_amount", columnDefinition="DECIMAL(15,2)")
  private Double totalInvoiceAmount;
  @Column(name="total_vat", columnDefinition="DECIMAL(15,2)")
  private Double totalVat;
  @Column(name="total_igst", columnDefinition="DECIMAL(15,2)")
  private Double totalIgst;
  @Column(name="unit")
  private Integer unit;
  @Column(name="vat", columnDefinition="DECIMAL(15,2)")
  private Double vat;
  @Basic(optional=false)
  @Column(name="authid")
  private int authid;
  @Column(name="tax_cgst", columnDefinition="DECIMAL(15,2)")
  private Double taxCgst;
  @Column(name="tax_sgst", columnDefinition="DECIMAL(15,2)")
  private Double taxSgst;
  @Column(name="tax_igst", columnDefinition="DECIMAL(15,2)")
  private Double taxIgst;
  @Column(name="igst", columnDefinition="DECIMAL(15,2)")
  private Double igst;
  @Column(name="is_igst")
  private String isIgst;
  @Size(max=30)
  @Column(name="pos")
  private String pos;
  
  public Double getDefIgst()
  {
    return this.defIgst;
  }
  
  public void setDefIgst(Double defIgst)
  {
    this.defIgst = defIgst;
  }
  
  public Double getTotalIgst()
  {
    return this.totalIgst;
  }
  
  public void setTotalIgst(Double totalIgst)
  {
    this.totalIgst = totalIgst;
  }
  
  public String getPos()
  {
    return this.pos;
  }
  
  public void setPos(String pos)
  {
    this.pos = pos;
  }
  
  public String getIsIgst()
  {
    return this.isIgst;
  }
  
  public void setIsIgst(String isIgst)
  {
    this.isIgst = isIgst;
  }
  
  public Purchase() {}
  
  public Purchase(String invoiceNo)
  {
    this.invoiceNo = invoiceNo;
  }
  
  public Purchase(String invoiceNo, String purchaseInvoiceId, int authid)
  {
    this.invoiceNo = invoiceNo;
    this.purchaseInvoiceId = purchaseInvoiceId;
    this.authid = authid;
  }
  
  public String getInvoiceNo()
  {
    return this.invoiceNo;
  }
  
  public void setInvoiceNo(String invoiceNo)
  {
    this.invoiceNo = invoiceNo;
  }
  
  public Double getAddOrLess()
  {
    return this.addOrLess;
  }
  
  public void setAddOrLess(Double addOrLess)
  {
    this.addOrLess = addOrLess;
  }
  
  public Double getAmount()
  {
    return this.amount;
  }
  
  public void setAmount(Double amount)
  {
    this.amount = amount;
  }
  
  public Double getAssessValue()
  {
    return this.assessValue;
  }
  
  public void setAssessValue(Double assessValue)
  {
    this.assessValue = assessValue;
  }
  
  public String getCashSupplier()
  {
    return this.cashSupplier;
  }
  
  public void setCashSupplier(String cashSupplier)
  {
    this.cashSupplier = cashSupplier;
  }
  
  public String getCategory()
  {
    return this.category;
  }
  
  public void setCategory(String category)
  {
    this.category = category;
  }
  
  public Double getCurrentBalance()
  {
    return this.currentBalance;
  }
  
  public void setCurrentBalance(Double currentBalance)
  {
    this.currentBalance = currentBalance;
  }
  
  public Date getDate()
  {
    return this.date;
  }
  
  public void setDate(Date date)
  {
    this.date = date;
  }
  
  public Double getDefAddless()
  {
    return this.defAddless;
  }
  
  public void setDefAddless(Double defAddless)
  {
    this.defAddless = defAddless;
  }
  
  public Double getDefCgst()
  {
    return this.defCgst;
  }
  
  public void setDefCgst(Double defCgst)
  {
    this.defCgst = defCgst;
  }
  
  public Double getDefVat()
  {
    return this.defVat;
  }
  
  public void setDefVat(Double defVat)
  {
    this.defVat = defVat;
  }
  
  public Double getEp()
  {
    return this.ep;
  }
  
  public void setEp(Double ep)
  {
    this.ep = ep;
  }
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Double getInvoiceAmount()
  {
    return this.invoiceAmount;
  }
  
  public void setInvoiceAmount(Double invoiceAmount)
  {
    this.invoiceAmount = invoiceAmount;
  }
  
  public String getItemCode()
  {
    return this.itemCode;
  }
  
  public void setItemCode(String itemCode)
  {
    this.itemCode = itemCode;
  }
  
  public String getMode()
  {
    return this.mode;
  }
  
  public void setMode(String mode)
  {
    this.mode = mode;
  }
  
  public String getNameOfSupplier()
  {
    return this.nameOfSupplier;
  }
  
  public void setNameOfSupplier(String nameOfSupplier)
  {
    this.nameOfSupplier = nameOfSupplier;
  }
  
  public String getNameOfTheItem()
  {
    return this.nameOfTheItem;
  }
  
  public void setNameOfTheItem(String nameOfTheItem)
  {
    this.nameOfTheItem = nameOfTheItem;
  }
  
  public String getNarration()
  {
    return this.narration;
  }
  
  public void setNarration(String narration)
  {
    this.narration = narration;
  }
  
  public String getPurchaseInvoiceId()
  {
    return this.purchaseInvoiceId;
  }
  
  public void setPurchaseInvoiceId(String purchaseInvoiceId)
  {
    this.purchaseInvoiceId = purchaseInvoiceId;
  }
  
  public Integer getQty()
  {
    return this.qty;
  }
  
  public void setQty(Integer qty)
  {
    this.qty = qty;
  }
  
  public Double getRate()
  {
    return this.rate;
  }
  
  public void setRate(Double rate)
  {
    this.rate = rate;
  }
  
  public Integer getSupplierId()
  {
    return this.supplierId;
  }
  
  public void setSupplierId(Integer supplierId)
  {
    this.supplierId = supplierId;
  }
  
  public String getSupplierType()
  {
    return this.supplierType;
  }
  
  public void setSupplierType(String supplierType)
  {
    this.supplierType = supplierType;
  }
  
  public String getTinNo()
  {
    return this.tinNo;
  }
  
  public void setTinNo(String tinNo)
  {
    this.tinNo = tinNo;
  }
  
  public Double getTotalAmount()
  {
    return this.totalAmount;
  }
  
  public void setTotalAmount(Double totalAmount)
  {
    this.totalAmount = totalAmount;
  }
  
  public Double getTotalCgst()
  {
    return this.totalCgst;
  }
  
  public void setTotalCgst(Double totalCgst)
  {
    this.totalCgst = totalCgst;
  }
  
  public Double getTotalInvoiceAmount()
  {
    return this.totalInvoiceAmount;
  }
  
  public void setTotalInvoiceAmount(Double totalInvoiceAmount)
  {
    this.totalInvoiceAmount = totalInvoiceAmount;
  }
  
  public Double getTotalVat()
  {
    return this.totalVat;
  }
  
  public void setTotalVat(Double totalVat)
  {
    this.totalVat = totalVat;
  }
  
  public Integer getUnit()
  {
    return this.unit;
  }
  
  public void setUnit(Integer unit)
  {
    this.unit = unit;
  }
  
  public Double getVat()
  {
    return this.vat;
  }
  
  public void setVat(Double vat)
  {
    this.vat = vat;
  }
  
  public int getAuthid()
  {
    return this.authid;
  }
  
  public void setAuthid(int authid)
  {
    this.authid = authid;
  }
  
  public Double getTaxCgst()
  {
    return this.taxCgst;
  }
  
  public void setTaxCgst(Double taxCgst)
  {
    this.taxCgst = taxCgst;
  }
  
  public Double getTaxSgst()
  {
    return this.taxSgst;
  }
  
  public Double getTaxIgst()
  {
    return this.taxIgst;
  }
  
  public void setTaxIgst(Double taxIgst)
  {
    this.taxIgst = taxIgst;
  }
  
  public void setTaxSgst(Double taxSgst)
  {
    this.taxSgst = taxSgst;
  }
  
  public Double getIgst()
  {
    return this.igst;
  }
  
  public void setIgst(Double igst)
  {
    this.igst = igst;
  }
  
  public int hashCode()
  {
    int hash = 0;
    hash += (this.invoiceNo != null ? this.invoiceNo.hashCode() : 0);
    return hash;
  }
  
  public boolean equals(Object object)
  {
    if (!(object instanceof Purchase)) {
      return false;
    }
    Purchase other = (Purchase)object;
    if (((this.invoiceNo == null) && (other.invoiceNo != null)) || ((this.invoiceNo != null) && (!this.invoiceNo.equals(other.invoiceNo)))) {
      return false;
    }
    return true;
  }
  
  public String toString()
  {
    return "com.accounting.bean.Purchase[ invoiceNo=" + this.invoiceNo + " ]";
  }
}
