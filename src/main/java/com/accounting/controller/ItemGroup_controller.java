/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;

import com.accounting.bean.ItemGroupMaster;
import com.accounting.service.ItemGroup_Service;
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
 * @author SHINELOGICS
 */

@Controller
public class ItemGroup_controller {
    
   @Autowired
    private ItemGroup_Service igs;
    
@RequestMapping(value={"Item_Group"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView item()
  {
      ItemGroupMaster ig = new ItemGroupMaster();

    ModelAndView model = new ModelAndView("Item_Group");
    model.addObject("itemList",igs.listItem());
    model.addObject("itemgroupForm", ig);
    return model;
  }
 
 @RequestMapping(value={"itemsave"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView save(@ModelAttribute("itemgroupForm") @Valid ItemGroupMaster ig, BindingResult result,HttpServletRequest req,RedirectAttributes redirectAttributes)
  {
      
         ItemGroupMaster igm = new ItemGroupMaster();
       String message="";
       String hiddenValue=req.getParameter("itemGroupname"); 
       if(result.hasErrors())
        
        {
            ModelAndView model = new ModelAndView("Item_Group");
            model.addObject("itemList",igs.listItem());
            model.addObject("itemgroupForm", ig);
           return model;
        }
       
     ig.setItemGroupName(ig.getItemGroupName().toUpperCase());
      Integer i=ig.getIdItem();
      if(i==null)
      {
      ig.setIdItem(null);
      String value=ig.getItemGroupName();
       Boolean checkItem=igs.checkItemGroup(value);
               String notification="";
                if(checkItem)
               {
                notification="Item Group you have entered is Already Exist!";
                ModelAndView model1 = new ModelAndView("Item_Group");
                
                model1.addObject("val",notification);
                 model1.addObject("itemList",igs.listItem());
                 model1.addObject("itemgroupForm",igm);
      return model1;
               }
                 message="Record Added Successfully";
      }
      else
      {
      ig.setIdItem(i);
      String formValue=ig.getItemGroupName();
      if(!hiddenValue.equals(formValue)){
      String value=ig.getItemGroupName();
       Boolean checkItem=igs.checkItemGroup(value);
               String notification="";
                if(checkItem)
               {
                notification="Item Group you have entered is Already Exist!";
                ModelAndView model1 = new ModelAndView("Item_Group");
                
                model1.addObject("val",notification);
                 model1.addObject("itemList",igs.listItem());
                 model1.addObject("itemgroupForm",igm);
      return model1;
               }
                message="Record Updated Successfully";  
      }
      
      } 
       igs.addItemGroup(ig);
               
                 
                 ModelAndView model = new ModelAndView("redirect:Item_Group.html");
                  redirectAttributes.addFlashAttribute("message",message);
                
      return model;
      
  }
  
   @RequestMapping(value={"itemEdit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView editItem(HttpServletRequest req)
  {
     String editid=req.getParameter("eid");
    ModelAndView model = new ModelAndView("Item_Group");
    model.addObject("itemgroupForm",igs.getItembyId(Integer.parseInt(editid)));  
    model.addObject("itemList",igs.listItem());
   
    return model;
  }
  @RequestMapping(value={"itemDelete"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView deleteItem(HttpServletRequest req,RedirectAttributes redirectAttributes)
  {
      try{
      String deleteid=req.getParameter("did");
     igs.deleteItem(Integer.parseInt(deleteid));
  }catch(Exception e)
  {
     
       ItemGroupMaster igm = new ItemGroupMaster();
       ModelAndView model1 = new ModelAndView("redirect:Item_Group.html");  
       redirectAttributes.addFlashAttribute("message", "Item Group Already Use Somewhere,Cannot Delete"); 
        return model1;
  }
   
    ModelAndView model = new ModelAndView("redirect:Item_Group.html");
     redirectAttributes.addFlashAttribute("message","Record Deleted Successfully");
    return model;
  }
  
}
