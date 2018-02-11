/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.dao;

import com.accounting.bean.DefaultSettings;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shinelogics
 */
@Repository("default_dao")
public class DefaultSettings_dao {
    @Autowired
     private SessionFactory sessionFactory;
	
	public void addDefault(DefaultSettings lm) {
             
            sessionFactory.getCurrentSession().saveOrUpdate(lm);
                
	}
        public DefaultSettings getDefaultbyId(int default_id) {
            
		return (DefaultSettings) sessionFactory.getCurrentSession().get(DefaultSettings.class, default_id);
	}
        
     public List<DefaultSettings> listDefaultSettings() {
        List<DefaultSettings> listDS = null;
        try {

            listDS = (List<DefaultSettings>)sessionFactory.openSession().createQuery("from DefaultSettings").list();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return listDS;
    }
}
