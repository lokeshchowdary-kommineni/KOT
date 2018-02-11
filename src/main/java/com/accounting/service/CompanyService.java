/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;


import com.accounting.bean.CompanyInformation;
import com.accounting.dao.CompanyDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component("companyLogo")
@Service
@Transactional
public class CompanyService  {

    
    @Autowired
    private CompanyDao companyDao;
    

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Integer addCompany (CompanyInformation company) {
        return companyDao.addCompany(company);
            
    }
 public List<CompanyInformation> listcompany() {
        return companyDao.listCompany();
 }

    public CompanyInformation getCompanyById(Integer companyId) {
        return companyDao.getCompanyById(companyId);
                        
    }
    public void deleteCompany(Integer companyId) {
        companyDao.deletecompany(companyId);
      

    }
   
}

