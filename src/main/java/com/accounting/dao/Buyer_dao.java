/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.dao;

import com.accounting.bean.BuyerMaster;
import java.math.BigInteger;
import com.accounting.bean.LedgerAccountMaster;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shinelogics
 */
@Repository("buyer_dao")
public class Buyer_dao {
   @Autowired
	private SessionFactory sessionFactory;
	
	public int addBuyer(BuyerMaster bm) {
             
		sessionFactory.getCurrentSession().saveOrUpdate(bm);
                return bm.getIdBuyer();
                
	}
        
        public int addToLedger(LedgerAccountMaster lam) {
             
		sessionFactory.getCurrentSession().saveOrUpdate(lam);
                return lam.getIdLedger();
                
	}
        public List<Object[]> GetDatatableBuyerObject(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
        }
        public List<Object[]> GetDatatableBuyerCount(String sql2) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql2).list();
        } 
        
        public LedgerAccountMaster getFromLedgerByIDandType(LedgerAccountMaster lam){
            Criteria criteria = sessionFactory.openSession().createCriteria(LedgerAccountMaster.class)
                    .add(Restrictions.eq("rId", lam.getRId())).add(Restrictions.eq("type", lam.getType()));
            System.out.println("Ledger ID is :"+lam.getIdLedger());
            return (LedgerAccountMaster) criteria.uniqueResult();
        }
        
         public List<BuyerMaster> listBuyer() {
            return (List<BuyerMaster>) sessionFactory.getCurrentSession().createQuery(" FROM BuyerMaster").list();
	}
         
          public List<BuyerMaster> listBuyerCredit() {
            return (List<BuyerMaster>) sessionFactory.getCurrentSession().createQuery(" FROM BuyerMaster").list();
	}
          public BuyerMaster getBuyerbyId(int buyer_id) {
            
		return (BuyerMaster) sessionFactory.getCurrentSession().get(BuyerMaster.class, buyer_id);
	}
         public void deleteBuyer(int del)throws ConstraintViolationException {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM BuyerMaster WHERE id_buyer= "+del+" ").executeUpdate();
	}
         public int maxid() {
            Integer i=(Integer)sessionFactory.getCurrentSession().createQuery(" SELECT MAX(idBuyer) FROM BuyerMaster").uniqueResult();
             if ( i== null)
            {
                i=0;
            } 
            return i;
	}
         
         public boolean checkBuyerName(String us) {
            
             List<BuyerMaster> BuyerInfo = null;
            
		BuyerInfo=(List<BuyerMaster>) sessionFactory.getCurrentSession().createQuery("  FROM BuyerMaster where buyer_name='"+us+"' ").list();
	if(BuyerInfo.size()>0)
        {
        return true;
        }else
        {
          return false;
        }
}
}
