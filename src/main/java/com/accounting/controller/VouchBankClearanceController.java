/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;

import com.accounting.bean.DatatableObject;
import com.accounting.bean.Entries;
import com.accounting.bean.Entryitems;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.VoucherContra;
import com.accounting.bean.VoucherReceiptAndPayment;
import com.accounting.service.LedgerAccount_Service;
import com.accounting.service.LedgerBalanceService;
import com.accounting.service.VoucherBankClearanceService;
import com.accounting.service.VoucherReceiptAndPaymentService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
   
/**
 *
 * @author MR
 */
@Controller
public class VouchBankClearanceController {
    
    @Autowired
    private VoucherBankClearanceService voucherBankClearanceService;
    
     @Autowired
    private VoucherReceiptAndPaymentService voucherService;
     
      @Autowired
    private LedgerBalanceService lbs;
    
      @Autowired
    private LedgerAccount_Service ledgerAccountService;
     
     VoucherReceiptAndPayment voucherPR = new VoucherReceiptAndPayment(); 
    
    @RequestMapping(value={"VoucherBankClearance"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView voucherBankClearance()
    {
      ModelAndView model = new ModelAndView("VoucherBankClearance");
      model.addObject("vouchAllBankList", voucherBankClearanceService.getAllBankss());
      return model;
    }
    
    @RequestMapping(value="/VoucherGetList", method = RequestMethod.POST, produces = "application/json")
    public ModelAndView showUsers(HttpServletRequest req){
        ModelAndView model = new ModelAndView("VoucherBankClearance");
        String vouchType = "";
        if(!req.getParameter("typeReceipt").isEmpty()){
            vouchType = req.getParameter("typeReceipt");
        }
        else if(!req.getParameter("typePayment").isEmpty()){
            vouchType = req.getParameter("typePayment");
        }
        
        
        model.addObject("getVoucherList", voucherBankClearanceService.getVouchersByBank(req.getParameter("id"), vouchType));
        model.addObject("vouchAllBankList", voucherBankClearanceService.getAllBankss());
        return model;    
    }
    
    @RequestMapping(value={"authoriseBankClearance"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView voucherAuthoriseBankClearance(HttpServletRequest req)
    {
       
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();        
        String CurrentDate= dmyFormat.format(date);
        // SINGLE AUTHORISATION
            if(req.getParameter("id") != null){     
                 System.out.println(" test value "+ req.getParameter("id"));
              voucherBankClearanceService.updateBankClearanceById(Integer.parseInt(req.getParameter("id")));
              
               String v_id=(req.getParameter("id"));
         
          VoucherReceiptAndPayment list=voucherService.getVoucherById(Integer.parseInt(req.getParameter("id")));
      
       List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(list.getVouchFrom()), CurrentDate);
      
       double openingBalanceOfLedger=0;
       if( currentBalance!=null && currentBalance.size()>0)
       {
             for (Object[] column : currentBalance) {
                        openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(list.getVouchFrom()));
           for (Object[] column : OpeningBalance) {
                        openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                }
       } 
       List<Object[]> currentBalancee=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(list.getVouchdepositTobank()), CurrentDate);
      
       double openingBalanceOfLedgerr=0;
       if( currentBalancee!=null && currentBalancee.size()>0)
       {
             for (Object[] column : currentBalancee) {
                        openingBalanceOfLedgerr = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalancee=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(list.getVouchdepositTobank()));
           for (Object[] column : OpeningBalancee) {
                        openingBalanceOfLedgerr = (Double)column[0]- (Double)column[1];
                }
       } 
          
         int et_id=0;
         
         Entries et = new Entries();
         et.setBillId(v_id);
         et.setBillType(list.getVouchMode());               
         et.setCrTotal(list.getVouchAmount());
         et.setDrTotal(list.getVouchAmount());
         if(list.getVouchType()=="Receipt"){
             et.setEntryType("R");
         }else{
             et.setEntryType("P");
         }         
         et.setNarration(list.getVouchNarration());
         et.setDate(list.getVouchCheqDate());
       
         et_id = voucherService.saveVoucherToEntries(et);
         
          if("receipt".equalsIgnoreCase(list.getVouchType())){
                 
               // from Entry   
                Double currentfromBal=openingBalanceOfLedger-list.getVouchAmount();
                Entryitems ei1 =  new Entryitems();
                ei1.setAmount(list.getVouchAmount());
                ei1.setEntryId(et_id);
                ei1.setLedgerId(list.getVouchFrom());
                if(currentfromBal>=0)
                ei1.setClosingAmtDr(currentfromBal);
                else
                ei1.setClosingAmtCr( Math.abs(currentfromBal));
                ei1.setType("C");
                voucherService.saveVoucherToEntryItems(ei1); 
                
//                to entry
                Double currentfromto=openingBalanceOfLedgerr+list.getVouchAmount();    
                Entryitems e2 =  new Entryitems();
                e2.setAmount(list.getVouchAmount());
                e2.setEntryId(et_id);
                    if(list.getVouchTo()!=0){
                        e2.setLedgerId((list.getVouchTo()));
                    }
                    if(list.getVouchdepositTobank()!=0){
                        e2.setLedgerId(list.getVouchdepositTobank());
                    }
                if(currentfromto>=0)
                e2.setClosingAmtDr(currentfromto);
                else
                e2.setClosingAmtCr( Math.abs(currentfromto));
                e2.setType("D");
                voucherService.saveVoucherToEntryItems(e2); 
                        
         }
         else{                 
                Double currentfromBal=openingBalanceOfLedger+list.getVouchAmount();
                Entryitems ei1 =  new Entryitems();
                ei1.setAmount(list.getVouchAmount());
                ei1.setEntryId(et_id);
                ei1.setLedgerId(list.getVouchFrom());
                if(currentfromBal>0)
                ei1.setClosingAmtDr(currentfromBal);
                else
                ei1.setClosingAmtCr( Math.abs(currentfromBal));
                ei1.setType("D");
                voucherService.saveVoucherToEntryItems(ei1);

        //cash ledger closing balance(Recipt)
               Double currentfromto=openingBalanceOfLedgerr-list.getVouchAmount();    
                Entryitems e2 =  new Entryitems();
                e2.setAmount(list.getVouchAmount());
                e2.setEntryId(et_id);
                    if(list.getVouchTo()!=0){
                        e2.setLedgerId((list.getVouchTo()));
                    }
                    if(list.getVouchdepositTobank()!=0){
                        e2.setLedgerId(list.getVouchdepositTobank());
                    }
                if(currentfromto>=0)
                e2.setClosingAmtDr(currentfromto);
                else
                e2.setClosingAmtCr( Math.abs(currentfromto));
                e2.setType("C");
                voucherService.saveVoucherToEntryItems(e2);  
              }

            }
            
        // MULTIPLE AUTHORISATION
          String id_checked[] = req.getParameterValues("authValues");
         System.out.println(" test value "+ id_checked.length);
            if(req.getParameterValues("authValues") != null){
                System.out.println(" test value "+ req.getParameterValues("authValues"));
             //   String id_checked[] = req.getParameterValues("authValues");
                int selectCount = id_checked.length;
                for(int i=0; i < selectCount; i++)
                {
                voucherBankClearanceService.updateBankClearanceById(Integer.parseInt(id_checked[i]));                  
                 
              VoucherReceiptAndPayment list=voucherService.getVoucherById(Integer.parseInt(id_checked[i]));
              int et_id=0;
            
              List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(list.getVouchFrom()), CurrentDate);
      
                double openingBalanceOfLedger=0;
                if( currentBalance!=null && currentBalance.size()>0)
                {
                      for (Object[] column : currentBalance) {
                                 openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                         }

                }
                else
                {
                    List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(list.getVouchFrom()));
                    for (Object[] column : OpeningBalance) {
                                 openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                         }
                } 
                List<Object[]> currentBalancee=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(list.getVouchdepositTobank()), CurrentDate);

                double openingBalanceOfLedgerr=0;
                if( currentBalancee!=null && currentBalancee.size()>0)
                {
                      for (Object[] column : currentBalancee) {
                                 openingBalanceOfLedgerr = (Double)column[0]- (Double)column[1];
                         }

                }
                else
                {
                    List<Object[]> OpeningBalancee=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(list.getVouchdepositTobank()));
                    for (Object[] column : OpeningBalancee) {
                                 openingBalanceOfLedgerr = (Double)column[0]- (Double)column[1];
                         }
                } 

                  Entries et = new Entries();
                  et.setBillId(id_checked[i]);
                  et.setBillType(list.getVouchMode());               
                  et.setCrTotal(list.getVouchAmount());
                  et.setDrTotal(list.getVouchAmount());
                  if(list.getVouchType()=="Receipt"){
                      et.setEntryType("R");
                  }else{
                      et.setEntryType("P");
                  }         
                  et.setNarration(list.getVouchNarration());
                  et.setDate(list.getVouchCheqDate());

                  et_id = voucherService.saveVoucherToEntries(et);

                   if("receipt".equalsIgnoreCase(list.getVouchType())){

                        // from Entry   
                         Double currentfromBal=openingBalanceOfLedger-list.getVouchAmount();
                         Entryitems ei1 =  new Entryitems();
                         ei1.setAmount(list.getVouchAmount());
                         ei1.setEntryId(et_id);
                         ei1.setLedgerId(list.getVouchFrom());
                         if(currentfromBal>=0)
                         ei1.setClosingAmtDr(currentfromBal);
                         else
                         ei1.setClosingAmtCr( Math.abs(currentfromBal));
                         ei1.setType("C");
                         voucherService.saveVoucherToEntryItems(ei1); 

         //                to entry
                         Double currentfromto=openingBalanceOfLedgerr+list.getVouchAmount();    
                         Entryitems e2 =  new Entryitems();
                         e2.setAmount(list.getVouchAmount());
                         e2.setEntryId(et_id);
                             if(list.getVouchTo()!=0){
                                 e2.setLedgerId((list.getVouchTo()));
                             }
                             if(list.getVouchdepositTobank()!=0){
                                 e2.setLedgerId(list.getVouchdepositTobank());
                             }
                         if(currentfromto>=0)
                         e2.setClosingAmtDr(currentfromto);
                         else
                         e2.setClosingAmtCr( Math.abs(currentfromto));
                         e2.setType("D");
                         voucherService.saveVoucherToEntryItems(e2); 

                  }
                  else{                 
                         Double currentfromBal=openingBalanceOfLedger+list.getVouchAmount();
                         Entryitems ei1 =  new Entryitems();
                         ei1.setAmount(list.getVouchAmount());
                         ei1.setEntryId(et_id);
                         ei1.setLedgerId(list.getVouchFrom());
                         if(currentfromBal>0)
                         ei1.setClosingAmtDr(currentfromBal);
                         else
                         ei1.setClosingAmtCr( Math.abs(currentfromBal));
                         ei1.setType("D");
                         voucherService.saveVoucherToEntryItems(ei1);

                 //cash ledger closing balance(Recipt)
                        Double currentfromto=openingBalanceOfLedgerr-list.getVouchAmount();    
                         Entryitems e2 =  new Entryitems();
                         e2.setAmount(list.getVouchAmount());
                         e2.setEntryId(et_id);
                             if(list.getVouchTo()!=0){
                                 e2.setLedgerId((list.getVouchTo()));
                             }
                             if(list.getVouchdepositTobank()!=0){
                                 e2.setLedgerId(list.getVouchdepositTobank());
                             }
                         if(currentfromto>=0)
                         e2.setClosingAmtDr(currentfromto);
                         else
                         e2.setClosingAmtCr( Math.abs(currentfromto));
                         e2.setType("C");
                         voucherService.saveVoucherToEntryItems(e2);  
                       }
                  
            }
            }
            
        ModelAndView model = new ModelAndView("redirect:VoucherBankClearance.html");
        model.addObject("vouchAllBankList", voucherBankClearanceService.getAllBankss());
        return model;
    }
    
    
    @RequestMapping(value={"GetDatatableBankClear"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
    public DatatableObject GetDatatableBankClear( HttpServletRequest req)
    {
      DatatableObject db =new DatatableObject();
           String[] cols = {"vouch_cheq_no", "date", "name_of_supplier", "tin_no","mode", "category", "total_amount"};

    int amount = 10;
    int start = 0;
    int echo = 0;
    int col = 0;
    
     
    String engine = "";
    String browser = "";
    String platform = "";
    String version = "";
    String grade = "";
 
    String dir = "asc";
    String sStart = req.getParameter("start");
    String sAmount = req.getParameter("length");
    String sEcho = req.getParameter("sEcho");
    String sCol = req.getParameter("order[0][column]");
    String sdir = req.getParameter("order[0][dir]");

    String individualSearch = "";

    if (sStart != null) {
        start = Integer.parseInt(sStart);
        if (start < 0)
            start = 0;
    }
    if (sAmount != null) {
        amount = Integer.parseInt(sAmount);
        if (amount < 10 || amount > 100)
            amount = 10;
    }
    if (sEcho != null) {
        echo = Integer.parseInt(sEcho);
    }
    if (sCol != null) {
        col = Integer.parseInt(sCol);
        if (col < 0 || col > 6)
            col = 0;
    }
    if (sdir != null) {
        if (!sdir.equals("asc"))
            dir = "desc";
    }
    String colName = cols[col];
    int total = 0;
   
    try {
        String sql = "";
        List<Object[]> list = voucherBankClearanceService.GetDatatableObject(sql);
        total=list.size();

    }catch(Exception e){
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "select vouch_type ,vouch_id,vouch_cash_date ,vouch_to ,vouch_cheq_no,vouch_cheq_date FROM VoucherReceiptAndPayment AS b WHERE authorisation='0' ORDER BY vouch_id";
        String sql = "";
        String searchTerm =req.getParameter("search[value]");
         String globeSearch =  " where (vouch_cheq_no like '"+searchTerm+"%')";
        if(searchTerm!=""&&individualSearch!=""){
            searchSQL = globeSearch + " and " + individualSearch;
        }
        else if(individualSearch!=""){
            searchSQL = " where " + individualSearch;
        }else if(searchTerm!=""){
            searchSQL=globeSearch;
        }
        sql += searchSQL;
        sql += " order by " + colName + " " + dir;
        sql += " limit " + start + ", " + amount;
        // For aData
         List<Object[]> list2 = voucherBankClearanceService.GetDatatableObject(sql);
         // For Filter Count 
        String sql2 = "select vouch_type ,vouch_id,vouch_cash_date ,vouch_to ,vouch_cheq_no,vouch_cheq_date FROM VoucherReceiptAndPayment AS b WHERE authorisation='0' ORDER BY vouch_id";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = voucherBankClearanceService.GetDatatableCount(sql2);
          totalAfterFilter=count.size();
        }
  
   db.setiTotalRecords(total);
   db.setiTotalDisplayRecords(totalAfterFilter);
   db.setAaData(list2);
    } catch (Exception e) {
 
    }
      return db;
	}  

    
}
