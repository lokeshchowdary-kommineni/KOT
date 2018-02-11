/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.service;

import com.accounting.bean.AccountGroupMaster;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.dao.LedgerAccount_dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author shinelogics
 */
@Service
@Transactional
public class LedgerAccount_Service {
    @Autowired
        private LedgerAccount_dao ladao;
	
	

     public void addLedgerAccount(LedgerAccountMaster la) {
            
		 ladao.addLedger(la);
	}
      public List<LedgerAccountMaster> listLedger() {
            return ladao.listLedger();
        }
       public List<LedgerAccountMaster> listAllLedger() {
            return ladao.listAllLedger();
        }
        public LedgerAccountMaster getLedgerbyId(int id) {
		return ladao.getLedgerbyId(id);
                
	}
         public void deleteLedger(int t) {
		ladao.deleteLedger(t);
	}
          public boolean checkLedgerName(String us) {
        return ladao.checkLedgerName(us);
    }
         
         public List<AccountGroupMaster> listAccount() {
            return ladao.listAccount();
         }
         
         public List<AccountGroupMaster> listAccountGroups() {
             return ladao.listAccountGroups();
         }
                 
//           public List<AccountGroupMaster> listAccountGroupsNew() {
//             return ladao.listAccountGroupsNew();
//         }       
         
        public List<AccountGroupMaster> listPrimaryAccountGroups() {
          return ladao.listPrimaryAccountGroups();
        }
        
        public List<AccountGroupMaster> listAccountGroupsByGroupId(String id) {
            return ladao.listAccountGroupsByGroupId(id);
        }
        
     
         
         public List<LedgerAccountMaster> getLederByReferenceId(String id, String type) {
             return ladao.getLederByReferenceId(id, type);
         }
         //Voucher Purpose
         public List<LedgerAccountMaster> getLederByReferenceIdVoucher(String id) {
             return ladao.getLederByReferenceIdVoucher(id);
         }
          //Voucher Purpose
         public List<LedgerAccountMaster> getLederVoucherByReferenceId(String id) {
             return ladao.getLederVoucherByReferenceId(id);
         }
         
         public List<LedgerAccountMaster> getLedgerByLedgerName(String us) {
            return ladao.getLedgerByLedgerName(us);
         }
         public List<Object[]> GetTotalByOpeningType() {
       return ladao.GetTotalByOpeningType();
        }
}
