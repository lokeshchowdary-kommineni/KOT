/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.service;

import com.accounting.bean.BuyerMaster;
import com.accounting.bean.CcodeMaster;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.TempKot;
import com.accounting.bean.SaleInvoice;
import com.accounting.bean.Sales;
import com.accounting.bean.SalesBill;

import com.accounting.bean.SalesItem;
import com.accounting.dao.SalesDao;
import com.accounting.util.BillVO;
import static java.sql.JDBCType.DECIMAL;
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
public class SalesService {
    
    @Autowired
    private SalesDao salesDao;

    //@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public String saveSales(Sales sales){
        return salesDao.saveSales(sales);
    }
     public void saveSalesinvoice(SaleInvoice emp){
        salesDao.saveSalesinvoice(emp);
    }
    
   
    //@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public int saveSalesItem(SalesItem sales){
        return salesDao.saveSalesItem(sales);
    }
     //@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public String getadditinalcharges(String tableName){
        return salesDao.getadditinalcharges(tableName);
    }
 public String voidbills(String invnumber,String canclebillvalue){
       return salesDao.voidbills(invnumber,canclebillvalue);
    }
            public String changepayementmode(String invnumber,String paymentmode){
       return salesDao.changepayementmode(invnumber, paymentmode);
    }
             public String cashandcardpayementmode(String invnumber,String paymentmode,String cashAmount,String cardAmount){
       return salesDao.cashandcardpayementmode(invnumber, paymentmode, cashAmount, cardAmount);
    }
            public String updatergb(String invoice,String rgb){
       return salesDao.updatergb(invoice,rgb);
    }
    public List<Sales> listSales() {
        return salesDao.listSales();
    }
      public List<SaleInvoice> listSaleInvoice() {
        return salesDao.listSaleInvoice();
    }
     public List<String> print(String inv) {
        return salesDao.print(inv);
    }
    public BillVO  printVO(String inv) {
        return salesDao.printVO(inv);
    }
    public List<Sales> listSalesRegister() {
        return salesDao.listSalesRigstrer();
    }
    public List<SaleInvoice> getSalesInvoicelistbyid(){
          return salesDao.getSalesInvoicelistbyid();
    }
    public List<Sales> listSalesWorkShite() {
        return salesDao.listSalesWorkShite();
    }
    
    public Sales getSalesById(String id){
        return salesDao.getSalesById(id);
       
    }
    
    public List<SalesItem> getSalesItemBySalesId(String id){
       return salesDao.getSalesItemBySalesId(id);
    }
    public String gettotalamount(String tableName){
       return salesDao.gettotalamount(tableName);
    }
    public String gettotalamountforrgb(String kotArr[]){
       return salesDao.gettotalamountforrgb(kotArr);
    }
    
    public String getWaitername(String WaiterID){
       return salesDao.getWaitername(WaiterID);
    }
    public String getSalesamt(String fdtae,String tdate){
       return salesDao.getSalesamt(fdtae,tdate);
    }
    public String getcashamt(String fdtae,String tdate){
       return salesDao.getcashamt(fdtae,tdate);
    }
    public String getcardamt(String fdtae,String tdate){
       return salesDao.getcardamt(fdtae,tdate);
    }
    public String getvoidamt(String fdtae,String tdate){
       return salesDao.getvoidamt(fdtae,tdate);
    }
     public String gettotalamountaudit(String tableName){
       return salesDao.gettotalamountaudit(tableName);
    }
      public String gettotalamountauditforrgb(String kotArr[]){
       return salesDao.gettotalamountauditforrgb(kotArr);
    }
     public List<TempKot> getSalesKotNos(String tableName){
       return salesDao.getSalesKotNos(tableName);
    }
    public List<TempKot> getSalesKotIds(String tableName){
        return salesDao.getSalesKotIds(tableName);
    } 
     
            public List<SalesBill> getRegeneratedBill(String kotIdArr[]){
        return salesDao.getRegeneratedBill(kotIdArr);
    }
    public List<SalesBill> getSalesItemBytableIdForBill(String tableName){
        return salesDao.getSalesItemBytableIdForBill(tableName);
    }
            public List<SalesBill> getSalesItembybill(String BillName){
        return salesDao.getSalesItembybill(BillName);
    } 
     public List<SaleInvoice> getvalues(String BillName){
        return salesDao.getvalues(BillName);
    }
    public String saveSalesItem(List<SalesBill> sales,String kot,String kotIds){
        return salesDao.saveSalesItem(sales,kot,kotIds);
     }
    public void saveInvoice(SaleInvoice si){
         salesDao.saveInvoice(si);   
    }
     public List<Object[]> getSalesItemBySalesIdInvoice(String id){
       return salesDao.getSalesItemBySalesIdInvoice(id);
    }
     public List<Object[]> getbilldetails(String BillName){
       return salesDao.getbilldetails(BillName);
    }
     public LedgerAccountMaster getLedgerIdByBuyerId(int id,String type){
       return salesDao.getledgerIdByBuyerId(id,type);
    }
    
   
    public void deleteSales(String invoiceNo) {
        salesDao.deleteSales(invoiceNo);       
    }
     public void deleteorderkot(String tbn) {
        salesDao.deleteorderkot(tbn);       
    }
    public List<BuyerMaster> listBuyerLike(String key)
    {
        return salesDao.listBuyerLike(key);
    }
    public List<BuyerMaster> listBuyerLikeGst(String key)
    {
        return salesDao.listBuyerLikewithGstno(key);
    }
    
    public List<BuyerMaster> listBuyerMediatorLike(String key){
        return salesDao.listBuyerMediatorLike(key);
    }
    
        public List<Sales> SalesReports(String gsQuery)
  {
    return salesDao.SalesReports(gsQuery);
  }
  
 public String getLastInvoiceDate()
  {
    return salesDao.getLastInvoiceDate();
  }
 
  public String getBreveragestotal(String inv)
  {
    return salesDao.getBreveragestotal(inv);
  }
 public List<Object[]> GetDatatableSalesObject(String query){
    return salesDao.GetDatatableSalesObject(query);
    }
     public List<Object[]> GetDatatableSalesCount(String query){
    return salesDao.GetDatatableSalesCount(query);
    }
public Sales getBuyerByid(int id)
  {
    return salesDao.getBuyerByid(id);
  }

public List<Object[]> GetDatatableObject(String query){
    return salesDao.GetDatatableObject(query);
}
public List<Object[]> GetDatatableCount(String query){
    return salesDao.GetDatatableCount(query);
}

public List<Object[]> getItemSalesReport(String itemname,String FDate,String ToDate){ 
    return salesDao.getItemSalesReport(itemname, FDate, ToDate);
    }

}
