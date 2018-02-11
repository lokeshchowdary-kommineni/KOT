/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.bean.Sales;
import com.accounting.bean.SalesReturn;
import com.accounting.bean.SalesReturnItem;
import com.accounting.dao.SalesReturnDao;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SHINELOGICS
 */
@Service("srService")
@Transactional
public class SalesReturnService {
    
    @Autowired
    private SalesReturnDao salesReturnDao;
    

   public String saveSalesReturn(SalesReturn sales){
        return salesReturnDao.saveSalesReturn(sales);
    }
    
    public int saveSalesReturnItemItem(SalesReturnItem sales){
        return salesReturnDao.saveSalesReturnItemItem(sales);
    }
    
    public SalesReturn getSalesReturnById(String id){
        return salesReturnDao.getSalesReturnById(id);
       
    }
    
    public List<SalesReturnItem> getSalesReturnItemBySalesId(String id){
        return salesReturnDao.getSalesReturnItemBySalesId(id);
    } 
    
  
    public List<SalesReturn> listSalesReturn() {
        return salesReturnDao.listSalesReturn();
    }
    
    
   
    
    public void deleteSalesReturn(String salesId) {
        salesReturnDao.deleteSalesReturn(salesId);
    }
    
    public List<SalesReturn> SalesReturnReports(String gsQuery)
  {
   return salesReturnDao.SalesReturnReports(gsQuery);
  }
    
    
    public double  getRSalesReturnId(String code,String invoiceno) {
            return salesReturnDao.getSalesReturnId(code,invoiceno);
        }
    public List<Object[]> GetDatatableSalesRetObject(String query){
    return salesReturnDao.GetDatatableSalesReturnObject(query);
    }
     public List<Object[]> GetDatatableSaleRetCount(String query){
    return salesReturnDao.GetDatatableSalesReturnCount(query);
    }
}
