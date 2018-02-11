/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.CompanyInformation;
import com.accounting.bean.Entries;
import com.accounting.bean.Entryitems;
import com.accounting.bean.Purchase;
import com.accounting.bean.PurchaseItem;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SHINELOGICS
 */
@Repository("entryDao")
public class EntryDao {
    
     @Autowired
	private SessionFactory sessionFactory;
     
    public int saveEntry(Entries e) {        
           sessionFactory.getCurrentSession().saveOrUpdate(e);
           return e.getId();
    }
    
    public int saveEntryItem(Entryitems e) {        
           sessionFactory.getCurrentSession().saveOrUpdate(e);
           return e.getId();
    }
    
    public Entries getEntryById(int id){
        return (Entries)sessionFactory.getCurrentSession().get(Entries.class, id);
       
    }
    public Entryitems getEntryItemById(int id){
        return (Entryitems)sessionFactory.getCurrentSession().get(Entryitems.class, id);
       
    }
    
    public Entries getEntryListId(String id,String type){
        Entries list=(Entries) sessionFactory.getCurrentSession().createQuery("FROM Entries WHERE bill_id='"+id+"' and bill_type='"+type+"'").uniqueResult();
        
        return list;
    }
    
    
    
    public void deleteEntry(String billId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM Entries WHERE billId='"+billId+"'").executeUpdate();    
    }
    
     public void deleteEntryItem(String entryId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM Entryitems WHERE entryId='"+entryId+"'").executeUpdate();    
    }
}
