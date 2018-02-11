/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.controller;

import com.accounting.bean.ItcReversalMaster;
import com.accounting.service.ItcReversal_service;
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
public class ItcReversal_controller {
     @Autowired
    private ItcReversal_service its;
     
    ItcReversalMaster irm=new ItcReversalMaster(); 
    
    @RequestMapping(value={"itcReversal"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView itc()
  {
    
      
    ModelAndView model = new ModelAndView("itcReversal");
    model.addObject("itcList",its.listReversal());
    model.addObject("itcForm",irm);
    return model;
  }
  @RequestMapping(value={"itcsave"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView save(@ModelAttribute("itcForm") @Valid  ItcReversalMaster im, BindingResult result,HttpServletRequest req,RedirectAttributes redirectAttributes)
  {
      
      String message="";
      String hiddenValue=req.getParameter("itcName");
       if(result.hasErrors())
        
        {
            ModelAndView model = new ModelAndView("itcReversal");
            model.addObject("itcList",its.listReversal());
            model.addObject("itcForm",im);
           return model;
        }
      im.setPredefined(1);
      Integer i=im.getIdItc();
      if(i==null)
      {
         
      im.setIdItc(null);
      String value=im.getCategoryName();
       Boolean checkItc=its.checkItcName(value);
               String notification="";
                if(checkItc)
               {
                notification="Category Name you have entered is Already Exist!";
                ModelAndView model = new ModelAndView("itcReversal");
                model.addObject("val",notification);
                model.addObject("itcList",its.listReversal());
                model.addObject("itcForm",irm);
      return model;
               }
                 message="Record Added Successfully";
      }
      else
      {
      im.setIdItc(i);
      String formValue=im.getCategoryName();
       message="Record Updated Successfully";
      if(!hiddenValue.equals(formValue)){
      String value=im.getCategoryName();
       Boolean checkItc=its.checkItcName(value);
               String notification="";
                if(checkItc)
               {
                notification="Category Name you have entered is Already Exist!";
                ModelAndView model = new ModelAndView("itcReversal");
                model.addObject("val",notification);
                model.addObject("itcList",its.listReversal());
                model.addObject("itcForm",irm);
      return model;
               }
                 message="Record Updated Successfully";
                
      }
      }
      
               
       its.addReversal(im);
               
      ModelAndView model = new ModelAndView("redirect:itcReversal.html");
       redirectAttributes.addFlashAttribute("message",message);
      return model;
      
  }
  @RequestMapping(value={"itcEdit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView editItc(HttpServletRequest req)
  {
     String editid=req.getParameter("eid");
    ModelAndView model = new ModelAndView("itcReversal");
    model.addObject("itcForm",its.getReversalbyId(Integer.parseInt(editid)));
     model.addObject("itcList",its.listReversal());
   
    return model;
  }
  @RequestMapping(value={"itcDelete"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView deleteItem(HttpServletRequest req,RedirectAttributes redirect)
  { 
     try{
         String deleteid=req.getParameter("did");
     its.deleteReversal(Integer.parseInt(deleteid));
         
     }catch(Exception e)
     {
         System.out.println("e:"+e);
         ModelAndView model1 = new ModelAndView("redirect:itcReversal.html");  
        redirect.addFlashAttribute("message", "ITC Reversal Already Use Somewhere,Cannot Delete"); 
        return model1;  
     }
     
    ModelAndView model = new ModelAndView("redirect:itcReversal.html");
     redirect.addFlashAttribute("message","Record Deleted Successfully");
    return model;
  }
}
