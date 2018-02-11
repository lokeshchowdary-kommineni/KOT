/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;


import com.accounting.bean.Purchase;
import com.accounting.bean.PurchaseEstimate;
import com.accounting.bean.PurchaseEstimateItem;
import com.accounting.dao.PurchaseEstimateDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("purchaseEstimateservice")
@Transactional
public class PurchaseEstimateService  {

    
    @Autowired
    private PurchaseEstimateDao purchaseEstimateDao;
   
    public String savePurchaseEstimate(PurchaseEstimate purchase){
        return purchaseEstimateDao.saveEPurchase(purchase); 
    }
    
   
    public int savePurchaseEstimateItem(PurchaseEstimateItem purchase){
        return purchaseEstimateDao.savePurchaseEstimateItem(purchase);
    }

 
    public List<PurchaseEstimate> listPurchaseEstimate() {
        return purchaseEstimateDao.listPurchaseEstimate();

    }
    
    public PurchaseEstimate getPurchaseEstimateById(String id){
        return purchaseEstimateDao.getPurchaseEstimateById(id);

    }
    
    public List<Object[]> getPurchaseEstimateItemByPurchaseEstimateId(String id){
       return purchaseEstimateDao.getPurchaseEstimateItemByPurchaseEstimateId(id);

    }
    
   
    public void deletePurchaseEstimate(String purchaseEId) {
        purchaseEstimateDao.deletePurchaseEstimate(purchaseEId);
    
    }
     public void deletePurchaseEstimateItem(String purchaseEId) {
        purchaseEstimateDao.deletePurchaseEstimateItem(purchaseEId);
    
    }
     
    
    public List<Object[]> GetDatatableObject(String query){
    return purchaseEstimateDao.GetDatatableObject(query);
}
public List<Object[]> GetDatatableCount(String query){
    return purchaseEstimateDao.GetDatatableCount(query);
}
    public List<PurchaseEstimateItem> getPurchaseEstimateItem(String id){
          return purchaseEstimateDao.getPurchaseEstimateItem(id);
    }
    
   
}

