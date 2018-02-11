/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.service;

import com.accounting.bean.AccountGroupMaster;
import com.accounting.dao.AccountGroup_dao;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author shinelogics
 */
@Service
@Transactional
public class AccountGroup_Service {
     @Autowired
        private AccountGroup_dao agdao;
	
	

     public void addAccountGroup(AccountGroupMaster ag) {
            
		 agdao.addAccount(ag);
	}
       public List<AccountGroupMaster> listAccount() {
            return agdao.listAccount();
        }
        public AccountGroupMaster getAccbyId(int id) {
		return agdao.getAccountbyId(id);
                
	}
         public void deleteAccount(int t)throws ConstraintViolationException {
		agdao.deleteAccount(t);
	}
           public boolean checkAccSymbol(String us) {
        return agdao.checkAccSymbol(us);
    }
         
}
