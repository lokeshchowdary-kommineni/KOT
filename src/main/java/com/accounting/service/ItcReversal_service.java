/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.service;

import com.accounting.bean.ItcReversalMaster;
import com.accounting.dao.ItcReversal_dao;
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
public class ItcReversal_service {
  @Autowired
        private ItcReversal_dao idao;
     public void addReversal(ItcReversalMaster ir) {
            
		 idao.addReversal(ir);
	}
      public List<ItcReversalMaster> listReversal() {
            return idao.listReversal();
        }
        public ItcReversalMaster getReversalbyId(int id) {
		return idao.getReversalbyId(id);
                
	}
         public void deleteReversal(int t)throws ConstraintViolationException {
		idao.deleteReversal(t);
	}
         public boolean checkItcName(String us) {
        return idao.checkItcName(us);
    }
}
