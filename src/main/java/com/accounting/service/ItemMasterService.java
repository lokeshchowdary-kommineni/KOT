/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.bean.ItemMaster;
import com.accounting.dao.ItemMasterDao;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SHINELOGICS
 */
@Service("itemMasterService")
@Transactional
public class ItemMasterService {
    
@Autowired
private ItemMasterDao itemMasterDao;   

public int saveItem(ItemMaster item){
    return itemMasterDao.saveItem(item);
}

public ItemMaster getItemByItemCode(int itemCode){
    return itemMasterDao.getItemByItemCode(itemCode);
}

public List<ItemMaster> getItemList(){
   return itemMasterDao.getItemList();
}

public List<ItemMaster> getItemListROL(){
   return itemMasterDao.getItemListROL();
}

public void deleteItemByItemCode(int itemCode) throws ConstraintViolationException{
   itemMasterDao.deleteItemByItemCode(itemCode);

}

public List<ItemMaster> getItemListByKey(String key){
    return itemMasterDao.getItemListByKey(key);
}
public List<ItemMaster> getNameOfItemByKey(String key){
    return itemMasterDao.getNameOfItemByKey(key);
}
public ItemMaster getItemWithHSnValue(String itemcode){
    return itemMasterDao.getItemwithHSn(itemcode);
}

public List<ItemMaster> getItemListByKeyWithTax(String key, String tax,String items){
    return itemMasterDao.getItemListByKeyWithTax(key, tax,items);
}
public List<ItemMaster> getNameOfItemListByKeyWithTax(String key, String tax,String items){
    return itemMasterDao.getNameOfItemByKeyWithTax(key, tax,items);
}
////

public List<ItemMaster> getItemListByKeyROL(String key){
    return itemMasterDao.getItemListByKeyROL(key);
}
public List<ItemMaster> getNameOfItemByKeyROL(String key){
    return itemMasterDao.getNameOfItemByKeyROL(key);
}

public List<ItemMaster> getItemListByKeyWithTaxROL(String key, String tax,String items){
    return itemMasterDao.getItemListByKeyWithTaxROL(key, tax,items);
}
public List<ItemMaster> getNameOfItemListByKeyWithTaxROL(String key, String tax,String items){
    return itemMasterDao.getNameOfItemByKeyWithTaxROL(key, tax,items);
}

////
public String sumOfStockValue(){
    return itemMasterDao.sumOfStockValue();
}

public ItemMaster getItmeByItemNo(String itemCode){
    return itemMasterDao.getItmeByItemNo(itemCode);
}
public ItemMaster getItmeByItemName(String itemName){
    return itemMasterDao.getItmeByItemName(itemName);
}
public void updateItemMaster(String query){
    itemMasterDao.updateItemMaster(query);
}

public List<ItemMaster> getItemAllItemFromGroup(String groupIds){
    return itemMasterDao.getAllItemFromGroup(groupIds);
}
public List<Object[]> GetDatatableObject(String query){
    return itemMasterDao.GetDatatableObject(query);
}
public List<Object[]> GetDatatableCount(String query){
    return itemMasterDao.GetDatatableCount(query);
}
}
