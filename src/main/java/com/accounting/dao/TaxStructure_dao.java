/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.dao;

import com.accounting.bean.TaxStructure;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shinelogics
 */
@Repository("tax_dao")
public class TaxStructure_dao {
  @Autowired
     private SessionFactory sessionFactory;
	
	public void addTax(TaxStructure ts) {
             
            sessionFactory.getCurrentSession().saveOrUpdate(ts);
                
	} 
        public TaxStructure getTaxbyId(int id) {
            
		return (TaxStructure) sessionFactory.getCurrentSession().get(TaxStructure.class, id);
	}
}
