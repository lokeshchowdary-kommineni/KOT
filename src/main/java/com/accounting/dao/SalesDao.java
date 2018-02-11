/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.BuyerMaster;
import com.accounting.bean.TempKot;
import com.accounting.bean.ItemMaster;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.SaleInvoice;
import com.accounting.bean.Sales;
import com.accounting.bean.SalesBill;
import com.accounting.bean.SaleInvoice;
import com.accounting.bean.SalesItem;
import com.accounting.util.BillVO;
import com.accounting.util.UtilHelper;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SHINELOGICS
 */ 
@Repository("salesDao")
public class SalesDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public String saveSales(Sales sales){
        
        sessionFactory.getCurrentSession().saveOrUpdate(sales);
        return sales.getInvoiceNo();
    }
 
    
    public List<Sales> listSales() {
        List<Sales> sale = null;
        try {

            sale = (List<Sales>)sessionFactory.openSession().createQuery("SELECT s.invoiceNo,s.date,s.nameOfBuyer,b.cellNo,s.mode,s.category,s.buyerBalance,s.invoiceAmount  FROM Sales AS s LEFT JOIN BuyerMaster AS b ON b.idBuyer=s.buyerId").list();
            System.out.print("sale"+sale);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sale;
    }
     public List<SaleInvoice> listSaleInvoice() {
        List<SaleInvoice> si = null;
        try {

            si = (List<SaleInvoice>)sessionFactory.openSession().createQuery("from SaleInvoice").list();
            System.out.print("SaleInvoice"+si);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return si;
    }
    public List<String> print(String inv) {
     List<String>plist=null;
     List<Object>billList=null;
     List<Map<String,String>> dataSource=new ArrayList<Map<String,String>>();
     
     billList=(List<Object>)sessionFactory.openSession().createQuery("SELECT i.invoiceNo,i.sdate,b.kotNos,i.cCtotal,i.cgstamount,i.sgstamount,i.additionalcharges,i.discount,i.customertotalamount,i.nettotal,b.itemName,b.quantity,b.rate,b.cap FROM SalesBill b INNER JOIN SaleInvoice i ON b.invoiceNo=i.invoiceNo WHERE i.invoiceNo='"+inv+"'").list();
        
      for(Object obj:billList){
            Object []arr=(Object [])obj;
          Map<String,String> row=new HashMap<>();
          row.put("item_name",String.valueOf(arr[10]));
          row.put("qty",String.valueOf(arr[11]));
          row.put("rate", String.valueOf(arr[12]));
          row.put("item_tot",String.valueOf(arr[13]));
          dataSource.add(row);
      }
    Map parametersMap = new HashMap(); 
    Object billInfo[] =(Object [])billList.get(0);
    
    parametersMap.put("invoice_num",String.valueOf(billInfo[10]));
     //
     //
     BillVO vo=new BillVO();
     vo.setDataSource(dataSource);
     vo.setParametersMap(parametersMap);
     //JRDataSource jRDataSoruce = new JRBeanCollectionDataSource(dataSource);
     //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parametersMap, jRDataSoruce);
        return plist;
    
    
    }
    
    public BillVO  printVO(String inv) {
     List<String>plist=null;
     List<Object>billList=null;
     List<Map<String,String>> dataSource=new ArrayList<Map<String,String>>();
     
     billList=(List<Object>)sessionFactory.openSession().createQuery("SELECT i.invoiceNo,i.sdate,b.kotNos,i.cCtotal,i.cgstamount,i.sgstamount,i.additionalcharges,i.discount,i.customertotalamount,i.roundoff,i.nettotal,b.itemName,b.quantity,b.rate,b.cap,b.tableName,i.waiterName FROM SalesBill b INNER JOIN SaleInvoice i ON b.invoiceNo=i.invoiceNo WHERE i.invoiceNo='"+inv+"'").list();
         Map<String,String> row=new HashMap<>();
      for(Object obj:billList){
            Object []arr=(Object [])obj;
         
          row.put("item_name",String.valueOf(arr[11]));
         
          dataSource.add(row);
      }
      
    Map parametersMap = new HashMap(); 
    Object billInfo[] =(Object [])billList.get(0);
    
    parametersMap.put("invoice_num",String.valueOf(billInfo[0]));
   
   
     BillVO vo=new BillVO();
     vo.setDataSource(dataSource);
     vo.setParametersMap(parametersMap);
     //JRDataSource jRDataSoruce = new JRBeanCollectionDataSource(dataSource);
     //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parametersMap, jRDataSoruce);
        return vo;
    
    
    }
    
    public List<Sales> listSalesRigstrer() {
        List<Sales> sale = null;
        try {

            sale = (List<Sales>)sessionFactory.openSession().createQuery("from Sales").list();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sale;
    }
    
    public List<Sales> listSalesWorkShite() {
        List<Sales> sale = null;
        try {

            sale = (List<Sales>)sessionFactory.openSession().createQuery("from Sales").uniqueResult();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sale;
    }
    
      
    public int saveSalesItem(SalesItem sales){
        sessionFactory.getCurrentSession().saveOrUpdate(sales);
        return sales.getId();
    }
    public void saveSalesinvoice(SaleInvoice emp){
        System.out.println("seka");
         String qry="update sale_invoice SET  C_C_total='"+emp.getCCtotal()+"',C_A_total='"+emp.getCAtotal()+"',discount='"+emp.getDiscount()+"',additionalcharges='"+emp.getAdditionalcharges()+"',customertotalamount='"+emp.getCustomertotalamount()+"',audittotalamount='"+emp.getAudittotalamount()+"',cgstamount='"+emp.getCgstamount()+"',sgstamount='"+emp.getSgstamount()+"',grandtotal='"+emp.getCustomertotalamount()+"',roundoff='"+emp.getRoundoff()+"',nettotal='"+emp.getNettotal()+"',sdate='"+emp.getSdate()+"',waiter_name='"+emp.getWaiterName()+"',payementmode='"+emp.getPayementmode()+"',cashAmount='"+emp.getCashAmount()+"',cardAmount='"+emp.getCardAmount()+"' WHERE  invoice_no='"+emp.getInvoiceNo()+"'";
    Query query1 = sessionFactory.getCurrentSession().createSQLQuery(qry);
    query1.executeUpdate();
    
             
    } 
    public String changepayementmode(String invnumber,String paymentmode){
      SaleInvoice s=sessionFactory.getCurrentSession().get(SaleInvoice.class,invnumber); 
      s.setPayementmode(paymentmode);
      String cashAmount="";
      String cardAmoun="";
      s.setCashAmount(cashAmount);
      s.setCardAmount(cardAmoun);
      sessionFactory.getCurrentSession().saveOrUpdate(s);
      String v="update sale_invoice SET  payementmode='"+paymentmode+"' WHERE  invoice_no='"+invnumber+"'";
        System.out.println("v-->"+v);
         Query qry = sessionFactory.getCurrentSession().createSQLQuery(v);
         qry.executeUpdate();  
        
        return null;
        
    }
    
     public String cashandcardpayementmode(String invnumber,String paymentmode,String cashAmount,String cardAmount){
      SaleInvoice s=sessionFactory.getCurrentSession().get(SaleInvoice.class,invnumber); 
      s.setPayementmode(paymentmode);
      s.setCashAmount(cashAmount);
      s.setCardAmount(cardAmount);
      sessionFactory.getCurrentSession().saveOrUpdate(s);
      String st="update sale_invoice SET  payementmode='"+paymentmode+"', cashAmount='"+cashAmount+"', cardAmount='"+cardAmount+"' WHERE invoice_no='"+invnumber+"'";
         Query qry = sessionFactory.getCurrentSession().createSQLQuery(st);
         qry.executeUpdate();  
        
        return null;
        
    }
     
     public String updatergb(String invoice,String rgb){
      SaleInvoice s=sessionFactory.getCurrentSession().get(SaleInvoice.class,invoice); 
      s.setRgb(rgb);
      sessionFactory.getCurrentSession().saveOrUpdate(s);
      String v="update sale_invoice SET  rgb='"+rgb+"' WHERE  invoice_no='"+invoice+"'";
        System.out.println("v-->"+v);
         Query qry = sessionFactory.getCurrentSession().createSQLQuery(v);
         qry.executeUpdate();  
        
        return null;
        
    }
    public String voidbills(String invnumber,String canclebillvalue){
      SaleInvoice si=sessionFactory.getCurrentSession().get(SaleInvoice.class,invnumber);
        si.setCancledbill(canclebillvalue);
        sessionFactory.getCurrentSession().saveOrUpdate(si);
        String v="update sales_bill SET  cancledbill='"+canclebillvalue+"' WHERE  invoice_no='"+invnumber+"'";
        System.out.println("v-->"+v);
         Query qry = sessionFactory.getCurrentSession().createSQLQuery(v);
         qry.executeUpdate();
        return null;
    
    }
    public void saveInvoice(SaleInvoice si){
        String qry="insert into sale_invoice ( invoice_no) values ('"+si.getInvoiceNo()+"')";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(qry);
        query.executeUpdate();
    }
     public String saveSalesItem(List<SalesBill> sales,String kot,String kotIds){
         
         
         String invs="BILL";
         
       Session session= sessionFactory.getCurrentSession(); 
       
       Query invno=session.createQuery("Select COUNT(invoiceNo) from SaleInvoice");
       long invnu=(long) invno.uniqueResult();
       long invo=invnu+1;
       String inv = String.valueOf(invo);
      
         System.out.println("LENGTH"+inv);
         String invoiceNum=invs.concat(inv);
         
      
      
      
        for (SalesBill bean : sales) {
            bean.setInvoiceNo(invoiceNum);
            bean.setKotNos(kot);
            bean.setKotIds(kotIds);
           System.out.println("vijay");
            session.saveOrUpdate(bean);
        }
        
        
        
        
        return invoiceNum; 
    }
     public String getBreveragestotal(String inv){
      Query totalbreveragevalue=sessionFactory.getCurrentSession().createQuery("SELECT COALESCE(SUM(s.cap),0)  AS cap  FROM SalesBill s INNER JOIN ItemMaster i ON s.itemCode=i.itemCode INNER JOIN ItemGroupMaster m ON i.itemGroup=m.idItem WHERE m.groupUnder='BEVRAGES' AND s.invoiceNo='"+inv+"' ");
        return totalbreveragevalue.uniqueResult().toString();
     
     }
     public String getadditinalcharges(String tableName){
       Query getadditionalcharges=sessionFactory.getCurrentSession().createQuery("SELECT  serviceCharges  FROM Tablemaster  WHERE tableName='"+tableName+"'");
         return getadditionalcharges.uniqueResult().toString() ;
         
     }
     
    public Sales getSalesById(String id){
        return (Sales)sessionFactory.getCurrentSession().get(Sales.class, id);
       
    }
    
    public List<SalesItem> getSalesItemBySalesId(String id){
        List<SalesItem> list=sessionFactory.getCurrentSession().createQuery("FROM SalesItem WHERE invoiceNo='"+id+"'").list();
        return list;
    }
    public List<SaleInvoice> getSalesInvoicelistbyid(){
        List<SaleInvoice> list=sessionFactory.getCurrentSession().createQuery("FROM SalesInvoice").list();
        return list;
    }
      public  String gettotalamount(String tableName){
        
          Query query=sessionFactory.getCurrentSession().createQuery(" SELECT SUM(k.cap) AS cap FROM TempKot k WHERE tableName='"+tableName+"'");
          
          return query.uniqueResult().toString();
        
       
        
      }
      public  String gettotalamountforrgb(String kotArr[]){
            String kot=UtilHelper.convertArray(kotArr);
        
          Query query=sessionFactory.getCurrentSession().createQuery(" SELECT SUM(k.cap) AS cap FROM KotItem k INNER JOIN Kot t ON k.kotNo=t.kotNo  WHERE t.kotNo IN ("+kot+")");
          
          return query.uniqueResult().toString();
        
       
        
      }
       public String getWaitername(String WaiterID){
            Query query6=sessionFactory.getCurrentSession().createQuery(" SELECT waiterName  FROM Waitermaster WHERE waiterId='"+WaiterID+"'");
           
        return query6.uniqueResult().toString();
       
           
       }
        public String getSalesamt(String fdtae,String tdate){
            Query query9=sessionFactory.getCurrentSession().createQuery(" SELECT COALESCE(SUM(nettotal),0)  FROM SaleInvoice WHERE cancledbill IS NULL  AND sdate  BETWEEN '"+fdtae+"' and '"+tdate+"'");
           
        return query9.uniqueResult().toString();
       
           
       }
        public String getcashamt(String fdtae,String tdate){
            Query query9=sessionFactory.getCurrentSession().createQuery(" SELECT COALESCE(SUM(nettotal-cardAmount),0)  FROM SaleInvoice WHERE cancledbill IS NULL  AND sdate  BETWEEN '"+fdtae+"' and '"+tdate+"'");
           
        return query9.uniqueResult().toString();
       
           
       }
        public String getcardamt(String fdtae,String tdate){
            Query query9=sessionFactory.getCurrentSession().createQuery(" SELECT COALESCE(SUM(nettotal-cashAmount),0)  FROM SaleInvoice WHERE cancledbill IS NULL  AND sdate  BETWEEN '"+fdtae+"' and '"+tdate+"'");
           
        return query9.uniqueResult().toString();
       
           
       }
        public String getvoidamt(String fdtae,String tdate){
            Query query9=sessionFactory.getCurrentSession().createQuery(" SELECT COALESCE(SUM(nettotal),0)  FROM SaleInvoice WHERE sdate  BETWEEN '"+fdtae+"' and '"+tdate+"' and cancledbill='void'");
           
        return query9.uniqueResult().toString();
       
           
       }
        
     public String gettotalamountaudit(String tableName){
         Query query1=sessionFactory.getCurrentSession().createQuery(" SELECT SUM(vap) AS vap FROM TempKot WHERE tableName='"+tableName+"' ");
        return query1.uniqueResult().toString();
        
       
      }
     public String gettotalamountauditforrgb(String kotArr[]){
           String kot=UtilHelper.convertArray(kotArr);
         Query query1=sessionFactory.getCurrentSession().createQuery(" SELECT SUM(k.vap) AS vap FROM KotItem k INNER JOIN Kot t ON k.kotNo=t.kotNo  WHERE t.kotNo IN ("+kot+")");
        return query1.uniqueResult().toString();
        
       
      }
        public List<TempKot> getSalesKotNos(String tableName){
            List<TempKot> klist=sessionFactory.getCurrentSession().createQuery(" SELECT DISTINCT kotNo  FROM TempKot WHERE tableName='"+tableName+"'").list();
            return klist;
        }
        public List<TempKot> getSalesKotIds(String tableName){
            List<TempKot> klist=sessionFactory.getCurrentSession().createQuery(" SELECT DISTINCT kotid  FROM TempKot WHERE tableName='"+tableName+"'").list();
            return klist;
        }
         public List<SalesBill> getRegeneratedBill(String kotIdArr[]){
             String kot=UtilHelper.convertArray(kotIdArr);
             System.out.println("kotttttttttt"+kot);
             List<Object> reList=(List<Object>)sessionFactory.getCurrentSession().createQuery("SELECT SUM(k.quantity) AS quantity,SUM(k.vap) AS vap,SUM(k.cap) AS cap,k.taxCgst,k.taxSgst,k.itemCode,k.itemName,i.tableName,i.kotTimestamp,k.rate,i.waiterId,k.unit FROM KotItem k  INNER JOIN Kot i ON k.kotid=i.id WHERE k.kotid IN ("+kot+") GROUP BY k.itemName ").list();
        List<SalesBill> list=new ArrayList<SalesBill>();
        
        for(Object obj:reList){
            Object []arr=(Object [])obj;
            SalesBill bill= new SalesBill();
            bill.setQuantity(Double.parseDouble(String.valueOf(arr[0])));
            bill.setVap(new BigDecimal(String.valueOf(arr[1])));
            bill.setCap(new BigDecimal(String.valueOf(arr[2])));
             bill.setTaxCgst(new BigDecimal(String.valueOf(arr[3])));
              bill.setTaxSgst(new BigDecimal(String.valueOf(arr[4])));
            bill.setItemName(String.valueOf(arr[6]));
            bill.setItemCode(String.valueOf(arr[5]));
            
              bill.setSalesdate(String.valueOf(arr[8]));
               bill.setRate(new BigDecimal(String.valueOf(arr[9])));
               bill.setWaiterId(String.valueOf(arr[10]));
                 bill.setUnit(String.valueOf(arr[11]));
               bill.setTableName(String.valueOf(arr[7]));
            list.add(bill);
        }
        
        return list;
         
         }
     
       public List<SalesBill> getSalesItemBytableIdForBill(String tableName){
        List<Object> oList=(List<Object>)sessionFactory.getCurrentSession().createQuery(" SELECT SUM(quantity) AS quantity,SUM(vap) AS vap,SUM(cap) AS cap,taxCgst,taxSgst,itemCode,itemName,tableName,kotTimestamp,rate,waiterId,unit FROM TempKot WHERE tableName='"+tableName+"' GROUP BY itemName ").list();
        List<SalesBill> list=new ArrayList<SalesBill>();
        
        for(Object obj:oList){
            Object []arr=(Object [])obj;
            SalesBill bill= new SalesBill();
            bill.setQuantity(Double.parseDouble(String.valueOf(arr[0])));
            bill.setVap(new BigDecimal(String.valueOf(arr[1])));
            bill.setCap(new BigDecimal(String.valueOf(arr[2])));
             bill.setTaxCgst(new BigDecimal(String.valueOf(arr[3])));
              bill.setTaxSgst(new BigDecimal(String.valueOf(arr[4])));
            bill.setItemName(String.valueOf(arr[6]));
            bill.setItemCode(String.valueOf(arr[5]));
            
              bill.setSalesdate(String.valueOf(arr[8]));
               bill.setRate(new BigDecimal(String.valueOf(arr[9])));
               bill.setWaiterId(String.valueOf(arr[10]));
                 bill.setUnit(String.valueOf(arr[11]));
               bill.setTableName(String.valueOf(arr[7]));
            list.add(bill);
        }
        
        return list;
    }
       public List<SalesBill> getSalesItembybill(String BillName){
        List<Object> oList=(List<Object>)sessionFactory.getCurrentSession().createQuery(" SELECT quantity ,cap,taxCgst,taxSgst,itemCode,itemName,tableName,salesdate,rate,waiterId,unit,kotNos FROM SalesBill WHERE invoiceNo='"+BillName+"'").list();
        List<SalesBill> rlist=new ArrayList<SalesBill>();
        
        for(Object obj:oList){
            Object []arr=(Object [])obj;
            SalesBill bill= new SalesBill();
            bill.setQuantity(Double.parseDouble(String.valueOf(arr[0])));
           
            bill.setCap(new BigDecimal(String.valueOf(arr[1])));
             bill.setTaxCgst(new BigDecimal(String.valueOf(arr[2])));
              bill.setTaxSgst(new BigDecimal(String.valueOf(arr[3])));
            bill.setItemName(String.valueOf(arr[5]));
            bill.setItemCode(String.valueOf(arr[4]));
            
              bill.setSalesdate(String.valueOf(arr[7]));
               bill.setRate(new BigDecimal(String.valueOf(arr[8])));
               bill.setWaiterId(String.valueOf(arr[9]));
                 bill.setUnit(String.valueOf(arr[10]));
                  bill.setKotNos(String.valueOf(arr[11]));
               bill.setTableName(String.valueOf(arr[6]));
            rlist.add(bill);
        }
        
        return rlist;
    }
       public List<SaleInvoice> getvalues(String BillName){
        List<Object> oList=(List<Object>)sessionFactory.getCurrentSession().createQuery(" SELECT cCtotal,cgstamount,sgstamount,discount,grandtotal,roundoff,nettotal,waiterName,additionalcharges,sdate FROM SaleInvoice WHERE invoiceNo='"+BillName+"'").list();
        List<SaleInvoice> rslist=new ArrayList<SaleInvoice>();
        
        for(Object obj:oList){
            Object []arr=(Object [])obj;
            SaleInvoice bill= new SaleInvoice();
            
           
            bill.setCCtotal(new BigDecimal(String.valueOf(arr[0])));
             bill.setCgstamount(new BigDecimal(String.valueOf(arr[1])));
              bill.setSgstamount(new BigDecimal(String.valueOf(arr[2])));
              bill.setDiscount(new BigDecimal(String.valueOf(arr[3])));
               bill.setAdditionalcharges(new BigDecimal(String.valueOf(arr[8])));
              bill.setGrandtotal(new BigDecimal(String.valueOf(arr[4])));
              bill.setRoundoff(new BigDecimal(String.valueOf(arr[5])));
               bill.setNettotal(new BigDecimal(String.valueOf(arr[6])));
            bill.setWaiterName(String.valueOf(arr[7]));
             bill.setSdate(String.valueOf(arr[9]));
           
            rslist.add(bill);
        }
        
        return rslist;
    }
      
    public List<Object[]> getSalesItemBySalesIdInvoice(String id){
        List<Object[]> list=sessionFactory.getCurrentSession().createQuery("SELECT s.itemName,s.quantity,u.unitSymbol,s.rate,s.amount,s.cgst,s.vat,s.igst FROM SalesItem AS s INNER JOIN UnitMaster AS u ON s.unit = u.idUnit WHERE s.invoiceNo='"+id+"'").list();
        return list;
    }
      public List<Object[]> getbilldetails(String BillName){
        List<Object[]> rlist=sessionFactory.getCurrentSession().createQuery("SELECT p.invoice_no,p.sdate,p.waiter_name,b.table_name  FROM sale_invoice AS p INNER JOIN sales_bill AS b ON p.invoice_no=b.invoice_no GROUP BY invoice_no WHERE p.invoice_no='"+BillName+"' ").list();
        return rlist;
    }
    
    public LedgerAccountMaster getledgerIdByBuyerId(int id,String type){
        LedgerAccountMaster list=(LedgerAccountMaster) sessionFactory.getCurrentSession().createQuery("FROM LedgerAccountMaster  WHERE rId='"+id+"' AND type='"+ type +"' ").uniqueResult();
        return list;
    }
       
    public void deleteSales(String invoiceNo) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM Sales WHERE invoiceNo='"+invoiceNo+"'").executeUpdate();    
    }
     public void deleteorderkot(String tbn) {
          sessionFactory.getCurrentSession().createQuery("DELETE FROM TempKot WHERE tableName='"+tbn+"'").executeUpdate();
     }
      
    public List<SalesItem> getSalesItemList(){
        List<SalesItem> list=sessionFactory.getCurrentSession().createQuery("FROM SalesItem").list();
        return list;
    }
    
    
public List<BuyerMaster> listBuyerLike(String key)
  {
    List<BuyerMaster> buyer = null;
      System.out.println("buyer LIST CREDIT");
    buyer = this.sessionFactory.getCurrentSession().createQuery("from BuyerMaster WHERE (LOWER(buyer_name) LIKE LOWER('" + key + "%')) AND NOT  buyer_name='CASH BUYER' AND NOT buyer_name='CASH MEDIATOR' and (tin  IS NULL or tin='') ").list();
    return buyer;
 }
public List<BuyerMaster> listBuyerLikewithGstno(String key)
  {
    List<BuyerMaster> buyer = null;
      System.out.println("buyer LIST CREDIT");
    buyer = this.sessionFactory.getCurrentSession().createQuery("from BuyerMaster WHERE (LOWER(buyer_name) LIKE LOWER('" + key + "%')) AND NOT  buyer_name='CASH BUYER' AND NOT buyer_name='CASH MEDIATOR' and tin<>''  ").list();
    return buyer;
 }

public List<BuyerMaster> listBuyerMediatorLike(String key)
  {
    List<BuyerMaster> buyer = null;
    buyer = this.sessionFactory.getCurrentSession().createQuery("from BuyerMaster WHERE buyer_name LIKE '" + key + "%' AND buyerType='Mediator'").list();
    return buyer;
 }

public List<Sales> SalesReports(String gsQuery)
  {
    List<Sales> Sales = sessionFactory.getCurrentSession().createQuery(gsQuery).list();
    
    return Sales;
  }


public String getLastInvoiceDate()
  {
   List date = sessionFactory.getCurrentSession().createQuery("SELECT MAX(date) FROM Sales ").list();
 
  String StringDate="";
   if(date.get(0)==null)
   {
  StringDate="";
   }else{
       
  
   DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    StringDate = df.format(date.get(0));
     }
   return StringDate;
  }
  public List<Object[]> GetDatatableSalesObject(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
        }
  public List<Object[]> GetDatatableSalesCount(String sql2) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql2).list();
        } 
        public Sales getBuyerByid(int buyerId)
  {
    return (Sales)this.sessionFactory.getCurrentSession().get(Sales.class, buyerId);
  }
        public List<Object[]> GetDatatableObject(String sql) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql).list();
    }
    public List<Object[]> GetDatatableCount(String sql2) {
        return (List<Object[]>)this.sessionFactory.getCurrentSession().createNativeQuery(sql2).list();
    }
    
       public List<Object[]> getItemSalesReport(String itemname,String FDate,String ToDate){
        List list=sessionFactory.getCurrentSession().createSQLQuery("SELECT i.item_name,igm.item_group_name ,ir.in_quantity, ir. out_quantity,ir.transaction_type,ir.transaction_id ,ir.transaction_date  FROM item_master AS i INNER JOIN item_group_master AS igm ON igm.id_item=i.item_group LEFT JOIN item_report AS ir ON ir.item_group_id=i.item_group  where i.id='"+itemname+"' AND ir.transaction_date  BETWEEN '"+FDate+"' and '"+ToDate+"'").list();
       System.out.println("list :"+list);
        return list;
    }
}
