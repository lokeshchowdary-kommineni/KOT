/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;

import com.accounting.bean.UnitMaster;
import com.accounting.service.Unit_service;
import com.accounting.validator.UserFormValidator;
import java.io.IOException;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.misc.Request;

/**
 *
 * @author SHINELOGICS
 */
@Controller
public class Unit_controller {
    
   @Autowired
    private Unit_service us;
    
@RequestMapping(value={"Unit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView unit()
  {
      UnitMaster uf = new UnitMaster();
//	user.setuName("Sivaraj");
//        user.setPassword("Sivaraj");
//        user.setGender("F");
    ModelAndView model = new ModelAndView("Unit");
    model.addObject("unitList",us.listUnit());
    model.addObject("unitForm", uf);
    return model;
  }
  @RequestMapping(value={"Edit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView editUnit(HttpServletRequest req)
  {
     String editid=req.getParameter("eid");
    ModelAndView model = new ModelAndView("Unit");
    model.addObject("unitForm",us.getUnitbyId(Integer.parseInt(editid)));
    model.addObject("unitList",us.listUnit());
    
   
    return model;
  }
  @RequestMapping(value={"Delete"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView deleteUnit(HttpServletRequest req,RedirectAttributes redirectAttributes)
  { 
        
    try{       
       
     String deleteid=req.getParameter("did");
      us.deleteUnit(Integer.parseInt(deleteid));
    ModelAndView model = new ModelAndView("redirect:Unit.html");
    redirectAttributes.addFlashAttribute("message", "Your Record has been Deleted... ");
    return model;
        
    }catch(Exception e)
    {
       
       
        ModelAndView model1 = new ModelAndView("redirect:Unit.html");  
        redirectAttributes.addFlashAttribute("message", "Unit Already Used Somewhere,Cannot be Deleted"); 
        return model1;
    }
    
    
   
  }
  @RequestMapping(value={"unitsave"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView save (  @ModelAttribute("unitForm") @Valid UnitMaster uf ,BindingResult result,HttpServletRequest req,RedirectAttributes redirectAttributes) 
  {
        String message="";
        String hiddenValue=req.getParameter("unitSym");       
        if(result.hasErrors())
        
        {
            ModelAndView model = new ModelAndView("Unit");
            model.addObject("unitList",us.listUnit());
            model.addObject("unitForm",uf);
           return model;
        }
      uf.setUnitSymbol(uf.getUnitSymbol().toUpperCase());
      
      Integer i=uf.getIdUnit();
      if(i==null)
      {
      uf.setIdUnit(null);
      
      String value=uf.getUnitSymbol();
       Boolean checkUnit=us.checkUnitSymbol(value);
               String notification="";
                if(checkUnit)
               {
                notification="UnitSymbol you have entered is Already Exist!";
                ModelAndView model = new ModelAndView("Unit");
                 model.addObject("val",notification);
                 model.addObject("unitList",us.listUnit());
                 
                 model.addObject("unitForm",uf);
      return model;
               }
        message="Record Added Successfully";       
      }
      else
      {
      uf.setIdUnit(i);
      String formValue=uf.getUnitSymbol();
    
       if(!hiddenValue.equals(formValue)){
       String value=uf.getUnitSymbol();
       Boolean checkUnit=us.checkUnitSymbol(value);
       String notification="";
                if(checkUnit)
               {
                notification="UnitSymbol you have entered is Already Exist!";
                ModelAndView model = new ModelAndView("Unit");
                 model.addObject("val",notification);
                 model.addObject("unitList",us.listUnit());
                 
                 model.addObject("unitForm",uf);
      return model;
               }
                 message="Record Updated Successfully";       
       }
       
      
      }     
      us.addUnit(uf);
       
        ModelAndView model = new ModelAndView("redirect:Unit.html");
        redirectAttributes.addFlashAttribute("message",message);
      
      
      return model;
      
   
  
  }
}
