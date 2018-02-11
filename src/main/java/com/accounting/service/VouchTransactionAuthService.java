/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.bean.Purchase;
import com.accounting.bean.ReversalOfItc;
import com.accounting.bean.Sales;
import com.accounting.bean.SalesReturn;
import com.accounting.dao.VouchTransactionAuthDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 
 *
 * @author MR
 */
@Service
@Transactional
public class VouchTransactionAuthService {
    
    @Autowired
    private VouchTransactionAuthDao vouchTransactionAuthDao;
    
    public List<Object[]> getAuthTransactionsSales(){
        return vouchTransactionAuthDao.getAuthTransactionsSales();
    }
     public List<Object[]> getAuthTransactionsSalesReturn(){
        return vouchTransactionAuthDao.getAuthTransactionsSalesReturn();
    }
    
    public List<Object[]> getAuthTransactionsPurchase(){
        return vouchTransactionAuthDao.getAuthTransactionsPurchase();
    }
        public List<Object[]> getAuthTransactionsPurchaseReturn(){
        return vouchTransactionAuthDao.getAuthTransactionsPurchaseReturn();
    }

    
    public void updateAuthoriseTransactionByIdS(String id){
         vouchTransactionAuthDao.updateAuthoriseTransactionByIdS(id);
     }
    
     public void updateAuthoriseTransactionByIdSR(String id){
         vouchTransactionAuthDao.updateAuthoriseTransactionByIdSR(id);
     }
     
      public void updateAuthoriseTransactionByIdP(String id){
         vouchTransactionAuthDao.updateAuthoriseTransactionByIdP(id);
     }
      
       public void updateAuthoriseTransactionByIdPR(String id){
         vouchTransactionAuthDao.updateAuthoriseTransactionByIdPR(id);
     }
       
    public void updateSalesById(String id){
        vouchTransactionAuthDao.updateSalesById(id);
     }
    
    public void updateSalesReturnById(String id){
        vouchTransactionAuthDao.updateSalesReturnById(id);
     }
    
    public void updatePurchaseById(String id){
        vouchTransactionAuthDao.updatePurchaseById(id);
     }
    
    public void updatePurchaseReturnById(String id){
        vouchTransactionAuthDao.updatePurchaseReturnById(id);
     }
    
    public List<Object[]> GetDatatableObject(String query){
    return vouchTransactionAuthDao.GetDatatableObject(query);
}
public List<Object[]> GetDatatableCount(String query){
    return vouchTransactionAuthDao.GetDatatableCount(query);
}
    
    
    
}
