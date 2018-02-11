/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.Waitermaster;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MR
 */
@Repository
public class WaiterDaoImpl implements WaiterDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void saveWaiter(Waitermaster waiter) {
        System.out.println("came to dao Impl for save");
        
        sessionFactory.getCurrentSession().saveOrUpdate(waiter);
        
    }

    @Override
    public List<Waitermaster> listWaiter() {
        System.out.println("came to dao Impl for list");
        List<Waitermaster> waiterList=null;
        waiterList=(List<Waitermaster>)sessionFactory.getCurrentSession().createQuery("from Waitermaster").list();
        return waiterList;
    }

    @Override
    public Waitermaster getWaiterById(int id) {
        return (Waitermaster) sessionFactory.getCurrentSession().get(Waitermaster.class, id);
    }

    @Override
    public void deleteWaiter(int id) {
       sessionFactory.getCurrentSession().createQuery("DELETE FROM Waitermaster WHERE id='"+id+"'").executeUpdate(); 
    }
 

}
