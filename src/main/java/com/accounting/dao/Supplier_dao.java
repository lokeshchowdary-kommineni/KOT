/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.dao;

import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.SupplierMaster;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shinelogics
 */
@Repository("supplier_dao")
public class Supplier_dao {
    @Autowired
	private SessionFactory sessionFactory;
	
	public int addSupplier(SupplierMaster sm) {
             
		sessionFactory.getCurrentSession().saveOrUpdate(sm);
                return sm.getIdSupplier();
                
	}
        
        public List<Object[]> GetDatatableSupplier(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
        }
        public List<Object[]> GetDatatableSupplierCount(String sql2) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql2).list();
        } 
        
         public List<SupplierMaster> listSupplier() {
            return (List<SupplierMaster>) sessionFactory.getCurrentSession().createQuery(" FROM SupplierMaster ").list();
	}
         /// Credit Not Show Cash Supplier///
         public List<SupplierMaster> listSupplierCredit() {
            return (List<SupplierMaster>) sessionFactory.getCurrentSession().createQuery(" FROM SupplierMaster WHERE NOT  supplier_name='CASH SUPPLIER' ").list();
	}
        /// Credit Not Show Cash Supplier/// 
          public SupplierMaster getSupplierbyId(int supplier_id) {
            
		return (SupplierMaster) sessionFactory.getCurrentSession().get(SupplierMaster.class, supplier_id);
	}
         public void deleteSupplier(int del)throws ConstraintViolationException {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM SupplierMaster WHERE id_supplier= "+del+"").executeUpdate();
	}
         public int maxSupplierid() {
            Integer i=(Integer) sessionFactory.getCurrentSession().createQuery(" SELECT MAX(idSupplier) FROM SupplierMaster").uniqueResult();
           
          

            if ( i== null)
            {
                i=0;
            } 
            return i;
	}
         public boolean checkSupplierName(String us) {
            
             List<SupplierMaster> SupplierInfo = null;
            
		SupplierInfo=(List<SupplierMaster>) sessionFactory.getCurrentSession().createQuery("  FROM SupplierMaster where supplier_name='"+us+"' ").list();
	if(SupplierInfo.size()>0)
        {
        return true;
        }else
        {
          return false;
        }
          }
}
