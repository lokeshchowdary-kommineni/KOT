/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accounting.controller;  

import com.accounting.bean.CcodeMaster;
import com.accounting.bean.CompanyInformation;
import com.accounting.bean.DatatableObject;
import com.accounting.bean.DefaultSettings;
import com.accounting.bean.ItemMaster;
import com.accounting.service.CcodeService;
import com.accounting.service.CompanyService;
import com.accounting.service.DefaultSetting_service;
import com.accounting.service.ItemGroup_Service;
import com.accounting.service.ItemMasterService;
import com.accounting.service.LedgerAccount_Service;
import com.accounting.service.Unit_service;
import com.accounting.validator.ItemMasterFormValidator;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author SHINELOGICS
 */
@RestController
public class ItemMasterController {
    
    @Autowired
    private ItemMasterFormValidator itemMasterFormValidator;
    
    @Autowired
    private ItemMasterService itemMasterService;
    
    @Autowired
    private LedgerAccount_Service ledgerService;
    
    @Autowired
    private ItemGroup_Service itemGroupService;
    
    @Autowired
    private CcodeService cService;
    
    @Autowired
    private Unit_service unitService;
    
     @Autowired
    private CompanyService companyservice;
     
     
      @Autowired
    private DefaultSetting_service des;
    
    //Set a form validator
    @InitBinder("itemForm")
    protected void initBinder(WebDataBinder binder) {
            binder.setValidator(itemMasterFormValidator);
    }
    
     DefaultSettings ds=new DefaultSettings();
    
    @RequestMapping(value={"ItemMaster"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView itemMasterPage(RedirectAttributes redirect)
    {
        ItemMaster itemMaster = new ItemMaster();
        
        if(companyservice.getCompanyById(1)!=null ){
           itemMaster.setStockDate(companyservice.getCompanyById(1).getAccountsFrom()); 
        }
        else
        {
            ModelAndView modelRedirect = new ModelAndView("redirect:Companyinformation.html");
            redirect.addFlashAttribute("message", "Create the Company Information!");
            return modelRedirect; 
        }    
         double dr=0;
         double cr=0;
         String dr_total=itemMasterService.sumOfStockValue();
         
        List<Object[]> type=ledgerService.GetTotalByOpeningType();
         if( type!=null && type.size()>0)
            {
                
                  for (Object[] column : type) {
                             dr=(Double)column[0] + Double.parseDouble(dr_total) ;
                             cr=(Double)column[1];
                     }

            }

        ModelAndView model = new ModelAndView("ItemMaster");
        if(des.getDefaultbyId(1)!=null)
        {
            ds=des.getDefaultbyId(1);
        itemMaster.setUnit(ds.getUnitItemMaster());
        itemMaster.setComCode(ds.getCategoryItemMaster());
        if(des.getDefaultbyId(1).getCategoryItemMaster()!=null){
        CcodeMaster list=cService.getCodeByID(ds.getCategoryItemMaster());
         itemMaster.setCategory(list.getCategory());
         itemMaster.setTr(list.getTaxRate());
        }else{
            
        }
        }
        else
        {
         itemMaster.setUnit(Integer.parseInt("0"));   
        }
      
        model.addObject("itemForm", itemMaster);
        model.addObject("itemList", itemMasterService.getItemList());
        model.addObject("itemGroupList",itemGroupService.listItem());
        model.addObject("cCodeList", cService.listCode());
        model.addObject("unitList",unitService.listUnit());
        model.addObject("sumOfStockValue",itemMasterService.sumOfStockValue());
        model.addObject("debit",dr);
        model.addObject("credit",cr);        
        model.addObject("CategoryItemMaster",ds.getCategoryItemMaster());
        
        
        return model;
    }
    
    @RequestMapping(value={"SaveItem"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public ModelAndView saveItem(@ModelAttribute("itemForm") @Validated ItemMaster itemMaster, BindingResult result,
            RedirectAttributes redirect,HttpServletRequest req)
    {
        if (result.hasErrors()) {
//           List<FieldError> errors = result.getFieldErrors();
//    for (FieldError error : errors ) {
//        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
//    }
            ModelAndView model=new ModelAndView("ItemMaster");
            model.addObject("itemList", itemMasterService.getItemList());
            model.addObject("itemGroupList",itemGroupService.listItem());
            model.addObject("cCodeList", cService.listCode());
            model.addObject("unitList",unitService.listUnit());
            model.addObject("sumOfStockValue",itemMasterService.sumOfStockValue());
            return model;
        }
        else{
         String message="Item Updated Successfully";
           System.out.println("Item Updated Place:" + itemMaster.getCp()); 
           if(itemMaster.getId()==null || itemMaster.getId().equals("")){
               if(itemMaster.getOpeningStock()==null){
                 itemMaster.setCurrentStock(0.0);                
               }else{              
                      itemMaster.setCurrentStock(itemMaster.getOpeningStock());
               }
               message="Item Added Successfully";
               if(itemMaster.getRate()!=0.0)
               {
                  itemMaster.setCp(itemMaster.getRate());  
               }
               else
               {
                  itemMaster.setCp(itemMaster.getEp());  
               }    
               
                 System.out.println("Insert Place:" + itemMaster.getCp()); 
                  }
           else
           {
           String value=req.getParameter("hicode");
           if(!value.equals(itemMaster.getItemCode()))
           {
            ItemMaster list=itemMasterService.getItmeByItemNo(itemMaster.getItemCode());       
                if(list!=null ){
                 ModelAndView model=new ModelAndView("redirect:ItemMaster.html");
                 redirect.addFlashAttribute("message","Item code already exits");   
                 return model;
                }
           }    
          
           }
           System.out.println("End CP:" + itemMaster.getCp()); 
           itemMasterService.saveItem(itemMaster);
           redirect.addFlashAttribute("itemList", itemMasterService.getItemList());
           ModelAndView model=new ModelAndView("redirect:ItemMaster.html");
           redirect.addFlashAttribute("message",message);
           return model;
         
        }
    }
    
    @RequestMapping(value={"UpdateItem"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView updateItem(@RequestParam("id") String itemCode, RedirectAttributes redirect)
    {
        double dr=0;
         double cr=0;
         String dr_total=itemMasterService.sumOfStockValue();
         
        List<Object[]> type=ledgerService.GetTotalByOpeningType();
         if( type!=null && type.size()>0)
            {
                
                  for (Object[] column : type) {
                             dr=(Double)column[0] + Double.parseDouble(dr_total) ;
                             cr=(Double)column[1];
                     }

            }
        
       if(itemCode!=null && itemCode.length()>0){
           ModelAndView model=new ModelAndView("ItemMaster");
           model.addObject("itemForm", itemMasterService.getItemByItemCode(Integer.parseInt(itemCode)));
           model.addObject("itemList", itemMasterService.getItemList());
           model.addObject("itemGroupList",itemGroupService.listItem());
           model.addObject("cCodeList", cService.listCode());
           model.addObject("unitList",unitService.listUnit());
           model.addObject("sumOfStockValue",itemMasterService.sumOfStockValue());
           model.addObject("debit",dr);
           model.addObject("credit",cr);
           model.addObject("CategoryItemMaster",ds.getCategoryItemMaster());
           return model;
       }
       else{
           ModelAndView model=new ModelAndView("redirect:ItemMaster.html");
           return model;
       }
           
    }
    
    @RequestMapping(value={"DeleteItem"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView deleteItem(@RequestParam("id") String itemCode, RedirectAttributes redirect)
    { 
       String message="Item Deleted Successfully" ;
       if(itemCode!=null && itemCode.length()>0){
        try{
            itemMasterService.deleteItemByItemCode(Integer.parseInt(itemCode));
        }catch(Exception e)
        {         
          System.out.println("e:"+e);
         ModelAndView model1 = new ModelAndView("redirect:ItemMaster.html");  
        redirect.addFlashAttribute("message", "Item  Already Use Somewhere,Cannot Delete"); 
        return model1;   
        }
           
           ModelAndView model=new ModelAndView("redirect:ItemMaster.html");
           redirect.addFlashAttribute("message",message);
           return model;
       }
       else{
           ModelAndView model=new ModelAndView("redirect:ItemMaster.html");
           redirect.addFlashAttribute("message",message);
           return model;
       }
           
    }
    @RequestMapping(value={"GetDatatable"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
    public DatatableObject getDatatable( HttpServletRequest req)
    {
      DatatableObject db =new DatatableObject();
           String[] cols = { "id", "item_code", "item_group", "item_name" };
    String table = "item_master";
    
//    JSONObject result = new JSONObject();
//    JSONArray array = new JSONArray();
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
     
    System.out.println("sStart" +sStart);
    System.out.println("sAmount" +sAmount);
    
    
      String itemCode = req.getParameter("columns[1][search][value]");
      System.out.println(" item code " + itemCode );
    List<String> sArray = new ArrayList<String>();
    if (!itemCode.equals("")) {
        String sCode = " LOWER(item_code) like LOWER('" + itemCode + "%')";
        sArray.add(sCode);
 
    }

    String individualSearch = "";
    if(sArray.size()==1){
        individualSearch = sArray.get(0);
    }else if(sArray.size()>1){
        for(int i=0;i<sArray.size()-1;i++){
            individualSearch += sArray.get(i)+ " and ";
        }
        individualSearch += sArray.get(sArray.size()-1);
    }
     
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
        String sql = "SELECT id,item_name,item_code,ccode FROM item_master INNER JOIN ccode_master ON item_master.com_code = ccode_master.ccode_id";
        System.out.println("abi" + sql);
        List<Object[]> list = itemMasterService.GetDatatableObject(sql);
        total=list.size();
        System.out.println("the list object" + total);
        
    }catch(Exception e){
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "SELECT id,item_name,item_code,ccode,la,lp FROM item_master INNER JOIN ccode_master ON item_master.com_code = ccode_master.ccode_id";
        String searchTerm =req.getParameter("columns[0][search][value]");
        System.out.println(" Valahsdfh "+searchTerm);
         String globeSearch =  " where (LOWER(item_name) like LOWER('"+searchTerm+"%'))";
        if(searchTerm!=""&&individualSearch!=""){
            searchSQL = globeSearch + " and " + individualSearch;
        }
        else if(individualSearch!=""){
            searchSQL = " where " + individualSearch;
        }else if(searchTerm!=""){
            searchSQL=globeSearch;
        }
        sql += searchSQL;
        sql += " order by " + colName + " " + dir;
        sql += " limit " + start + ", " + amount;
        System.out.println(" SQL abi "+sql);
        // For aData
         List<Object[]> list2 = itemMasterService.GetDatatableObject(sql);
         // For Filter Count 
        String sql2 = "SELECT id,item_name,item_code,ccode,la,lp FROM item_master INNER JOIN ccode_master ON item_master.com_code = ccode_master.ccode_id";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = itemMasterService.GetDatatableCount(sql2);
          totalAfterFilter=count.size();
        }
  
   db.setiTotalRecords(total);
   db.setiTotalDisplayRecords(totalAfterFilter);
   db.setAaData(list2);
    } catch (Exception e) {
 
    }
      return db;
	}  
           
    
    
    @RequestMapping(value={"ComCodeOnChenage"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public CcodeMaster comCodeOnChenage(@RequestParam("id") String comCode)
    {
       if(comCode!=null && comCode.length()>0){
          return cService.getCodeByID(Integer.parseInt(comCode));
          
       }
       else{
           return new CcodeMaster();
       }
           
    }
    
     @RequestMapping(value={"ItemMasterQuickEdit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public ModelAndView ItemMasterQuickEdit()
    {
        ItemMaster itemMaster = new ItemMaster();
        List list=companyservice.listcompany();
        Iterator itr=list.iterator();
        while(itr.hasNext()){
            CompanyInformation company=(CompanyInformation)itr.next();
            itemMaster.setStockDate(company.getAccountsFrom());
        }
     
         double dr=0;
         double cr=0;
         String dr_total=itemMasterService.sumOfStockValue();
         
        List<Object[]> type=ledgerService.GetTotalByOpeningType();
         if( type!=null && type.size()>0)
            {
                
                  for (Object[] column : type) {
                             dr=(Double)column[0] + Double.parseDouble(dr_total) ;
                             cr=(Double)column[1];
                     }

            }
        
       
        
        ModelAndView model = new ModelAndView("ItemMasterQuickEdit");
        if(des.getDefaultbyId(1)!=null)
        {ds=des.getDefaultbyId(1);
        itemMaster.setUnit(ds.getUnitItemMaster());} 
        model.addObject("itemForm", itemMaster);
        model.addObject("itemList", itemMasterService.getItemList());
        model.addObject("itemGroupList",itemGroupService.listItem());
        model.addObject("cCodeList", cService.listCode());
        model.addObject("unitList",unitService.listUnit());
        model.addObject("sumOfStockValue",itemMasterService.sumOfStockValue());
        model.addObject("debit",dr);
        model.addObject("credit",cr);
        model.addObject("CategoryItemMaster",ds.getCategoryItemMaster());
        
        
        return model;
    }
    
}

