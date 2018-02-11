/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;
    
import com.accounting.bean.CompanyInformation;
import com.accounting.bean.DatatableObject;
import com.accounting.bean.DefaultSettings;
import com.accounting.bean.Entries;
import com.accounting.bean.Entryitems;
import java.math.BigInteger;
import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.VoucherReceiptAndPayment;
import com.accounting.service.CompanyService;
import com.accounting.service.ConvertNumberToString;
import com.accounting.service.DefaultSetting_service;
import com.accounting.service.LedgerAccount_Service;
import com.accounting.service.LedgerBalanceService;
import com.accounting.service.VoucherReceiptAndPaymentService;
import com.accounting.validator.VoucherReceiptAndPaymentValidator;
import static java.lang.String.format;
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
public class VouchReceiptAndPaymentController {
    
    @Autowired
    private VoucherReceiptAndPaymentValidator voucherValidator;
    
    @Autowired
    private VoucherReceiptAndPaymentService voucherService;
    
    @Autowired
    private CompanyService companyservice;
    
    @Autowired 
    private ConvertNumberToString convertToString;
    @Autowired
    private LedgerBalanceService lbs;
    
      @Autowired
    private LedgerAccount_Service ledgerAccountService;
     
         @Autowired
    private DefaultSetting_service des;
     DefaultSettings ds=new DefaultSettings();
    
    //Set a form validator
    @InitBinder("vouchPaymentReceipt")
    protected void initBinder(WebDataBinder binder) {
            binder.setValidator(voucherValidator);
    }
    
    VoucherReceiptAndPayment voucher = new VoucherReceiptAndPayment(); 
  @RequestMapping(value={"VoucherReceipt&Payment"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView indexPage()
  {
       VoucherReceiptAndPayment voucher = new VoucherReceiptAndPayment();
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
       Date date = new Date();
       
       System.out.println("current date newly"+dateFormat.format(date)); 
       voucher.setVouchCashDate(date);
       
//    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
//    Date convertedFromDate = sdf.parse(date);
//    String fdate=dmyFormat.format(convertedFromDate );    
    
	
    ModelAndView model = new ModelAndView("VoucherReceipt&Payment");
    if(des.getDefaultbyId(1)!=null){
     ds=des.getDefaultbyId(1);
     voucher.setVouchMode(ds.getPaymentMode());}
     System.out.print("Payment mode  :"+(ds.getPaymentMode()));
     
    model.addObject("vMode", ds.getPaymentMode());
    
    model.addObject("vouchPaymentReceipt", voucher);
     model.addObject("NameOFAmountList", voucherService.listAllLedgerNames());
      model.addObject("NameOFAmountListECIH", voucherService.listAllLedgerExceptCIN());     
    model.addObject("vouchLedgerList", voucherService.listCashLedgersTO());
    model.addObject("vouchCashLedgerList", voucherService.listCashLedgersCheque());
    return model;
  }
  
  @RequestMapping(value={"VoucherPayment"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView vocherPay()
  {
       VoucherReceiptAndPayment voucher = new VoucherReceiptAndPayment();
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
       Date date = new Date(); 
       voucher.setVouchCashDate(date);
	
    ModelAndView model = new ModelAndView("VoucherPayment");
    if(des.getDefaultbyId(1)!=null){
     ds=des.getDefaultbyId(1);
     voucher.setVouchMode(ds.getPaymentMode());}
      model.addObject("vMode", ds.getPaymentMode());
    model.addObject("vouchPaymentReceipt", voucher);
    model.addObject("NameOFAmountList", voucherService.listAllLedgerNames());
      model.addObject("NameOFAmountListECIH", voucherService.listAllLedgerExceptCIN());  
    model.addObject("vouchLedgerList", voucherService.listCashLedgersTO());
    model.addObject("vouchCashLedgerList", voucherService.listCashLedgersCheque());
    return model;
  }
  
  @RequestMapping(value={"VoucherReceipt&PaymentGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView voucherPaymentGrid()
    {
        ModelAndView model = new ModelAndView("VoucherReceipt&PaymentGrid");
    //        model.addObject("vouchReceiptList", voucherService.getVoucherList());
        model.addObject("vouchPaymentList", voucherService.getVoucherPaymentList("payment"));
        return model;
    }
    @RequestMapping(value={"ReceiptGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView voucherReceiptGrid()
    {
        ModelAndView model = new ModelAndView("ReceiptGrid");
         model.addObject("vouchReceiptList", voucherService.getVoucherReceiptList("receipt"));
        return model;
    }
    @RequestMapping(value={"VoucherReceiptEdit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView voucherPaymentEdit(@RequestParam("id") int pId)
    {
        
        VoucherReceiptAndPayment list=voucherService.getVoucherById(pId);
 
        ModelAndView model = new ModelAndView("VoucherReceipt&Payment");
        model.addObject("vouchPaymentReceipt", voucherService.getVoucherById(pId));   
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();        
        String CurrentDate= dmyFormat.format(date);
        String AFROM="";
        String BTO="";
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
           AFROM="DR";
        }
          else
        {
           AFROM="CR";
        }
       if(list.getVouchdepositTobank()==0){
           int suplierLedgerT=ledgerAccountService.getLederByReferenceIdVoucher(String.valueOf(list.getVouchTo())).iterator().next().getIdLedger();  
             List<Object[]> currentBalanceT=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(suplierLedgerT), CurrentDate);
           double openingBalanceOfLedgerTO=0;
       if( currentBalanceT!=null && currentBalanceT.size()>0)
       {
             for (Object[] column : currentBalanceT) {
                        openingBalanceOfLedgerTO = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(suplierLedgerT));
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
          BTO="CR";
         
        }
        Double currentTOBalance=openingBalanceOfLedgerTO;
        model.addObject("currentTOmBalance",Math.abs(currentTOBalance));
        model.addObject("toType",BTO );
       
       }else{
              int suplierLedgerC=ledgerAccountService.getLederByReferenceIdVoucher(String.valueOf(list.getVouchdepositTobank())).iterator().next().getIdLedger();         
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
       String CFROM="DR";
       if(openingBalanceOfLedgerC>=0)
        {
           CFROM="DR";
        }
          else
        {
           CFROM="CR";
        }          
        Double currentCBalance=openingBalanceOfLedgerC;
         model.addObject("currentCmBalance",Math.abs(currentCBalance));
         model.addObject("cType",CFROM );
           
       }
        
        Double currentFromBalance=openingBalanceOfLedgerFROM;
        model.addObject("currentFromBalance",Math.abs(currentFromBalance));
         model.addObject("fromType",AFROM );
         model.addObject("NameOFAmountList", voucherService.listAllLedgerNames());
           model.addObject("NameOFAmountListECIH", voucherService.listAllLedgerExceptCIN());  
    model.addObject("vouchLedgerList", voucherService.listCashLedgersTO());
    model.addObject("vouchCashLedgerList", voucherService.listCashLedgersCheque());
        return model;
    }
    
    
    @RequestMapping(value={"VoucherPayemtEdit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView VoucherPayemtEdit(@RequestParam("id") int pId)
    {
        
        VoucherReceiptAndPayment list=voucherService.getVoucherById(pId);
 
        ModelAndView model = new ModelAndView("VoucherPayment");
        model.addObject("vouchPaymentReceipt", voucherService.getVoucherById(pId));
        model.addObject("vpId",pId);
        System.out.print("id"+pId);
         SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();        
        String CurrentDate= dmyFormat.format(date);
        String AFROM="";
        String BTO="";      
                
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

       if(openingBalanceOfLedgerFROM>=0)
        {
           AFROM="DR";
        }
          else
        {
           AFROM="CR";
        }
      
        List<Object[]> currentBalanceT=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(list.getVouchTo()), CurrentDate);
        double openingBalanceOfLedgerTO=0;
       if( currentBalanceT!=null && currentBalanceT.size()>0)
       {
             for (Object[] column : currentBalanceT) {
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
         BTO="CR";
         
        }
        Double currentTOBalance=openingBalanceOfLedgerTO;
        model.addObject("currentTOmBalance",Math.abs(currentTOBalance));
        model.addObject("typeT",BTO);
       
     
        
        Double currentFromBalance=openingBalanceOfLedgerFROM;
        model.addObject("currentFromBalance",Math.abs(currentFromBalance));
        model.addObject("typeF",AFROM);
         model.addObject("NameOFAmountList", voucherService.listAllLedgerNames());
           model.addObject("NameOFAmountListECIH", voucherService.listAllLedgerExceptCIN());  
    model.addObject("vouchLedgerList", voucherService.listCashLedgersTO());
    model.addObject("vouchCashLedgerList", voucherService.listCashLedgersCheque());
        return model;
    }
    @RequestMapping(value={"VoucherDelete"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView deleteVoucher(@RequestParam("id") String id, RedirectAttributes redirect)
    {
       
       ModelAndView model = new ModelAndView("redirect:VoucherReceipt&PaymentGrid.html");
       if(id!=null && id.length()>0){
           voucherService.deleteVoucherById(Integer.parseInt(id));
           return model;
       }
       else{
           return model;
       }
    }
  
  @RequestMapping(value={"SaveVoucherReceipt"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public ModelAndView saveVoucherReceipt(@ModelAttribute("vouchPaymentReceipt")  VoucherReceiptAndPayment voucher, BindingResult result,
            RedirectAttributes redirect, Model model)
    {
        Integer id = voucher.getVouchId(); 
        
        if (result.hasErrors()) {

            model.addAttribute("NameOFAmountList", voucherService.listAllLedgerNames()); 
             model.addAttribute("NameOFAmountListECIH", voucherService.listAllLedgerExceptCIN());
            model.addAttribute("vouchLedgerList", voucherService.listCashLedgersTO());
            model.addAttribute("vouchCashLedgerList", voucherService.listCashLedgersCheque());
            return new ModelAndView("VoucherReceipt&Payment");
        }
        else{
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();        
        String CurrentDate= dmyFormat.format(date);     
            
       // Getting Current Balance of From Ledger     
       List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(voucher.getVouchFrom()), CurrentDate);
      
       double openingBalanceOfLedger_from=0;
       if( currentBalance!=null && currentBalance.size()>0)
       {
             for (Object[] column : currentBalance) {
                        openingBalanceOfLedger_from = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(voucher.getVouchFrom()));
           for (Object[] column : OpeningBalance) {
                        openingBalanceOfLedger_from = (Double)column[0]- (Double)column[1];
                }
       }   
               
       // Getting Current Balance of to Ledger   
       List<Object[]> currentBalancee=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(voucher.getVouchTo()), CurrentDate);
       double openingBalanceOfLedger_to=0;
       if( currentBalancee!=null && currentBalancee.size()>0)
       {
             for (Object[] column : currentBalancee) {
                        openingBalanceOfLedger_to = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalancee=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(voucher.getVouchTo()));
           for (Object[] column : OpeningBalancee) {
                        openingBalanceOfLedger_to = (Double)column[0]- (Double)column[1];
                }
       } 
       String vouchType= voucher.getVouchType(); 
       double balanceFrom=0.0;
       double balanceto=0.0;
             if("receipt".equalsIgnoreCase(vouchType))
             {
                balanceFrom=openingBalanceOfLedger_from-voucher.getVouchAmount();
                balanceto=openingBalanceOfLedger_to+voucher.getVouchAmount();
             }
             else
             {
                balanceFrom=openingBalanceOfLedger_from+voucher.getVouchAmount();
                balanceto=openingBalanceOfLedger_to-voucher.getVouchAmount(); 
             }    
            
            
            
            
            String mode=voucher.getVouchMode();
            if("cheque".equals(mode))
            { 
                int vouchTo= voucher.getVouchdepositTobank();                
                voucher.setVouchTo(vouchTo);
                voucher.setVouchdepositTobank(vouchTo);
            }
          
        
           if(balanceFrom>=0)
             {
             voucher.setVouchBal(Math.abs(balanceFrom));
             voucher.setVouchCashType("DR");
             }
             else
             {
             voucher.setVouchBal(Math.abs(balanceFrom));
             voucher.setVouchCashType("CR");
             }
           
             if(balanceto>0)
             {
             voucher.setVouchCheqBal(Math.abs(balanceto));
             voucher.setVouchCheqType("DR");
             }
             else
             {
             voucher.setVouchCheqBal(Math.abs(balanceto));
             voucher.setVouchCheqType("CR");
             }
         
           int v_id = voucherService.saveVoucher(voucher);

         
           if(voucher.getVouchMode().equalsIgnoreCase("cash")) {
               int et_id=0;
             //         to add entries
         Entries et = new Entries();
         et.setBillId(String.valueOf(v_id));
         et.setBillType(voucher.getVouchMode());
         et.setCrTotal(voucher.getVouchAmount());
         et.setDrTotal(voucher.getVouchAmount());
         
         et.setEntryType(String.valueOf(voucher.getVouchType().charAt(0)));
         et.setNarration(voucher.getVouchNarration());
         et.setDate(voucher.getVouchCashDate());
           et_id = voucherService.saveVoucherToEntries(et);
       
        // to add entry items
          //Name Of Account closing balance(Recipt)
    
             if("receipt".equalsIgnoreCase(vouchType)){
                 
               // from Entry   
                Double currentfromBal=openingBalanceOfLedger_from-voucher.getVouchAmount();
                Entryitems ei1 =  new Entryitems();
                ei1.setAmount(voucher.getVouchAmount());
                ei1.setEntryId(et_id);
                ei1.setLedgerId(voucher.getVouchFrom());
                if(currentfromBal>=0)
                ei1.setClosingAmtDr(currentfromBal);
                else
                ei1.setClosingAmtCr( Math.abs(currentfromBal));
                ei1.setType("C");
                voucherService.saveVoucherToEntryItems(ei1); 
                
//                to entry
                Double currentfromto=openingBalanceOfLedger_to+voucher.getVouchAmount();    
                Entryitems e2 =  new Entryitems();
                e2.setAmount(voucher.getVouchAmount());
                e2.setEntryId(et_id);
                    if(voucher.getVouchTo()!=0){
                        e2.setLedgerId((voucher.getVouchTo()));
                    }
                    if(voucher.getVouchdepositTobank()!=0){
                        e2.setLedgerId(voucher.getVouchdepositTobank());
                    }
                if(currentfromto>=0)
                e2.setClosingAmtDr(currentfromto);
                else
                e2.setClosingAmtCr( Math.abs(currentfromto));
                e2.setType("D");
                voucherService.saveVoucherToEntryItems(e2); 
                        
         }
         else{                 
                Double currentfromBal=openingBalanceOfLedger_from+voucher.getVouchAmount();
                Entryitems ei1 =  new Entryitems();
                ei1.setAmount(voucher.getVouchAmount());
                ei1.setEntryId(et_id);
                ei1.setLedgerId(voucher.getVouchFrom());
                if(currentfromBal>0)
                ei1.setClosingAmtDr(currentfromBal);
                else
                ei1.setClosingAmtCr( Math.abs(currentfromBal));
                ei1.setType("D");
                voucherService.saveVoucherToEntryItems(ei1);

        //cash ledger closing balance(Recipt)
               Double currentfromto=openingBalanceOfLedger_to-voucher.getVouchAmount();    
                Entryitems e2 =  new Entryitems();
                e2.setAmount(voucher.getVouchAmount());
                e2.setEntryId(et_id);
                    if(voucher.getVouchTo()!=0){
                        e2.setLedgerId((voucher.getVouchTo()));
                    }
                    if(voucher.getVouchdepositTobank()!=0){
                        e2.setLedgerId(voucher.getVouchdepositTobank());
                    }
                if(currentfromto>=0)
                e2.setClosingAmtDr(currentfromto);
                else
                e2.setClosingAmtCr( Math.abs(currentfromto));
                e2.setType("C");
                voucherService.saveVoucherToEntryItems(e2);  
              }
        
          
           }
           if(voucher.getVouchType().equalsIgnoreCase("receipt")){
           
         return new ModelAndView("redirect:ReceiptGrid.html");
           }else{
               return new ModelAndView("redirect:VoucherReceipt&PaymentGrid.html");
           }
        }
    }
    
    
    @RequestMapping(value={"getLedgerAccount"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public LedgerAccountMaster getLedgerAccount(@RequestParam("id") String ledgerID, LedgerAccountMaster lam)
    {
       //System.out.println("key :"+ledgerID);
       SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd"); 
       Date date = new Date();        
       String CurrentDate= dmyFormat.format(date);
       List<Object[]> currentBalance=lbs.SingleLedgerCurrentBalanceFromEntriesTable(String.valueOf(ledgerID), CurrentDate);
      
       double openingBalanceOfLedger=0;
       if( currentBalance!=null && currentBalance.size()>0)
       {
             for (Object[] column : currentBalance) {
                 
                        openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                }
           
       }
       else
       {
           List<Object[]> OpeningBalance=lbs.SingleLedgerOpeningBalanceFromLedgerTable(String.valueOf(ledgerID));
           for (Object[] column : OpeningBalance) {
                        openingBalanceOfLedger = (Double)column[0]- (Double)column[1];
                }
       }
        if(openingBalanceOfLedger>=0)
        {
          lam.setOpeningAmount(openingBalanceOfLedger);
          lam.setOpeningType("DR");
        }
          else
        {
          lam.setOpeningAmount(Math.abs(openingBalanceOfLedger));
          lam.setOpeningType("CR");  
         
        }
        return lam;
    }
    
    @RequestMapping(value={"VoucherPaymentPrint"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView voucherPaymentPrint(@RequestParam("id") String id){
    System.out.println("asdasdsd");

        ModelAndView model=new ModelAndView("VoucherPaymentPrint");
        
        List list=companyservice.listcompany();
        CompanyInformation company=(CompanyInformation)list.iterator().next();
        VoucherReceiptAndPayment vouch = voucherService.getVoucherById(Integer.parseInt(id));
        LedgerAccountMaster lam = null;
        LedgerAccountMaster lam_from = null;
        Double d = new Double(vouch.getVouchAmount());
        if(vouch.getVouchFrom()!=0){
          
            lam_from = voucherService.getLedgerAccountByKey(String.valueOf(vouch.getVouchFrom()));
        }
        if(vouch.getVouchTo()!=0){
           
            lam = voucherService.getLedgerAccountByKey(String.valueOf(vouch.getVouchTo()));
        }
        else if(vouch.getVouchdepositTobank()!=0){
            lam = voucherService.getLedgerAccountByKey(String.valueOf(vouch.getVouchdepositTobank()));
        }
        
        model.addObject("company", company);
        model.addObject("vouch", vouch);
        model.addObject("ledger", lam_from);
      //  model.addObject("lam_from", lam);
        model.addObject("amountInWords", convertToString.convert(d.intValue()).toUpperCase());
         company=companyservice.getCompanyById(1);
         model.addObject("companyFrom",company);
        return model;
    }
    
    // Datatable for Receipt
    @ResponseBody
    @RequestMapping(value={"GetDatatableReceipt"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
    public DatatableObject getDatatableReceipt( HttpServletRequest req)
    {
      DatatableObject db =new DatatableObject();
           String[] cols = { "vouch_id", "vouch_cash_date", "vouch_amount", "vouch_type","vouch_from","vouch_bal","vouch_to","vouch_bal" };
    String table = "voucher_receipt_and_payment";
 
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
        String sql = "SELECT receipt1.*,receipt2.vouch_type FROM (select  vrp.vouch_id ,vrp.vouch_cash_date ,vrp.vouch_amount ,vrp.vouch_mode,la.name_of_ledger ,vrp.vouch_bal  from VOUCHER_RECEIPT_AND_PAYMENT  as vrp inner join ledger_account_master as la  on vrp.vouch_from =la.id_ledger ) as receipt1  inner join (select vrp.vouch_id ,la.name_of_ledger as to_ledger,vrp.vouch_bal,vrp.vouch_type  from VOUCHER_RECEIPT_AND_PAYMENT  as vrp inner join ledger_account_master as la  on vrp.vouch_to  = la.id_ledger)   as receipt2 on receipt1.vouch_id =receipt2.vouch_id  and receipt2.vouch_type='receipt'";
        
       
        List<Object[]> list = voucherService.GetDatatableReceiptObject(sql);
        total=list.size();
       
        
    }catch(Exception e){
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "SELECT receipt1.*,receipt2.vouch_type FROM (select  vrp.vouch_id ,vrp.vouch_cash_date ,vrp.vouch_amount ,vrp.vouch_mode,la.name_of_ledger ,vrp.vouch_bal  from VOUCHER_RECEIPT_AND_PAYMENT  as vrp inner join ledger_account_master as la  on vrp.vouch_from =la.id_ledger ) as receipt1  inner join (select vrp.vouch_id ,la.name_of_ledger as to_ledger,vrp.vouch_bal,vrp.vouch_type  from VOUCHER_RECEIPT_AND_PAYMENT  as vrp inner join ledger_account_master as la  on vrp.vouch_to  = la.id_ledger)   as receipt2 on receipt1.vouch_id =receipt2.vouch_id  and receipt2.vouch_type='receipt'";
        String searchTerm =req.getParameter("search[value]");
        
         String globeSearch =  " where (receipt2.vouch_id like '"+searchTerm+"%')";
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
         List<Object[]> list2 = voucherService.GetDatatableReceiptObject(sql);
         // For Filter Count 
        String sql2 = "SELECT receipt1.*,receipt2.vouch_type FROM (select  vrp.vouch_id ,vrp.vouch_cash_date ,vrp.vouch_amount ,vrp.vouch_mode,la.name_of_ledger ,vrp.vouch_bal  from VOUCHER_RECEIPT_AND_PAYMENT  as vrp inner join ledger_account_master as la  on vrp.vouch_from =la.id_ledger ) as receipt1  inner join (select vrp.vouch_id ,la.name_of_ledger as to_ledger,vrp.vouch_bal,vrp.vouch_type  from VOUCHER_RECEIPT_AND_PAYMENT  as vrp inner join ledger_account_master as la  on vrp.vouch_to  = la.id_ledger)  as receipt2 on receipt1.vouch_id =receipt2.vouch_id  and receipt2.vouch_type='receipt'";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = voucherService.GetDatatableReceiptCount(sql2);
          totalAfterFilter=count.size();
        }
  
   db.setiTotalRecords(total);
   db.setiTotalDisplayRecords(totalAfterFilter);
   db.setAaData(list2);
    } catch (Exception e) {
  
    }
      return db;
	} 
    // Datatable for ---> payment 
    
     @ResponseBody
    @RequestMapping(value={"GetDatatablePayment"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
    public DatatableObject getDatatablePayment( HttpServletRequest req)
    {
      DatatableObject db =new DatatableObject();
           String[] cols = { "vouch_id", "vouch_cash_date", "vouch_amount", "vouch_type","vouch_from","vouch_bal","vouch_to","vouch_bal" };
    String table = "voucher_receipt_and_payment";
    
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
        String sql = "SELECT receipt1.*,receipt2.to_ledger,receipt2.vouch_cheq_bal ,receipt2.authorisation FROM (select  vrp.vouch_id ,vrp.vouch_cash_date ,vrp.vouch_amount ,vrp.vouch_mode  from VOUCHER_RECEIPT_AND_PAYMENT  as vrp inner join ledger_account_master as la  on vrp.vouch_from =la.id_ledger ) as receipt1  inner join (select vrp.vouch_id ,la.name_of_ledger as to_ledger,vrp.vouch_cheq_bal ,vrp.vouch_type,vrp.authorisation  from VOUCHER_RECEIPT_AND_PAYMENT  as vrp inner join ledger_account_master as la  on vrp.vouch_to  =la.id_ledger )  as receipt2 on receipt1.vouch_id =receipt2.vouch_id  and receipt2.vouch_type='payment'";
        
       
        List<Object[]> list = voucherService.GetDatatableReceiptObject(sql);
        total=list.size();
        
        
    }catch(Exception e){
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "SELECT receipt1.*,receipt2.to_ledger,receipt2.vouch_cheq_bal ,receipt2.authorisation FROM (select  vrp.vouch_id ,vrp.vouch_cash_date ,vrp.vouch_amount ,vrp.vouch_mode  from VOUCHER_RECEIPT_AND_PAYMENT  as vrp inner join ledger_account_master as la  on vrp.vouch_from =la.id_ledger ) as receipt1  inner join (select vrp.vouch_id ,la.name_of_ledger as to_ledger,vrp.vouch_cheq_bal ,vrp.vouch_type,vrp.authorisation  from VOUCHER_RECEIPT_AND_PAYMENT  as vrp inner join ledger_account_master as la  on vrp.vouch_to  =la.id_ledger )  as receipt2 on receipt1.vouch_id =receipt2.vouch_id  and receipt2.vouch_type='payment'";
        String searchTerm =req.getParameter("search[value]");
        
         String globeSearch =  " where (receipt2.vouch_id like '"+searchTerm+"%')";
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
         List<Object[]> list2 = voucherService.GetDatatableReceiptObject(sql);
         // For Filter Count 
        String sql2 = "SELECT receipt1.*,receipt2.to_ledger,receipt2.vouch_cheq_bal ,receipt2.authorisation FROM (select  vrp.vouch_id ,vrp.vouch_cash_date ,vrp.vouch_amount ,vrp.vouch_mode  from VOUCHER_RECEIPT_AND_PAYMENT  as vrp inner join ledger_account_master as la  on vrp.vouch_from =la.id_ledger ) as receipt1  inner join (select vrp.vouch_id ,la.name_of_ledger as to_ledger,vrp.vouch_cheq_bal ,vrp.vouch_type,vrp.authorisation  from VOUCHER_RECEIPT_AND_PAYMENT  as vrp inner join ledger_account_master as la  on vrp.vouch_to  =la.id_ledger )  as receipt2 on receipt1.vouch_id =receipt2.vouch_id  and receipt2.vouch_type='payment'";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = voucherService.GetDatatableReceiptCount(sql2);
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
