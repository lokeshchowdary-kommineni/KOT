/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.bean.ItemReport;
import com.accounting.dao.StockMaintanceDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SHINELOGICS
 */
@Service
@Transactional
public class StockMaintanceService {
    @Autowired
     private StockMaintanceDao smdao;
	
     public List<ItemReport> listStock()
      {
        return this.smdao.listStock();
      }
        
    public List<Object[]> GetStockReportObject(String query){
    return smdao.GetStockReportObject(query);
    }
     
    public List<Object[]> GetStockReportCount(String query){
    return smdao.GetStockReportCount(query);
    }
    
     public List<Object[]> getWaiterReport(String fdtae,String tdate,String wname){
    return smdao.getWaiterReport(fdtae, tdate ,wname);
    }
}
