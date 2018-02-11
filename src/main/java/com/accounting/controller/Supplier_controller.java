/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accounting.controller;

import com.accounting.bean.DatatableObject;
import com.accounting.bean.SupplierMaster;
import com.accounting.service.Supplier_Service;
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
public class Supplier_controller {
     
     
   @Autowired
    private Supplier_Service ss;
    
    SupplierMaster sm = new SupplierMaster();
    
    @RequestMapping(value={"Supplier"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView account(RedirectAttributes redirect)
  {
    ModelAndView model = new ModelAndView("Supplier");
            model.addObject("supplierList",ss.listSupplier());
            model.addObject("supplierForm",sm);
            return model;
  } 
  
   @RequestMapping(value={"supplierEdit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView editSupplier(HttpServletRequest req)
  { 
      
    String editid=req.getParameter("eid");
    ModelAndView model = new ModelAndView("Supplier");
    model.addObject("supplierForm",ss.getSupplierbyId(Integer.parseInt(editid)));
    model.addObject("supplierList",ss.listSupplier());
    return model;
  } 
  
  @RequestMapping(value={"supplierDelete"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView deleteSupplier(HttpServletRequest req,RedirectAttributes redirect)
  { 
        String deleteid=req.getParameter("did");
        ss.deleteSupplier(Integer.parseInt(deleteid));
        ModelAndView model = new ModelAndView("redirect:Supplier.html");
        redirect.addFlashAttribute("Supmessage", "Record Deleted Successfully !!"); 
        return model;
  }
  
  @RequestMapping(value={"supplierSave"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public ModelAndView supplierSave(@ModelAttribute("supplierForm") @Valid  SupplierMaster s, BindingResult result,HttpServletRequest req,RedirectAttributes redirectAttributes)
  {
      String Supmessage="";
      Integer i=s.getIdSupplier();
                if(i==null )
                    {
                    s.setIdSupplier(null);
                    }
                else
                    {
                    s.setIdSupplier(i);
                    }
        ss.addSupplier(s);
      ModelAndView model = new ModelAndView("redirect:Supplier.html");
      redirectAttributes.addFlashAttribute("Supmessage",Supmessage);
      return model;
  }
  
//  @ResponseBody
//  @RequestMapping(value={"getSupplierNextId"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
//  public String getSupplierNextId()
//  {
//    int count=ss.maxSupplierId();
//      int ct=count+1;
//    return String.valueOf(ct);
//  } 
  
  @ResponseBody
   @RequestMapping(value={"GetDatatableSupplier"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
    public DatatableObject getDatatableSupplier( HttpServletRequest req)
    {
      DatatableObject db =new DatatableObject();
      
           String[] cols = {  "supplier_code", "supplier_name", "location","tin" };
    String table = "supplier_master";
    

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
        String sql = "SELECT supplier_code,supplier_name,supplier_state,supplier_city FROM supplier_master";
        List<Object[]> list = ss.GetDatatableSupplier(sql);
        total=list.size();
        
    }catch(Exception e){
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
                                        
    try {
        String searchSQL = "";
        String sql = "SELECT supplier_code,supplier_name,supplier_state,supplier_city,id_supplier FROM supplier_master";
        String searchTerm =req.getParameter("search[value]");
         String globeSearch =  " where (supplier_name like UCASE('"+searchTerm+"%') OR supplier_city like '"+searchTerm+"%')";
    
        if(searchTerm!=""){
            searchSQL=globeSearch;
        }
        sql += searchSQL;
        sql += " order by " + colName + " " + dir;
        sql += " limit " + start + ", " + amount;
        // For aData
         List<Object[]> list2 = ss.GetDatatableSupplier(sql);
         // For Filter Count 
        String sql2 = "SELECT supplier_code,supplier_name,supplier_state,supplier_city,id_supplier FROM supplier_master";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = ss.GetDatatableSupplierCount(sql2);
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
