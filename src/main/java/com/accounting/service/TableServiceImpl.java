/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.bean.Tablemaster;
import com.accounting.dao.TableDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MR
 */
@Service
@Transactional
public class TableServiceImpl implements TableService {

    @Autowired
    private TableDao tableDao;
    
    @Override
    public void saveTable(Tablemaster table) {
        
        tableDao.saveTable(table);
    }

    @Override
    public List<Tablemaster> listTable() {
        return tableDao.listTable();
    }

    @Override
    public Tablemaster getTableById(int id) {
        return tableDao.getTableById(id);
    }

    @Override
    public void deleteTable(int id) {
        tableDao.deleteTable(id);
    }
    
}
