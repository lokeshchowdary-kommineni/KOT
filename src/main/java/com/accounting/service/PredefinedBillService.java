/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;


import com.accounting.bean.PredefinedBill;
import com.accounting.bean.PredefinedBillItem;
import com.accounting.bean.Purchase;
import com.accounting.dao.PredefinedBillTableDao;
//import com.accounting.dao.PurchseDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("predefinedBillervice")
@Transactional
public class PredefinedBillService  {

    
    @Autowired
    private PredefinedBillTableDao predefinedBillTableDao;
   
    public int savepredefinedBill(PredefinedBill pre){
        return predefinedBillTableDao.savePredefinedBillTable(pre);
    }
    
   
    public int savepredefinedBillItem(PredefinedBillItem pre){
        return predefinedBillTableDao.savePredefinedBillTableItem(pre);
    }

 
    public List<PredefinedBill> listpredefinedBill() {
        return predefinedBillTableDao.listPredefinedBillTable();
    }
    
    public PredefinedBill getpredefinedBillById(int id){
        return predefinedBillTableDao.getPredefinedBillTableById(id);
     }
    
    public List<PredefinedBillItem> getpredefinedBillItemBypredefinedBillId(int id){
       return predefinedBillTableDao.getPredefinedBillTableByPredefinedBillTableId(id);
     }
    
   
    public void deletepredefinedBill(String preDId) {
        predefinedBillTableDao.deletePredefinedBillTable(preDId);

    }
    
    public void deletepredefinedBillItem(String preDId) {
        predefinedBillTableDao.deletePredefinedBillTableItem(preDId);

    }
    public List<Object[]> GetDatatablePredefinedObject(String query){
    return predefinedBillTableDao.GetDatatablePredefinedObject(query);
    }
     public List<Object[]> GetDatatablePredefinedCount(String query){
    return predefinedBillTableDao.GetDatatablePredefinedCount(query);
    }
     
}

