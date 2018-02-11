/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.controller;

import com.accounting.bean.TaxStructure;
import com.accounting.service.TaxStructure_Service;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author shinelogics
 */
@Controller
public class TaxStructure_controller {
  
    @Autowired
    private TaxStructure_Service tServ;
    
      TaxStructure ts = new TaxStructure();
      
      
    @RequestMapping(value={"TaxStructure"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView tax()
  {
    
   
    ModelAndView model = new ModelAndView("TaxStructure");
    
     ts=tServ.getTaxId(1);
    if(ts==null )
    {
         TaxStructure t = new TaxStructure();
         model.addObject("tax",t);
    }
    else{
     model.addObject("tax",ts);
    }
   
    
    return model;
  } 
  
  
  @RequestMapping(value={"taxsave"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView taxsave(@ModelAttribute("tax") @Valid TaxStructure t, BindingResult result)
  {
     
     if (result.hasErrors()) {
      
                 ModelAndView model = new ModelAndView("TaxStructure");
                model.addObject("tax",t);
                 return model;
		} 
      
      Integer i=t.getId();
      System.out.println("check tax structure id:" + i);
      if(i==null)
      {
      t.setId(null);
      
      }
      else
      {
      t.setId(i);
      
      }
       tServ.addTax(t);
               
      ModelAndView model = new ModelAndView("redirect:TaxStructure.html");
     
      return model;
      
  }

}

