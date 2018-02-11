/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.service;

import com.accounting.bean.ItemGroupMaster;
import com.accounting.bean.ItemMaster;
import com.accounting.dao.ItemGroup_dao;
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
public class ItemGroup_Service {
  
     @Autowired
        private ItemGroup_dao itemdao;
    
	
	@Transactional
	public void addItemGroup(ItemGroupMaster ig) {
            
		 itemdao.addItem(ig);
	}
        public List<ItemGroupMaster> listItem() {
            return itemdao.listItem();
        }
        public ItemGroupMaster getItembyId(int id) {
		return itemdao.getItembyId(id);
                
	}
         public void deleteItem(int t)throws ConstraintViolationException {
		itemdao.deleteItem(t);
	}
          public boolean checkItemGroup(String us) {
        return itemdao.checkItemGroup(us);
    }
         
}
