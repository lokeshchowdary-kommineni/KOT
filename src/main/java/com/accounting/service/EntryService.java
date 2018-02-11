/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.bean.Entries;
import com.accounting.bean.Entryitems;
import com.accounting.bean.Purchase;
import com.accounting.dao.EntryDao;
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
public class EntryService {
    
    @Autowired
    private EntryDao entryDao;
    
    public int saveEntry(Entries e) {        
           return entryDao.saveEntry(e);
    }
    
    public int saveEntryItem(Entryitems e) {        
           return entryDao.saveEntryItem(e);
    }
    
    public Entries getEntryById(int id){
        return entryDao.getEntryById(id);
    }
    
    public Entryitems getEntryItemById(int id){
        return entryDao.getEntryItemById(id);
    }
    
     public Entries getEntryListId(String id,String type){
       return entryDao.getEntryListId(id,type);
    }
     
//      public List<Entryitems> getEntryitemsListId(int id){
//       return entryDao.getEntryItemListId(id);
//    }
     
     public void deleteEntry(String billId) {
        entryDao.deleteEntry(billId);
    
    }
     
     public void deleteEntryItem(String entryId) {
        entryDao.deleteEntryItem(entryId);
    
    }
}
