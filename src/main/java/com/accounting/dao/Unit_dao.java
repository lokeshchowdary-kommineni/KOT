/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.dao;

import com.accounting.bean.UnitMaster;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shinelogics
 */
@Repository("unit_dao")
public class Unit_dao {
     @Autowired
	private SessionFactory sessionFactory;
	
	public void addUnit(UnitMaster u) {
             
		sessionFactory.getCurrentSession().saveOrUpdate(u);
                
	}
         public List<UnitMaster> listUnit() {
            return (List<UnitMaster>) sessionFactory.getCurrentSession().createQuery(" FROM UnitMaster").list();
	}
         public UnitMaster getUnitbyId(int unit_id) {
            
		return (UnitMaster) sessionFactory.getCurrentSession().get(UnitMaster.class, unit_id);
	}
         public void deleteUnit(int del) throws ConstraintViolationException{
		sessionFactory.getCurrentSession().createQuery("DELETE FROM UnitMaster WHERE id_unit= "+del+" ").executeUpdate();
	}
         
        public boolean checkUnitSymbol(String us) {
            
             List<UnitMaster> unitInfo = null;
            
		unitInfo=(List<UnitMaster>) sessionFactory.getCurrentSession().createQuery("  FROM UnitMaster where unit_symbol='"+us+"' ").list();
	if(unitInfo.size()>0)
        {
        return true;
        }else
        {
          return false;
        }
      }
}
