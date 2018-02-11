/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.dao;

import com.accounting.bean.ItemReport;
import com.accounting.bean.UnitMaster;
import static java.util.Collections.list;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.StringUtils;
/**
 *
 * @author shinelogics
 */
@Repository("ItemGroupReportDao")
public class ItemGroupReportDao {
     @Autowired
	private SessionFactory sessionFactory;
	
	
         public String listChildCategoryIds(String id ) {
             Set<String> set=new HashSet<String>();
            List<Object[]> list=  sessionFactory.getCurrentSession().createQuery("SELECT P.idAccount, C1.idAccount, C2.idAccount, C3.idAccount \n" +
                "     FROM     AccountGroupMaster P\n" +
                "LEFT JOIN AccountGroupMaster C1\n" +
                "    ON C1.underGroup = P.idAccount\n" +
                "LEFT JOIN AccountGroupMaster C2\n" +
                "    ON C2.underGroup = C1.idAccount\n" +
                "LEFT JOIN AccountGroupMaster C3\n" +
                "    ON C3.underGroup = C2.idAccount\n" +
                "WHERE\n" +
"    P.underGroup='"+id+"'  ").list();
            
        Iterator  i=   list.iterator();
       
        while(i.hasNext()){
            Object[] o=(Object[])i.next();
             if(o[0]!=null)
             {
                 set.add(String.valueOf( o[0]));
             }
             if(o[1]!=null)
             {
                 set.add(String.valueOf( o[1]));
             }
             if(o[2]!=null)
             {
                 set.add(String.valueOf( o[2]));
             }
             if(o[3]!=null)
             {
                 set.add(String.valueOf( o[3]));
             }
              
         
        }
  
       String step1="";
       if(set.size()>0)
       {
            step1 = "'"+StringUtils.join(set,"','") + "'";
       }
      


       System.out.println(step1);
       return step1;
	}
    public String listChildItemCategoryIds(String id ) {
             Set<String> set=new HashSet<String>();
            List<Object[]> list=  sessionFactory.getCurrentSession().createQuery("SELECT P.idItem, C1.idItem, C2.idItem, C3.idItem \n" +
                "     FROM     ItemGroupMaster P\n" +
                "LEFT JOIN ItemGroupMaster C1\n" +
                "    ON C1.groupUnder = cast (P.idItem as string)\n" +
                "LEFT JOIN ItemGroupMaster C2\n" +
                "    ON C2.groupUnder = cast (C1.idItem as string)\n" +
                "LEFT JOIN ItemGroupMaster C3\n" +
                "    ON C3.groupUnder = cast( C2.idItem as string)\n" +
                "WHERE\n" +
"    P.groupUnder='"+id+"'  ").list();
            
        Iterator  i=   list.iterator();
       
        while(i.hasNext()){
            Object[] o=(Object[])i.next();
             if(o[0]!=null)
             {
                 set.add(String.valueOf( o[0]));
             }
             if(o[1]!=null)
             {
                 set.add(String.valueOf( o[1]));
             }
             if(o[2]!=null)
             {
                 set.add(String.valueOf( o[2]));
             }
             if(o[3]!=null)
             {
                 set.add(String.valueOf( o[3]));
             }
              
         
        }
       String step1="";
       if(set.size()>0)
       {
            step1 = "'"+StringUtils.join(set,"','") + "'";
       }
       


       System.out.println(step1);
       return step1;
	}      
   //  groupids liske '1','2'.... and fromdate like 2017-05-19     
   public List<Object[]> getGroupOpeningAndClosingBalance(String groupids,String fromdate)
   {
         List<Object[]> list=  sessionFactory.getCurrentSession().createQuery("SELECT coalesce(SUM(ir.closingQuantity),0),coalesce(SUM(ir.closingValue),0) FROM ItemReport AS  ir WHERE ir.id IN\n" +
" (\n" +
" SELECT MAX(irm.id) FROM ItemReport AS irm INNER JOIN ItemMaster AS im  ON irm.itemId=im.id INNER JOIN ItemGroupMaster AS  igm ON\n" +
" im.itemGroup =igm.idItem AND irm.itemGroupId IN ("+groupids+") AND irm.transactionDate<'"+fromdate+"' GROUP BY irm.itemId " +
" " +
" \n" +
" )").list();
       
       return list;
   }
   
    public List<Object[]> getGroupOpeningAndClosingBalanceEnddate(String groupids,String enddate)
   {
         List<Object[]> list=  sessionFactory.getCurrentSession().createQuery("SELECT ir.id,ir.itemId,ir.itemGroupId,coalesce(SUM(ir.closingQuantity),0),coalesce(SUM(ir.closingValue),0) FROM ItemReport AS  ir WHERE ir.id IN\n" +
" (\n" +
" SELECT MAX(irm.id) FROM ItemReport AS irm INNER JOIN ItemMaster AS im  ON irm.itemId=im.id INNER JOIN ItemGroupMaster AS  igm ON\n" +
" im.itemGroup =igm.idItem AND irm.itemGroupId IN ("+groupids+") AND irm.transactionDate<='"+enddate+"' GROUP BY irm.itemId ORDER BY irm.transactionDate DESC,\n" +
" irm.id DESC\n" +
" \n" +
" )").list();
       
       return list;
   }
   
    public List<Object[]> getGroupOpeningAndClosingBalanceByGroupBy(String groupids,String fromdate)
   {
         List<Object[]> list=  sessionFactory.getCurrentSession().createQuery("SELECT ir.id,ir.itemId,ir.itemGroupId,coalesce(SUM(ir.closingQuantity),0),coalesce(SUM(ir.closingValue),0) FROM ItemReport AS  ir WHERE ir.id IN\n" +
" (\n" +
" SELECT MAX(irm.id) FROM ItemReport AS irm INNER JOIN ItemMaster AS im  ON irm.itemId=im.id INNER JOIN ItemGroupMaster AS  igm ON\n" +
" im.itemGroup =igm.idItem AND irm.itemGroupId IN ("+groupids+") AND irm.transactionDate<'"+fromdate+"' GROUP BY irm.itemId ORDER BY irm.transactionDate DESC,\n" +
" irm.id DESC\n" +
" \n" +
" ) Group by ir.itemGroupId").list();
       
       return list;
   }
    
        public List<Object[]> getGroupOpeningAndClosingBalanceByGroupByEnddate(String groupids,String enddate)
   {
         List<Object[]> list=  sessionFactory.getCurrentSession().createQuery("SELECT ir.id,ir.itemId,ir.itemGroupId,coalesce(SUM(ir.closingQuantity),0),coalesce(SUM(ir.closingValue),0) FROM ItemReport AS  ir WHERE ir.id IN\n" +
" (\n" +
" SELECT MAX(irm.id) FROM ItemReport AS irm INNER JOIN ItemMaster AS im  ON irm.itemId=im.id INNER JOIN ItemGroupMaster AS  igm ON\n" +
" im.itemGroup =igm.idItem AND irm.itemGroupId IN ("+groupids+") AND irm.transactionDate<='"+enddate+"' GROUP BY irm.itemId ORDER BY irm.transactionDate DESC,\n" +
" irm.id DESC\n" +
" \n" +
" ) Group by ir.itemGroupId").list();
       
       return list;
   }
   // Get Single Item Balance from Item Report Table
    public List<Object[]> getItemOpeningAndClosingBalance(String itemID,String fromdate)
   {
         List<Object[]> list=   sessionFactory.getCurrentSession().createQuery("SELECT ir.closingQuantity,ir.closingValue FROM ItemReport AS  ir WHERE ir.id IN \n" +
" (\n" +
" SELECT MAX(i.id) FROM ItemReport AS i WHERE i.transactionDate<'"+fromdate+"' AND i.itemId="+itemID+"" +
" )").list();
       
       return list;
   } 
   // Get Single Item Opening Balance from Item Master Table 
    public List<Object[]> getSingleItemOpenigBalance(String itemID)
   {
         List<Object[]> list=   sessionFactory.getCurrentSession().createQuery("SELECT im.openingStock,im.stockValue FROM ItemMaster im WHERE im.id='"+itemID+"'").list();
       
       return list;
   }  

      // Get all Item Opening Balance which not in item report based on group selection 
    public List<Object[]> getAllItemOpeningBalanceFromItemMaster(String FromDate,String GroupIds)
   {
       
       String itemGroupOPeningQry="SELECT COALESCE(SUM(im.openingStock),0),COALESCE(SUM(im.stockValue),0) FROM ItemMaster im WHERE im.itemGroup IN ("+GroupIds+") AND im.id NOT IN "
       + "(SELECT (ir.itemId) FROM ItemReport ir WHERE  ir.transactionDate<'"+FromDate+"'  AND ir.itemGroupId IN ("+GroupIds+") GROUP BY ir.itemId " +
           ")";
       //  List<Object[]> list=   sessionFactory.getCurrentSession().createQuery("SELECT sum(im.openingStock),sum(im.stockValue) FROM ItemMaster im LEFT JOIN ItemReport ir ON " +
//"ir.itemId=im.id  WHERE ir.itemId IS NULL AND im.itemGroup IN("+GroupIds+")").list();
        List<Object[]> list=   sessionFactory.getCurrentSession().createQuery(itemGroupOPeningQry).list();  
       
       return list;
   }   
    
public Set<String> setChildCategoryIds(String id ) {
     Set<String> set=new HashSet<String>();
    List<Object[]> list=  sessionFactory.getCurrentSession().createQuery("SELECT P.idAccount, C1.idAccount, C2.idAccount, C3.idAccount \n" +
        "     FROM     AccountGroupMaster P\n" +
        "LEFT JOIN AccountGroupMaster C1\n" +
        "    ON C1.underGroup = P.idAccount\n" +
        "LEFT JOIN AccountGroupMaster C2\n" +
        "    ON C2.underGroup = C1.idAccount\n" +
        "LEFT JOIN AccountGroupMaster C3\n" +
        "    ON C3.underGroup = C2.idAccount\n" +
        "WHERE\n" +
        "P.underGroup="+id+"  ").list();

    Iterator  i=   list.iterator();

    while(i.hasNext()){
        Object[] o=(Object[])i.next();
         if(o[0]!=null)
         {
             set.add(String.valueOf( o[0]));
         }
         if(o[1]!=null)
         {
             set.add(String.valueOf( o[1]));
         }
         if(o[2]!=null)
         {
             set.add(String.valueOf( o[2]));
         }
         if(o[3]!=null)
         {
             set.add(String.valueOf( o[3]));
         }


    }

    return set;
}

 // For profit and loss for every sales and purchase
// Made some changes(column order) for H2 database
public List<Object[]> getALLGroupOpeningBalanceByGroupBy_everySales_purchase(String fromdate)
   {
         List<Object[]> list=  sessionFactory.getCurrentSession().createQuery("SELECT ir.itemGroupId,ir.itemGroupId,igmo.itemGroupName,coalesce(SUM(ir.closingQuantity),0),coalesce(SUM(ir.closingValue),0) FROM ItemReport AS  ir INNER JOIN ItemGroupMaster AS  igmo  ON igmo.idItem=ir.itemGroupId WHERE ir.id IN\n" +
"(\n" +
"  SELECT MAX(irm.id) FROM ItemReport AS irm INNER JOIN ItemMaster AS im  ON irm.itemId=im.id INNER JOIN ItemGroupMaster AS  igm ON\n" +
"  im.itemGroup =igm.idItem AND  irm.transactionDate<='"+fromdate+"' WHERE irm.id NOT IN \n" +
"	(\n" +
"	SELECT MAX(irm.id) FROM ItemReport AS irm INNER JOIN ItemMaster AS im  ON irm.itemId=im.id INNER JOIN ItemGroupMaster AS  igm ON\n" +
"	im.itemGroup =igm.idItem AND  irm.transactionDate<='"+fromdate+"' GROUP BY irm.itemId \n" +
"	) \n" +
"  GROUP BY irm.itemId \n" +
") \n" +
"GROUP BY ir.itemGroupId").list();
       
       return list;
   }

// For Profit and LOss
 public List<Object[]> getALLGroupOpeningAndClosingBalanceByGroupBy(String fromdate)
   {
         List<Object[]> list=  sessionFactory.getCurrentSession().createQuery("SELECT '','',igmo.itemGroupName,coalesce(SUM(ir.closingQuantity),0),coalesce(SUM(ir.closingValue),0) FROM ItemReport AS  ir INNER JOIN ItemGroupMaster AS  igmo  on igmo.idItem=ir.itemGroupId WHERE ir.id IN\n" +
" (\n" +
" SELECT MAX(irm.id) FROM ItemReport AS irm INNER JOIN ItemMaster AS im  ON irm.itemId=im.id INNER JOIN ItemGroupMaster AS  igm ON\n" +
" im.itemGroup =igm.idItem AND  irm.transactionDate<'"+fromdate+"' GROUP BY irm.itemId ORDER BY irm.transactionDate DESC\n" +
" \n" +
" \n" +
" ) Group by ir.itemGroupId").list();
       
       return list;
   }
   public List<Object[]> getAllItemOpeningBalanceFromItemMaster_Profit_loss(String FromDate)
   {
        String itemGroupOPeningQry="SELECT COALESCE(SUM(im.openingStock),0),COALESCE(SUM(im.stockValue),0) FROM ItemMaster im WHERE  im.id NOT IN "
       + "(SELECT (ir.itemId) FROM ItemReport ir WHERE  ir.transactionDate<'"+FromDate+"'  GROUP BY ir.itemId " +
           ")";
         List<Object[]> list=   sessionFactory.getCurrentSession().createQuery(itemGroupOPeningQry).list();
       
       return list;
   }
    public List<Object[]> getAllItemOpeningBalanceFromItemMaster_Profit_loss_every_Transcation(String FromDate)
   {
        String itemGroupOPeningQry="SELECT COALESCE(SUM(im.openingStock),0),COALESCE(SUM(im.stockValue),0) FROM ItemMaster im WHERE  im.id NOT IN "
       + "(SELECT (ir.itemId) FROM ItemReport ir WHERE  ir.transactionDate<='"+FromDate+"'  GROUP BY ir.itemId " +
           ")";
         List<Object[]> list=   sessionFactory.getCurrentSession().createQuery(itemGroupOPeningQry).list();
       
       return list;
   }
 
 
      public List<Object[]> getAllGroupOpeningAndClosingBalanceByGroupByEnddate(String fdate,String enddate)
   {
//         List<Object[]> list=  sessionFactory.getCurrentSession().createQuery("SELECT ir.id,ir.itemId,igmo.itemGroupName,coalesce(SUM(ir.closingQuantity),0),coalesce(SUM(ir.closingValue),0) FROM ItemReport AS  ir INNER JOIN ItemGroupMaster AS  igmo  on igmo.idItem=ir.itemGroupId WHERE ir.id IN\n" +
//" (\n" +
//" SELECT MAX(irm.id) FROM ItemReport AS irm INNER JOIN ItemMaster AS im  ON irm.itemId=im.id INNER JOIN ItemGroupMaster AS  igm ON\n" +
//" im.itemGroup =igm.idItem  AND irm.transactionDate between '"+fdate+"' and '"+enddate+"' GROUP BY irm.itemId ORDER BY irm.transactionDate DESC,\n" +
//" irm.id DESC\n" +
//" \n" +
//" ) Group by ir.itemGroupId").list();
     
      List<Object[]> list=  sessionFactory.getCurrentSession().createQuery("SELECT ir.itemGroupId,ir.itemGroupId,igmo.itemGroupName,coalesce(SUM(ir.closingQuantity),0),coalesce(SUM(ir.closingValue),0) FROM ItemReport AS  ir INNER JOIN ItemGroupMaster AS  igmo  on igmo.idItem=ir.itemGroupId WHERE ir.id IN\n" +
" (\n" +
" SELECT MAX(irm.id) FROM ItemReport AS irm INNER JOIN ItemMaster AS im  ON irm.itemId=im.id INNER JOIN ItemGroupMaster AS  igm ON\n" +
" im.itemGroup =igm.idItem  AND irm.transactionDate<='"+enddate+"' GROUP BY irm.itemId \n" +
" ) Group by ir.itemGroupId").list();     
         
       
       return list;
   }
        
// made some changes for h2 database
   public List<Object[]> getAllStockJournalsOutValueFromDate_endDate(String fromdate,String enddate)
   {
         List<Object[]> list=  sessionFactory.getCurrentSession().createQuery(" SELECT SUM(ir.inQuantity) ,SUM(ir.inValue) ,coalesce(SUM(ir.outQuantity),0),coalesce(SUM(ir.outValue),0)\n" +
" ,ir.transactionType FROM ItemReport AS ir INNER JOIN ItemMaster AS im ON\n" +
" ir.itemId=im.id INNER JOIN ItemGroupMaster AS igm ON im.itemGroup=igm.idItem WHERE   ir.transactionDate BETWEEN '"+fromdate+"' AND '"+enddate+"'   AND ir.transactionType IN('Stock Journals')   GROUP BY ir.transactionType").list();
       
       return list;
   }
   // Get all Item Stock summary;
      public List<Object[]> getAllStockSummartBasedOnPRIMARYGroup(String groupids,String enddate)
   {
         String id="";
         String childGroupsids=listChildItemCategoryIds(groupids);
            if("".equals(childGroupsids)) 
            {  
            id="''";
            }
            else
            {
             id=childGroupsids;   
            }
            System.out.println(" id "+id);

         List<Object[]> list=  sessionFactory.getCurrentSession().createNativeQuery(" SELECT  COALESCE(t2.closing_quantity,0)+(CASE WHEN t2.item_id IS NOT NULL THEN 0 ELSE im.opening_stock  END) AS closingQty,\n" +
" COALESCE(t2.closing_value,0)+(CASE WHEN t2.item_id IS NOT NULL THEN 0 ELSE im.stock_value  END) AS closingValue ,im.id,im.item_name,igm.item_group_name\n" +
" FROM (SELECT item_id,MAX(id) AS maxids  FROM item_report WHERE transaction_date\n" +
" <= '"+enddate+"'  GROUP BY item_id) AS t1 INNER JOIN item_report AS t2 ON t1.maxids= t2.id  RIGHT JOIN  \n" +
" item_master im ON im.id=t2.item_id  INNER JOIN item_group_master igm ON igm.id_item=im.item_group AND igm.id_item IN("+id+") GROUP BY  im.id").list();
       
       return list;
   }
       public List<Object[]> getGroupWiseOpeningBalance(String groupids,String enddate)
   {
       
       
//         List<Object[]> list=  sessionFactory.getCurrentSession().createQuery("   SELECT t2.id, COALESCE(t2.closingQuantity,0) AS cq,COALESCE(t2.closingValue,0) AS cv,im.id,im.itemName,igm.itemGroupName,\n" +
//"(CASE WHEN t2.itemId != NULL THEN 0 ELSE im.openingStock  END) as qty,(CASE WHEN t2.itemId != NULL THEN 0 ELSE im.stockValue  END) AS qtyValue\n" +
//" FROM \n" +
//"       (SELECT id, MAX(id) AS maxids ,transactionDate \n" +
//"          FROM ItemReport WHERE transactionDate\n" +
//"        <= '"+enddate+"'  GROUP BY itemId) AS t1 \n" +
//"       INNER JOIN ItemReport AS t2 ON t1.maxids= t2.id  RIGHT JOIN  ItemMaster im ON im.id=t2.item_id  INNER JOIN \n" +
//"       ItemGroupMaster igm ON igm.idItem=im.itemGroup AND igm.idItem IN("+id+")   GROUP BY im.id ").list();

         List<Object[]> list=  sessionFactory.getCurrentSession().createNativeQuery(" SELECT  COALESCE(t2.closing_quantity,0)+(CASE WHEN t2.item_id IS NOT NULL THEN 0 ELSE im.opening_stock  END) AS closingQty,\n" +
" COALESCE(t2.closing_value,0)+(CASE WHEN t2.item_id IS NOT NULL THEN 0 ELSE im.stock_value  END) AS closingValue ,im.id,im.item_name,igm.item_group_name\n" +
" FROM (SELECT item_id,MAX(id) AS maxids  FROM item_report WHERE transaction_date\n" +
" <'"+enddate+"'  GROUP BY item_id) AS t1 INNER JOIN item_report AS t2 ON t1.maxids= t2.id  RIGHT JOIN  \n" +
" item_master im ON im.id=t2.item_id  INNER JOIN item_group_master igm ON igm.id_item=im.item_group AND igm.id_item IN("+groupids+") GROUP BY  im.id").list();
       
       return list;
   }   
}
