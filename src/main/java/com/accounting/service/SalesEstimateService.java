/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.bean.Sales;
import com.accounting.bean.SalesEstimate;
import com.accounting.bean.SalesEstimateItem;
import com.accounting.dao.SalesEstimateDao;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SHINELOGICS
 */
@Service
@Transactional
public class SalesEstimateService {
    
    @Autowired
    private SalesEstimateDao salesEstimateDao;

    public String saveSalesEstimate(SalesEstimate sales){
        
        return salesEstimateDao.saveSalesEstimate(sales);
    }
    
    public int saveSalesEstimateItem(SalesEstimateItem sales){
        return salesEstimateDao.saveSalesEstimateItem(sales);
    }
    
    public List<SalesEstimate> listSalesEstimate() {
        return salesEstimateDao.listSalesEstimate();
              
    }
    
    public SalesEstimate getSalesEstimateById(String id){
        return salesEstimateDao.getSalesEstimateById(id);
       
    }
    
    public List<SalesEstimateItem> getSalesEstimateItemBySalesId(String id){
        return salesEstimateDao.getSalesEstimateItemBySalesId(id);
    } 
   
  
    public void deleteSalesEstimate(String salesId) {
        salesEstimateDao.deleteSalesEstimate(salesId);
    }
    
     public void deleteSalesEstimateItem(String salesId) {
        salesEstimateDao.deleteSalesEstimateItem(salesId);
    }
    
    public void updateSalesOrSalesEstimate(String query){
       salesEstimateDao.updateSalesOrSalesEstimate(query);
    }
   public List<Object[]> getSalesEstimateItemForSalesINvoice(String id){
        return salesEstimateDao.getSalesEstimateItemBySalesIdForInvoice(id);
    } 
}
