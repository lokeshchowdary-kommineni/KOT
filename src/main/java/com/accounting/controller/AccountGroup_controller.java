/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.controller;

import com.accounting.bean.AccountGroupMaster;
import com.accounting.service.AccountGroup_Service;
import java.util.Date;
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
public class AccountGroup_controller {
    
    @Autowired
    private AccountGroup_Service ags;
    
    
    @RequestMapping(value={"AccountGroup"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView account()
  {
      AccountGroupMaster ag = new AccountGroupMaster();

    ModelAndView model = new ModelAndView("AccountGroup");
    
     model.addObject("accountList",ags.listAccount());   
    model.addObject("accountForm",ag); 
    return model;
  }   
  @RequestMapping(value={"accountsave"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView save(@ModelAttribute("accountForm") @Valid AccountGroupMaster ag, BindingResult result,HttpServletRequest req,RedirectAttributes redirectAttributes)
  {
        String message="";
        String hiddenValue=req.getParameter("accGroupName"); 
       AccountGroupMaster agm = new AccountGroupMaster();
       
     if (result.hasErrors()) {
      
                 ModelAndView model = new ModelAndView("AccountGroup");
                 model.addObject("accountList",ags.listAccount());
		 model.addObject("accountForm",ag);
                 return model;
		} 
     ag.setAccountGroupName(ag.getAccountGroupName().toUpperCase());
     ag.setPredefined(1);
     
      Integer i=ag.getIdAccount();
      if(i==null)
      {
      ag.setIdAccount(null);
      String value=ag.getAccountGroupName();
       Boolean checkAcc=ags.checkAccSymbol(value);
               String notification="";
                if(checkAcc)
               {
                notification="AccountGroupName you have entered is Already Exist!";
                ModelAndView model = new ModelAndView("AccountGroup");
                 model.addObject("val",notification);
                 model.addObject("accountList",ags.listAccount());
                 model.addObject("accountForm",ag);
      return model;
               }
                 message="Record Added Successfully";
      }
      else
      {
      ag.setIdAccount(i);
      String formValue=ag.getAccountGroupName();
      if(!hiddenValue.equals(formValue)){
      String value=ag.getAccountGroupName();
       Boolean checkAcc=ags.checkAccSymbol(value);
               String notification="";
                if(checkAcc)
               {
                notification="AccountGroupName you have entered is Already Exist!";
                ModelAndView model = new ModelAndView("AccountGroup");
                 model.addObject("val",notification);
                 model.addObject("accountList",ags.listAccount());
                 model.addObject("accountForm",ag);
      return model;
               }
                message="Record Updated Successfully";  
      }
      
      }    
      ags.addAccountGroup(ag);
     
      ModelAndView model = new ModelAndView("redirect:AccountGroup.html");
      redirectAttributes.addFlashAttribute("message",message);
      return model;
      
  }
   @RequestMapping(value={"accountEdit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView editAccount(HttpServletRequest req)
  {
     String editid=req.getParameter("eid");
    ModelAndView model = new ModelAndView("AccountGroup");
    model.addObject("accountForm",ags.getAccbyId(Integer.parseInt(editid)));
    model.addObject("accountList",ags.listAccount());  
   
    return model;
  }
  @RequestMapping(value={"accountDelete"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView deleteItem(HttpServletRequest req,RedirectAttributes redirectAttributes)
  { 
      
      try{
          String deleteid=req.getParameter("did");
     ags.deleteAccount(Integer.parseInt(deleteid));
      }catch(Exception e)
      {
        System.out.println("e:"+e);
         ModelAndView model1 = new ModelAndView("redirect:AccountGroup.html");  
        redirectAttributes.addFlashAttribute("message", "Account Group Already Use Somewhere,Cannot Delete"); 
        return model1;   
      }
    ModelAndView model = new ModelAndView("redirect:AccountGroup.html");
    redirectAttributes.addFlashAttribute("message","Record Deleted Successfully");
    return model;
  }
  
}