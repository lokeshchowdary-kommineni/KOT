/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.ItemReport;
import static java.util.Collections.list;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SHINELOGICS
 */
@Repository("stockMaintanceDao")
public class StockMaintanceDao {
    
@Autowired
    private SessionFactory sessionFactory;
	
        public List<ItemReport> listStock()
        {
          List<ItemReport> group = null;
          try
          {
            group = this.sessionFactory.getCurrentSession().createQuery("from ItemReport").list();
          }
          catch (HibernateException e)
          {
            e.printStackTrace();
          }
          return group;
        }

     
       public List<Object[]> GetStockReportObject(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
        }
       
        public List<Object[]> GetStockReportCount(String sql2) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql2).list();
        } 
   
          
        public List<Object[]> getWaiterReport(String fdtae,String tdate,String wname){
        List list=sessionFactory.getCurrentSession().createSQLQuery("SELECT w.waiter_name,i.item_name,i.vap_alt,s.quantity,(i.vap_alt*s.quantity),s.invoice_no,si.sdate ,gm.item_group_name FROM sales_bill AS s JOIN sale_invoice AS si ON  s.invoice_no=si.invoice_no LEFT JOIN waitermaster AS w ON w.waiter_id =s.waiter_id JOIN item_master AS i ON  s.item_code=i.item_code INNER JOIN item_group_master AS gm ON gm.id_item =i.item_group where w.id='"+wname+"' AND sdate  BETWEEN '"+fdtae+"' and '"+tdate+"'").list();
       System.out.println("list :"+list);
        return list;
    }
      
       
           
}
