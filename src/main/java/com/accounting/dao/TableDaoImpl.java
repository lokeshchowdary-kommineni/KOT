/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.Tablemaster;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MR
 */
@Repository
public class TableDaoImpl implements TableDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void saveTable(Tablemaster table) {
        System.out.println("came to dao Impl for save");
        
        sessionFactory.getCurrentSession().saveOrUpdate(table);
        
    }

    @Override
    public List<Tablemaster> listTable() {
        System.out.println("came to dao Impl for list");
        List<Tablemaster> tableList=null;
        tableList=(List<Tablemaster>)sessionFactory.getCurrentSession().createQuery("from Tablemaster").list();
        return tableList;
    }

    @Override
    public Tablemaster getTableById(int id) {
        return (Tablemaster) sessionFactory.getCurrentSession().get(Tablemaster.class, id);
    }

    @Override
    public void deleteTable(int id) {
       sessionFactory.getCurrentSession().createQuery("DELETE FROM Tablemaster WHERE id='"+id+"'").executeUpdate(); 
    }

}
