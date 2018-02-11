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
import com.accounting.service.LedgerAccount_Service;
import com.accounting.service.LedgerBalanceService;
import com.accounting.service.VoucherContraService;
import com.accounting.service.VoucherJournalService;
import com.accounting.service.VoucherReceiptAndPaymentService;
import com.accounting.validator.VoucherContraValidator;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
public class VouchContraController {
    
    @Autowired
    private VoucherContraService voucherContraService;
    
    @Autowired
    private VoucherJournalService voucherJournalService;
    
    @Autowired
    private VoucherContraValidator coucherContraValidator;
    
    @Autowired
    private VoucherReceiptAndPaymentService voucherService;
    
     @Autowired
    private LedgerBalanceService lbs;
    
     
         @Autowired
    private LedgerAccount_Service ledgerAccountService;
         
    @InitBinder({"vouchContraForm"})
    protected void initBinder(WebDataBinder binder) {
            binder.setValidator(coucherContraValidator);
    }
    
    VoucherContra vouchContra=new VoucherContra();
    
    @RequestMapping(value={"VoucherContra"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView voucherContra()
    {
         VoucherContra voucherContra = new VoucherContra();
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
       Date date = new Date();
       
       System.out.println("current date newly"+dateFormat.format(date)); 
       voucherContra.setVouchCashDate(date);

      ModelAndView model = new ModelAndView("VoucherContra");
      model.addObject("vouchContraForm", voucherContra);
      model.addObject("vouchContraList", voucherContraService.listAllVoucherContra());
      model.addObject("vouchLedgerBank", voucherJournalService.listCashLedgersWithBank());
      model.addObject("vouchLedgerCIH", voucherJournalService.listCashLedgersWithCIH());
      model.addObject("TypeCash", voucherJournalService.listCashLedgersWithCIH());
      model.addObject("vouchLedgerBankList", voucherJournalService.listCashLedgersWithBankList());
      model.addObject("TypeBank", voucherJournalService.listCashLedgersWithBankList());
      return model;
    }
    
    
    @RequestMapping(value={"SaveVoucherContra"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String saveVoucherContra(@ModelAttribute ("vouchContraForm") @Validated VoucherContra vouchContra, 
            BindingResult result, RedirectAttributes redirect, Model model,HttpServletRequest req)
    {
        Integer from=Integer.parseInt(req.getParameter("fromLedger"));
        Integer to=Integer.parseInt(req.getParameter("toLedger"));
        String chequeNO=req.getParameter("chequeNo");
        vouchContra.setVouchFrom(from);
        vouchContra.setVouchTo(to);
        vouchContra.setVouchCheqNo(chequeNO);
        Integer id = vouchContra.getVouchId();
        if(result.hasErrors()){
            model.addAttribute("vouchLedgerBank", voucherJournalService.listCashLedgersWithBank());
            return "VoucherContra";
        }
        else{ 
            Double vocAmt=vouchContra.getVouchAmount();
            double balanceFrom=0.0;
            double balanceto=0.0;
          
            SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();        
            String CurrentDate= dmyFormat.format(date);
    
       int buyerLedgerId=ledgerAccountService.getLederVoucherByReferenceId(String.valueOf(vouchContra.getVouchFrom())).iterator().next().getIdLedger();
       List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(buyerLedgerId), CurrentDate);
    
       double openingBalanceOfLedger=0;
       if( currentBalance!=null && currentBalance.size()>0)
       {
             for (Object[] column : currentBalance) {
                        openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(buyerLedgerId));
           for (Object[] column : OpeningBalance) {
                        openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                }
       }   
               
       
       int buyerLedgerIdd=ledgerAccountService.getLederVoucherByReferenceId(String.valueOf(vouchContra.getVouchTo())).iterator().next().getIdLedger();
       List<Object[]> currentBalancee=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(buyerLedgerIdd), CurrentDate);
      
       double openingBalanceOfLedgerr=0;
       if( currentBalancee!=null && currentBalancee.size()>0)
       {
             for (Object[] column : currentBalancee) {
                        openingBalanceOfLedgerr = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalancee=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(buyerLedgerIdd));
           for (Object[] column : OpeningBalancee) {
                        openingBalanceOfLedgerr = (Double)column[0]- (Double)column[1];
                }
       } 
        balanceFrom=openingBalanceOfLedger-vocAmt;
        balanceto=openingBalanceOfLedgerr+vocAmt;       
            if(balanceFrom>0)
             {
             vouchContra.setVouchBalFrom(Math.abs(balanceFrom));
             vouchContra.setVouchCashType("DR");
             }
             else
             {
             vouchContra.setVouchBalFrom(Math.abs(balanceFrom));
             vouchContra.setVouchCashType("CR");
             }
             
              if(balanceto>0)
             {
             vouchContra.setVouchBalTo(Math.abs(balanceto));
             vouchContra.setVouchCheqType("DR");
             }
             else
             {
             vouchContra.setVouchBalTo(Math.abs(balanceto));
             vouchContra.setVouchCheqType("CR");
             }
           
            int c_id = voucherContraService.saveVoucherContra(vouchContra);
            int et_id=0;   
            Entries et = new Entries();
            et.setBillId(String.valueOf(c_id));
            et.setBillType(vouchContra.getVouchType());
            et.setCrTotal(vouchContra.getVouchAmount());
            et.setDrTotal(vouchContra.getVouchAmount());
            et.setDate(vouchContra.getVouchCashDate());
            et.setEntryType("C");
            et.setNarration(vouchContra.getVouchNarration());         
            et_id = voucherService.saveVoucherToEntries(et);
           
            Double currentBuyerBalance=openingBalanceOfLedger-vouchContra.getVouchAmount();  
//             Double currentBuyerBalance=BalFrom;  
             Entryitems ei1 =  new Entryitems();
            ei1.setAmount(vouchContra.getVouchAmount());
            ei1.setEntryId(et_id);
            ei1.setLedgerId(vouchContra.getVouchFrom());
            if(currentBuyerBalance>=0)
            ei1.setClosingAmtDr(currentBuyerBalance);
            
            else
            ei1.setClosingAmtCr( Math.abs(currentBuyerBalance));
            ei1.setType("C");            
            voucherService.saveVoucherToEntryItems(ei1);
     
            Double currentBuyerBalancee=openingBalanceOfLedgerr+vouchContra.getVouchAmount();//              Double currentBuyerBalancee=BalTo;
            Entryitems ei2 =  new Entryitems();
            ei2.setAmount(vouchContra.getVouchAmount());
            ei2.setEntryId(et_id);
            ei2.setLedgerId(vouchContra.getVouchTo());
            if(currentBuyerBalancee>=0)
            ei2.setClosingAmtDr(currentBuyerBalancee);
            else
            ei2.setClosingAmtCr( Math.abs(currentBuyerBalancee));
            ei2.setType("D");
          
            voucherService.saveVoucherToEntryItems(ei2);
          
            return "redirect:VoucherContraGrid.html";         
        }
    }
    
    @RequestMapping(value={"VoucherContraGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView voucherContraGrid()
    {
        ModelAndView model = new ModelAndView("VoucherContraGrid");
        model.addObject("vouchContraList", voucherContraService.listAllVoucherContra());
        return model;
    }
    
    @RequestMapping(value={"VoucherContraEdit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView voucherContraEdit(@RequestParam("id") int pId)
    {
       VoucherContra list=voucherContraService.getVoucherContraByID(pId);
        
        ModelAndView model = new ModelAndView("VoucherContraUpdate");
        model.addObject("vouchContraForm", voucherContraService.getVoucherContraByID(pId));
        model.addObject("pId", voucherContraService.getVoucherContraByID(pId));
        String type=list.getVouchType();
      
        model.addObject("modeType", type);
        String AFROM="";
        String BTO="";

//        System.out.print("type :::::"+type);
       
//     String Fid=list.getVouchFrom();  
//     String Tid=list.getVouchTo();
    // String Cid=list.getVouchdepositTobank();
        
         SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();        
        String CurrentDate= dmyFormat.format(date);        
       
         List<Object[]> currentBalanceF=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(list.getVouchFrom()), CurrentDate);
         double openingBalanceOfLedgerFROM=0;
       if( currentBalanceF!=null && currentBalanceF.size()>0)
       {
             for (Object[] column : currentBalanceF) {
                        openingBalanceOfLedgerFROM = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(list.getVouchFrom()));
           for (Object[] column : OpeningBalance) {
                        openingBalanceOfLedgerFROM = (Double)column[0]- (Double)column[1];
                }
       } 

     
      // System.out.print("list.getVouchNameofBank():"+list.getVouchdepositTobank());
        
         List<Object[]> currentBalanceC=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(list.getVouchTo()), CurrentDate);
         double openingBalanceOfLedgerTO=0;
       if( currentBalanceC!=null && currentBalanceC.size()>0)
       {
             for (Object[] column : currentBalanceC) {
                        openingBalanceOfLedgerTO = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(list.getVouchTo()));
           for (Object[] column : OpeningBalance) {
                        openingBalanceOfLedgerTO = (Double)column[0]- (Double)column[1];
                }
       } 

       if(openingBalanceOfLedgerTO>=0)
        {
          BTO="DR";           
        }
          else
        {
          BTO="DR";
        } 
         if(openingBalanceOfLedgerFROM>=0)
        {
           AFROM="DR";
          
        }
          else
        {
           AFROM="CR"; 
        }
       
        model.addObject("currentCmBalance",Math.abs(openingBalanceOfLedgerTO));
        model.addObject("BTO",BTO); 
        model.addObject("currentFromBalance",Math.abs(openingBalanceOfLedgerFROM)); 
        model.addObject("AFROM",AFROM);
        
        
         model.addObject("vouchContraList", voucherContraService.listAllVoucherContra());
         model.addObject("vouchLedgerBank", voucherJournalService.listCashLedgersWithBank());
         model.addObject("vouchLedgerCIH", voucherJournalService.listCashLedgersWithCIH());
         model.addObject("vouchLedgerBankList", voucherJournalService.listCashLedgersWithBankList());  
         
         
        return model;
    }
    
    @RequestMapping(value={"VoucherContraDelete"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView voucherContraDelete(@RequestParam("id") String id, RedirectAttributes redirect)
    {
       
       ModelAndView model = new ModelAndView("redirect:VoucherContraGrid.html");
       if(id!=null && id.length()>0){
           voucherContraService.deleteVoucherContraById(Integer.parseInt(id));
           return model;
       }
       else{
           return model;
       }
    }
    @ResponseBody
    @RequestMapping(value={"GetDatatableContra"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
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
        String sql = "SELECT contra1.*,contra2.vouch_to,contra2.vouch_bal_to      FROM (select  vc.vouch_id ,vc.vouch_cash_date,vc.vouch_amount ,vc.vouch_type ,vc.vouch_from ,vc.vouch_bal_from   ,la.name_of_ledger  from voucher_contra as vc inner join ledger_account_master as la  on vc.vouch_from =la.id_ledger ) as contra1 inner join (select  vc.vouch_id ,vc.vouch_to,vc.vouch_bal_to   from voucher_contra as vc inner join ledger_account_master as la  on vc.vouch_to  =la.id_ledger )  as contra2 on contra1.vouch_id =contra2.vouch_id ";
        
      
        List<Object[]> list = voucherContraService.GetDatatableContraObject(sql);
        total=list.size();
       
        
    }catch(Exception e){
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "SELECT contra1.*,contra2.to_ledger,contra2.vouch_bal_to,contra2.vt  FROM (select  vc.vouch_id ,vc.vouch_cash_date,vc.vouch_amount ,vc.vouch_type ,la.name_of_ledger ,vc.vouch_bal_from    from voucher_contra as vc inner join ledger_account_master as la  on vc.vouch_from =la.id_ledger ) as contra1 inner join (select  vc.vouch_id ,la.name_of_ledger as to_ledger,vc.vouch_bal_to,vc.vouch_type as vt  from voucher_contra as vc inner join ledger_account_master as la  on vc.vouch_to  =la.id_ledger )  as contra2 on contra1.vouch_id =contra2.vouch_id";
        String searchTerm =req.getParameter("search[value]");
        
         String globeSearch =  " where (contra1.vouch_id like '"+searchTerm+"%')";
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
         List<Object[]> list2 = voucherContraService.GetDatatableContraObject(sql);
         // For Filter Count 
        String sql2 = "SELECT contra1.*,contra2.to_ledger,contra2.vouch_bal_to,contra2.vt  FROM (select  vc.vouch_id ,vc.vouch_cash_date,vc.vouch_amount ,vc.vouch_type ,la.name_of_ledger ,vc.vouch_bal_from    from voucher_contra as vc inner join ledger_account_master as la  on vc.vouch_from =la.id_ledger ) as contra1 inner join (select  vc.vouch_id ,la.name_of_ledger as to_ledger,vc.vouch_bal_to,vc.vouch_type as vt  from voucher_contra as vc inner join ledger_account_master as la  on vc.vouch_to  =la.id_ledger )  as contra2 on contra1.vouch_id =contra2.vouch_id";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = voucherContraService.GetDatatableContraCount(sql2);
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
