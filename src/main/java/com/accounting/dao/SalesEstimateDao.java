/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.ItemMaster;
import com.accounting.bean.Sales;
import com.accounting.bean.SalesEstimate;
import com.accounting.bean.SalesEstimateItem;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SHINELOGICS
 */
@Repository("salesEstimateDao")
public class SalesEstimateDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public String saveSalesEstimate(SalesEstimate sales){
        sessionFactory.getCurrentSession().saveOrUpdate(sales);
        return sales.getInvoiceNo();
    }
    
    public int saveSalesEstimateItem(SalesEstimateItem sales){
        sessionFactory.getCurrentSession().saveOrUpdate(sales);
        return sales.getId();
    }
   
    public List<SalesEstimate> listSalesEstimate() {
        List<SalesEstimate> sale = (List<SalesEstimate>)sessionFactory.getCurrentSession().createQuery("SELECT s.invoiceNo,s.date,s.nameOfBuyer,b.cellNo,s.mode,s.category,s.status,s.invoiceAmount  FROM SalesEstimate AS s LEFT JOIN BuyerMaster AS b ON cast (b.idBuyer as string)=s.buyerId").list();
       
        return sale;
    }
       
    public SalesEstimate getSalesEstimateById(String id){
        return (SalesEstimate)sessionFactory.getCurrentSession().get(SalesEstimate.class, id);
       
    }
    
    public List<SalesEstimateItem> getSalesEstimateItemBySalesId(String id){
        List<SalesEstimateItem> list=sessionFactory.getCurrentSession().createQuery("FROM SalesEstimateItem WHERE invoiceNo='"+id+"'").list();
        return list;
    } 
       
    public void deleteSalesEstimate(String salesId) {
    sessionFactory.getCurrentSession().createQuery("DELETE FROM SalesEstimate WHERE invoiceNo='"+salesId+"'").executeUpdate();    
    }
    
     public void deleteSalesEstimateItem(String salesId) {
    sessionFactory.getCurrentSession().createQuery("DELETE FROM SalesEstimateItem WHERE invoiceNo='"+salesId+"'").executeUpdate();    
    }
    
    public void updateSalesOrSalesEstimate(String query){
       sessionFactory.getCurrentSession().createQuery(query).executeUpdate();
    
    }
    public List<Object[]> getSalesEstimateItemBySalesIdForInvoice(String id){
        List<Object[]> list=sessionFactory.getCurrentSession().createQuery("SELECT s.itemName,s.quantity,u.unitSymbol,s.rate,s.amount,s.cgst,s.vat,s.igst FROM  SalesEstimateItem AS s INNER JOIN UnitMaster AS u ON s.unit = u.idUnit WHERE s.invoiceNo='"+id+"'").list();
        return list;
    } 
}
