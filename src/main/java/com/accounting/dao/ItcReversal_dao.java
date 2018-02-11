/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.dao;

import com.accounting.bean.ItcReversalMaster;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shinelogics
 */
@Repository("itc_dao")
public class ItcReversal_dao {
      @Autowired
     private SessionFactory sessionFactory;
	
	public void addReversal(ItcReversalMaster ir) {
             
            sessionFactory.getCurrentSession().saveOrUpdate(ir);
                
	}
          public List<ItcReversalMaster> listReversal() {
            return (List<ItcReversalMaster>) sessionFactory.getCurrentSession().createQuery(" FROM ItcReversalMaster").list();
	}
          public ItcReversalMaster getReversalbyId(int reversal_id) {
            
		return (ItcReversalMaster) sessionFactory.getCurrentSession().get(ItcReversalMaster.class, reversal_id);
	}
           public void deleteReversal(int del)throws ConstraintViolationException {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItcReversalMaster WHERE id_itc= "+del+" ").executeUpdate();
	}
           public boolean checkItcName(String us) {
            
             List<ItcReversalMaster> itcInfo = null;
            
		itcInfo=(List<ItcReversalMaster>) sessionFactory.getCurrentSession().createQuery("  FROM ItcReversalMaster where category_name='"+us+"' ").list();
	if(itcInfo.size()>0)
        {
        return true;
        }else
        {
          return false;
        }
          }
}
