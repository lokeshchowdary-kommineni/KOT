/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;

import com.accounting.bean.Tablemaster;
import com.accounting.service.TableService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author MR
 */
@Controller
public class TableController {
    
    @Autowired
    private TableService tableservice;
    
    Tablemaster table=new Tablemaster();

    @RequestMapping(value={"TableMaster"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView tableForm(HttpSession session)
    {
       ModelAndView model=new ModelAndView("TableMaster");
       model.addObject("tableList",tableservice.listTable());
       return model.addObject("tableForm",table);
    }

    @RequestMapping(value="saveTable",method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String saveTable(@ModelAttribute("tableForm")@Validated Tablemaster table, BindingResult result){
        tableservice.saveTable(table);
        return "redirect:/TableMaster.html";
    }
    
    @RequestMapping(value="EditTable", method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView editTable(@ModelAttribute("tableForm")@Validated Tablemaster table, BindingResult result,HttpServletRequest req)
    {
        int editid=Integer.parseInt(req.getParameter("tid"));
        System.out.println("tid is "+editid);
        ModelAndView model = new ModelAndView("TableMaster");
        model.addObject("tableList",tableservice.listTable());
        model.addObject("tableForm",tableservice.getTableById(editid));
        return model;
    }
    
    @RequestMapping(value={"DeleteTable"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView deleteTable(HttpServletRequest req,RedirectAttributes redirectAttributes)
    { 

        try{       

        int deleteid=Integer.parseInt(req.getParameter("tid"));
        tableservice.deleteTable(deleteid);
        ModelAndView model = new ModelAndView("redirect:/TableMaster.html");
        redirectAttributes.addFlashAttribute("message", "Your Record has been Deleted... ");
        return model;

        }catch(Exception e)
        {


            ModelAndView model1 = new ModelAndView("redirect:/TableMaster.html");  
            redirectAttributes.addFlashAttribute("message", "Table Already Used Somewhere,Cannot be Deleted"); 
            return model1;
        }



    }

}
