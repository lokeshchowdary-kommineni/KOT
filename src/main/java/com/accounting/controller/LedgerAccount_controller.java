
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.controller;

import com.accounting.bean.LedgerAccountMaster;
import com.accounting.bean.SupplierMaster;
import com.accounting.dao.AccountGroup_dao;
import com.accounting.service.AccountGroup_Service;
import com.accounting.service.CompanyService;
import com.accounting.service.ItemMasterService;
import com.accounting.service.LedgerAccount_Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author shinelogics
 */
@Controller
public class LedgerAccount_controller {
     @Autowired
    private LedgerAccount_Service lgs;
      @Autowired
     private  AccountGroup_Service ad;
      @Autowired
    private CompanyService cs;
    @Autowired
    private ItemMasterService itemMasterService;
    @Autowired
    private LedgerAccount_Service ledgerService;  
      
      
  @RequestMapping(value={"LedgerAccount"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView ledger(RedirectAttributes redirect)
  {
      LedgerAccountMaster lm = new LedgerAccountMaster();
         double dr=0;
         double cr=0;
         String dr_total=itemMasterService.sumOfStockValue();
         
 
        
        List<Object[]> type=ledgerService.GetTotalByOpeningType();
         if( type!=null && type.size()>0)
            {
                
                  for (Object[] column : type) {
                             dr=(Double)column[0] + Double.parseDouble(dr_total) ;
                             cr=(Double)column[1];
                     }

            }
         
    ModelAndView model = new ModelAndView("LedgerAccount");
    
    if(cs.getCompanyById(1)!=null ){
      Date d=cs.getCompanyById(1).getAccountsFrom();
      DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");      
      model.addObject("StockOpenDate", formatter.format(d));
      lm.setOpeningDate(d);
    }
    else
    { 
        ModelAndView modelRedirect = new ModelAndView("redirect:Companyinformation.html");
       redirect.addFlashAttribute("message", "Create the Company Information!");
       return modelRedirect;
    }
     model.addObject("ledgerList",lgs.listLedger());
     model.addObject("accountGroupList",lgs.listAccountGroups());
//     model.addObject("accountGroupListNew",lgs.listAccountGroupsNew()) ;       
     model.addObject("GroupMaster",ad.listAccount());
     model.addObject("debit",dr);
     model.addObject("credit",cr);
     model.addObject("ledgerForm",lm);
    return model;
  } 
  @RequestMapping(value={"ledgersave"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView save(@ModelAttribute("ledgerForm") @Valid LedgerAccountMaster lm, BindingResult result,HttpServletRequest req,RedirectAttributes redirectAttributes)
  {
      
       String message="";
        String hiddenValue=req.getParameter("LedName"); 
      if(result.hasErrors())
        
        { 
            ModelAndView model = new ModelAndView("LedgerAccount");
            model.addObject("ledgerList",lgs.listLedger());
            model.addObject("accountGroupList",lgs.listAccountGroups());
            model.addObject("GroupMaster",ad.listAccount());
            model.addObject("ledgerForm",lm);
           return model;
        } 
      lm.setNameOfLedger(lm.getNameOfLedger().toUpperCase());      
      lm.setPredefined(1);
      Integer i=lm.getIdLedger();
     
      if(i==null)
      {  
      lm.setIdLedger(null);
      String value=lm.getNameOfLedger();
       Boolean checkLedger=lgs.checkLedgerName(value);
               String notification="";
                if(checkLedger)
               {
                notification="LedgerGroupName you have entered is Already Exist!";
                ModelAndView model = new ModelAndView("LedgerAccount");
                 model.addObject("val",notification);
                 model.addObject("ledgerList",lgs.listLedger());
                 model.addObject("accountGroupList",lgs.listAccountGroups());
                 model.addObject("GroupMaster",ad.listAccount());
                 model.addObject("ledgerForm",lm);
      return model;
               }
                 message="Record Added Successfully"; 
      }
      else
      {
      lm.setIdLedger(i);
       String formValue=lm.getNameOfLedger();
      if(!hiddenValue.equals(formValue)){
      String value=lm.getNameOfLedger();
       Boolean checkLedger=lgs.checkLedgerName(value);
               String notification="";
                if(checkLedger)
               {
                notification="LedgerGroupName you have entered is Already Exist!";
                ModelAndView model = new ModelAndView("LedgerAccount");
                 model.addObject("val",notification);
                 model.addObject("ledgerList",lgs.listLedger());
                 model.addObject("accountGroupList",lgs.listAccountGroups());
                 model.addObject("GroupMaster",ad.listAccount());
                 model.addObject("ledgerForm",lm);
      return model;
               }
              message="Record Updated Successfully";    
      }
      }
       lgs.addLedgerAccount(lm);
               
      ModelAndView model = new ModelAndView("redirect:LedgerAccount.html");
      redirectAttributes.addFlashAttribute("message",message);
      return model;
      
  }
   @RequestMapping(value={"ledgerEdit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView editItem(@ModelAttribute("ledgerForm") LedgerAccountMaster lm,HttpServletRequest req)
  {
     String editid=req.getParameter("eid");
         double dr=0;
         double cr=0;
         String dr_total=itemMasterService.sumOfStockValue();
         
        List<Object[]> type=ledgerService.GetTotalByOpeningType();
         if( type!=null && type.size()>0)
            {
                
                  for (Object[] column : type) {
                             dr=(Double)column[0] + Double.parseDouble(dr_total) ;
                             cr=(Double)column[1];
                     }

            }
    ModelAndView model = new ModelAndView("LedgerAccount");
    if(cs.getCompanyById(1)!=null ){
      Date d=cs.getCompanyById(1).getAccountsFrom();
      DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");      
      model.addObject("StockOpenDate", formatter.format(d));
      lm.setOpeningDate(d);
    }
    else
    { 
        ModelAndView modelRedirect = new ModelAndView("redirect:Companyinformation.html");
      
       return modelRedirect;
    }
    model.addObject("ledgerForm",lgs.getLedgerbyId(Integer.parseInt(editid)));
     model.addObject("ledgerList",lgs.listLedger());
     model.addObject("accountGroupList",lgs.listAccountGroups());
     model.addObject("GroupMaster",ad.listAccount());
     model.addObject("debit",dr);
     model.addObject("credit",cr);
    return model;
  }
  @RequestMapping(value={"ledgerDelete"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView deleteItem(HttpServletRequest req,RedirectAttributes redirectAttributes)
  { 
     try
     {
     String deleteid=req.getParameter("did");
     lgs.deleteLedger(Integer.parseInt(deleteid));
     }
     catch(Exception e)
     {       
        ModelAndView model1 = new ModelAndView("redirect:LedgerAccount.html");  
        redirectAttributes.addFlashAttribute("message", "Ledger Already Use Somewhere,Cannot Delete"); 
        return model1;    
     }
    ModelAndView model = new ModelAndView("redirect:LedgerAccount.html");
     redirectAttributes.addFlashAttribute("message", "Your Record has been Deleted... ");
    return model;
  }
}
