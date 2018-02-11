/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;

import com.accounting.bean.AccountGroupMaster;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.service.AccountReportsService;
import com.accounting.service.CompanyService;
import com.accounting.service.ItemGroupZReportService;
import com.accounting.service.LedgerAccount_Service;
import com.accounting.service.LedgerBalanceService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author MR
 */
@Controller
public class ProfitAndLossController {
    
     @Autowired
     private LedgerAccount_Service lgs;
     
     @Autowired 
     private AccountReportsService accountReportsService;
     
     @Autowired 
     private ItemGroupZReportService igrs;
     
     @Autowired
     private LedgerBalanceService lbs;
     @Autowired
    private CompanyService companyservice;
    
    @RequestMapping(value={"ProfitAndLoss"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView accountReports()
    {
        
        String fromDate = companyservice.getCompanyById(1).getFinancialYearFrom();
       
    String financialdateMonth=fromDate.substring(0, 5);
       
    String financialmonth=financialdateMonth.substring(3, 5);
    int intFinancialMonth=Integer.parseInt(financialmonth);
       
       
       
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
    Date currentdate = new Date();
    String dt=dateFormat.format(currentdate);
    
    String currentMonth=dt.substring(3,5);
    int intcurrentMonth=Integer.parseInt(currentMonth);
   
    String currentYear=dt.substring(6,10);
    int intCurrentYear=Integer.parseInt(currentYear);
    
      
     int fromYear=0;
       if(intFinancialMonth <= intcurrentMonth)
       {
       fromYear = intCurrentYear;
         
       }
       else
       {
         fromYear = intCurrentYear-1;
          
       }
       
       String finalDate=financialdateMonth+"/" + String.valueOf(fromYear); 
        ModelAndView model = new ModelAndView("ProfitAndLoss");
        model.addObject("curDate", dt);
        model.addObject("finalDate", finalDate);
        return model;
    }
    @RequestMapping(value={"ProfitLoss_Vat"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView ProfitLoss_Vat()
    {
        ModelAndView model = new ModelAndView("ProfitLoss_Vat");
       
        return model;
    }
     @RequestMapping(value={"BalanceSheet"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView BalanceSheet()
    {
         String fromDate = companyservice.getCompanyById(1).getFinancialYearFrom();
       
    String financialdateMonth=fromDate.substring(0, 5);
       
    String financialmonth=financialdateMonth.substring(3, 5);
    int intFinancialMonth=Integer.parseInt(financialmonth);
       
       
       
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
    Date currentdate = new Date();
    String dt=dateFormat.format(currentdate);
    
    String currentMonth=dt.substring(3,5);
    int intcurrentMonth=Integer.parseInt(currentMonth);
   
    String currentYear=dt.substring(6,10);
    int intCurrentYear=Integer.parseInt(currentYear);
    
      
     int fromYear=0;
       if(intFinancialMonth <= intcurrentMonth)
       {
       fromYear = intCurrentYear;
         
       }
       else
       {
         fromYear = intCurrentYear-1;
          
       }
       
       String finalDate=financialdateMonth+"/" + String.valueOf(fromYear); 
       
        ModelAndView model = new ModelAndView("BalanceSheet");
        model.addObject("curDate", dt);
        model.addObject("finalDate", finalDate);
        return model;
    }
    // Profit and loss Purchase Defination
     @RequestMapping(value={"GetPF"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public ModelAndView GetPF(HttpServletRequest req) throws ParseException
    {
        
        String fdate = req.getParameter("startDate");
        String Edate = req.getParameter("endDate");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedCurrentDate = sdf.parse(fdate);
        String gsFromDate=dmyFormat.format(convertedCurrentDate );
        Date convertedEndDate = sdf.parse(Edate);
        String gsToDate=dmyFormat.format(convertedEndDate );

        
        // for all opening balance
        List<Object[]> openingStack=igrs.getALLGroupOpeningAndClosingBalanceByGroupBy(gsFromDate);
          // for all opening balance of Item Master
        List<Object[]> openingStock_item_master=igrs.getAllItemOpeningBalanceFromItemMaster_Profit_loss(gsFromDate);
        // for all Closing balance
        List<Object[]> ClosingStock=igrs.getAllGroupOpeningAndClosingBalanceByGroupByEnddate(gsFromDate,gsToDate);
         List<Object[]> closing_item_master_endDate=igrs.getAllItemOpeningBalanceFromItemMaster_Profit_loss_every_Transcation(gsToDate);
        // sum of Stock journalso outwards Value
         List<Object[]> StockjournalsOutValue=igrs.getAllStockJournalsOutValueFromtoEndDate(gsFromDate,gsToDate);
        
        // for purchase account group         
        List<Object[]> SigleGroupLedgerSUM_P=accountReportsService.GetSingleGroupEndDateCurrentBalance("22",gsFromDate,gsToDate);
        
       // List<Object[]> SigleGroupOPening_P=accountReportsService.GetSingleGroupClosingBalanceBasedOnFromDate("22",gsFromDate);
        
        List<Object[]> SigleGroupLedgerOPening_P=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"22");
        
        
          // for Sales account group         
        List<Object[]> SigleGroupLedgerSUM_S=accountReportsService.GetSingleGroupEndDateCurrentBalance("25",gsFromDate,gsToDate);
        
      //  List<Object[]> SigleGroupOPening_S=accountReportsService.GetSingleGroupClosingBalanceBasedOnFromDate("25",gsFromDate);
        
        List<Object[]> SigleGroupLedgerOPening_S=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"25");
       
            // for Indirect income account group         
        List<Object[]> SigleGroupLedgerSUM_income=accountReportsService.GetSingleGroupEndDateCurrentBalance("27",gsFromDate,gsToDate);
        
       // List<Object[]> SigleGroupOPening_income=accountReportsService.GetSingleGroupClosingBalanceBasedOnFromDate("27",gsFromDate);
        
        List<Object[]> SigleGroupLedgerOPening_income=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"27");
       
        // for Indirect expense account group         
        List<Object[]> SigleGroupLedgerSUM_expense=accountReportsService.GetSingleGroupEndDateCurrentBalance("24",gsFromDate,gsToDate);
        
      //  List<Object[]> SigleGroupOPening_expense=accountReportsService.GetSingleGroupClosingBalanceBasedOnFromDate("24",gsFromDate);
        
        List<Object[]> SigleGroupLedgerOPening_expense=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"24");
        
        
        // For Direct Expense 
        
         List<Object[]> SigleGroupLedgerSUM_Dexpense=accountReportsService.GetSingleGroupEndDateCurrentBalance("23",gsFromDate,gsToDate);
        
       // List<Object[]> SigleGroupOPening_Dexpense=accountReportsService.GetSingleGroupClosingBalanceBasedOnFromDate("23",gsFromDate);
        
        List<Object[]> SigleGroupLedgerOPening_Dexpense=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"23");
        
        
        ModelAndView model = new ModelAndView("ProfitAndLoss");
       
        model.addObject("openingStack", openingStack);
        model.addObject("openingStock_item_master", openingStock_item_master);
        model.addObject("ClosingStock", ClosingStock);
           model.addObject("StockjournalsOutValue", StockjournalsOutValue);
            model.addObject("closing_item_master_endDate", closing_item_master_endDate);
            model.addObject("SigleGroupLedgerSUM_P", SigleGroupLedgerSUM_P);
         //   model.addObject("SigleGroupOPening_P", SigleGroupOPening_P);
            model.addObject("SigleGroupLedgerOPening_P", SigleGroupLedgerOPening_P);
                model.addObject("SigleGroupLedgerSUM_S", SigleGroupLedgerSUM_S);
           //     model.addObject("SigleGroupOPening_S", SigleGroupOPening_S);
                model.addObject("SigleGroupLedgerOPening_S", SigleGroupLedgerOPening_S);
                     model.addObject("SigleGroupLedgerSUM_income", SigleGroupLedgerSUM_income);
                 //    model.addObject("SigleGroupOPening_income", SigleGroupOPening_income);
                     model.addObject("SigleGroupLedgerOPening_income", SigleGroupLedgerOPening_income);
                        model.addObject("SigleGroupLedgerSUM_expense", SigleGroupLedgerSUM_expense);
                      //  model.addObject("SigleGroupOPening_expense", SigleGroupOPening_expense);
                        model.addObject("SigleGroupLedgerOPening_expense", SigleGroupLedgerOPening_expense);
                             model.addObject("SigleGroupLedgerSUM_Dexpense", SigleGroupLedgerSUM_Dexpense);
                           //  model.addObject("SigleGroupOPening_Dexpense", SigleGroupOPening_Dexpense);
                             model.addObject("SigleGroupLedgerOPening_Dexpense", SigleGroupLedgerOPening_Dexpense);
                        
        System.out.println(" calculateNetProfit "+calculateNetProfit(gsFromDate,gsToDate));
        
        return model;
    }
      // Profit and loss Purchase Vat Formula
      @RequestMapping(value={"GetPFVAT"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public ModelAndView GetPFVAT(HttpServletRequest req)
    {
        
        String gsFromDate = req.getParameter("startDate");
        String gsToDate = req.getParameter("endDate");
     
        
        // for all opening balance
        List<Object[]> openingStack=igrs.getALLGroupOpeningAndClosingBalanceByGroupBy(gsFromDate);
          // for all opening balance of Item Master
        List<Object[]> openingStock_item_master=igrs.getAllItemOpeningBalanceFromItemMaster_Profit_loss(gsFromDate);
        // for all Closing balance
        List<Object[]> ClosingStock=igrs.getAllGroupOpeningAndClosingBalanceByGroupByEnddate(gsFromDate,gsToDate);
         List<Object[]> closing_item_master_endDate=igrs.getAllItemOpeningBalanceFromItemMaster_Profit_loss_every_Transcation(gsToDate);
        // sum of Stock journalso outwards Value
         List<Object[]> StockjournalsOutValue=igrs.getAllStockJournalsOutValueFromtoEndDate(gsFromDate,gsToDate);
        
        // for purchase account group         
        List<Object[]> SigleGroupLedgerSUM_P=accountReportsService.GetSingleGroupEndDateCurrentBalance("22",gsFromDate,gsToDate);
        
       // List<Object[]> SigleGroupOPening_P=accountReportsService.GetSingleGroupClosingBalanceBasedOnFromDate("22",gsFromDate);
        
        List<Object[]> SigleGroupLedgerOPening_P=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"22");
        
        
          // for Sales account group         
        List<Object[]> SigleGroupLedgerSUM_S=accountReportsService.GetSingleGroupEndDateCurrentBalance("25",gsFromDate,gsToDate);
        
      //  List<Object[]> SigleGroupOPening_S=accountReportsService.GetSingleGroupClosingBalanceBasedOnFromDate("25",gsFromDate);
        
        List<Object[]> SigleGroupLedgerOPening_S=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"25");
       
            // for Indirect income account group         
        List<Object[]> SigleGroupLedgerSUM_income=accountReportsService.GetSingleGroupEndDateCurrentBalance("27",gsFromDate,gsToDate);
        
       // List<Object[]> SigleGroupOPening_income=accountReportsService.GetSingleGroupClosingBalanceBasedOnFromDate("27",gsFromDate);
        
        List<Object[]> SigleGroupLedgerOPening_income=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"27");
       
        // for Indirect expense account group         
        List<Object[]> SigleGroupLedgerSUM_expense=accountReportsService.GetSingleGroupEndDateCurrentBalance("24",gsFromDate,gsToDate);
        
      //  List<Object[]> SigleGroupOPening_expense=accountReportsService.GetSingleGroupClosingBalanceBasedOnFromDate("24",gsFromDate);
        
        List<Object[]> SigleGroupLedgerOPening_expense=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"24");
        
        
        // For Direct Expense 
        
         List<Object[]> SigleGroupLedgerSUM_Dexpense=accountReportsService.GetSingleGroupEndDateCurrentBalance("23",gsFromDate,gsToDate);
        
       // List<Object[]> SigleGroupOPening_Dexpense=accountReportsService.GetSingleGroupClosingBalanceBasedOnFromDate("23",gsFromDate);
        
        List<Object[]> SigleGroupLedgerOPening_Dexpense=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"23");
        
        
        ModelAndView model = new ModelAndView("ProfitAndLoss");
       
        model.addObject("openingStack", openingStack);
        model.addObject("openingStock_item_master", openingStock_item_master);
        model.addObject("ClosingStock", ClosingStock);
           model.addObject("StockjournalsOutValue", StockjournalsOutValue);
            model.addObject("closing_item_master_endDate", closing_item_master_endDate);
            model.addObject("SigleGroupLedgerSUM_P", SigleGroupLedgerSUM_P);
         //   model.addObject("SigleGroupOPening_P", SigleGroupOPening_P);
            model.addObject("SigleGroupLedgerOPening_P", SigleGroupLedgerOPening_P);
                model.addObject("SigleGroupLedgerSUM_S", SigleGroupLedgerSUM_S);
           //     model.addObject("SigleGroupOPening_S", SigleGroupOPening_S);
                model.addObject("SigleGroupLedgerOPening_S", SigleGroupLedgerOPening_S);
                     model.addObject("SigleGroupLedgerSUM_income", SigleGroupLedgerSUM_income);
                 //    model.addObject("SigleGroupOPening_income", SigleGroupOPening_income);
                     model.addObject("SigleGroupLedgerOPening_income", SigleGroupLedgerOPening_income);
                        model.addObject("SigleGroupLedgerSUM_expense", SigleGroupLedgerSUM_expense);
                      //  model.addObject("SigleGroupOPening_expense", SigleGroupOPening_expense);
                        model.addObject("SigleGroupLedgerOPening_expense", SigleGroupLedgerOPening_expense);
                             model.addObject("SigleGroupLedgerSUM_Dexpense", SigleGroupLedgerSUM_Dexpense);
                           //  model.addObject("SigleGroupOPening_Dexpense", SigleGroupOPening_Dexpense);
                             model.addObject("SigleGroupLedgerOPening_Dexpense", SigleGroupLedgerOPening_Dexpense);
                        
        System.out.println(" calculateNetProfit "+calculateNetProfit(gsFromDate,gsToDate));
        
        return model;
    }
    
       @RequestMapping(value={"GetBalanceSheetReport"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public ModelAndView GetBalanceSheetReport(HttpServletRequest req) throws ParseException
    {
        
        String fdate = req.getParameter("startDate");
        String Edate = req.getParameter("endDate");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedCurrentDate = sdf.parse(fdate);
        String gsFromDate=dmyFormat.format(convertedCurrentDate );
        Date convertedEndDate = sdf.parse(Edate);
        String gsToDate=dmyFormat.format(convertedEndDate );
     
        
       
        // for Capital Account
        List<Object[]> SigleGroupLedgerSUM_Capital=accountReportsService.GetSingleGroupEndDateCurrentBalance("1",gsFromDate,gsToDate);
        List<Object[]> SigleGroupLedgerOPening_Capital=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"1");
        
        
          // for loan        
        List<Object[]> SigleGroupLedgerSUM_Loan=accountReportsService.GetSingleGroupEndDateCurrentBalance("3",gsFromDate,gsToDate);
        List<Object[]> SigleGroupLedgerOPening_Loan=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"3");
       
            // for current liabilities        
        List<Object[]> SigleGroupLedgerSUM_liabilities=accountReportsService.GetSingleGroupEndDateCurrentBalance("2",gsFromDate,gsToDate);
        List<Object[]> SigleGroupLedgerOPening_liabilities=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"2");
       
        // for assets        
        List<Object[]> SigleGroupLedgerSUM_assets=accountReportsService.GetSingleGroupEndDateCurrentBalance("4",gsFromDate,gsToDate);
        List<Object[]> SigleGroupLedgerOPening_assets=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"4");
        
        // for fixed Assets        
        List<Object[]> SigleGroupLedgerSUM_f_assets=accountReportsService.GetSingleGroupEndDateCurrentBalance("6",gsFromDate,gsToDate);
        List<Object[]> SigleGroupLedgerOPening_f_assets=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(gsFromDate,"6");
        
          double openingstockMaster=0.0;
      
        
        double totalOPening=0.0;
       
        
        // for all opening balance
       
         List<Object[]> openingStock_item_master=igrs.getAllItemOpeningBalanceFromItemMaster_Profit_loss(gsFromDate);
        
        
         if( openingStock_item_master!=null && openingStock_item_master.size()>0)
            {
                  for (Object[] column : openingStock_item_master) {
                             openingstockMaster = openingstockMaster+(Double)column[1];
                     }

            }
         
         totalOPening=openingstockMaster; 
        
        
        // for closing stock
        List<Object[]> ClosingStock=igrs.getAllGroupOpeningAndClosingBalanceByGroupByEnddate(gsFromDate,gsToDate);
        
        // sum of Stock journalso outwards Value
         List<Object[]> StockjournalsOutValue=igrs.getAllStockJournalsOutValueFromtoEndDate(gsFromDate,gsToDate);
         // item master ledger closing 
         List<Object[]> closing_item_master_endDate=igrs.getAllItemOpeningBalanceFromItemMaster_Profit_loss_every_Transcation(gsToDate);
        double closingstockReport=0.0;
        double totalStockJourtnals=0.0;
        double itemLegder_closing=0.0;
        double totalClosing=0.0;
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
             if( closing_item_master_endDate!=null && closing_item_master_endDate.size()>0)
            {
                  for (Object[] column : closing_item_master_endDate) {
                             itemLegder_closing = itemLegder_closing+(Double)column[1];
                     }

            }
         totalClosing=closingstockReport+totalStockJourtnals+itemLegder_closing;
         double netProfit=calculateNetProfit(gsFromDate,gsToDate);
         
         // Profit and Loss OPening Account
         
          List<Object[]> currentBalance=lbs.SingleLedgerCurrentOpeningBalanceFromEntriesTable(String.valueOf(2), gsFromDate);

            double Profit_openingBalanceOfLedger=0;
            if( currentBalance!=null && currentBalance.size()>0)
            {
                  for (Object[] column : currentBalance) {
                             Profit_openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                     }

            }
            else
            {
                List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(2));
                for (Object[] column : OpeningBalance) {
                             Profit_openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                     }
            }
         
         
         
        ModelAndView model = new ModelAndView("BalanceSheet");
  
        
                     model.addObject("SigleGroupLedgerSUM_Capital", SigleGroupLedgerSUM_Capital);
                     model.addObject("SigleGroupLedgerOPening_Capital", SigleGroupLedgerOPening_Capital);
                     model.addObject("SigleGroupLedgerSUM_Loan", SigleGroupLedgerSUM_Loan);
                     model.addObject("SigleGroupLedgerOPening_Loan", SigleGroupLedgerOPening_Loan);
                      model.addObject("SigleGroupLedgerSUM_liabilities", SigleGroupLedgerSUM_liabilities);
                     model.addObject("SigleGroupLedgerOPening_liabilities", SigleGroupLedgerOPening_liabilities);
                      model.addObject("SigleGroupLedgerSUM_assets", SigleGroupLedgerSUM_assets);
                     model.addObject("SigleGroupLedgerOPening_assets", SigleGroupLedgerOPening_assets);
                     
                        model.addObject("SigleGroupLedgerSUM_f_assets", SigleGroupLedgerSUM_f_assets);
                     model.addObject("SigleGroupLedgerOPening_f_assets", SigleGroupLedgerOPening_f_assets);
                     
                     model.addObject("closing_stock", totalClosing);
                     model.addObject("netProfit", netProfit);
                     
                      model.addObject("Profit_openingBalanceOfLedger", Profit_openingBalanceOfLedger);
                        
        
        
        return model;
    }
    public double calculateNetProfit(String gsFromDate,String gsToDate)
    {       
        double openingstockReport=0.0;
        double openingstockMaster=0.0;
        double closingstockReport=0.0;
        double totalStockJourtnals=0.0;
        double totalOPening=0.0;
        double totalClosing=0.0;
        double itemLegder_closing=0.0;
        
        // for all opening balance
        List<Object[]> openingStack=igrs.getALLGroupOpeningAndClosingBalanceByGroupBy(gsFromDate);
         List<Object[]> openingStock_item_master=igrs.getAllItemOpeningBalanceFromItemMaster_Profit_loss(gsFromDate);
        
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
         
         totalOPening=openingstockReport+openingstockMaster;
          // for all opening balance of Item Master
       
        // for all Closing balance
        List<Object[]> ClosingStock=igrs.getAllGroupOpeningAndClosingBalanceByGroupByEnddate(gsFromDate,gsToDate);
        
        // sum of Stock journalso outwards Value
         List<Object[]> StockjournalsOutValue=igrs.getAllStockJournalsOutValueFromtoEndDate(gsFromDate,gsToDate);
          List<Object[]> closing_item_master_endDate=igrs.getAllItemOpeningBalanceFromItemMaster_Profit_loss_every_Transcation(gsToDate);
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
           if( closing_item_master_endDate!=null && closing_item_master_endDate.size()>0)
            {
                  for (Object[] column : closing_item_master_endDate) {
                             itemLegder_closing = itemLegder_closing+(Double)column[1];
                     }

            }
            // Include OPening from Item Master
         totalClosing=closingstockReport+totalStockJourtnals+itemLegder_closing;
        
      
         
         
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
       
         if( SigleGroupLedgerSUM_income!=null && SigleGroupLedgerSUM_income.size()>0)
            {
                  for (Object[] column : SigleGroupLedgerSUM_income) {
                             indirectLedgerClosing = indirectLedgerClosing+((Double)column[1]-(Double)column[2]);
                     }

            }
         if( SigleGroupLedgerOPening_income!=null && SigleGroupLedgerOPening_income.size()>0)
            {
                  for (Object[] column : SigleGroupLedgerOPening_income) {
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
    
}
