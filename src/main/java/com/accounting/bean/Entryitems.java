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
 * @author SHINELOGICS
 */
@Entity
@Table(name = "entryitems")

public class Entryitems implements Serializable {

    @Column(name = "closing_amt_dr",columnDefinition = "DECIMAL(15,2)")
    private Double closingAmtDr=0.0;
    @Column(name = "closing_amt_cr",columnDefinition = "DECIMAL(15,2)")
    private Double closingAmtCr=0.0;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "entry_id")
    private Integer entryId;
    @Column(name = "ledger_id")
    private Integer ledgerId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount",columnDefinition = "DECIMAL(15,2)")
    private Double amount;
    @Size(max = 5)
    @Column(name = "type")
    private String type;

    public Entryitems() {
    }

    public Entryitems(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public Integer getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(Integer ledgerId) {
        this.ledgerId = ledgerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof Entryitems)) {
            return false;
        }
        Entryitems other = (Entryitems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.Entryitems[ id=" + id + " ]";
    }


    public Double getClosingAmtDr() {
        return closingAmtDr;
    }

    public void setClosingAmtDr(Double closingAmtDr) {
        this.closingAmtDr = closingAmtDr;
    }

    public Double getClosingAmtCr() {
        return closingAmtCr;
    }

    public void setClosingAmtCr(Double closingAmtCr) {
        this.closingAmtCr = closingAmtCr;
    }
    
}
