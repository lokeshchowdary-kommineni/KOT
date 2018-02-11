/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.ItemMaster;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SHINELOGICS
 */
@Repository("itemMasterDao")
@Transactional
public class ItemMasterDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public int saveItem(ItemMaster item){
        
        sessionFactory.getCurrentSession().saveOrUpdate(item);
        return item.getId();
    }
    
    public ItemMaster getItemByItemCode(int itemCode){
        return (ItemMaster) sessionFactory.getCurrentSession().get(ItemMaster.class, itemCode);
    }
    
    public List<ItemMaster> getItemList(){
        List<ItemMaster> list=sessionFactory.getCurrentSession().createQuery("FROM ItemMaster").list();
        return list;
    }
    
     public List<ItemMaster> getItemListROL(){
        List<ItemMaster> list=sessionFactory.getCurrentSession().createNativeQuery("SELECT  `id`,  `alt_unit`,  `basic_price`,  `basic_vat_price`,  `bit_item`,  `ca`,  `cap`,  `cap_alt`,  `cap_checkbox`,  item_master.`category`,  `cb`,  `cbp`,  `cbp_atl`,  `cbp_checkbox`,`com_code`,  `cp`,  `created_by`,  `created_on`,  `current_stock`,  `ea`,  `ep`,  `ep_alt`,  `er`,  `item_code`,  `item_group`,  `item_name`,  `la`,  `lp`,  `lp_alt`,  `lr`,  `mc`,  `mc_mca`,  `mca`,  `mcmca_amount`,  `modifyed_by`,  `modifyed_on`,  `opening_stock`,  `rate`,  `rol`,  `stock_date`,  `stock_value`,  `ta`,  `tax`, CCODE_MASTER.cgst_rate as tax_cgst  ,CCODE_MASTER.sgst_rate as tax_sgst ,CCODE_MASTER.igst_rate as tax_igst,  `total_unit`,  `tp`,  `tp_alt`,  `tr`,  `unit`,  `va`,  `vap`,  `vap_alt`,  `vap_checkbox`,  `vb`,  `vbp`,  `vbp_alt`,  `vbp_checkbox` FROM  item_master  inner join CCODE_MASTER on CCODE_MASTER.ccode_id =item_master.com_code WHERE current_stock<rol",ItemMaster.class).list();
        return list;
    }
    
    public void deleteItemByItemCode(int itemCode) throws ConstraintViolationException{
        sessionFactory.getCurrentSession().createQuery("DELETE FROM ItemMaster WHERE id="+itemCode+"").executeUpdate();
       
    }
    
    public List<ItemMaster> getItemListByKey(String key){
        List<ItemMaster> list=sessionFactory.getCurrentSession().createQuery("FROM ItemMaster WHERE itemCode LIKE '"+key+"%'").list();
        return list;
    }
     public List<ItemMaster> getNameOfItemByKey(String key){
        List<ItemMaster> list=sessionFactory.getCurrentSession().createQuery("FROM ItemMaster WHERE itemName LIKE '"+key+"%'").list();
        return list;
    }
    public ItemMaster getItemwithHSn(String itemcode){
     ItemMaster list=null;
      list=(ItemMaster) sessionFactory.getCurrentSession().createNativeQuery("SELECT  `id`,  `alt_unit`,  `basic_price`,  `basic_vat_price`,  `bit_item`,  `ca`,  `cap`,  `cap_alt`,  `cap_checkbox`,  item_master.`category`,  `cb`,  `cbp`,  `cbp_atl`,  `cbp_checkbox`,`com_code`,  `cp`,  `created_by`,  `created_on`,  `current_stock`,  `ea`,  `ep`,  `ep_alt`,  `er`,  `item_code`,  `item_group`,  `item_name`,  `la`,  `lp`,  `lp_alt`,  `lr`,  `mc`,  `mc_mca`,  `mca`,  `mcmca_amount`,  `modifyed_by`,  `modifyed_on`,  `opening_stock`,  `rate`,  `rol`,  `stock_date`,  `stock_value`,  `ta`,  `tax`, CCODE_MASTER.cgst_rate as tax_cgst  ,CCODE_MASTER.sgst_rate as tax_sgst , CCODE_MASTER.igst_rate as tax_igst , `total_unit`,  `tp`,  `tp_alt`,  CCODE_MASTER. tax_rate as tr,  `unit`,  `va`,  `vap`,  `vap_alt`,  `vap_checkbox`,  `vb`,  `vbp`,  `vbp_alt`,  `vbp_checkbox` FROM  item_master  inner join CCODE_MASTER on CCODE_MASTER.ccode_id =item_master.com_code WHERE item_code='"+itemcode+"'",ItemMaster.class).getSingleResult();
      return list;
    }
    public List<ItemMaster> getItemListByKeyWithTax(String key, String tax,String items){
       List<ItemMaster> list=null;
        if(tax.equalsIgnoreCase(""))
        {
         list=sessionFactory.getCurrentSession().createNativeQuery("SELECT  `id`,  `alt_unit`,  `basic_price`,  `basic_vat_price`,  `bit_item`,  `ca`,  `cap`,  `cap_alt`,  `cap_checkbox`,  item_master.`category`,  `cb`,  `cbp`,  `cbp_atl`,  `cbp_checkbox`,`com_code`,  `cp`,  `created_by`,  `created_on`,  `current_stock`,  `ea`,  `ep`,  `ep_alt`,  `er`,  `item_code`,  `item_group`,  `item_name`,  `la`,  `lp`,  `lp_alt`,  `lr`,  `mc`,  `mc_mca`,  `mca`,  `mcmca_amount`,  `modifyed_by`,  `modifyed_on`,  `opening_stock`,  `rate`,  `rol`,  `stock_date`,  `stock_value`,  `ta`,  `tax`, CCODE_MASTER.cgst_rate as tax_cgst  ,CCODE_MASTER.sgst_rate as tax_sgst , CCODE_MASTER.igst_rate as tax_igst , `total_unit`,  `tp`,  `tp_alt`,  CCODE_MASTER. tax_rate as tr,  `unit`,  `va`,  `vap`,  `vap_alt`,  `vap_checkbox`,  `vb`,  `vbp`,  `vbp_alt`,  `vbp_checkbox` FROM  item_master  inner join CCODE_MASTER on CCODE_MASTER.ccode_id =item_master.com_code WHERE (LOWER(item_code) LIKE LOWER('"+key+"%')) and item_code not in ("+items+")",ItemMaster.class).list();
        }
       else
        {
         list=sessionFactory.getCurrentSession().createNativeQuery("SELECT  `id`,  `alt_unit`,  `basic_price`,  `basic_vat_price`,  `bit_item`,  `ca`,  `cap`,  `cap_alt`,  `cap_checkbox`,  item_master.`category`,  `cb`,  `cbp`,  `cbp_atl`,  `cbp_checkbox`,`com_code`,  `cp`,  `created_by`,  `created_on`,  `current_stock`,  `ea`,  `ep`,  `ep_alt`,  `er`,  `item_code`,  `item_group`,  `item_name`,  `la`,  `lp`,  `lp_alt`,  `lr`,  `mc`,  `mc_mca`,  `mca`,  `mcmca_amount`,  `modifyed_by`,  `modifyed_on`,  `opening_stock`,  `rate`,  `rol`,  `stock_date`,  `stock_value`,  `ta`,  `tax`, CCODE_MASTER.cgst_rate as tax_cgst  ,CCODE_MASTER.sgst_rate as tax_sgst , CCODE_MASTER.igst_rate as tax_igst , `total_unit`,  `tp`,  `tp_alt`,  CCODE_MASTER. tax_rate as tr,  `unit`,  `va`,  `vap`,  `vap_alt`,  `vap_checkbox`,  `vb`,  `vbp`,  `vbp_alt`,  `vbp_checkbox` FROM  item_master  inner join CCODE_MASTER on CCODE_MASTER.ccode_id =item_master.com_code WHERE (LOWER(item_code) LIKE LOWER('"+key+"%')) and  tr='"+tax+"' and item_code not in ("+items+")",ItemMaster.class).list();   
        }    
            
        return list;
    }
    public List<ItemMaster> getNameOfItemByKeyWithTax(String key, String tax,String items){
       List<ItemMaster> list=null;
        if(tax.equalsIgnoreCase(""))
        {
         list=sessionFactory.getCurrentSession().createNativeQuery("SELECT  `id`,  `alt_unit`,  `basic_price`,  `basic_vat_price`,  `bit_item`,  `ca`,  `cap`,  `cap_alt`,  `cap_checkbox`,  item_master.`category`,  `cb`,  `cbp`,  `cbp_atl`,  `cbp_checkbox`,`com_code`,  `cp`,  `created_by`,  `created_on`,  `current_stock`,  `ea`,  `ep`,  `ep_alt`,  `er`,  `item_code`,  `item_group`,  `item_name`,  `la`,  `lp`,  `lp_alt`,  `lr`,  `mc`,  `mc_mca`,  `mca`,  `mcmca_amount`,  `modifyed_by`,  `modifyed_on`,  `opening_stock`,  `rate`,  `rol`,  `stock_date`,  `stock_value`,  `ta`,  `tax`, CCODE_MASTER.cgst_rate as tax_cgst  ,CCODE_MASTER.sgst_rate as tax_sgst , CCODE_MASTER.igst_rate as tax_igst , `total_unit`,  `tp`,  `tp_alt`,  CCODE_MASTER. tax_rate as tr,  `unit`,  `va`,  `vap`,  `vap_alt`,  `vap_checkbox`,  `vb`,  `vbp`,  `vbp_alt`,  `vbp_checkbox` FROM  item_master  inner join CCODE_MASTER on CCODE_MASTER.ccode_id =item_master.com_code WHERE (LOWER(item_name) LIKE LOWER('"+key+"%')) and item_code not in ("+items+")",ItemMaster.class).list();
        }
       else
        {
         list=sessionFactory.getCurrentSession().createNativeQuery("SELECT  `id`,  `alt_unit`,  `basic_price`,  `basic_vat_price`,  `bit_item`,  `ca`,  `cap`,  `cap_alt`,  `cap_checkbox`,  item_master.`category`,  `cb`,  `cbp`,  `cbp_atl`,  `cbp_checkbox`,`com_code`,  `cp`,  `created_by`,  `created_on`,  `current_stock`,  `ea`,  `ep`,  `ep_alt`,  `er`,  `item_code`,  `item_group`,  `item_name`,  `la`,  `lp`,  `lp_alt`,  `lr`,  `mc`,  `mc_mca`,  `mca`,  `mcmca_amount`,  `modifyed_by`,  `modifyed_on`,  `opening_stock`,  `rate`,  `rol`,  `stock_date`,  `stock_value`,  `ta`,  `tax`, CCODE_MASTER.cgst_rate as tax_cgst  ,CCODE_MASTER.sgst_rate as tax_sgst ,CCODE_MASTER.igst_rate as tax_igst ,  `total_unit`,  `tp`,  `tp_alt`,  CCODE_MASTER. tax_rate as tr,  `unit`,  `va`,  `vap`,  `vap_alt`,  `vap_checkbox`,  `vb`,  `vbp`,  `vbp_alt`,  `vbp_checkbox` FROM  item_master  inner join CCODE_MASTER on CCODE_MASTER.ccode_id =item_master.com_code WHERE (LOWER(item_name) LIKE LOWER('"+key+"%')) and  tr='"+tax+"' and item_code not in ("+items+")",ItemMaster.class).list();   
        }     
            
        return list;
    }
    /////
  public List<ItemMaster> getItemListByKeyROL(String key){
        List<ItemMaster> list=sessionFactory.getCurrentSession().createQuery("FROM ItemMaster WHERE current_stock<=rol and itemCode LIKE '"+key+"%'").list();
        return list;
    }
     public List<ItemMaster> getNameOfItemByKeyROL(String key){
        List<ItemMaster> list=sessionFactory.getCurrentSession().createQuery("FROM ItemMaster WHERE current_stock<=rol and itemName LIKE '"+key+"%'").list();
        return list;
    }
    
    public List<ItemMaster> getItemListByKeyWithTaxROL(String key, String tax,String items){
       List<ItemMaster> list=null;
        if(tax.equalsIgnoreCase(""))
        {
         list=sessionFactory.getCurrentSession().createQuery("FROM ItemMaster WHERE current_stock<=rol and (LOWER(itemCode) LIKE LOWER('"+key+"%')) and itemCode not in ("+items+")").list();
        }
       else
        {
         list=sessionFactory.getCurrentSession().createQuery("FROM ItemMaster WHERE current_stock<=rol and (LOWER(itemCode) LIKE LOWER('"+key+"%')) and  tr='"+tax+"' and itemCode not in ("+items+")").list();   
        }    
            
        return list;
    }
    public List<ItemMaster> getNameOfItemByKeyWithTaxROL(String key, String tax,String items){
       List<ItemMaster> list=null;
        if(tax.equalsIgnoreCase(""))
        {
         list=sessionFactory.getCurrentSession().createQuery("FROM ItemMaster WHERE current_stock<=rol and (LOWER(itemName) LIKE LOWER('"+key+"%')) and itemCode not in ("+items+")").list();
        }
       else
        {
         list=sessionFactory.getCurrentSession().createQuery("FROM ItemMaster WHERE current_stock<=rol and (LOWER(itemName) LIKE LOWER('"+key+"%')) and  tr='"+tax+"' and itemCode not in ("+items+")").list();   
        }    
            
        return list;
    }
    /////
    public String sumOfStockValue(){
        String value=(String) sessionFactory.openSession().createQuery("SELECT COALESCE(SUM(stockValue),0) FROM ItemMaster").uniqueResult().toString();
        return value;
    }
    
    public ItemMaster getItmeByItemNo(String itemCode){
        //System.out.println("itemCode Excute on Before"+itemCode);
        ItemMaster list=(ItemMaster) sessionFactory.getCurrentSession().createQuery("FROM ItemMaster WHERE itemCode='"+itemCode+"'").uniqueResult();
        //System.out.println("itemCode  list Excute on After"+list);
        return list;
    }
    public ItemMaster getItmeByItemName(String itemName){
        //System.out.println("itemCode Excute on Before"+itemCode);
        ItemMaster list=(ItemMaster) sessionFactory.getCurrentSession().createQuery("FROM ItemMaster WHERE itemName='"+itemName+"'").uniqueResult();
        //System.out.println("itemCode  list Excute on After"+list);
        return list;
    }
    
    public void updateItemMaster(String query){
       sessionFactory.getCurrentSession().createSQLQuery(query).executeUpdate();
    
    }
    public List<ItemMaster> getAllItemFromGroup(String grouopids){
        List<ItemMaster> list=sessionFactory.getCurrentSession().createQuery("FROM ItemMaster WHERE itemGroup in ("+grouopids+")").list();
        return list;
    }
    public List<Object[]> GetDatatableObject(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
    }
    public List<Object[]> GetDatatableCount(String sql2) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql2).list();
    }
    
}
