/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.dao;

import com.accounting.bean.AccountGroupMaster;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shinelogics
 */
@Repository("account_dao")
public class AccountGroup_dao {
     @Autowired
	private SessionFactory sessionFactory;
	
	public void addAccount(AccountGroupMaster ag) {
             
		sessionFactory.getCurrentSession().saveOrUpdate(ag);
                
	}
         public List<AccountGroupMaster> listAccount() {
            return (List<AccountGroupMaster>) sessionFactory.getCurrentSession().createQuery(" FROM AccountGroupMaster where account_group_name!='PRIMARY'   ORDER BY account_group_name  ASC ").list();
	}
         
         public AccountGroupMaster getAccountbyId(int acc_id) {
            
		return (AccountGroupMaster) sessionFactory.getCurrentSession().get(AccountGroupMaster.class, acc_id);
	}
         public void deleteAccount(int del)throws ConstraintViolationException {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM AccountGroupMaster WHERE id_account= "+del+"").executeUpdate();
	}
          public boolean checkAccSymbol(String us) {
            
             List<AccountGroupMaster> accInfo = null;
            
		accInfo=(List<AccountGroupMaster>) sessionFactory.getCurrentSession().createQuery("  FROM AccountGroupMaster where account_group_name='"+us+"' ").list();
	if(accInfo.size()>0)
        {
        return true;
        }else
        {
          return false;
        }
}
}
