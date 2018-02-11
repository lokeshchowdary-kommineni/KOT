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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MR
 */
@Repository
public class KOTDaoImpl implements KOTDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<Tablemaster> listTableCategory() {
        return sessionFactory.getCurrentSession().createQuery("select distinct tableCategory from Tablemaster").list();
    }

    @Override
    public List<Tablemaster> listTablesByCategory(String category) {
        return sessionFactory.getCurrentSession().createQuery("select tableName from Tablemaster where tableCategory='"+category+"' ORDER BY id ASC").list();
    }

    @Override
    public List<Waitermaster> listWaiterId() {
        return sessionFactory.getCurrentSession().createQuery("select waiterId from Waitermaster").list();
    }

    @Override
    public List<ItemMaster> itemCodes() {
        return sessionFactory.getCurrentSession().createQuery("select itemCode from ItemMaster").list();
    }

    @Override
    public String saveKOT(Kot kot) {
        sessionFactory.getCurrentSession().saveOrUpdate(kot);
        return kot.getKotNo();
    }

    @Override
    public long saveKOTItem(KotItem kotItem) {
        sessionFactory.getCurrentSession().saveOrUpdate(kotItem);
        return kotItem.getId();
    }

    @Override
    public void saveOrderKOT(TempKot orderKot) {
        sessionFactory.getCurrentSession().saveOrUpdate(orderKot);
    }

    @Override
    public List<TempKot> listOrderKot(String tableName) {
        List<Object> oList=sessionFactory.getCurrentSession().createQuery("select kotNo,sum(cap) as cap,kotTimestamp,kotid from TempKot where tableName='"+tableName+"' group by kotid").list();
        List<TempKot> list=new ArrayList<TempKot>();
         for(Object obj:oList){
            Object []arr=(Object [])obj;
            TempKot okot= new TempKot();
            okot.setKotNo(String.valueOf(arr[0]));
            okot.setCap(new BigDecimal(String.valueOf(arr[1])));
            okot.setKotTimestamp(Timestamp.valueOf(String.valueOf(arr[2])));
            okot.setKotid(Long.parseLong(String.valueOf(arr[3])));
            list.add(okot);
        }
        return list;
    }   

    @Override
    public List<TempKot> listOrderKOTTables(String category) {
        return sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT k.tableName FROM TempKot k INNER JOIN Tablemaster t ON k.tableName=t.tableName WHERE t.tableCategory='"+category+"'").list(); 
    }

    @Override
    public List<KotItem> listkotItem(String kotId) {
        return sessionFactory.getCurrentSession().createQuery("from KotItem where kotid='"+kotId+"'").list();
    }

    @Override
    public List<Kot> listkot(String kotId) {
       return sessionFactory.getCurrentSession().createQuery("from Kot where id='"+kotId+"'").list();
    }

    @Override
    public List<Object[]> GetDatatableKOTList(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
    }

    @Override
    public List<Waitermaster> getWitersListByName(String waiterName) {
        return sessionFactory.getCurrentSession().createNativeQuery("SELECT  `id`,  `waiter_id`,  `waiter_name`,  `mobile_no`,  `address`,  `gender`,  `from_serial`,  `to_serial` FROM  waitermaster WHERE (LOWER(waiter_name) LIKE LOWER('"+waiterName+"%'))",Waitermaster.class).list();
    }

    @Override
    public List<Tablemaster> getTableListByName(String tableName) {
        return sessionFactory.getCurrentSession().createNativeQuery("SELECT  `id`,  `table_name`,  `table_category`,  `service_charges` FROM  tablemaster WHERE (LOWER(table_name) LIKE LOWER('"+tableName+"%'))",Tablemaster.class).list();
    }

    @Override
    public String getWaiterNameById(String waiterId) {
        Query query=sessionFactory.getCurrentSession().createQuery("select waiterName from Waitermaster where waiterId='"+waiterId+"'");
        return query.uniqueResult().toString();
    }

    @Override
    public String deleteItemById(String itemId) {
        sessionFactory.getCurrentSession().createNativeQuery("DELETE kot_item,temp_kot FROM kot_item INNER JOIN temp_kot ON kot_item.id=temp_kot.id WHERE kot_item.id='"+itemId+"'").executeUpdate();
        return "Item Deleted";
    }

    @Override
    public String getWaiterNameByTableName(String tableName) {
        List<Object> waiterMaster=sessionFactory.getCurrentSession().createQuery("SELECT o.waiterId, w.waiterName FROM TempKot o INNER JOIN Waitermaster w ON o.waiterId=w.waiterId WHERE o.tableName='"+tableName+"' GROUP BY o.waiterId").list();
        System.out.println("size of the waiterMaster"+waiterMaster.size());
        String nameAndId="";
        for(Object obj:waiterMaster){
            Object []arr=(Object [])obj;
            nameAndId=String.valueOf(arr[0])+","+String.valueOf(arr[1]);
        }
        System.out.println("name and Id is "+nameAndId);
        return nameAndId;
    }

    @Override
    public String deleteItemByIdfromKOTList(String itemId) {
        sessionFactory.getCurrentSession().createNativeQuery("DELETE FROM kot_item WHERE id='"+itemId+"'").executeUpdate();
        return "Item Deleted";
    }

    @Override
    public List<TempKot> listTempKotById(String kotId) {
        return sessionFactory.getCurrentSession().createQuery("from TempKot where kotid='"+kotId+"'").list();
    }

}
