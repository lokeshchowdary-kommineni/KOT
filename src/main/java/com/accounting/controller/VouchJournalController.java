/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;

import com.accounting.bean.DatatableObject;
import com.accounting.bean.Entries;
import com.accounting.bean.Entryitems;
import com.accounting.bean.VoucherContra;
import com.accounting.bean.VoucherJournal;
import com.accounting.service.LedgerAccount_Service;
import com.accounting.service.LedgerBalanceService;
import com.accounting.service.VoucherJournalService;
import com.accounting.service.VoucherReceiptAndPaymentService;
import com.accounting.validator.VoucherJournalValidator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author MR
 */
@Controller
public class VouchJournalController {
    
    @Autowired
    private VoucherJournalService voucherJournalService;
    
    @Autowired
    private VoucherReceiptAndPaymentService voucherService;
    
    @Autowired
    private VoucherJournalValidator voucherJournalValidator;
    
    @Autowired
    private LedgerBalanceService lbs;
    
      @Autowired
    private LedgerAccount_Service ledgerAccountService;
    
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
            binder.setValidator(voucherJournalValidator);
    }
    
     VoucherJournal vouchJournal=new VoucherJournal();
    
    @RequestMapping(value={"VoucherJournal"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView voucherJournal()
    {
         VoucherJournal voucherJournal = new VoucherJournal();

       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
       Date date = new Date();
       
       voucherJournal.setVouchDate(date);
      ModelAndView model = new ModelAndView("VoucherJournal");
      model.addObject("vouchJournalForm", voucherJournal);
      model.addObject("vouchLedgerList", voucherService.listAllLedgerNames());
      model.addObject("vouchLedgerListExceptCashBankAcc", voucherJournalService.listOfVoucherJournalExceptCashBankAcc());
      return model;
    } 
    
    @RequestMapping(value={"SaveVoucherJournal"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String saveVoucherJournal(@ModelAttribute ("vouchJournalForm") @Validated VoucherJournal vouchJournal, 
            BindingResult result, RedirectAttributes redirect,Model model)
    {
                
        Integer id = vouchJournal.getVouchId();
        
        if(result.hasErrors()){
            model.addAttribute("vouchLedgerList", voucherService.listAllLedgerNames());
            model.addAttribute("vouchLedgerListExceptCashBankAcc", voucherJournalService.listOfVoucherJournalExceptCashBankAcc());
            return "VoucherJournal";
        }else
        { 
       SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
       Date date = new Date();        
       String CurrentDate= dmyFormat.format(date);
       
       List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(vouchJournal.getVouchFrom()), CurrentDate);
      
       double openingBalanceOfLedger=0;
       if( currentBalance!=null && currentBalance.size()>0)
       {
             for (Object[] column : currentBalance) {
                        openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(vouchJournal.getVouchFrom()));
           for (Object[] column : OpeningBalance) {
                        openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                }
       }   
               
       
      
       List<Object[]> currentBalancee=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(vouchJournal.getVouchTo()), CurrentDate);
      
       double openingBalanceOfLedgerr=0;
       if( currentBalancee!=null && currentBalancee.size()>0)
       {
             for (Object[] column : currentBalancee) {
                        openingBalanceOfLedgerr = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalancee=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(vouchJournal.getVouchTo()));
           for (Object[] column : OpeningBalancee) {
                        openingBalanceOfLedgerr = (Double)column[0]- (Double)column[1];
                }
       } 
            
        
        String typeFrom="";
        String typeTo="";
        Double currentBuyerBalance=0.0;
        Double currentBuyerBalancee=0.0;
        // to add entry items
        
        
        if("CR".equals(vouchJournal.getMode()))
        {
         typeFrom="C";   
         currentBuyerBalance=openingBalanceOfLedger-vouchJournal.getVouchAmount();
        }
        else
        {
         typeFrom="D";   
         currentBuyerBalance=openingBalanceOfLedger+vouchJournal.getVouchAmount();  
        }
        
         System.out.print("vouchJournal.getModeOn() :"+vouchJournal.getModeOn());
        if("CR".equals(vouchJournal.getModeOn()))
        {
           
         typeTo="C";   
         currentBuyerBalancee=openingBalanceOfLedgerr-vouchJournal.getVouchAmount();
        }
        else
        {
         typeTo="D";   
        currentBuyerBalancee=openingBalanceOfLedgerr+vouchJournal.getVouchAmount();
        } 
        
         if(currentBuyerBalance>=0)
             {
             vouchJournal.setVouchBalFrom(Math.abs(currentBuyerBalance));
             vouchJournal.setVouchCashType("DR");
             }
             else
             {
             vouchJournal.setVouchBalFrom(Math.abs(currentBuyerBalance));
             vouchJournal.setVouchCashType("CR");
             }
         if(currentBuyerBalancee>=0)
             {
             vouchJournal.setVouchBalTo(Math.abs(currentBuyerBalancee));
             vouchJournal.setVouchCheqType("DR");
             }
             else
             {
             vouchJournal.setVouchBalTo(Math.abs(currentBuyerBalancee));
             vouchJournal.setVouchCheqType("CR");
             }
        
        int v_id = voucherJournalService.saveVoucherJournal(vouchJournal);
        int et_id=0;
        // to add entries
        Entries et = new Entries();
        et.setBillId((String.valueOf(v_id)));
        et.setBillType("Journal");
        et.setCrTotal(vouchJournal.getVouchAmount());
        et.setDrTotal(vouchJournal.getVouchAmount());
        et.setEntryType("J");
        et.setNarration(vouchJournal.getVouchNarration());
        et.setDate(vouchJournal.getVouchDate());
        et_id = voucherService.saveVoucherToEntries(et);
        
        Entryitems ei1 =  new Entryitems();
        ei1.setAmount(vouchJournal.getVouchAmount());
        ei1.setEntryId(et_id);
        ei1.setLedgerId(vouchJournal.getVouchFrom());
         if(currentBuyerBalance>0)
        ei1.setClosingAmtDr(currentBuyerBalance);
        else
        ei1.setClosingAmtCr( Math.abs(currentBuyerBalance));
        ei1.setType(typeFrom);
        voucherService.saveVoucherToEntryItems(ei1);      
//        Double currentBuyerBalancee=BalT;
        Entryitems ei2 =  new Entryitems();
        ei2.setAmount(vouchJournal.getVouchAmount());
        ei2.setEntryId(et_id);
        ei2.setLedgerId(vouchJournal.getVouchTo());
        if(currentBuyerBalancee>0)
       ei2.setClosingAmtDr(currentBuyerBalancee);
       else
       ei2.setClosingAmtCr( Math.abs(currentBuyerBalancee));
        ei2.setType(typeTo);    
        voucherService.saveVoucherToEntryItems(ei2);
    
        
            return "redirect:VoucherJournalGrid.html";         
        }
    }
    
    @RequestMapping(value={"VoucherJournalGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView voucherJournalGrid()
    {
        ModelAndView model = new ModelAndView("VoucherJournalGrid");
        model.addObject("vouchJournalList", voucherJournalService.listAllVoucherJournal());
        return model;
    }
    
    @RequestMapping(value={"VoucherJournalEdit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView voucherJournalEdit(@RequestParam("id") int pId)
    {
        VoucherJournal list= voucherJournalService.getVoucherJournalByID(pId);
        ModelAndView model = new ModelAndView("VoucherJournal");
        model.addObject("vouchJournalForm", voucherJournalService.getVoucherJournalByID(pId));
        model.addObject("vouchLedgerList", voucherService.listAllLedgerNames());
        model.addObject("vouchLedgerListExceptCashBankAcc", voucherJournalService.listOfVoucherJournalExceptCashBankAcc());
        
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();        
        String CurrentDate= dmyFormat.format(date);
        
         int suplierLedgerF=ledgerAccountService.getLederByReferenceIdVoucher(String.valueOf(list.getVouchFrom())).iterator().next().getIdLedger();         
         List<Object[]> currentBalanceF=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(suplierLedgerF), CurrentDate);
         double openingBalanceOfLedgerFROM=0;
       if( currentBalanceF!=null && currentBalanceF.size()>0)
       {
             for (Object[] column : currentBalanceF) {
                        openingBalanceOfLedgerFROM = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(suplierLedgerF));
           for (Object[] column : OpeningBalance) {
                        openingBalanceOfLedgerFROM = (Double)column[0]- (Double)column[1];
                }
       } 

       if(openingBalanceOfLedgerFROM>=0)
        {
          String AFROM="DR";
           Double currentFromBalance=openingBalanceOfLedgerFROM;
           model.addObject("currentFromBalance",Math.abs(currentFromBalance));
           model.addObject("AFROM",AFROM);
        }
          else
        {
           String AFROM="CR";
           Double currentFromBalance=openingBalanceOfLedgerFROM;
           model.addObject("currentFromBalance",Math.abs(currentFromBalance));
           model.addObject("AFROM",AFROM);
        }

           int suplierLedgerC=ledgerAccountService.getLederByReferenceIdVoucher(String.valueOf(list.getVouchTo())).iterator().next().getIdLedger();         
         List<Object[]> currentBalanceC=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(suplierLedgerC), CurrentDate);
         double openingBalanceOfLedgerC=0;
       if( currentBalanceC!=null && currentBalanceC.size()>0)
       {
             for (Object[] column : currentBalanceC) {
                        openingBalanceOfLedgerC = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(suplierLedgerC));
           for (Object[] column : OpeningBalance) {
                        openingBalanceOfLedgerC = (Double)column[0]- (Double)column[1];
                }
       } 

       if(openingBalanceOfLedgerC>=0)
        {
           String ATO="DR";
           Double currentCBalance=openingBalanceOfLedgerC;
           model.addObject("currentTOmBalance",Math.abs(currentCBalance));
           model.addObject("ATO",ATO);
        }
          else
        {
           String ATO="CR";
           Double currentCBalance=openingBalanceOfLedgerC;
           model.addObject("currentTOmBalance",Math.abs(currentCBalance));
           model.addObject("ATO",ATO);
        }   
      
        return model;
    }
    
    @RequestMapping(value={"VoucherJournalDelete"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView voucherJournalDelete(@RequestParam("id") String id, RedirectAttributes redirect)
    {
       
       ModelAndView model = new ModelAndView("redirect:VoucherJournalGrid.html");
       if(id!=null && id.length()>0){
           voucherJournalService.deleteVoucherJournalById(Integer.parseInt(id));
           return model;
       }
       else{
           return model;
       }
    }
    @ResponseBody
    @RequestMapping(value={"GetDatatableJournal"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
    public DatatableObject getDatatableContra( HttpServletRequest req)
    {
      DatatableObject db =new DatatableObject();
           String[] cols = { "vouch_id", "vouch_cash_date", "vouch_amount", "vouch_type","vouch_from","vouch_bal_from","vouch_to","vouch_bal_to" };
    String table = "voucher_contra";
    
//    JSONObject result = new JSONObject();
//    JSONArray array = new JSONArray();
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
        String sql = "SELECT journal1.*,journal2.to_ledger,journal2.vouch_bal_to,journal2.dt  FROM (select  vj.vouch_id ,vj.vouch_date ,vj.vouch_amount  ,la.name_of_ledger ,vj.vouch_bal_from  from voucher_journal as vj inner join ledger_account_master as la on vj.vouch_from =la.id_ledger ) as journal1 inner join (select  vj.vouch_id ,la.name_of_ledger as to_ledger,vj.vouch_bal_to,vj.vouch_date as dt  from voucher_journal as vj inner join ledger_account_master as la  on vj.vouch_to  =la.id_ledger )  as journal2 on journal1.vouch_id =journal2.vouch_id";
        
        
        List<Object[]> list = voucherJournalService.GetDatatableJournalObject(sql);
        total=list.size();
       
        
    }catch(Exception e){
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "SELECT journal1.*,journal2.to_ledger,journal2.vouch_bal_to,journal2.dt  FROM (select  vj.vouch_id ,vj.vouch_date ,vj.vouch_amount  ,la.name_of_ledger ,vj.vouch_bal_from  from voucher_journal as vj inner join ledger_account_master as la on vj.vouch_from =la.id_ledger ) as journal1 inner join (select  vj.vouch_id ,la.name_of_ledger as to_ledger,vj.vouch_bal_to,vj.vouch_date as dt  from voucher_journal as vj inner join ledger_account_master as la  on vj.vouch_to  =la.id_ledger )  as journal2 on journal1.vouch_id =journal2.vouch_id";
        String searchTerm =req.getParameter("search[value]");
        
         String globeSearch =  " where (journal1.vouch_id like '"+searchTerm+"%')";
//        if(searchTerm!=""&&individualSearch!=""){
//            searchSQL = globeSearch + " and " + individualSearch;
//        }
//        else if(individualSearch!=""){
//            searchSQL = " where " + individualSearch;
//        }
        if(searchTerm!=""){
            searchSQL=globeSearch;
        }
        sql += searchSQL;
        sql += " order by " + colName + " " + dir;
        sql += " limit " + start + ", " + amount;
       
        // For aData
         List<Object[]> list2 = voucherJournalService.GetDatatableJournalObject(sql);
         // For Filter Count 
        String sql2 = "SELECT journal1.*,journal2.to_ledger,journal2.vouch_bal_to FROM (select  vj.vouch_id ,vj.vouch_date ,vj.vouch_amount  ,la.name_of_ledger ,vj.vouch_bal_from  from voucher_journal as vj inner join ledger_account_master as la on vj.vouch_from =la.id_ledger ) as journal1 inner join (select  vj.vouch_id ,la.name_of_ledger as to_ledger,vj.vouch_bal_to  from voucher_journal as vj inner join ledger_account_master as la  on vj.vouch_to  =la.id_ledger )  as journal2 on journal1.vouch_id =journal2.vouch_id";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = voucherJournalService.GetDatatableJournalCount(sql2);
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
