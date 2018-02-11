/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.bean.ItemGroupMaster;
import com.accounting.bean.ItemMaster;
import com.accounting.bean.ItemReport;
import com.accounting.dao.ItemReportDao;

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
public class ItemReport_service {
    @Autowired
        private ItemReportDao idao;
    
	
	@Transactional
	public void addItemReport(ItemReport i) {
            
		 idao.addItemReport(i);
	}
          public List<ItemReport> listItemReport() {
            return idao.listItemReport();
        }
      public ItemReport getItemReportbyId(int id) {
		return idao.getItemReportbyId(id);
                
	}  
      public void deleteItemReport(String t) {
		idao.deleteItemReport(t);
	} 
      
       public List<Object[]> filterReport(String qry)
       {
    return this.idao.filterReport(qry);
      }
       public List<ItemMaster> getItemList(){
   return idao.getItemList();
}
    public List<ItemGroupMaster> listItem() {
            return idao.listItem();
        }    

    
}
