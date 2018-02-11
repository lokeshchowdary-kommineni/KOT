/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.service;

import com.accounting.bean.TaxStructure;
import com.accounting.dao.TaxStructure_dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author shinelogics
 */
@Service
@Transactional
public class TaxStructure_Service {
    @Autowired
        private TaxStructure_dao tdao;
	
	

     public void addTax(TaxStructure t) {
            
		 tdao.addTax(t);
	}
     public TaxStructure getTaxId(int id) {
		return tdao.getTaxbyId(id);
                
	}
}
