/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.ItemMaster;
import com.accounting.bean.KOTForm;
import com.accounting.bean.Kot;
import com.accounting.bean.KotItem;
import com.accounting.bean.TempKot;
import com.accounting.bean.Tablemaster;
import com.accounting.bean.Waitermaster;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author MR
 */
public interface KOTDao {
    
    
    public List<Tablemaster> listTableCategory();
    public List<Tablemaster> listTablesByCategory(String category);
    public List<Waitermaster> listWaiterId();
    public List<ItemMaster> itemCodes();
    public String saveKOT(Kot kot);
    public long saveKOTItem(KotItem kotItem);
    public void saveOrderKOT(TempKot orderKot);
    public List<TempKot> listOrderKot(String tableName);
    public List<TempKot> listTempKotById(String kotId);
    public List<TempKot> listOrderKOTTables(String category);
    public List<KotItem> listkotItem(String kotId);
    public List<Kot> listkot(String kotId);
    public List<Object[]> GetDatatableKOTList(String sql);
    public List<Waitermaster> getWitersListByName(String waiterName);
    public List<Tablemaster> getTableListByName(String tableName);
    public String getWaiterNameById(String waiterId);
    public String deleteItemById(String itemId);
    public String deleteItemByIdfromKOTList(String itemId);
    public String getWaiterNameByTableName(String tableName);
}
