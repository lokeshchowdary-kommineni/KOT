/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.bean.ItemMaster;
import com.accounting.bean.KOTForm;
import com.accounting.bean.Kot;
import com.accounting.bean.KotItem;
import com.accounting.bean.TempKot;
import com.accounting.bean.Tablemaster;
import com.accounting.bean.Waitermaster;
import com.accounting.dao.KOTDao;
import java.math.BigInteger;
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
public class KOTServiceImpl implements KOTService {
    
    @Autowired
    private KOTDao kotdao;

    @Override
    public List<Tablemaster> listTableCategory() {
        return kotdao.listTableCategory();
    }

    @Override
    public List<Tablemaster> listTablesByCategory(String category) {
        return kotdao.listTablesByCategory(category);    
    }

    @Override
    public List<Waitermaster> listWaiterId() {
        return kotdao.listWaiterId();
    }

    @Override
    public List<ItemMaster> itemCodes() {
        return kotdao.itemCodes();
    }

    @Override
    public String saveKOT(Kot kot) {
        return kotdao.saveKOT(kot);
    }

    @Override
    public long saveKOTItem(KotItem kotItem) {
        return kotdao.saveKOTItem(kotItem);
    }

    @Override
    public void saveOrderKOT(TempKot orderKot) {
        kotdao.saveOrderKOT(orderKot);
    }

    @Override
    public List<TempKot> listOrderKot(String tableName) {
       return kotdao.listOrderKot(tableName);
    }

    @Override
    public List<TempKot> listOrderKOTTables(String category) {
        return kotdao.listOrderKOTTables(category);
    }

    @Override
    public List<KotItem> listkotItem(String kotId) {
        return kotdao.listkotItem(kotId);
    }

    @Override
    public List<Kot> listkot(String kotId) {
        return kotdao.listkot(kotId);
    }

    @Override
    public List<Object[]> GetDatatableKOTList(String sql) {
        return kotdao.GetDatatableKOTList(sql);
    }

    @Override
    public List<Waitermaster> getWitersListByName(String waiterName) {
        return kotdao.getWitersListByName(waiterName);
    }

    @Override
    public List<Tablemaster> getTableListByName(String tableName) {
        return kotdao.getTableListByName(tableName);
    }

    @Override
    public String getWaiterNameById(String waiterId) {
        return kotdao.getWaiterNameById(waiterId);
    }

    @Override
    public String deleteItemById(String itemId) {
        return kotdao.deleteItemById(itemId);
    }

    @Override
    public String getWaiterNameByTableName(String tableName) {
        return kotdao.getWaiterNameByTableName(tableName);
    }

    @Override
    public String deleteItemByIdfromKOTList(String itemId) {
        return kotdao.deleteItemByIdfromKOTList(itemId);
    }

    @Override
    public List<TempKot> listTempKotById(String kotId) {
        return kotdao.listTempKotById(kotId);
    }

    
    
}
