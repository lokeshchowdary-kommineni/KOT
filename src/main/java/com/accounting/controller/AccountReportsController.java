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
import com.accounting.service.ItemMasterService;
import com.accounting.service.LedgerAccount_Service;
import com.accounting.service.LedgerBalanceService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author MR
 */
@Controller
public class AccountReportsController {
    
     @Autowired
     private LedgerAccount_Service lgs;
     @Autowired
     private ItemMasterService ims;
     
     @Autowired 
     private AccountReportsService accountReportsService;
     
     @Autowired 
     private ItemGroupZReportService itemGroupZReportService;
     @Autowired
     private LedgerBalanceService lbs;
      @Autowired
     private CompanyService companyservice;
    
    @RequestMapping(value={"SelectLedgerDropDownBasedOnGroup"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView SelectLedgerDropDownBasedOnGroup(HttpServletRequest req)
    {
        String groupid=req.getParameter("parentId_group");
        String inIds=itemGroupZReportService.returnChildids(groupid);
        
       
        String id="";
        if("".equals(inIds)) 
        {  
        id="'"+groupid+"'";
        }
        else
        {
         id="'"+groupid+"',"+inIds;   
        }    
       
        ModelAndView model = new ModelAndView("AjaxDropDown");
        model.addObject("listAllLedger",accountReportsService.getAllLedgerfromGroups(id));
        model.addObject("Ledger",1);
        return model;
    }
     @RequestMapping(value={"SelectGroupDropDownBasedOnGroup"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView SelectGroupDropDownBasedOnGroup(HttpServletRequest req)
    {
        String groupid=req.getParameter("groupid");
        String inIds=itemGroupZReportService.returnChildItemids(groupid);
        
       
        String id="";
        if("".equals(inIds)) 
        {  
        id="'"+groupid+"'";
        }
        else
        {
         id="'"+groupid+"',"+inIds;   
        }    
       
        ModelAndView model = new ModelAndView("AjaxDropDown");
        model.addObject("listAllItem",ims.getItemAllItemFromGroup(id));
        model.addObject("item",2);
        return model;
    }
     
    @RequestMapping(value={"AccountReports"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView accountReports()
    {
        ModelAndView model = new ModelAndView("AccountReports");
        model.addObject("accountGroupList",lgs.listAccountGroups());
        model.addObject("listAllLedger",lgs.listAllLedger());
        return model;
    }
    
    @RequestMapping(value={"getLedgerAccountGroups"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<LedgerAccountMaster> getLedgerAccountGroups(@RequestParam("id") String key, LedgerAccountMaster lam)
    {
        System.out.println("The ID id :"+key);
       return accountReportsService.getLedgerAccountByKey(key);
    }
    
    @RequestMapping(value={"GetEntryReports"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public ModelAndView getEntryReports(HttpServletRequest req)
    {
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String byLedger = req.getParameter("byLedger");
        String byAccountGroups = req.getParameter("byAccountGroups");
        String type=req.getParameter("reportByType");
        String query = null;
   
        
        
        ModelAndView model = new ModelAndView("AccountReports");
        model.addObject("byLedger_id", byLedger);
        model.addObject("byAccountGroups", byAccountGroups);
        model.addObject("gsFromDate", startDate);
        model.addObject("gsToDate", endDate);
        model.addObject("type", type);
    if(type.equalsIgnoreCase("Daily")){    
        if(byLedger!=null && byLedger.length()>0){
            
            query = "SELECT lam.nameOfLedger, et.date, et.billType, (CASE WHEN ei.type = 'D' THEN ei.amount ELSE 0 END) AS dr,(CASE WHEN ei.type = 'C' THEN ei.amount ELSE 0 END) AS cr , ei.type,ei.closingAmtDr,ei.closingAmtCr,et.billId FROM Entries et INNER JOIN Entryitems ei ON ei.entryId=et.id INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId WHERE lam.idLedger='"+byLedger+"' AND et.date BETWEEN '"+startDate+"' AND '"+endDate+"' order by ei.id";
            
            List<Object[]> currentBalance=lbs.SingleLedgerCurrentOpeningBalanceFromEntriesTable(String.valueOf(byLedger), startDate);

            double openingBalanceOfLedger=0;
            if( currentBalance!=null && currentBalance.size()>0)
            {
                  for (Object[] column : currentBalance) {
                             openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                     }

            }
            else
            {
                List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(byLedger));
                for (Object[] column : OpeningBalance) {
                             openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                     }
            }
            
            model.addObject("byDateAndLedger", accountReportsService.getReportByDateAndunderGroup(query));
            model.addObject("ledgerOpeningBalance", openingBalanceOfLedger);
            model.addObject("byLedger","byLedger");
        }
        else if(byAccountGroups!=null && byAccountGroups.length()>0){
         
            String inIds=itemGroupZReportService.returnChildids(byAccountGroups);
      
            String id="";
            if("".equals(inIds)) 
            {  
            id="'"+byAccountGroups+"'";
            }
            else
            {
             id="'"+byAccountGroups+"',"+inIds;   
            }    
                 query = "SELECT '',SUM(CASE WHEN ei.type = 'C' THEN ei.amount ELSE 0 END) AS cr,SUM(CASE WHEN ei.type = 'D' THEN ei.amount ELSE 0 END) AS dr ,e.date,agm.accountGroupName,agm.idAccount,lam.idLedger,lam.nameOfLedger,'' FROM Entryitems ei INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup AND lam.underGroup in ("+id+") and e.date BETWEEN '"+startDate+"' AND '"+endDate+"'  GROUP BY lam.underGroup, ei.ledgerId,e.date order by ei.ledgerId";
             String query2 = " SELECT ei.closingAmtDr,ei.closingAmtCr FROM  Entryitems AS ei INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup inner join   Entries e  ON ei.entryId=e.id  WHERE ei.id IN (\n" +
                "SELECT MAX(ei.id)  FROM Entries et INNER JOIN Entryitems ei ON ei.entryId=et.id INNER JOIN LedgerAccountMaster lam ON \n" +
                "lam.idLedger=ei.ledgerId WHERE lam.underGroup in ("+id+") \n" +
                "AND et.date BETWEEN '"+startDate+"' AND '"+endDate+"' GROUP BY lam.underGroup, ei.ledgerId, et.date" +
                ") GROUP BY lam.underGroup, ei.ledgerId,e.date order by ei.ledgerId";
             
          //  String ledger_accountbyGroup="SELECT ei.id,SUM(CASE WHEN ei.type = 'C' THEN ei.amount ELSE 0 END) AS cr,SUM(CASE WHEN ei.type = 'D' THEN ei.amount ELSE 0 END) AS dr ,DATE_FORMAT(e.date,'%M-%Y'),agm.accountGroupName,agm.idAccount,lam.idLedger,lam.nameOfLedger,e.entryType FROM Entryitems ei INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup AND lam.underGroup in ("+id+") and e.date BETWEEN '"+startDate+"' AND '"+endDate+"'  GROUP BY lam.underGroup, month(e.date), ei.ledgerId";
                     
            List<Object[]> currentBalance=lbs.AllsubGroupLedgerCurrentOpeningBalanceFromEntriesTable(id, startDate);

            double openingBalanceOfEntries=0;
            double openingBalanceOfLedger=0;
            double totalGroupBalance=0;
            if( currentBalance!=null && currentBalance.size()>0)
            {
                  for (Object[] column : currentBalance) {
                             openingBalanceOfEntries = (Double)column[0]- (Double)column[1];
                     }

            }
            List<Object[]> OpeningBalance=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(startDate,byAccountGroups);
            if( OpeningBalance!=null && OpeningBalance.size()>0)
            {
               
                for (Object[] column : OpeningBalance) {
                             openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                     }
            }   
            totalGroupBalance=openingBalanceOfEntries-openingBalanceOfLedger;
            
            
            List<Object> list1=accountReportsService.getReportByDateAndunderGroup(query);
            List<Object> list2=accountReportsService.getReportByDateAndunderGroup(query2);
            Object[] both={};
             Iterator    it1 = list1.iterator();
            Iterator it2 = list2.iterator();
            List<Object[]> finalGroupobj = new ArrayList<Object[]>();
           List<Object[]> a=null;
            while(it1.hasNext() && it2.hasNext()) 
            {
               Object[] v1= (Object[]) it1.next();
               Object[] v2= (Object[]) it2.next();
             
                both = ArrayUtils.addAll(v1, v2);
                finalGroupobj.add(both);
            }

            for (Object[] column : finalGroupobj) {
                System.out.println(column[0]+" "+column[9]);

                     }
            
            model.addObject("AccountGroupLedger", finalGroupobj);
            model.addObject("openingBalanceOfLedger", openingBalanceOfLedger );
             model.addObject("openingBalanceOfEntries", openingBalanceOfEntries);
            model.addObject("byGroup","byGroup");
        }
    }
    if(type.equalsIgnoreCase("Monthly")){
        if(byLedger!=null && byLedger.length()>0){
            
            
            query = "SELECT lam.nameOfLedger, MONTHNAME(et.date), '', sum(CASE WHEN ei.type = 'D' THEN ei.amount ELSE 0 END) AS dr,sum(CASE WHEN ei.type = 'C' THEN ei.amount ELSE 0 END) AS cr , '' FROM Entries et INNER JOIN Entryitems ei ON ei.entryId=et.id INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId WHERE lam.idLedger='"+byLedger+"' AND et.date BETWEEN '"+startDate+"' AND '"+endDate+"' group by MONTHNAME(et.date) ";
           String query2 = " SELECT ei.closingAmtDr,ei.closingAmtCr FROM  Entryitems AS ei WHERE ei.id IN (\n" +
"SELECT MAX(ei.id)  FROM Entries et INNER JOIN Entryitems ei ON ei.entryId=et.id INNER JOIN LedgerAccountMaster lam ON \n" +
"lam.idLedger=ei.ledgerId WHERE lam.idLedger='"+byLedger+"' \n" +
"AND et.date BETWEEN '"+startDate+"' AND '"+endDate+"' GROUP BY MONTH(et.date) " +
") ";
           
             List<Object[]> currentBalance=lbs.SingleLedgerCurrentOpeningBalanceFromEntriesTable(String.valueOf(byLedger), startDate);

            double openingBalanceOfLedger=0;
            if( currentBalance!=null && currentBalance.size()>0)
            {
                  for (Object[] column : currentBalance) {
                             openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                     }

            }
            else
            {
                List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(byLedger));
                for (Object[] column : OpeningBalance) {
                             openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                     }
            }
            
            List<Object> list1=accountReportsService.getReportByDateAndunderGroup(query);
            List<Object> list2=accountReportsService.getReportByDateAndunderGroup(query2);
            Object[] both={};
             Iterator    it1 = list1.iterator();
            Iterator it2 = list2.iterator();
            List<Object[]> obj = new ArrayList<Object[]>();
           List<Object[]> a=null;
            while(it1.hasNext() && it2.hasNext()) 
            {
               Object[] v1= (Object[]) it1.next();
               Object[] v2= (Object[]) it2.next();
             
                both = ArrayUtils.addAll(v1, v2);
                obj.add(both);
            }

            for (Object[] column : obj) {
                System.out.println(column[0]+" "+column[5]);

                     }
            
            model.addObject("byDateAndLedger", obj);
            model.addObject("ledgerOpeningBalance", openingBalanceOfLedger);
            model.addObject("byLedger","byLedger");
        }
        else if(byAccountGroups!=null && byAccountGroups.length()>0){
         

            String inIds=itemGroupZReportService.returnChildids(byAccountGroups);
      
            String id="";
            if("".equals(inIds)) 
            {  
            id="'"+byAccountGroups+"'";
            }
            else
            {
             id="'"+byAccountGroups+"',"+inIds;   
            }
            System.out.println(" id" +id);
             query = "SELECT '',SUM(CASE WHEN ei.type = 'C' THEN ei.amount ELSE 0 END) AS cr,SUM(CASE WHEN ei.type = 'D' THEN ei.amount ELSE 0 END) AS dr ,MONTHNAME(e.date),agm.accountGroupName,agm.idAccount,lam.idLedger,lam.nameOfLedger,'' FROM Entryitems ei INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup AND lam.underGroup in ("+id+") and e.date BETWEEN '"+startDate+"' AND '"+endDate+"'  GROUP BY lam.underGroup, MONTHNAME(e.date), ei.ledgerId order by ei.ledgerId  ";
             String query2 = " SELECT ei.closingAmtDr,ei.closingAmtCr FROM  Entryitems AS ei INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup inner join   Entries e  ON ei.entryId=e.id WHERE ei.id IN (\n" +
                "SELECT MAX(ei.id)  FROM Entries et INNER JOIN Entryitems ei ON ei.entryId=et.id INNER JOIN LedgerAccountMaster lam ON \n" +
                "lam.idLedger=ei.ledgerId WHERE lam.underGroup in ("+id+") \n" +
                "AND et.date BETWEEN '"+startDate+"' AND '"+endDate+"' GROUP BY lam.underGroup, month(et.date), ei.ledgerId" +
                ")GROUP BY lam.underGroup, MONTHNAME(e.date), ei.ledgerId order by ei.ledgerId  ";
             
          //  String ledger_accountbyGroup="SELECT ei.id,SUM(CASE WHEN ei.type = 'C' THEN ei.amount ELSE 0 END) AS cr,SUM(CASE WHEN ei.type = 'D' THEN ei.amount ELSE 0 END) AS dr ,DATE_FORMAT(e.date,'%M-%Y'),agm.accountGroupName,agm.idAccount,lam.idLedger,lam.nameOfLedger,e.entryType FROM Entryitems ei INNER JOIN Entries e ON e.id=ei.entryId INNER JOIN LedgerAccountMaster lam ON lam.idLedger=ei.ledgerId INNER JOIN AccountGroupMaster agm ON agm.idAccount=lam.underGroup AND lam.underGroup in ("+id+") and e.date BETWEEN '"+startDate+"' AND '"+endDate+"'  GROUP BY lam.underGroup, month(e.date), ei.ledgerId";
                     
            List<Object[]> currentBalance=lbs.AllsubGroupLedgerCurrentOpeningBalanceFromEntriesTable(id, startDate);

            double openingBalanceOfEntries=0;
            double openingBalanceOfLedger=0;
            double totalGroupBalance=0;
            if( currentBalance!=null && currentBalance.size()>0)
            {
                  for (Object[] column : currentBalance) {
                             openingBalanceOfEntries = (Double)column[0]- (Double)column[1];
                     }

            }
            // send single group id only 
            List<Object[]> OpeningBalance=lbs.AllsubGroupLedgerOpeningBalanceFromLedgerTable(startDate,byAccountGroups);
            if( OpeningBalance!=null && OpeningBalance.size()>0)
            {
               
                for (Object[] column : OpeningBalance) {
                             openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                     }
            }   
            totalGroupBalance=openingBalanceOfEntries+openingBalanceOfLedger;
            
            
            List<Object> list1=accountReportsService.getReportByDateAndunderGroup(query);
            List<Object> list2=accountReportsService.getReportByDateAndunderGroup(query2);
            Object[] both={};
             Iterator    it1 = list1.iterator();
            Iterator it2 = list2.iterator();
            List<Object[]> finalGroupobj = new ArrayList<Object[]>();
           List<Object[]> a=null;
            while(it1.hasNext() && it2.hasNext()) 
            {
               Object[] v1= (Object[]) it1.next();
               Object[] v2= (Object[]) it2.next();
             
                both = ArrayUtils.addAll(v1, v2);
                finalGroupobj.add(both);
            }

            for (Object[] column : finalGroupobj) {
                System.out.println(column[0]+" "+column[9]);

                     }
           
            model.addObject("AccountGroupLedger", finalGroupobj);
            model.addObject("openingBalanceOfLedger", openingBalanceOfLedger );
             model.addObject("openingBalanceOfEntries", openingBalanceOfEntries);
            
          
            model.addObject("byGroup","byGroup");
        }
    }

        
        
           model.addObject("accountGroupList",lgs.listAccountGroups());
           model.addObject("listAllLedger",lgs.listAllLedger());
        return model;
    }
    
    @RequestMapping(value={"s"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public List<Object> s(){
       return accountReportsService.getReportByDateAndunderGroup("");
    }
   @RequestMapping(value={"trailBalance"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView trailBalance()
  {
      
    ModelAndView model = new ModelAndView("trailBalance");  
    return model;
  }
    @RequestMapping(value={"TrialBalance"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView TrialBalance()
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
       
       
    ModelAndView model = new ModelAndView("TrialBalance");  
    model.addObject("curDate", dt);
    model.addObject("finalDate", finalDate);
    return model;
  }
  @RequestMapping(value={"GetTrialBalance.html"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView Get_Stock_Summary( HttpServletRequest req) throws ParseException
  {
    
    ModelAndView model = new ModelAndView("trailBalance");    
   
   
    String gsFromDate = req.getParameter("startDate");
    String gsToDate = req.getParameter("endDate");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date convertedCurrentDate = sdf.parse(gsFromDate);
    String fdate=dmyFormat.format(convertedCurrentDate );//    System.out.println(fdate);
    
    Date convertedEndDate = sdf.parse(gsToDate);
    String Edate=dmyFormat.format(convertedEndDate ); 
    model.addObject("gsFromDate", gsFromDate);
    model.addObject("gsToDate", gsToDate);   
     List<Object[]> tbalance=accountReportsService.trailBalance(0,fdate,Edate);
    model.addObject("tb",tbalance);   
    return model;
  }
}
