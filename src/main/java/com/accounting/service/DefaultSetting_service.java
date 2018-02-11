/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.service;

import com.accounting.bean.DefaultSettings;
import com.accounting.dao.DefaultSettings_dao;
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
public class DefaultSetting_service {
     @Autowired
        private DefaultSettings_dao dedao;
	
	

     public void addDefaultSetting(DefaultSettings d) {
            
		 dedao.addDefault(d);
	}
      public DefaultSettings getDefaultbyId(int id) {
		return dedao.getDefaultbyId(id);
                
	}
        public List<DefaultSettings> listDefaultSettings() {
        return dedao.listDefaultSettings();
    }
}
