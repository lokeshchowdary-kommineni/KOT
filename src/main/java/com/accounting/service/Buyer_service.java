/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.service;

import com.accounting.bean.BuyerMaster;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.dao.Buyer_dao;
import java.math.BigInteger;
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
public class Buyer_service {
    @Autowired
        private Buyer_dao bdao;
	
	
	public int addBuyer(BuyerMaster sm) {
            
		 return bdao.addBuyer(sm);
	}   
        public int addToLedger(LedgerAccountMaster lam) {
            return bdao.addToLedger(lam);
        }
        public LedgerAccountMaster getFromLedgerByIDandType(LedgerAccountMaster lam){
            return bdao.getFromLedgerByIDandType(lam);
        }
         public List<BuyerMaster> listBuyer() {
            return bdao.listBuyer();
        }
         /////
          public List<BuyerMaster> listBuyerCredit() {
            return bdao.listBuyerCredit();
        }
         /////
          public BuyerMaster getBuyerbyId(int id) {
		return bdao.getBuyerbyId(id);
                
	}  
      public void deleteBuyer(int t)throws ConstraintViolationException {
		bdao.deleteBuyer(t);
	} 
      public int maxId() {
            return bdao.maxid();
        }
       public boolean checkBuyerName(String us) {
        return bdao.checkBuyerName(us);
    }
       public List<Object[]> GetDatatableBuyerObject(String query){
    return bdao.GetDatatableBuyerObject(query);
    }
     public List<Object[]> GetDatatableBuyerCount(String query){
    return bdao.GetDatatableBuyerCount(query);
    }
}
