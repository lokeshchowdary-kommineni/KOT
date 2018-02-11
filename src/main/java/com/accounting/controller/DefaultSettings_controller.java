/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.controller;

import com.accounting.bean.DefaultSettings;
import com.accounting.service.CcodeService;
import com.accounting.service.DefaultSetting_service;
import com.accounting.service.ItcReversal_service;
import com.accounting.service.Unit_service;
import java.util.List;
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
public class DefaultSettings_controller {
     @Autowired
    private DefaultSetting_service des;
     @Autowired
     private Unit_service us;
      @Autowired
     private ItcReversal_service is;
      
       @Autowired
    private CcodeService cService;
    
    DefaultSettings ds=new DefaultSettings();
    
    @RequestMapping(value={"DefaultSettings"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView defaultsetting()
  {
     
    ModelAndView model = new ModelAndView("DefaultSettings");
    
    ds=des.getDefaultbyId(1);
    if(ds==null )
    {
        DefaultSettings ds1=new DefaultSettings();
        model.addObject("defaultForm",ds1);
    }
    else{
     model.addObject("defaultForm",ds);
      model.addObject("CategoryItemMaster",ds.getCategoryItemMaster());
    }
     model.addObject("defaultList",us.listUnit());
     model.addObject("itcList",is.listReversal());
     model.addObject("cCodeList", cService.listCode());
    return model;
  } 
  @RequestMapping(value={"defaultsave"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView save(@ModelAttribute("defaultForm")  DefaultSettings d, BindingResult result)
  {
     
      
      Integer i=d.getIdSetting();
      if(i==null)
      {
      d.setIdSetting(null);
      }
      else
      {
      d.setIdSetting(i);
      
      }
     
       des.addDefaultSetting(d);
       
      ModelAndView model = new ModelAndView("redirect:DefaultSettings.html");
      
     
      return model;
      
  }
}
