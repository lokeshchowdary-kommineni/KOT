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
import com.accounting.bean.SalesReturn;
import com.accounting.bean.SalesReturnItem;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SHINELOGICS
 */
@Repository("salesReturnDao")
public class SalesReturnDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public String saveSalesReturn(SalesReturn sales){
        sessionFactory.getCurrentSession().saveOrUpdate(sales);
        return sales.getId();
    }
    
    public int saveSalesReturnItemItem(SalesReturnItem sales){
        sessionFactory.getCurrentSession().saveOrUpdate(sales);
        return sales.getId();
    }
    
    public SalesReturn getSalesReturnById(String id){
        return (SalesReturn)sessionFactory.getCurrentSession().get(SalesReturn.class, id);
       
    }
    
    public List<SalesReturnItem> getSalesReturnItemBySalesId(String id){
        List<SalesReturnItem> list=sessionFactory.getCurrentSession().createQuery("FROM SalesReturnItem WHERE invoiceNo='"+id+"'").list();
        return list;
    } 
    
  
    public List<SalesReturn> listSalesReturn() {
        List<SalesReturn> sale = (List<SalesReturn>)sessionFactory.getCurrentSession().createQuery("from SalesReturn").list();
        return sale;
    }
       
    public void deleteSalesReturn(String salesId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM SalesReturn WHERE id='"+salesId+"'").executeUpdate();    
    }
       
    
     public List<SalesReturn> SalesReturnReports(String gsQuery)
  {
    List<SalesReturn> salesReturn = sessionFactory.getCurrentSession().createQuery(gsQuery).list();
    
    return salesReturn;
  }
     public List<Object[]> GetDatatableSalesReturnObject(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
        }
  public List<Object[]> GetDatatableSalesReturnCount(String sql2) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql2).list();
        } 
     
     
     public double  getSalesReturnId(String code,String invoiceno) {
            double i=(double) sessionFactory.getCurrentSession().createQuery("SELECT coalesce(SUM(sri.returnQuantity),0) AS Totalreturnqty FROM SalesReturnItem as sri inner join SalesReturn sr on sr.id=sri.invoiceNo inner join Sales s on s.invoiceNo=sr.invoiceNo WHERE  sri.itemCode='"+code+"' and s.invoiceNo='"+invoiceno+"'").uniqueResult();

            if( i<=0){
                
              return 0;
            }
            
            return i;
	}
    
}
