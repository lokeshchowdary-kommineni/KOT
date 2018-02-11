/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.service;

import com.accounting.bean.UnitMaster;
import com.accounting.dao.Unit_dao;
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
public class Unit_service {
    @Autowired
        private Unit_dao udao;
	
	@Transactional
	public void addUnit(UnitMaster u) {
            
		 udao.addUnit(u);
	}
          public List<UnitMaster> listUnit() {
            return udao.listUnit();
        }
      public UnitMaster getUnitbyId(int id) {
		return udao.getUnitbyId(id);
                
	}  
      public void deleteUnit(int t) throws ConstraintViolationException{
		udao.deleteUnit(t);
	}
      public boolean checkUnitSymbol(String us) {
        return udao.checkUnitSymbol(us);
    }
}
