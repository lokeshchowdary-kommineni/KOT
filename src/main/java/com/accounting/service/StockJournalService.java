/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;


import com.accounting.bean.Purchase;
import com.accounting.bean.StockFjournalItem;
import com.accounting.bean.StockJournal;
import com.accounting.bean.StockJournalItem;
//import com.accounting.dao.PurchseDao;
import com.accounting.dao.StockJournalDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("stockJournalservice")
@Transactional
public class StockJournalService  {

    
    @Autowired
    private StockJournalDao stockJournalDao;
    
 public int saveStockJournal(StockJournal stock){
        return stockJournalDao.saveStockJournalTable(stock);
    }
    
   
    public int saveStockJournalItem(StockJournalItem stock){
        return stockJournalDao.saveStockJournalTableItem(stock);

    }
    
      public int saveFStockJournalItem(StockFjournalItem stock){
        return stockJournalDao.saveFStockJournalTableItem(stock);

    }

 
    public List<StockJournal> listStockJournal() {
        return stockJournalDao.listStockJournalTable();
    }
    
    public StockJournal getStockJournalById(int id){
        return stockJournalDao.getStockJournalTableById(id);
    }
    
    public List<StockJournalItem> getStockJournalItemByStockJournalId(int id){
       return stockJournalDao.getStockJournalTableByStockJournalTableId(id);
 
    }
    
    public List<Object[]> getStockJournalItemByStockJournalIdInvoice(int id){
       return stockJournalDao.getStockJournalTableByStockJournalTableIdInvoice(id);
 
    }
    
    public List<StockFjournalItem> getFStockJournalItemByStockJournalId(int id){
       return stockJournalDao.getFStockJournalTableByStockJournalTableId(id);
 
    }
    
    public List<Object[]> getFStockJournalItemByStockJournalIdInvoice(int id){
       return stockJournalDao.getFStockJournalTableByStockJournalTableIdInvoice(id);
 
    }
    
   
    public void deleteStockJournal(String stockId) {
        stockJournalDao.deleteStockJournalTable(stockId);

    }
    
    public void deleteStockJournalItem(String stockId) {
        stockJournalDao.deleteStockJournalItemTable(stockId);

    }
    
    public void deleteFStockJournalItem(String stockId) {
        stockJournalDao.deleteFStockJournalItemTable(stockId);

    }
    
    
    public List<Object[]> GetDatatableObject(String query){
    return stockJournalDao.GetDatatableObject(query);
}
public List<Object[]> GetDatatableCount(String query){
    return stockJournalDao.GetDatatableCount(query);
}

   
}

