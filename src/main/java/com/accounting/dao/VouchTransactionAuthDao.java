/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.Purchase;
import com.accounting.bean.ReversalOfItc;
import com.accounting.bean.Sales;
import com.accounting.bean.SalesReturn;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MR
 */
@Repository("vouchTransactionAuthDao")
public class VouchTransactionAuthDao {
    
    @Autowired 
    private SessionFactory sessionfactory;
    
    public List<Object[]> getAuthTransactionsSales(){
        List<Object[]> list = (List<Object[]>)sessionfactory.getCurrentSession().createQuery("SELECT s.invoiceNo,s.date,s.nameOfBuyer,s.invoiceAmount,'Sales' FROM Sales AS s WHERE s.mode='Cash' AND s.authid='0'").list();
        return list;
    }
    public List<Object[]> getAuthTransactionsSalesReturn(){
        List<Object[]> list = (List<Object[]>)sessionfactory.getCurrentSession().createQuery("SELECT sr.id,sr.returnDate,sr.nameOfBuyer,sr.invoiceAmount,'SalesReturn' FROM SalesReturn AS sr  WHERE sr.mode='Cash' AND sr.authid='0'").list();
        return list;
    }
    
    public List<Object[]> getAuthTransactionsPurchase(){
        List<Object[]> list = (List<Object[]>)sessionfactory.getCurrentSession().createQuery("SELECT p.invoiceNo,p.date,p.nameOfSupplier,p.invoiceAmount,'Purchase' FROM Purchase AS p WHERE p.mode='Cash' AND p.authid='0' ").list();
        return list;
    }
    
        public List<Object[]> getAuthTransactionsPurchaseReturn(){
        List<Object[]> list = (List<Object[]>)sessionfactory.getCurrentSession().createQuery("SELECT pr.reversalOdItcId,pr.returnDate,pr.supplierId,pr.totalAmount,'PurchaseReturn' FROM ReversalOfItc AS pr WHERE pr.mode='Cash' AND pr.authid='0' ").list();
        return list;
    }
        
    public void updateAuthoriseTransactionByIdS(String id){
        String hqlUpdate = "DELETE e FROM Entryitems AS e INNER JOIN Sales AS s ON s.invoiceNo = e.entryId WHERE s.invoiceNo ='"+id+"' AND e.ledgerId='6'";
        sessionfactory.getCurrentSession().createQuery(hqlUpdate).executeUpdate();
       
    }
    
     public void updateAuthoriseTransactionByIdSR(String id){
        String hqlUpdate = "DELETE entries FROM entries INNER JOIN SalesReturn ON SalesReturn.invoiceNo = entries.billId WHERE SalesReturn.invoiceNo ='"+id+"'";
        sessionfactory.getCurrentSession().createQuery(hqlUpdate).executeUpdate();
       
    }
     
      public void updateAuthoriseTransactionByIdP(String id){
        String hqlUpdate = "DELETE entries FROM entries INNER JOIN Purchase ON Purchase.invoiceNo = entries.billId WHERE Purchase.invoiceNo ='"+id+"'";
        sessionfactory.getCurrentSession().createQuery(hqlUpdate).executeUpdate();
       
    }
      
       public void updateAuthoriseTransactionByIdPR(String id){
        String hqlUpdate = "DELETE entries FROM entries INNER JOIN ReversalOfItc ON ReversalOfItc.reversalOdItcId = entries.billId WHERE ReversalOfItc.reversalOdItcId ='"+id+"'";
        sessionfactory.getCurrentSession().createQuery(hqlUpdate).executeUpdate();
       
    }
       
       
       public void updateSalesById(String id){
         String queryUpdate = "update Sales vRP set vRP.authid = 1 where vRP.invoiceNo ='"+id+"'";
        sessionfactory.getCurrentSession().createQuery(queryUpdate).executeUpdate();
       
    }
       
        public void updateSalesReturnById(String id){
         String queryUpdate = "update SalesReturn vRP set vRP.authid = 1 where vRP.id ='"+id+"'";
        sessionfactory.getCurrentSession().createQuery(queryUpdate).executeUpdate();
       
    }
        
         public void updatePurchaseById(String id){
         String queryUpdate = "update Purchase vRP set vRP.authid = 1 where vRP.invoiceNo ='"+id+"'";
        sessionfactory.getCurrentSession().createQuery(queryUpdate).executeUpdate();
       
    }
         
          public void updatePurchaseReturnById(String id){
         String queryUpdate = "update ReversalOfItc vRP set vRP.authid = 1 where vRP.reversalOdItcId ='"+id+"'";
        sessionfactory.getCurrentSession().createQuery(queryUpdate).executeUpdate();
       
    }
       
          
           public List<Object[]> GetDatatableObject(String sql) {
        return (List<Object[]>)sessionfactory.getCurrentSession().createNativeQuery(sql).list();
    }
    public List<Object[]> GetDatatableCount(String sql2) {
        return (List<Object[]>)sessionfactory.getCurrentSession().createNativeQuery(sql2).list();
    }
       
}
