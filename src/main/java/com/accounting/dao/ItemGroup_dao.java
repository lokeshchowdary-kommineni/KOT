/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.dao;

import com.accounting.bean.ItemGroupMaster;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shinelogics
 */
@Repository("item_dao")
public class ItemGroup_dao {
     @Autowired
	private SessionFactory sessionFactory;
	
	public void addItem(ItemGroupMaster ig) {
             
		sessionFactory.getCurrentSession().saveOrUpdate(ig);
                
	}
         public List<ItemGroupMaster> listItem() {
            return (List<ItemGroupMaster>) sessionFactory.getCurrentSession().createQuery(" FROM ItemGroupMaster where itemGroupName!='PRIMARY'").list();
	}
          public ItemGroupMaster getItembyId(int item_id) {
            
		return (ItemGroupMaster) sessionFactory.getCurrentSession().get(ItemGroupMaster.class, item_id);
	}
           public void deleteItem(int del)throws ConstraintViolationException {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItemGroupMaster WHERE id_item= "+del+" ").executeUpdate();
	}
            public boolean checkItemGroup(String us) {
            
             List<ItemGroupMaster> itemInfo = null;
            
		itemInfo=(List<ItemGroupMaster>) sessionFactory.getCurrentSession().createQuery("  FROM ItemGroupMaster where item_group_name='"+us+"' ").list();
	if(itemInfo.size()>0)
        {
        return true;
        }else
        {
          return false;
        }
      }
}
