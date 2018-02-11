/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.bean.Waitermaster;
import com.accounting.dao.WaiterDao;
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
public class WaiterServiceImpl implements WaiterService {

    @Autowired
    private WaiterDao waiterDao;
    
    @Override
    public void saveWaiter(Waitermaster waiter) {
        
        waiterDao.saveWaiter(waiter);
    }

    @Override
    public List<Waitermaster> listWaiter() {
        return waiterDao.listWaiter();
    }

    @Override
    public Waitermaster getWaiterById(int id) {
        return waiterDao.getWaiterById(id);
    }

    @Override
    public void deleteWaiter(int id) {
        waiterDao.deleteWaiter(id);
    }
    
}
