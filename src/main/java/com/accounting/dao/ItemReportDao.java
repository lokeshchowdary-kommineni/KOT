/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.ItemGroupMaster;
import com.accounting.bean.ItemMaster;
import com.accounting.bean.ItemReport;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shinelogics
 */
@Repository("ItemReportDao")
public class ItemReportDao {
   @Autowired
	private SessionFactory sessionFactory;
	
	public void addItemReport(ItemReport ir) {
             
		sessionFactory.getCurrentSession().saveOrUpdate(ir);
                
	}
         public List<ItemReport> listItemReport() {
            return (List<ItemReport>) sessionFactory.getCurrentSession().createQuery(" FROM ItemReport").list();
	}
         public ItemReport getItemReportbyId(int id) {
            
		return (ItemReport) sessionFactory.getCurrentSession().get(ItemReport.class, id);
	}
         public void deleteItemReport(String del) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItemReport WHERE transactionId= '"+del+"'").executeUpdate();
	}
         public List<Object[]> filterReport(String qry)
       {
           
    List<Object[]> list= this.sessionFactory.getCurrentSession().createQuery(qry).list();
    
    return list;
       }
         public List<ItemMaster> getItemList(){
        List<ItemMaster> list=sessionFactory.getCurrentSession().createQuery("FROM ItemMaster").list();
        return list;
    }
          public List<ItemGroupMaster> listItem() {
            return (List<ItemGroupMaster>) sessionFactory.getCurrentSession().createQuery(" FROM ItemGroupMaster").list();
	}
          
       
}
