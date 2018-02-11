/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;

import com.accounting.bean.PrintSetting;
import com.accounting.service.PrintService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class PrintSettingController {
    
    @Autowired
    private PrintService ps;
    
  

  
    
     @RequestMapping(value={"PrintSetting"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView PrintSetting( HttpSession session)
  {
      ModelAndView model = new ModelAndView("PrintSetting");
      model.addObject("printForm", new PrintSetting());
      model.addObject("PrintInfo",ps.listprintService());
    
    
    return model;
  }
  
  @RequestMapping(value={"savePrint"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView savePrint(@ModelAttribute("printForm")  PrintSetting psform ) throws IOException
  { 
    List list= ps.listprintService();  
    int   count=list.size();
    if(psform.getPrintId()==null)
    {
        psform.setPrintId(null);
    }
    else
    {
        count=list.size()-1; 
    }    
  
     if(count<=1){
    ps.addprintSetting(psform);
     }
     else
     {
     ModelAndView model = new ModelAndView("redirect:PrintSetting.html");
     return model;
     }    
    
    
     ModelAndView model = new ModelAndView("redirect:PrintSetting.html");
     return model;
   
  }
  
        @RequestMapping(value={"editPrint"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView editPrint(@RequestParam("id") String id)
    {   
        ModelAndView model = new ModelAndView("PrintSetting");
       
           model.addObject("printForm", ps.getPrintById(Integer.parseInt(id)));
           model.addObject("PrintInfo",ps.listprintService());
           return model;
      
           
    }
            
            @RequestMapping(value={"deletePrint"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView deletePrint(@RequestParam("id") String id, RedirectAttributes redirect)
    {
        ModelAndView model = new ModelAndView("redirect:PrintSetting.html");
       if(id!=null && id.length()>0){
           ps.deletePrint(Integer.parseInt(id));
           return model;
       }
       else{
           return model;
       }
           
    }
    
}
