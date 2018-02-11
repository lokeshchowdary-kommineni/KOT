/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.Tablemaster;
import java.util.List;

/**
 *
 * @author MR
 */
public interface TableDao {
    
    public void saveTable(Tablemaster table);
    public List<Tablemaster> listTable();
    public Tablemaster getTableById(int id);
    public void deleteTable(int id);
    
}
