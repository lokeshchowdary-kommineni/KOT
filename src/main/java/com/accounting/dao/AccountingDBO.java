/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.dao;

import com.accounting.bean.CompanyInformation;
import com.accounting.ed.Encryptor;
import com.accounting.ed.GetSystemData;
import com.accounting.service.AccountReportsService;
import com.accounting.service.CompanyService;
import com.accounting.service.ItemGroupZReportService;
import com.accounting.service.LedgerAccount_Service;
import com.accounting.service.LedgerBalanceService;
import com.accounting.service.UserControllerService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MR
 */
@Repository("AccountingDBO")
public class AccountingDBO {
    @Autowired private SessionFactory sessionFactory;
     @Autowired
    private CompanyService companyservice;
     
         @Autowired
     private LedgerAccount_Service lgs;
     
     @Autowired 
     private AccountReportsService accountReportsService;
     
     @Autowired 
     private ItemGroupZReportService igrs;
     
     @Autowired
     private LedgerBalanceService lbs; 
     
      @Autowired
  private UserControllerService userControllerService;
      
    public String getFinacialYearRange()
    {
         //finacial year
          List clist=companyservice.listcompany();
          CompanyInformation ci=(CompanyInformation)clist.iterator().next();
          String financialYear=ci.getFinancialYearFrom();             
          int a=Integer.parseInt(financialYear.substring(3,5));                  
          int monthindex=(a);  
                        String yearRange="";
                        int year = Calendar.getInstance().get(Calendar.YEAR);
                        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
                        if (month < monthindex) {
//                            System.out.println("Financial Year : " + (year - 1) + "-" + year);
                            String start=String.valueOf(year-1).substring(2,4);
                            String end=String.valueOf(year).substring(2,4);    
                            yearRange=start + "-" + end;
                        }
                        else if(monthindex==1)
                        {
//                            System.out.println(" monthindex  test "+monthindex);
                           // if finacial year start from jan 1
                            String start=String.valueOf(year).substring(2,4);
                            String end=String.valueOf(year).substring(2,4);  
                            yearRange=start + "-" + end;
                        }
                        else {
//                            System.out.println("Financial Year : " + year + "-" + (year + 1));
                            String start=String.valueOf(year).substring(2,4);
                            String end=String.valueOf(year+1).substring(2,4);  
                            yearRange=start + "-" + end;
                        }
                        return yearRange;
    }
     public  double RoundUp(double toRound) // Rounding Concept
{
       String[] arr=String.valueOf(toRound).split("\\.");
       String before=String.valueOf(arr[0]);
       
       int length=before.length(); // length before decimal
       int before1=Integer.parseInt(arr[0]);// number before decimal
       
       int after=Integer.parseInt(arr[1]); // number after decimal
       
       int finalResult=0;
       String concatDouble="";
    if(length == 1)
    { 
        finalResult= (10 - after % 10) + after;
        
  if(finalResult > 10 && finalResult < 100) 
  {
    concatDouble=before+"."+finalResult;
  }
  else if(finalResult == 10)
  {
  concatDouble=before+"."+after;
  }
  else if(finalResult == 100)
  {
  int get=Integer.parseInt(before)+1;
   concatDouble=String.valueOf(get);
  }
    }
    if(length == 2)
    {
         finalResult= (25 - after % 25) + after;
     
  if(finalResult > 25 &&finalResult < 100) 
  {
    concatDouble=before+"."+finalResult;
  }
  else if(finalResult == 25)
  {
    concatDouble=before+"."+after;
  }
  else if(finalResult == 100)
  {
   int get=Integer.parseInt(before)+1;
   concatDouble=String.valueOf(get);
  }
    }
    if(length == 3)
    { 
       
        finalResult=  after;
        
        if(finalResult == 0){
       concatDouble=before;
      
        }
        else{
            int get=Integer.parseInt(before)+1;
    concatDouble=String.valueOf(get);
        }
        
    
    }
    if(length == 4)
    {  
         finalResult=  after;
        
        if(finalResult == 0){
       concatDouble=before;
       System.out.println(concatDouble);
        }
        else{
       finalResult= (10 - before1 % 10) + before1;
       concatDouble=String.valueOf(finalResult);
        }
    
    }
    if(length >= 5)
    {  
         finalResult=  after;
        
        if(finalResult == 0){
       concatDouble=before;
       System.out.println(concatDouble);
        }
        else{
       finalResult= (100 - before1 % 100) + before1;
       concatDouble=String.valueOf(finalResult);
        }
    
    }
     return Double.valueOf(concatDouble);
     
}
    public long getPreviousIdBasedStartAndEndYear(String ColumnName,String TableName,String yearRange){
     //   int id= (int) sessionFactory.getCurrentSession().createQuery("SELECT COALESCE(MAX(CAST(SUBSTRING("+ColumnName+", 1, LENGTH("+ColumnName+")-3) AS int)),0)  FROM "+TableName+" WHERE SUBSTRING("+ColumnName+",-5)='"+yearRange+"'").uniqueResult();
        long id= (long) sessionFactory.getCurrentSession().createQuery("SELECT COALESCE(MAX(CAST(SUBSTRING("+ColumnName+", 1,LENGTH("+ColumnName+")-6) AS long)),0)  FROM "+TableName+" WHERE SUBSTRING("+ColumnName+",-5)='"+yearRange+"'").uniqueResult();
        return id;
    }
     public double calculateNetProfit_Every_transcation(String gsFromDate,String gsToDate)
    {       
        double openingstockReport=0.0;
        double openingstockMaster=0.0;
        double closingstockReport=0.0;
        double totalStockJourtnals=0.0;
        double totalOPening=0.0;
        double totalClosing=0.0;
        double itemLegder_closing=0.0;
        
        // for all opening balance
        List<Object[]> openingStack=igrs.getALLGroupOpeningBalanceByGroupBy_everySales_purchase(gsFromDate);
         List<Object[]> openingStock_item_master=igrs.getAllItemOpeningBalanceFromItemMaster_Profit_loss_every_Transcation(gsFromDate);
           List<Object[]> closing_item_master_endDate=igrs.getAllItemOpeningBalanceFromItemMaster_Profit_loss_every_Transcation(gsToDate);
         if( openingStack!=null && openingStack.size()>0)
            {
                  for (Object[] column : openingStack) {
                             openingstockReport = openingstockReport+(Double)column[4];
                     }

            }
         if( openingStock_item_master!=null && openingStock_item_master.size()>0)
            {
                  for (Object[] column : openingStock_item_master) {
                             openingstockMaster = openingstockMaster+(Double)column[1];
                     }

            }
          if( closing_item_master_endDate!=null && closing_item_master_endDate.size()>0)
            {
                  for (Object[] column : closing_item_master_endDate) {
                             itemLegder_closing = itemLegder_closing+(Double)column[1];
                     }

            }
            // Include OPening from Item Master
         totalOPening=openingstockMaster+openingstockMaster;
          // for all opening balance of Item Master
       
        // for all Closing balance
        List<Object[]> ClosingStock=igrs.getAllGroupOpeningAndClosingBalanceByGroupByEnddate(gsFromDate,gsToDate);
        
        // sum of Stock journalso outwards Value
         List<Object[]> StockjournalsOutValue=igrs.getAllStockJournalsOutValueFromtoEndDate(gsFromDate,gsToDate);
        
          if( ClosingStock!=null && ClosingStock.size()>0)
            {
                  for (Object[] column : ClosingStock) {
                             closingstockReport = closingstockReport+(Double)column[4];
                     }

            }
         if( StockjournalsOutValue!=null && StockjournalsOutValue.size()>0)
            {
                  for (Object[] column : StockjournalsOutValue) {
                             totalStockJourtnals = totalStockJourtnals+(Double)column[3];
                     }

            }
         totalClosing=closingstockReport+totalStockJourtnals+openingstockMaster;
         
         
        double purchaseLedgerClosing=0.0; 
        double purchaseLedgerOpening=0.0; 
        double totalPurchase=0.0;
        // for purchase account group         
        List<Object[]> SigleGroupLedgerSUM_P=accountReportsService.GetSingleGroupEndDateCurrentBalance("22",gsFromDate,gsToDate);
        
       // List<Object[]> SigleGroupOPening_P=accountReportsService.GetSingleGroupClosingBalanceBasedOnFromDate("22",gsFromDate);
        
        List<Object[]> SigleGroupLedgerOPening_P=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"22");
        
        if( SigleGroupLedgerSUM_P!=null && SigleGroupLedgerSUM_P.size()>0)
            {
                  for (Object[] column : SigleGroupLedgerSUM_P) {
                             purchaseLedgerClosing = purchaseLedgerClosing+((Double)column[1]-(Double)column[2]);
                     }

            }
         if( SigleGroupLedgerOPening_P!=null && SigleGroupLedgerOPening_P.size()>0)
            {
                  for (Object[] column : SigleGroupLedgerOPening_P) {
                             purchaseLedgerOpening = purchaseLedgerOpening+((Double)column[0]-(Double)column[1]);
                     }

            }
        totalPurchase= purchaseLedgerClosing+purchaseLedgerOpening;
         
         
        double salesLedgerClosing=0.0; 
        double salesLedgerOpening=0.0; 
        double totalsales=0.0;
        // for Sales account group         
        List<Object[]> SigleGroupLedgerSUM_S=accountReportsService.GetSingleGroupEndDateCurrentBalance("25",gsFromDate,gsToDate);
        
      //  List<Object[]> SigleGroupOPening_S=accountReportsService.GetSingleGroupClosingBalanceBasedOnFromDate("25",gsFromDate);
        
        List<Object[]> SigleGroupLedgerOPening_S=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"25");
       
        if( SigleGroupLedgerSUM_S!=null && SigleGroupLedgerSUM_S.size()>0)
            {
                  for (Object[] column : SigleGroupLedgerSUM_S) {
                             salesLedgerClosing = salesLedgerClosing+((Double)column[1]-(Double)column[2]);
                     }

            }
         if( SigleGroupLedgerOPening_S!=null && SigleGroupLedgerOPening_S.size()>0)
            {
                  for (Object[] column : SigleGroupLedgerOPening_S) {
                             salesLedgerOpening = salesLedgerOpening+((Double)column[0]-(Double)column[1]);
                     }

            }
        totalsales= salesLedgerClosing+salesLedgerOpening;
        
        double indirectLedgerClosing=0.0; 
        double indirectLedgerOpening=0.0; 
        double totalindirectincome=0.0;
            // for Indirect income account group         
        List<Object[]> SigleGroupLedgerSUM_income=accountReportsService.GetSingleGroupEndDateCurrentBalance("27",gsFromDate,gsToDate);
        
       // List<Object[]> SigleGroupOPening_income=accountReportsService.GetSingleGroupClosingBalanceBasedOnFromDate("27",gsFromDate);
        
        List<Object[]> SigleGroupLedgerOPening_income=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"27");
       
        System.out.println("Size of An Object:"+SigleGroupLedgerSUM_income.size());
         System.out.println("Size of An Object:"+SigleGroupLedgerOPening_income.size());
        
         if( SigleGroupLedgerSUM_income!=null && SigleGroupLedgerSUM_income.size()>0)
            {
                  for (Object[] column : SigleGroupLedgerSUM_income) {
                         System.out.println("Size of An Object:"+column.length);
                             indirectLedgerClosing = indirectLedgerClosing+((Double)column[1]-(Double)column[2]);
                     }

            }
         if( SigleGroupLedgerOPening_income!=null && SigleGroupLedgerOPening_income.size()>0)
            {
                  for (Object[] column : SigleGroupLedgerOPening_income) {
                       System.out.println("Size of An Object:"+column.length);
                             indirectLedgerOpening = indirectLedgerOpening+((Double)column[0]-(Double)column[1]);
                     }

            }
        totalindirectincome= indirectLedgerClosing+indirectLedgerOpening; 
        
        double expenseLedgerClosing=0.0; 
        double expenseLedgerOpening=0.0; 
        double totalindirectexpense=0.0;
        // for Indirect expense account group         
        List<Object[]> SigleGroupLedgerSUM_expense=accountReportsService.GetSingleGroupEndDateCurrentBalance("24",gsFromDate,gsToDate);
        
      //  List<Object[]> SigleGroupOPening_expense=accountReportsService.GetSingleGroupClosingBalanceBasedOnFromDate("24",gsFromDate);
        
        List<Object[]> SigleGroupLedgerOPening_expense=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"24");
        
         if( SigleGroupLedgerSUM_expense!=null && SigleGroupLedgerSUM_expense.size()>0)
            {
                  for (Object[] column : SigleGroupLedgerSUM_expense) {
                             expenseLedgerClosing = expenseLedgerClosing+((Double)column[1]-(Double)column[2]);
                     }

            }
         if( SigleGroupLedgerOPening_expense!=null && SigleGroupLedgerOPening_expense.size()>0)
            {
                  for (Object[] column : SigleGroupLedgerOPening_expense) {
                             expenseLedgerOpening = expenseLedgerOpening+((Double)column[0]-(Double)column[1]);
                     }

            }
         totalindirectexpense= expenseLedgerClosing+expenseLedgerOpening; 
         
        double dexpenseLedgerClosing=0.0; 
        double dexpenseLedgerOpening=0.0; 
        double dtotaldirectexpense=0.0;
         
        // For Direct Expense 
        
         List<Object[]> SigleGroupLedgerSUM_Dexpense=accountReportsService.GetSingleGroupEndDateCurrentBalance("23",gsFromDate,gsToDate);
        
       // List<Object[]> SigleGroupOPening_Dexpense=accountReportsService.GetSingleGroupClosingBalanceBasedOnFromDate("23",gsFromDate);
        
        List<Object[]> SigleGroupLedgerOPening_Dexpense=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"23");
        
        if( SigleGroupLedgerSUM_Dexpense!=null && SigleGroupLedgerSUM_Dexpense.size()>0)
            {
                  for (Object[] column : SigleGroupLedgerSUM_Dexpense) {
                             dexpenseLedgerClosing = dexpenseLedgerClosing+((Double)column[1]-(Double)column[2]);
                     }

            }
         if( SigleGroupLedgerOPening_Dexpense!=null && SigleGroupLedgerOPening_Dexpense.size()>0)
            {
                  for (Object[] column : SigleGroupLedgerOPening_Dexpense) {
                             dexpenseLedgerOpening = dexpenseLedgerOpening+((Double)column[0]-(Double)column[1]);
                     }

            }
         dtotaldirectexpense= dexpenseLedgerClosing+dexpenseLedgerOpening; 
         
         double open_purchase_direct_expense=0.0;
         double close_sale=0.0;
         double gross_profit=0.0;
         double open_purchase_direct_expense_grossProfit=0.0;
         open_purchase_direct_expense=dtotaldirectexpense+totalPurchase+totalOPening;
         close_sale=totalClosing+Math.abs(totalsales);
         gross_profit=close_sale-open_purchase_direct_expense;
         open_purchase_direct_expense_grossProfit=open_purchase_direct_expense+gross_profit;
         
          System.out.println(" totalClosing totalClosing  "+totalClosing +" "+totalsales);
         System.out.println(" open_purchase_direct_expense "+open_purchase_direct_expense);
          System.out.println(" close_sale "+close_sale);
           System.out.println(" gross_profit "+gross_profit);
            System.out.println(" open_purchase_direct_expense_grossProfit "+open_purchase_direct_expense_grossProfit);
        
         
         
         double rightTotal=0.0;
         double netProfit=0.0;
         double leftTotal=0.0;
         rightTotal=gross_profit+totalindirectincome;
         netProfit=rightTotal-totalindirectexpense;
         leftTotal=totalindirectexpense+netProfit;
         
        
     return netProfit;   
    }    
   
    public boolean CheckValidLicens(String propertiesFilePath) throws IOException, ParseException
    {
      boolean license=false;
      String key = "Bar12345Bar12345"; // 128 bit key
      String initVector = "RandomInitVector"; // 16 bytes IV
     // String  PropFile = req.getServletContext().getRealPath("/")+"//conf//conf.properties";
                 Properties prop = new Properties();
                 InputStream input = null;
                 File f=new File(propertiesFilePath);
                 if(f.exists() && !f.isDirectory()) 
                 { 
                        // do something
              
                 input = new FileInputStream(f);
		// load a properties file
	         prop.load(input);
                 
                 String encMac=prop.getProperty("1");
                 String encValidity=prop.getProperty("2");
                 String encEmail=prop.getProperty("3");
                 String encSerialnumber=prop.getProperty("4");
                 String encDate=prop.getProperty("5");
                 String encDays=prop.getProperty("6");
                 String Mac=Encryptor.decrypt(key, initVector, encMac);
                 String Validity=Encryptor.decrypt(key, initVector, encValidity);
                 String Email_prop=Encryptor.decrypt(key, initVector, encEmail);
                 String Sysserialnumber=Encryptor.decrypt(key, initVector, encSerialnumber);
                 String activateDate=Encryptor.decrypt(key, initVector, encDate);
                 String expireDays=Encryptor.decrypt(key, initVector, encDays);
                 String email="";              
                 String SysMacAddress=GetSystemData.getMacAddress();                 
                 String serailnumber=GetSystemData.getBaseboardSerialnumber();             
                 Date startDate=new Date();
                 Date endDate=new Date();
                 Date activated_date=null;
                 try{
                  endDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(activateDate);
                  activated_date=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(activateDate);
                
                  endDate.setDate(endDate.getDate()+Integer.parseInt(expireDays));
           System.out.println("activatedRate "+startDate+"/ "+endDate+" / "+activated_date);
                
                  email=userControllerService.getUserByid(1).getEmail();
                 }
                 catch(NullPointerException n)
                 {
                   license=false;   
                    System.out.println("sndfhsd NullPointerException -10");
                 }
               //  System.out.println(" Mac "+Mac+"  SysMacAddress"+SysMacAddress);
              //   System.out.println(" Email  "+Email_prop+" email "+email);
                 if(SysMacAddress.equals(Mac) && email.equals(Email_prop) && serailnumber.equals(Sysserialnumber) )
                 {
                    license=true; 
                    if(expireDays.equalsIgnoreCase("0"))
                    {
                        license=true;  
                        System.out.println(" expireDays sndfhsd -10");
                    }
                    else
                    {
                     if(startDate.compareTo(activated_date)<0)  
                     {
                         license=false; 
                         System.out.println("sndfhsd 1");
                     }
                     else
                     {   
                        if(startDate.compareTo(endDate)<0) 
                        {
                           license=true;    
 System.out.println("sndfhsd 2");
                        }
                        else
                        {
                           license=false; 
                            System.out.println("sndfhsd 43");

                        }
                     }
                    }   
                    
                 }
                 }
                 else
                 {    
                    license=false; 
                     System.out.println("else 2");
                 }
      System.out.println("else zxdvfcsadfsd last 2"+license); 
      return license;
    }
    public  boolean netIsAvailable() {
    try {
        final URL url = new URL("http://www.google.com");
        final URLConnection conn = url.openConnection();
        conn.connect();
        return true;
    } catch (MalformedURLException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        return false;
    }
}
}
