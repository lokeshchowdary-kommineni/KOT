/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.Waitermaster;
import java.util.List;

/**
 *
 * @author MR
 */
public interface WaiterDao {
    
    public void saveWaiter(Waitermaster waiter);
    public List<Waitermaster> listWaiter();
    public Waitermaster getWaiterById(int id);
    public void deleteWaiter(int id);
    
}
