/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.dao;

import com.accounting.bean.AccountGroupMaster;
import com.accounting.bean.LedgerAccountMaster;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shinelogics
 */
@Repository("ledger_dao")
public class LedgerAccount_dao {
    
     @Autowired
     private SessionFactory sessionFactory;
	
	public void addLedger(LedgerAccountMaster lm) {
             
            sessionFactory.getCurrentSession().saveOrUpdate(lm);
                
	}
          public List<LedgerAccountMaster> listLedger() {
            return (List<LedgerAccountMaster>) sessionFactory.getCurrentSession().createQuery(" FROM LedgerAccountMaster where predefined='1' OR predefined='0' OR predefined IS NOT NULL ").list();
	}
        public LedgerAccountMaster getLedgerbyId(int ledger_id) {
            
		return (LedgerAccountMaster) sessionFactory.getCurrentSession().get(LedgerAccountMaster.class, ledger_id);
	}
          
           public void deleteLedger(int del) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM LedgerAccountMaster WHERE id_ledger= '"+del+"'").executeUpdate();
	}
        public boolean checkLedgerName(String us) {
            
             List<LedgerAccountMaster> ledgeInfo = null;
            
		ledgeInfo=(List<LedgerAccountMaster>) sessionFactory.getCurrentSession().createQuery("  FROM LedgerAccountMaster where name_of_ledger='"+us+"' ").list();
	if(ledgeInfo.size()>0)
        {
        return true;
        }else
            
        {
          return false;
        }
          }
           
        public List<AccountGroupMaster> listAccount() {
            return (List<AccountGroupMaster>) sessionFactory.getCurrentSession().createQuery(" FROM AccountGroupMaster where account_group_name='Bank Accounts'").list();
	}
        
//        public List<AccountGroupMaster> listAccountGroups() {
//            return (List<AccountGroupMaster>) sessionFactory.getCurrentSession().createQuery(" FROM AccountGroupMaster ").list();
//	}
        
         public List<AccountGroupMaster> listAccountGroups() {
            return (List<AccountGroupMaster>) sessionFactory.getCurrentSession().createQuery("FROM AccountGroupMaster WHERE NOT account_group_name='SUPPLIERS' AND NOT account_group_name='BUYERS'  AND NOT account_group_name='CGST' AND NOT account_group_name='VAT' AND NOT account_group_name='PRIMARY'  ").list();
	}
         
        public List<AccountGroupMaster> listPrimaryAccountGroups() {
            return (List<AccountGroupMaster>) sessionFactory.getCurrentSession().createQuery(" FROM AccountGroupMaster  WHERE under_group='0'").list();
	}
        
        public List<LedgerAccountMaster> getLederByReferenceId(String id, String type) {
            return (List<LedgerAccountMaster>) sessionFactory.getCurrentSession().createQuery("FROM LedgerAccountMaster WHERE rId='"+id+"' AND type='"+type+"'").list();
	}
        //Voucher purpose
         public List<LedgerAccountMaster> getLederByReferenceIdVoucher(String id) {
            return (List<LedgerAccountMaster>) sessionFactory.getCurrentSession().createQuery("FROM LedgerAccountMaster WHERE idLedger='"+id+"'").list();
	}
           //Voucher purpose
        
        public List<LedgerAccountMaster> getLederVoucherByReferenceId(String id) {
            return (List<LedgerAccountMaster>) sessionFactory.getCurrentSession().createQuery("FROM LedgerAccountMaster WHERE idLedger='"+id+"'").list();
	}
        
        public List<AccountGroupMaster> listAccountGroupsByGroupId(String id) {
            return (List<AccountGroupMaster>) sessionFactory.getCurrentSession().createQuery("FROM AccountGroupMaster  WHERE under_group='"+id+"'").list();
	}
        
        public List<LedgerAccountMaster> getLedgerByLedgerName(String us) {
            
            List<LedgerAccountMaster> ledgeInfo = null;
            ledgeInfo=(List<LedgerAccountMaster>) sessionFactory.getCurrentSession().createQuery("FROM LedgerAccountMaster where name_of_ledger='"+us+"' ").list();
            return ledgeInfo;
        }
          public List<LedgerAccountMaster> listAllLedger() {
            return (List<LedgerAccountMaster>) sessionFactory.getCurrentSession().createQuery(" FROM LedgerAccountMaster ").list();
	}
          public List<Object[]> GetTotalByOpeningType() {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createQuery("SELECT  COALESCE(SUM(CASE WHEN lam.openingType = 'DR' THEN lam.openingAmount ELSE 0 END),0) AS ldr, \n" +
" COALESCE(SUM(CASE WHEN lam.openingType = 'CR' THEN lam.openingAmount ELSE 0 END),0) AS lcr  FROM  LedgerAccountMaster lam\n ").list();
    }
}
