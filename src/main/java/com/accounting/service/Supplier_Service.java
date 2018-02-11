/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.service;

import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.SupplierMaster;
import com.accounting.dao.Supplier_dao;
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
public class Supplier_Service {
 @Autowired
        private Supplier_dao sdao;
	
	@Transactional
	public int addSupplier(SupplierMaster sm) {
            
		 return sdao.addSupplier(sm);
	}  
        
         public List<SupplierMaster> listSupplier() {
            return sdao.listSupplier();
        }
         /// Credit Not Show Cash Supplier///
         public List<SupplierMaster> listSupplierCredit() {
            return sdao.listSupplierCredit();
        }
         /// Credit Not Show Cash Supplier///
          public SupplierMaster getSupplierbyId(int id) {
		return sdao.getSupplierbyId(id);
                
	}  
      public void deleteSupplier(int t)throws ConstraintViolationException {
		sdao.deleteSupplier(t);
	}
      public int maxSupplierId() {
            return sdao.maxSupplierid();
        }
       public boolean checkSupplierName(String us) {
        return sdao.checkSupplierName(us);
    }
       public List<Object[]> GetDatatableSupplier(String query){
    return sdao.GetDatatableSupplier(query);
    }
     public List<Object[]> GetDatatableSupplierCount(String query){
    return sdao.GetDatatableSupplierCount(query);
    }
}
