/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;


import com.accounting.bean.ItemReport;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.Sales;
import com.accounting.bean.Purchase;
import com.accounting.bean.PurchaseItem;
import com.accounting.bean.SupplierMaster;
import com.accounting.dao.PurchseDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PurchaseService  {

    
    @Autowired
    private PurchseDao purchseDao;
  
 
    public String savePurchase(Purchase purchase){
        return purchseDao.savePurchase(purchase);
    }
    
   
    public int savePurchaseItem(PurchaseItem purchase){
        return purchseDao.savePurchaseItem(purchase);
    }

 
    public List<Purchase> listPurchse() {
        return purchseDao.listPurchase();
    }
    
    public Purchase getPurchaseById(String id){
        return purchseDao.getPurchaseById(id);
    }
     public LedgerAccountMaster getLedgerIdBySupplierId(int id,String type){
       return purchseDao.getledgerIdBySupplierId(id,type);
    }
    
    public List<PurchaseItem> getPurchaseItemByPurchaseId(String id){
       return purchseDao.getPurchaseItemByPurchaseId(id); 
    }
    
   public List<ItemReport> getItemReportByPurchaseId(String id,String type,String iName){
       return purchseDao.getItemReportByPurchaseId(id, type, iName);
    }
    
   public void updateItemReport(String query){
    purchseDao.updateItemReport(query);
}
   
    public void deletePurchase(String purchaseId) {
        purchseDao.deletePurchse(purchaseId);
    
    }
    public void deletePurchaseItem(String purchaseId) {
        purchseDao.deletePurchseItem(purchaseId);
    
    }
    
        public List<SupplierMaster> listSupplierLike(String key)
  {
    return purchseDao.listSupplierLike(key);
  }
          public List<SupplierMaster> listSupplierWithoutGstin(String key)
  {
    return purchseDao.listSupplierWithoutGstin(key);
  }

    
    public List<SupplierMaster> listSupplierMasterCusomerLike(String key){
        return purchseDao.listSupplierMasterCustomerLike(key);
    }
 //Reports Start
    
    public List<Sales> listItemReportsSSR() {
        return purchseDao.listItemReportsSSR();
    }


     public List<Purchase> purchaseReports(String gsQuery)
  {
    return purchseDao.PurchaseReports(gsQuery);
  }
     
      public List<Sales> listVAAMCASSR() {
        return purchseDao.listVAAMCASSR();
    }
      public List<Object[]> listVAAMCASSRReports(String gsQuery)
  {
    return purchseDao.listVAAMCASSRReports(gsQuery);
  }
      
       public List<Object[]> listItemSaleReports(String gsQuery)
  {
    return purchseDao.listItemSaleReports(gsQuery);
  }
public List<Object[]> GetDatatableObject(String query){
    return purchseDao.GetDatatableObject(query);
}
public List<Object[]> GetDatatableCount(String query){
    return purchseDao.GetDatatableCount(query);
}
public Double sumTotalinvoiceAmt(String Categoary,String Date){      
        return purchseDao.sumTotalinvoiceAmt(Categoary, Date);
    }
 public Double sumTotalCgstSgstPerday(String Categoary,String Date,int LedgerID){
      
        return purchseDao.sumTotalCgstSgstPerday(Categoary, Date, LedgerID);
    }
    public List<Object[]> getTotalSgtsandcgstFromPurchaseTable(String Categoary,String Date) {
        return purchseDao.getTotalSgtsandcgstFromPurchaseTable(Categoary, Date);
    } 
}

