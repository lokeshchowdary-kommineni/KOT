/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.controller;

import com.accounting.bean.BuyerMaster;
import com.accounting.bean.DatatableObject;
import com.accounting.service.Buyer_service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author shinelogics
 */
@Controller
public class Buyer_controller {
    @Autowired
    private Buyer_service bs;
    
     BuyerMaster bm = new BuyerMaster();
      
     
      
    @RequestMapping(value={"Buyer"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView buyer(RedirectAttributes redirect)
    {
      ModelAndView model = new ModelAndView("Buyer");
      model.addObject("buyerList",bs.listBuyer());
      model.addObject("buyerForm",bm);
      return model;
    }
    
  
    @RequestMapping(value={"buyersave"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public ModelAndView save(@ModelAttribute("buyerForm") @Valid BuyerMaster lm, BindingResult result,HttpServletRequest req,RedirectAttributes redirectAttributes)
    {
        System.out.println("seju");
              Integer i=lm.getIdBuyer();
                  if(i==null)
                          {
                          lm.setIdBuyer(null);
                          }
                  else
                          {
                          lm.setIdBuyer(i);
                          }
                bs.addBuyer(lm);
        ModelAndView model = new ModelAndView("redirect:Buyer.html");
        return model;

    }
   
    
    @RequestMapping(value={"buyerEdit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView editItem(HttpServletRequest req)
    {
          String editid=req.getParameter("eid");
          ModelAndView model = new ModelAndView("Buyer");
          model.addObject("buyerForm",bs.getBuyerbyId(Integer.parseInt(editid)));
          model.addObject("buyerList",bs.listBuyer());
          return model;
    }
  
    @RequestMapping(value={"buyerDelete"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView deleteItem(HttpServletRequest req,RedirectAttributes redirect)
    { 
          String deleteid=req.getParameter("did");
          bs.deleteBuyer(Integer.parseInt(deleteid));
          ModelAndView model = new ModelAndView("redirect:Buyer.html");
          redirect.addFlashAttribute("buyerMessage", "Your Record has been Deleted... ");
          return model;
    }
    
   @ResponseBody
   @RequestMapping(value={"GetDatatableBuyer"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
        public DatatableObject getDatatableBuyer( HttpServletRequest req)
        {
          DatatableObject db =new DatatableObject();

               String[] cols = {  "customer_name",  "customer_state","customer_mobile","customer_address" };
        String table = "buyer_master";


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
            String sql = "SELECT customer_name,customer_state,customer_mobile,customer_status,id_buyer FROM buyer_master";
               List<Object[]> list = bs.GetDatatableBuyerObject(sql);
               total=list.size();
        }catch(Exception e){

        }
        int totalAfterFilter = total;

        try {
            String searchSQL = "";
            String sql = "SELECT customer_name,customer_state,customer_mobile,customer_status,id_buyer FROM buyer_master";
            String searchTerm =req.getParameter("search[value]");
             String globeSearch =  " where ( customer_name like ('"+searchTerm+"%') OR id_buyer like '"+searchTerm+"%')";
             
            if(searchTerm!=""){
                searchSQL=globeSearch;
            }
            sql += searchSQL;
            sql += " order by " + colName + " " + dir;
            sql += " limit " + start + ", " + amount;
            // For aData
             List<Object[]> list2 = bs.GetDatatableBuyerObject(sql);
             // For Filter Count 
            String sql2 = "SELECT customer_name,customer_state,customer_mobile,customer_status,id_buyer FROM buyer_master";
           if (searchTerm != "") {
                sql2 += searchSQL;
              List<Object[]> count = bs.GetDatatableBuyerCount(sql2);
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
