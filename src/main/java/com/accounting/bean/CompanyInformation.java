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
import javax.persistence.Lob;
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
 * @author shine
 */
@Entity
@Table(name = "company_information")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyInformation.findAll", query = "SELECT c FROM CompanyInformation c")
    , @NamedQuery(name = "CompanyInformation.findByIdCompany", query = "SELECT c FROM CompanyInformation c WHERE c.idCompany = :idCompany")
    , @NamedQuery(name = "CompanyInformation.findByAccountsFrom", query = "SELECT c FROM CompanyInformation c WHERE c.accountsFrom = :accountsFrom")
    , @NamedQuery(name = "CompanyInformation.findByCellNo", query = "SELECT c FROM CompanyInformation c WHERE c.cellNo = :cellNo")
    , @NamedQuery(name = "CompanyInformation.findByCity", query = "SELECT c FROM CompanyInformation c WHERE c.city = :city")
    , @NamedQuery(name = "CompanyInformation.findByCompanyEmail", query = "SELECT c FROM CompanyInformation c WHERE c.companyEmail = :companyEmail")
    , @NamedQuery(name = "CompanyInformation.findByCompanyLogo", query = "SELECT c FROM CompanyInformation c WHERE c.companyLogo = :companyLogo")
    , @NamedQuery(name = "CompanyInformation.findByCompanyName", query = "SELECT c FROM CompanyInformation c WHERE c.companyName = :companyName")
    , @NamedQuery(name = "CompanyInformation.findByCompanyPan", query = "SELECT c FROM CompanyInformation c WHERE c.companyPan = :companyPan")
    , @NamedQuery(name = "CompanyInformation.findByCompanyTin", query = "SELECT c FROM CompanyInformation c WHERE c.companyTin = :companyTin")
    , @NamedQuery(name = "CompanyInformation.findByFinancialYearFrom", query = "SELECT c FROM CompanyInformation c WHERE c.financialYearFrom = :financialYearFrom")
    , @NamedQuery(name = "CompanyInformation.findByPhoneNo", query = "SELECT c FROM CompanyInformation c WHERE c.phoneNo = :phoneNo")
    , @NamedQuery(name = "CompanyInformation.findByTaxType", query = "SELECT c FROM CompanyInformation c WHERE c.taxType = :taxType")
    , @NamedQuery(name = "CompanyInformation.findByFinancialYearTo", query = "SELECT c FROM CompanyInformation c WHERE c.financialYearTo = :financialYearTo")})
public class CompanyInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_company")
    private Integer idCompany;
    @Basic(optional = false)
    @NotNull
    @Column(name = "accounts_from")
    @Temporal(TemporalType.DATE)
    private Date accountsFrom;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "address")
    private String address;
    @Size(max = 11)
    @Column(name = "cell_no")
    private String cellNo;
    @Size(max = 255)
    @Column(name = "city")
    private String city;
    @Size(max = 255)
    @Column(name = "company_email")
    private String companyEmail;
    @Size(max = 255)
    @Column(name = "company_logo")
    private String companyLogo;
    @Size(max = 255)
    @Column(name = "company_name")
    private String companyName;
    @Size(max = 255)
    @Column(name = "company_pan")
    private String companyPan;
    @Size(max = 25)
    @Column(name = "company_tin")
    private String companyTin;
    @Size(max = 255)
    @Column(name = "financial_year_from")
    private String financialYearFrom;
    @Size(max = 15)
    @Column(name = "phone_no")
    private String phoneNo;
    @Size(max = 255)
    @Column(name = "tax_type")
    private String taxType;
    @Size(max = 255)
    @Column(name = "financial_year_to")
    private String financialYearTo;

    public CompanyInformation() {
    }

    public CompanyInformation(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public CompanyInformation(Integer idCompany, Date accountsFrom) {
        this.idCompany = idCompany;
        this.accountsFrom = accountsFrom;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public Date getAccountsFrom() {
        return accountsFrom;
    }

    public void setAccountsFrom(Date accountsFrom) {
        this.accountsFrom = accountsFrom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPan() {
        return companyPan;
    }

    public void setCompanyPan(String companyPan) {
        this.companyPan = companyPan;
    }

    public String getCompanyTin() {
        return companyTin;
    }

    public void setCompanyTin(String companyTin) {
        this.companyTin = companyTin;
    }

    public String getFinancialYearFrom() {
        return financialYearFrom;
    }

    public void setFinancialYearFrom(String financialYearFrom) {
        this.financialYearFrom = financialYearFrom;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getFinancialYearTo() {
        return financialYearTo;
    }

    public void setFinancialYearTo(String financialYearTo) {
        this.financialYearTo = financialYearTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompany != null ? idCompany.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyInformation)) {
            return false;
        }
        CompanyInformation other = (CompanyInformation) object;
        if ((this.idCompany == null && other.idCompany != null) || (this.idCompany != null && !this.idCompany.equals(other.idCompany))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.accounting.bean.CompanyInformation[ idCompany=" + idCompany + " ]";
    }
    
}
