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
 * @author shine
 */
@Entity
@Table(name = "item_master")
@XmlRootElement
public class ItemMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "alt_unit")
    private Integer altUnit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "basic_price",columnDefinition = "DECIMAL(15,2)")
    private Double basicPrice;
    @Column(name = "basic_vat_price",columnDefinition = "DECIMAL(15,2)")
    private Double basicVatPrice;
   
    @Column(name = "ca",columnDefinition = "DECIMAL(15,4)")
    private Double ca=0.0;
    @Column(name = "cap",columnDefinition = "DECIMAL(15,2)")
    private Double cap=0.0;
    @Column(name = "cap_alt",columnDefinition = "DECIMAL(15,2)")
    private Double capAlt;
    @Column(name = "cap_checkbox")
    private Boolean capCheckbox;
    @Size(max = 50)
    @Column(name = "category")
    private String category;
    @Column(name = "cb",columnDefinition = "DECIMAL(15,4)")
    private Double cb=0.0;
    @Column(name = "cbp",columnDefinition = "DECIMAL(15,2)")
    private Double cbp=0.0;
    @Column(name = "cbp_atl",columnDefinition = "DECIMAL(15,2)")
    private Double cbpAtl;
    @Column(name = "cbp_checkbox")
    private Boolean cbpCheckbox;
    @Column(name = "com_code")
    private Integer comCode;
    @Column(name = "cp",columnDefinition = "DECIMAL(15,2)")
    private Double cp=0.0;
//    @Size(max = 30)
//    @Column(name = "cpcpv_amount")
//    private String cpcpvAmount;
    @Size(max = 30)
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
 
    @Column(name = "current_stock",columnDefinition = "DECIMAL(15,2)")
    private Double currentStock;
    @Column(name = "ea",columnDefinition = "DECIMAL(15,2)")
    private Double ea=0.0;
    @Column(name = "ep",columnDefinition = "DECIMAL(15,2)")
    private Double ep=0.0;
    @Column(name = "ep_alt",columnDefinition = "DECIMAL(15,2)")
    private Double epAlt;
    @Column(name = "er",columnDefinition = "DECIMAL(15,2)")
    private Double er=0.0;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "item_code")
    private String itemCode;
    @Column(name = "item_group")
    private Integer itemGroup;
    @Size(max = 50)
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "la",columnDefinition = "DECIMAL(15,2)")
    private Double la=0.0;
    @Column(name = "lp",columnDefinition = "DECIMAL(15,2)")
    private Double lp=0.0;
    @Column(name = "lp_alt",columnDefinition = "DECIMAL(15,2)")
    private Double lpAlt;
    @Column(name = "lr",columnDefinition = "DECIMAL(15,2)")
    private Double lr=0.0;
    @Column(name = "mc",columnDefinition = "DECIMAL(15,2)")
    private Double mc;
  
    @Column(name = "mc_mca",columnDefinition = "DECIMAL(15,2)")
    private Double mcMca;
    @Column(name = "mca",columnDefinition = "DECIMAL(15,2)")
    private Double mca;
   
    @Column(name = "mcmca_amount",columnDefinition = "DECIMAL(15,2)")
    private Double mcmcaAmount;
    @Size(max = 30)
    @Column(name = "modifyed_by")
    private String modifyedBy;
    @Column(name = "modifyed_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyedOn;
   
    @Column(name = "opening_stock",columnDefinition = "DECIMAL(15,2)")
    private Double openingStock=0.0;
    @Column(name = "rate",columnDefinition = "DECIMAL(15,2)")
    private Double rate=0.0;
  
    @Column(name = "rol")
    private Double rol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy") 
    private Date stockDate;
    @Column(name = "stock_value",columnDefinition = "DECIMAL(15,4)")
    private Double stockValue =0.0;
    @Column(name = "ta",columnDefinition = "DECIMAL(15,2)")
    private Double ta=0.0;
    @Size(max = 30)
    @Column(name = "tax")
    private String tax;
    @Size(max = 10)
    @Column(name = "total_unit")
    private String totalUnit;
    @Column(name = "tp",columnDefinition = "DECIMAL(15,2)")
    private Double tp=0.0;
    @Column(name = "tp_alt",columnDefinition = "DECIMAL(15,2)")
    private Double tpAlt;
    @Column(name = "tr",columnDefinition = "DECIMAL(15,2)")
    private Double tr;
    @Column(name = "unit")
    private Integer unit;
    @Column(name = "va",columnDefinition = "DECIMAL(15,4)")
    private Double va=0.0;
    @Column(name = "vap",columnDefinition = "DECIMAL(15,2)")
    private Double vap=0.0;
    @Column(name = "vap_alt",columnDefinition = "DECIMAL(15,2)")
    private Double vapAlt;
    @Column(name = "vap_checkbox")
    private Boolean vapCheckbox;
    @Column(name = "vb",columnDefinition = "DECIMAL(15,4)")
    private Double vb=0.0;
    @Column(name = "vbp",columnDefinition = "DECIMAL(15,2)")
    private Double vbp=0.0;
    @Column(name = "vbp_alt",columnDefinition = "DECIMAL(15,2)")
    private Double vbpAlt;
    @Column(name = "vbp_checkbox")
    private Boolean vbpCheckbox;
    @Column(name = "bit_item")
    private Boolean bit_item;

    public Boolean getBit_item() {
        return bit_item;
    }

    public void setBit_item(Boolean bit_item) {
        this.bit_item = bit_item;
    }
    @Column(name = "tax_sgst",columnDefinition = "DECIMAL(15,2)")
    private Double taxSgst;
    @Column(name = "tax_cgst",columnDefinition = "DECIMAL(15,2)")
    private Double taxCgst;
      @Column(name = "tax_igst",columnDefinition = "DECIMAL(15,2)")
    private Double taxIgst;

    public Double getTaxIgst() {
        return taxIgst;
    }

    public void setTaxIgst(Double taxIgst) {
        this.taxIgst = taxIgst;
    }

    public ItemMaster() {
    }

    public ItemMaster(Integer id) {
        this.id = id;
    }

    public ItemMaster(Integer id, String itemCode, Date stockDate) {
        this.id = id;
        this.itemCode = itemCode;
        this.stockDate = stockDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAltUnit() {
        return altUnit;
    }

    public void setAltUnit(Integer altUnit) {
        this.altUnit = altUnit;
    }

    public Double getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(Double basicPrice) {
        this.basicPrice = basicPrice;
    }

    public Double getBasicVatPrice() {
        return basicVatPrice;
    }

    public void setBasicVatPrice(Double basicVatPrice) {
        this.basicVatPrice = basicVatPrice;
    }

  

    public Double getCa() {
        return ca;
    }

    public void setCa(Double ca) {
        this.ca = ca;
    }

    public Double getCap() {
        return cap;
    }

    public void setCap(Double cap) {
        this.cap = cap;
    }

    public Double getCapAlt() {
        return capAlt;
    }

    public void setCapAlt(Double capAlt) {
        this.capAlt = capAlt;
    }

    public Boolean getCapCheckbox() {
        return capCheckbox;
    }

    public void setCapCheckbox(Boolean capCheckbox) {
        this.capCheckbox = capCheckbox;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getCb() {
        return cb;
    }

    public void setCb(Double cb) {
        this.cb = cb;
    }

    public Double getCbp() {
        return cbp;
    }

    public void setCbp(Double cbp) {
        this.cbp = cbp;
    }

    public Double getCbpAtl() {
        return cbpAtl;
    }

    public void setCbpAtl(Double cbpAtl) {
        this.cbpAtl = cbpAtl;
    }

    public Boolean getCbpCheckbox() {
        return cbpCheckbox;
    }

    public void setCbpCheckbox(Boolean cbpCheckbox) {
        this.cbpCheckbox = cbpCheckbox;
    }

    public Integer getComCode() {
        return comCode;
    }

    public void setComCode(Integer comCode) {
        this.comCode = comCode;
    }

    public Double getCp() {
        return cp;
    }

    public void setCp(Double cp) {
        this.cp = cp;
    }

   

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    

    public Double getEa() {
        return ea;
    }

    public void setEa(Double ea) {
        this.ea = ea;
    }

    public Double getEp() {
        return ep;
    }

    public void setEp(Double ep) {
        this.ep = ep;
    }

    public Double getEpAlt() {
        return epAlt;
    }

    public void setEpAlt(Double epAlt) {
        this.epAlt = epAlt;
    }

    public Double getEr() {
        return er;
    }

    public void setEr(Double er) {
        this.er = er;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(Integer itemGroup) {
        this.itemGroup = itemGroup;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getLa() {
        return la;
    }

    public void setLa(Double la) {
        this.la = la;
    }

    public Double getLp() {
        return lp;
    }

    public void setLp(Double lp) {
        this.lp = lp;
    }

    public Double getLpAlt() {
        return lpAlt;
    }

    public void setLpAlt(Double lpAlt) {
        this.lpAlt = lpAlt;
    }

    public Double getLr() {
        return lr;
    }

    public void setLr(Double lr) {
        this.lr = lr;
    }

    public Double getMc() {
        return mc;
    }

    public void setMc(Double mc) {
        this.mc = mc;
    }

  

    public Double getMca() {
        return mca;
    }

    public void setMca(Double mca) {
        this.mca = mca;
    }

  

    public String getModifyedBy() {
        return modifyedBy;
    }

    public void setModifyedBy(String modifyedBy) {
        this.modifyedBy = modifyedBy;
    }

    public Date getModifyedOn() {
        return modifyedOn;
    }

    public void setModifyedOn(Date modifyedOn) {
        this.modifyedOn = modifyedOn;
    }

    public Double getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Double currentStock) {
        this.currentStock = currentStock;
    }

    public Double getMcMca() {
        return mcMca;
    }

    public void setMcMca(Double mcMca) {
        this.mcMca = mcMca;
    }

    public Double getMcmcaAmount() {
        return mcmcaAmount;
    }

    public void setMcmcaAmount(Double mcmcaAmount) {
        this.mcmcaAmount = mcmcaAmount;
    }

    public Double getOpeningStock() {
        return openingStock;
    }

    public void setOpeningStock(Double openingStock) {
        this.openingStock = openingStock;
    }

 

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRol() {
        return rol;
    }

    public void setRol(Double rol) {
        this.rol = rol;
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    public Double getStockValue() {
        return stockValue;
    }

    public void setStockValue(Double stockValue) {
        this.stockValue = stockValue;
    }

    public Double getTa() {
        return ta;
    }

    public void setTa(Double ta) {
        this.ta = ta;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(String totalUnit) {
        this.totalUnit = totalUnit;
    }

    public Double getTp() {
        return tp;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public Double getTpAlt() {
        return tpAlt;
    }

    public void setTpAlt(Double tpAlt) {
        this.tpAlt = tpAlt;
    }

    public Double getTr() {
        return tr;
    }

    public void setTr(Double tr) {
        this.tr = tr;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Double getVa() {
        return va;
    }

    public void setVa(Double va) {
        this.va = va;
    }

    public Double getVap() {
        return vap;
    }

    public void setVap(Double vap) {
        this.vap = vap;
    }

    public Double getVapAlt() {
        return vapAlt;
    }

    public void setVapAlt(Double vapAlt) {
        this.vapAlt = vapAlt;
    }

    public Boolean getVapCheckbox() {
        return vapCheckbox;
    }

    public void setVapCheckbox(Boolean vapCheckbox) {
        this.vapCheckbox = vapCheckbox;
    }

    public Double getVb() {
        return vb;
    }

    public void setVb(Double vb) {
        this.vb = vb;
    }

    public Double getVbp() {
        return vbp;
    }

    public void setVbp(Double vbp) {
        this.vbp = vbp;
    }

    public Double getVbpAlt() {
        return vbpAlt;
    }

    public void setVbpAlt(Double vbpAlt) {
        this.vbpAlt = vbpAlt;
    }

    public Boolean getVbpCheckbox() {
        return vbpCheckbox;
    }

    public void setVbpCheckbox(Boolean vbpCheckbox) {
        this.vbpCheckbox = vbpCheckbox;
    }

    public Double getTaxSgst() {
        return taxSgst;
    }

    public void setTaxSgst(Double taxSgst) {
        this.taxSgst = taxSgst;
    }

    public Double getTaxCgst() {
        return taxCgst;
    }

    public void setTaxCgst(Double taxCgst) {
        this.taxCgst = taxCgst;
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
        if (!(object instanceof ItemMaster)) {
            return false;
        }
        ItemMaster other = (ItemMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.ItemMaster[ id=" + id + " ]";
    }

    public Object iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
