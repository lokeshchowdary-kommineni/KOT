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
import com.accounting.bean.PrintSetting;
import com.accounting.bean.PurchaseForm;
import com.accounting.bean.Sales;
import com.accounting.bean.SalesEstimate;
import com.accounting.bean.SalesEstimateForm;
import com.accounting.bean.SalesEstimateItem;
import com.accounting.bean.SalesForm;
import com.accounting.bean.SalesItem;
import com.accounting.service.AccountDBOService;
import com.accounting.service.Buyer_service;
import com.accounting.service.CcodeService;
import com.accounting.service.CompanyService;
import com.accounting.service.DefaultSetting_service;
import com.accounting.service.PrintService;
import com.accounting.service.SalesEstimateService;
import com.accounting.service.SalesService;
import com.accounting.service.TaxStructure_Service;
import com.accounting.validator.SalesEstimateFormValidator;
import com.accounting.validator.SalesFormValidator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.eclipse.persistence.expressions.ExpressionOperator.as;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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


@RestController
public class SalesEstimateController {
    
    @Autowired
    private SalesEstimateFormValidator salesEstimateFormValidator;
    
    @Autowired
    private SalesEstimateService salesEstimateService;
    
    @Autowired
    private TaxStructure_Service tServ;
    
    @Autowired
    private CcodeService cService;
    
    @Autowired
    private CompanyService companyservice;
    
    @Autowired
    private Buyer_service bs;
     
     @Autowired
    private DefaultSetting_service des;
     
     @Autowired
    private AccountDBOService as;
    
     @Autowired
    private PrintService ps;  
     
     DefaultSettings ds=new DefaultSettings();
    
    //Set a form validator
    @InitBinder("salesEstimateForm")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(this.salesEstimateFormValidator);
    }

@RequestMapping(value={"SalesEstimate"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView salesEstimate()
{
    SalesEstimate s=new SalesEstimate();
    SalesEstimateItem seItem=new SalesEstimateItem();
    SalesEstimateForm sForm=new SalesEstimateForm();
    List<SalesEstimateItem> list=new ArrayList<SalesEstimateItem>();
    list.add(seItem);
    sForm.setSale(s);
    sForm.setSales(list);
    ModelAndView model = new ModelAndView("SalesEstimate");
    if(des.getDefaultbyId(1)!=null){
     ds=des.getDefaultbyId(1);
    s.setCategory(ds.getSalesCategory());
    s.setMode(ds.getSalesMode());
    model.addObject("CategoryAuto", ds.getSalesCategory());
    }
    else
    {
    model.addObject("CategoryAuto", "");    
    }
    String CompanyGstno="";
    if(companyservice.getCompanyById(1)!=null)
    {
      CompanyGstno=companyservice.getCompanyById(1).getCompanyTin();
      CompanyGstno=CompanyGstno.substring(0, 2);
      model.addObject("CompanyGstno",CompanyGstno);
    }
    else
    {
     model.addObject("CompanyGstno",CompanyGstno);   
    }    
    model.addObject("salesEstimateForm", sForm);
    model.addObject("taxStructure", tServ.getTaxId(1));
    HashSet<String> set=new HashSet<String> ();
    List cCodelist=cService.listCode();
    Iterator itr=cCodelist.iterator();
    while(itr.hasNext()){
        CcodeMaster c=(CcodeMaster)itr.next();
        set.add(c.getTaxRate().toString());
    }
    model.addObject("cCodeList", set);
    return model;
}

@RequestMapping(value={"SE_Addrow"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView itemMasterPages(@ModelAttribute("salesEstimateForm")  SalesEstimateForm salesEstimateForm)
{
  
    SalesEstimateItem seItem_plus=new SalesEstimateItem(); // for empty row objects
    SalesEstimateItem seItem=new SalesEstimateItem();
    SalesEstimateForm sForm=salesEstimateForm;
    List<SalesEstimateItem> list=sForm.getSales(); // Getting all rows from forms
     List<SalesEstimateItem> listNew= new ArrayList<SalesEstimateItem>(); // for add undeleted items 
    Iterator<SalesEstimateItem> i = list.iterator();
    // this loop used to iterate all rows of data
    while (i.hasNext())
    {
        seItem = i.next();
        // this condition exclude deleted empty row of data
        if(seItem.getItemCode()=="" || seItem.getItemCode()==null)
        {
           
        }
        else
        {
          // here add existing data to new arraylist  
          listNew.add(seItem);
        }    

    }
    
    listNew.add(seItem_plus);    // this step add empty row into arraylist
    sForm.setSales(listNew);    // setting all data with empty row into form
    
    ModelAndView model = new ModelAndView("SalesEstimate_AddRow");    
    model.addObject("salesEstimateForm", sForm);
    model.addObject("taxStructure", tServ.getTaxId(1));
    HashSet<String> set=new HashSet<String> ();
    List cCodelist=cService.listCode();
    Iterator itr=cCodelist.iterator();
    while(itr.hasNext()){
        CcodeMaster c=(CcodeMaster)itr.next();
        set.add(c.getTaxRate().toString());
    }
    model.addObject("cCodeList", set);
    return model;
}


@RequestMapping(value={"SaveSalesEstimate"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView saveItem(@ModelAttribute("salesEstimateForm") @Validated SalesEstimateForm salesForm, BindingResult result,
         RedirectAttributes redirect,HttpSession ses)
{
    
    if (result.hasErrors()) {
        return new ModelAndView("SalesEstimate");
        
    }
    else{
        
    // code to ignore validator(itemCode) while removing addRow --- Starts(Abi-20.07.2017)
   
    SalesEstimateItem saleEstItem=new SalesEstimateItem();
    List<SalesEstimateItem> listSaleEst=salesForm.getSales(); // Getting all rows from forms
    List<SalesEstimateItem> listNew= new ArrayList<SalesEstimateItem>(); // for add undeleted items 
    Iterator<SalesEstimateItem> i = listSaleEst.iterator();   
    // this loop used to iterate all rows of data
    while (i.hasNext())  
    {
        saleEstItem = i.next();
        // this condition exclude deleted empty row of data
        if(saleEstItem.getItemCode()==null || saleEstItem.getItemCode().isEmpty() )
        {
           
        }
        else
        {
          // here add existing data to new arraylist  
          listNew.add(saleEstItem);
        }    

    }
    salesForm.setSales(listNew);
    // ---- End(Abi 20.07.2017)
    
        // start finacial year  
          String yearRange=as.GetYearRange();  
          long previousId=as.getPreviousIdBasedStartAndEndYear("invoiceNo", "SalesEstimate", yearRange);
          String primaryIdPE="";  
          if(previousId==0){
             primaryIdPE=previousId+1+"/"+yearRange;
          }else{
               primaryIdPE=previousId+1+"/"+yearRange;
          }
           ses.setAttribute("primaryIdPE", primaryIdPE);       
          // end of finacial year  

       SalesEstimate s=salesForm.getSale();
       s.setStatus("Direct Estimate");
       s.getInvoiceNo();
        System.out.println("Sales id edit:"+ s.getInvoiceNo());
       
        String invoicNo = s.getInvoiceNo();
        salesEstimateService.deleteSalesEstimateItem(invoicNo);
//        System.out.println("Sales id length:"+invoicNo.length());
//       System.out.println("Sales id :"+invoicNo);
    if(invoicNo==null || invoicNo.isEmpty()  ){
        System.out.println("insert ");
        s.setInvoiceNo(null);
        invoicNo=salesEstimateService.saveSalesEstimate(s);
       List list=salesForm.getSales();
       Iterator itr=list.iterator();
       while(itr.hasNext()){
           SalesEstimateItem sItem=(SalesEstimateItem)itr.next();
           sItem.setInvoiceNo((invoicNo));
           sItem.setId(null);
           salesEstimateService.saveSalesEstimateItem(sItem);
       }
       return new ModelAndView("redirect:SalesEstimateGrid.html");
    }
    else{
         System.out.println("update ");
        invoicNo=salesEstimateService.saveSalesEstimate(s);
        List list=salesForm.getSales();
       
       Iterator itr=list.iterator();
       while(itr.hasNext()){
           SalesEstimateItem sItem=(SalesEstimateItem)itr.next();
           sItem.setInvoiceNo((invoicNo));
            sItem.setId(null);
           salesEstimateService.saveSalesEstimateItem(sItem);
       }
       return new ModelAndView("redirect:SalesEstimateGrid.html");
        
       }
    }
}

@RequestMapping(value={"SalesEstimateGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView salesEstimateGrid()
{

ModelAndView model = new ModelAndView("SalesEstimateGrid");
model.addObject("salesinfo",salesEstimateService.listSalesEstimate());
System.out.print("salesinfo   -"+salesEstimateService.listSalesEstimate());
return model;
}

@RequestMapping(value={"editSalesEstimate"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView editSales(@RequestParam("id") String id, RedirectAttributes redirect)
{
    
    if(id!=null && id.length()>0){
      SalesEstimate sale=salesEstimateService.getSalesEstimateById(id);
     
      sale.getInvoiceNo();
      System.out.println("Sivaraj  id:"+ sale.getInvoiceNo());
      List list=salesEstimateService.getSalesEstimateItemBySalesId(id);
      
      SalesEstimateForm sForm=new SalesEstimateForm();
      sForm.setSale(sale);
      sForm.setSales(list);
      System.out.println("Sivaraj  :"+sale.getCategory());
      ModelAndView model = new ModelAndView("SalesEstimate");
      SalesEstimate sale1=salesEstimateService.getSalesEstimateById(id);
      sale.setCategory(sale1.getCategory());
         sale.setMode(sale1.getMode());
         model.addObject("CategoryAuto", sale1.getCategory());
      model.addObject("salesEstimateForm", sForm);
      HashSet<String> set=new HashSet<String> ();
        List cCodelist=cService.listCode();
        Iterator itr=cCodelist.iterator();
        while(itr.hasNext()){
            CcodeMaster c=(CcodeMaster)itr.next();
            set.add(c.getTaxRate().toString());
        }
        model.addObject("cCodeList", set);
      return model;
    }
   else{
        ModelAndView model = new ModelAndView("redirect:SalesEstimateGrid.html");
       return model;
   }

}
  
  
    
@RequestMapping(value={"deleteSalesEstimate"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView deleteSalesEstimate(@RequestParam("id") String id, RedirectAttributes redirect)
{
    ModelAndView model = new ModelAndView("redirect:SalesEstimateGrid.html");
   if(id!=null && id.length()>0){
        
      salesEstimateService.deleteSalesEstimate(id);
      salesEstimateService.deleteSalesEstimateItem(id);      
      
       return model;
       
   }
   else{
       return model;
   }

}

@RequestMapping(value={"SalesEstimateInvoice"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView salesInvoice(@RequestParam("id") String id,HttpServletRequest req){
    
    if(id!=null && id.length()>0){
        
        List list=companyservice.listcompany();
        CompanyInformation company=(CompanyInformation)list.iterator().next();
        
      SalesEstimate sale=salesEstimateService.getSalesEstimateById(id);
      List slaesItemList=salesEstimateService.getSalesEstimateItemForSalesINvoice(id);
      ModelAndView model=new ModelAndView("SalesInvoice");
      String type=req.getParameter("type");           
      if(type!=null && type.equalsIgnoreCase("pre"))
      {
        PrintSetting prePrint=ps.getPrintType("Pre Printed");  
        model.addObject("Type", "pre");  
        model.addObject("print", prePrint );  
      }
      else
      {
        PrintSetting plain=ps.getPrintType("Plain");  
        model.addObject("Type", "plain");  
        model.addObject("print", plain);  
      } 
      
      
      model.addObject("company", company);
      model.addObject("sales", sale);
      model.addObject("buyer", bs.getBuyerbyId(sale.getBuyerId()));
      model.addObject("salesItem", slaesItemList);
       company=companyservice.getCompanyById(1);
      model.addObject("companyFrom",company);
      return model;
    }
   else{
       ModelAndView model = new ModelAndView("redirect:SalesEstimate.html");
       return model;
   }
    
}
@RequestMapping(value={"SalesEstimateinvoice"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView salesInvoices(@RequestParam("id") String id,HttpServletRequest req){
    
    if(id!=null && id.length()>0){
        
        List list=companyservice.listcompany();
        CompanyInformation company=(CompanyInformation)list.iterator().next();
        
      SalesEstimate sale=salesEstimateService.getSalesEstimateById(id);
      List slaesItemList=salesEstimateService.getSalesEstimateItemForSalesINvoice(id);
       String type=req.getParameter("type");     
      ModelAndView model=new ModelAndView("SalesInvoice");
      if(type!=null && type.equalsIgnoreCase("pre"))
      {
        PrintSetting prePrint=ps.getPrintType("Pre Printed");  
        model.addObject("Type", "pre");  
        model.addObject("print", prePrint );  
      }
      else
      {
        PrintSetting plain=ps.getPrintType("Plain");  
        model.addObject("Type", "plain");  
        model.addObject("print", plain);  
      } 
      
      model.addObject("company", company);
      model.addObject("sales", sale);
      model.addObject("buyer", bs.getBuyerbyId(sale.getBuyerId()));
      model.addObject("salesItem", slaesItemList);
      model.addObject("withVat", "withVat");
       company=companyservice.getCompanyById(1);
      model.addObject("companyFrom",company);
      return model;
    }
   else{
       ModelAndView model = new ModelAndView("redirect:SalesEstimate.html");
       return model;
   }
    
}
@ResponseBody
   @RequestMapping(value={"GetDatatableSaleEstimate"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
    public DatatableObject getDatatableSaleEstimate( HttpServletRequest req)
    {
      DatatableObject db =new DatatableObject();
      
           String[] cols = {  "invoice_no", "date", "name_of_buyer","mode","category","buyer_balance","invoice_amount" };
    String table = "sales_estimate";
    

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
        String sql = "select se.invoice_no , se.date ,bm.buyer_name , bm.cell_no, se.mode ,se.category ,se.status,se.invoice_amount,se.assess_value from SALES_ESTIMATE  as se inner join BUYER_MASTER as bm on se.buyer_id = bm.id_buyer ";
        System.out.println("abi" + sql);
        List<Object[]> list = bs.GetDatatableBuyerObject(sql);
        total=list.size();
        System.out.println("the list size buyer" + total);
        
    }catch(Exception e){
         
    }
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "select se.invoice_no , se.date ,bm.buyer_name , bm.cell_no, se.mode ,se.category ,se.status,se.invoice_amount,se.assess_value from SALES_ESTIMATE  as se inner join BUYER_MASTER as bm on se.buyer_id = bm.id_buyer ";
        String searchTerm =req.getParameter("search[value]");
        System.out.println(" Valahsdfh "+searchTerm);
         String globeSearch =  " where (se.invoice_no like '"+searchTerm+"%')";
         System.out.println(" globeSearch "+globeSearch);
    
        if(searchTerm!=""){
            searchSQL=globeSearch;
        }
        sql += searchSQL;
        sql += " order by " + colName + " " + dir;
        sql += " limit " + start + ", " + amount;
        System.out.println(" SQL abi "+sql);
        // For aData
         List<Object[]> list2 = bs.GetDatatableBuyerObject(sql);
         // For Filter Count 
        String sql2 = "select se.invoice_no , se.date ,bm.buyer_name , bm.cell_no, se.mode ,se.category ,se.status,se.invoice_amount,se.assess_value from SALES_ESTIMATE  as se inner join BUYER_MASTER as bm on se.buyer_id = bm.id_buyer ";
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
