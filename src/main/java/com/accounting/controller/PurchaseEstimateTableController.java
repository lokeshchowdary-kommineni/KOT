package com.accounting.controller;

import com.accounting.bean.CompanyInformation;
import com.accounting.bean.DatatableObject;
import com.accounting.bean.ItemMaster;
import com.accounting.bean.PurchaseEstimate;
import com.accounting.bean.PurchaseEstimateForm;
import com.accounting.bean.PurchaseEstimateItem;
import com.accounting.service.AccountDBOService;
import com.accounting.service.CompanyService;
import com.accounting.service.ItemMasterService;
import com.accounting.service.PurchaseEstimateService;
import com.accounting.service.Supplier_Service;
import com.accounting.service.TaxStructure_Service;
import com.accounting.validator.PurchaseEstimateFormValidator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
public class PurchaseEstimateTableController {
    

      @Autowired
    private PurchaseEstimateService purchaseEstimateService;
      
       @Autowired
    private ItemMasterService itemMasterService;
       
       
        @Autowired
    private Supplier_Service ss;
       
        @Autowired
    private CompanyService companyservice;
           @Autowired
    private AccountDBOService adbo;
        
        @Autowired
    private TaxStructure_Service tServ;

      @Autowired
    private PurchaseEstimateFormValidator purchaseEstimateFormValidator;

   @InitBinder("purchaseEFrom")
    protected void initBinder(WebDataBinder binder) {

                          binder.setValidator(purchaseEstimateFormValidator);
          
    }
    
    @RequestMapping(value={"PurchaseEstimate"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView Purchaseinformation()
  {
   PurchaseEstimate p=new PurchaseEstimate();
    PurchaseEstimateForm pEForm=new PurchaseEstimateForm();
    PurchaseEstimateItem pEItem=new PurchaseEstimateItem();
    List<PurchaseEstimateItem> list=new ArrayList<PurchaseEstimateItem>();
    list.add(pEItem);
    pEForm.setEpurchases(list);
    pEForm.setEpurchase(p);
    
    CompanyInformation tin= companyservice.getCompanyById(1);    
    String gstinCompany=tin.getCompanyTin().substring(0, 2);
  
    ModelAndView model = new ModelAndView("PurchaseEstimate");
    model.addObject("purchaseEFrom", pEForm);
    model.addObject("taxStructure", tServ.getTaxId(1));
    model.addObject("CompanyGstin", gstinCompany); 
    return model;
}

@RequestMapping(value={"SavePurchaseEstimate"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView saveItem(@ModelAttribute("purchaseEFrom")@Validated PurchaseEstimateForm purchaseEFrom, BindingResult result,RedirectAttributes redirect,HttpSession ses,HttpServletRequest req)
{
    
    
    if (result.hasErrors()) {
        return new ModelAndView("PurchaseEstimate");
        
    }
    else{  
    // code to ignore validator(itemCode) while removing addRow --- Starts  (Abi-20.07.2017) 
    
    List<PurchaseEstimateItem> listPurchaseEst= purchaseEFrom.getEpurchases();  
    PurchaseEstimateItem purchaseEstItem=new PurchaseEstimateItem();
    List<PurchaseEstimateItem> listNew=new ArrayList<PurchaseEstimateItem>(); 
     Iterator<PurchaseEstimateItem> i = listPurchaseEst.iterator();
     while (i.hasNext())
    {
       purchaseEstItem = i.next();
        // this condition exclude deleted empty row of data
        if(purchaseEstItem.getItemCode()==null || purchaseEstItem.getItemCode().isEmpty() )
        {
           
        }
        else
        {
          // here add existing data to new arraylist  
          listNew.add(purchaseEstItem);
        }    

    }
     purchaseEFrom.setEpurchases(listNew);
     
    // End --- (Abi 20.07.2017) 
         // start finacial year  
       String yearRange=adbo.GetYearRange(); 
       long previousId=adbo.getPreviousIdBasedStartAndEndYear("purchaseEstimateId", "PurchaseEstimate", yearRange);
        
        String primaryIdPE="";  
          if(previousId==0){
             primaryIdPE=previousId+1+"/"+yearRange;
          }else{
               primaryIdPE=previousId+1+"/"+yearRange;
          }
           ses.setAttribute("primaryIdPE", primaryIdPE);
          // end of finacial year    
          
            
        PurchaseEstimate p=purchaseEFrom.getEpurchase(); 
        System.out.println("dummy");
           String invoicNo = p.getPurchaseEstimateId();
            System.out.println("p.getPurchaseEstimateId() ------ >" + invoicNo);
           purchaseEstimateService.deletePurchaseEstimateItem(invoicNo); 
             if(invoicNo==null || invoicNo.isEmpty()  ){
                   System.out.println("seju insert :"+invoicNo);
                p.setPurchaseEstimateId(null);
                String purchaseEstimateId=purchaseEstimateService.savePurchaseEstimate(p);
                List list=purchaseEFrom.getEpurchases();       
                Iterator itr=list.iterator();
                while(itr.hasNext()){
           PurchaseEstimateItem pEItem=(PurchaseEstimateItem)itr.next();
           pEItem.setPurchaseEstimateId(purchaseEstimateId);
           pEItem.setId(null);
           purchaseEstimateService.savePurchaseEstimateItem(pEItem);
       }
        }        
             else {
                 System.out.println("seju update "+invoicNo);
              p.setPurchaseEstimateId(invoicNo);
              String purchaseEstimateId=purchaseEstimateService.savePurchaseEstimate(p);
       List list=purchaseEFrom.getEpurchases();       
       Iterator itr=list.iterator();
       while(itr.hasNext()){
           PurchaseEstimateItem pEItem=(PurchaseEstimateItem)itr.next();
           pEItem.setPurchaseEstimateId(purchaseEstimateId);
            pEItem.setId(null);
           purchaseEstimateService.savePurchaseEstimateItem(pEItem);
       }
        } 
        
       return new ModelAndView("redirect:PurchaseEstimateGrid.html");
        } 
}



 @RequestMapping(value={"PurchaseEstimateGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView SalesGrid()
  {
    PurchaseEstimateForm pEForm=new PurchaseEstimateForm();
    ModelAndView model = new ModelAndView("PurchaseEstimateGrid");    
      model.addObject("purchaseEinfo",purchaseEstimateService.listPurchaseEstimate());
    model.addObject("purchaseEFrom", pEForm);
    return model;
  }
  
@RequestMapping(value={"editPurchaseE"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView editSales(@RequestParam("id") String id, RedirectAttributes redirect)
{
     System.out.println("id:"+id);
    if(id!=null && id.length()>0){
       System.out.print("seju----"+ id); 
      PurchaseEstimate p;
        p = purchaseEstimateService.getPurchaseEstimateById(id);
      List list=purchaseEstimateService.getPurchaseEstimateItem(id);
      
      PurchaseEstimateForm pEForm=new PurchaseEstimateForm();
      pEForm.setEpurchases(list);
      pEForm.setEpurchase(p);
      ModelAndView model = new ModelAndView("PurchaseEstimate");
      model.addObject("peId", id);
      model.addObject("purchaseEFrom", pEForm);
     
     
      return model;
    }
   else{
        ModelAndView model = new ModelAndView("redirect:PurchaseEstimateGrid.html");
       return model;
   }

}
  
   
  
@RequestMapping(value={"deletePurchaseE"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView deleteSales(@RequestParam("id") String id, RedirectAttributes redirect)
{
    ModelAndView model = new ModelAndView("redirect:PurchaseEstimateGrid.html");
   if(id!=null && id.length()>0){
       purchaseEstimateService.deletePurchaseEstimate(id);   
       purchaseEstimateService.deletePurchaseEstimateItem(id);
       
       return model;
   }
   else{
       return model;
   }

}    

@RequestMapping(value={"purchaseE_addrow"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView purchaseaddrow(@ModelAttribute("purchaseEFrom") PurchaseEstimateForm purchaseEFrom, HttpServletRequest req)
{
    PurchaseEstimateItem pEItem_plus=new PurchaseEstimateItem();
    PurchaseEstimateItem pEItem=new PurchaseEstimateItem();
    PurchaseEstimateForm pEForm=purchaseEFrom;    
      System.out.println(" p.getPos() "+purchaseEFrom.getEpurchase().getDate());
    List<PurchaseEstimateItem> list= pEForm.getEpurchases();
    List<PurchaseEstimateItem> listNew=new ArrayList<PurchaseEstimateItem>(); 
     Iterator<PurchaseEstimateItem> i = list.iterator();
     while (i.hasNext())
    {
        pEItem = i.next();
        // this condition exclude deleted empty row of data
        if(pEItem.getItemCode()=="" || pEItem.getItemCode()==null)
        {
           
        }
        else
        {
          // here add existing data to new arraylist  
          listNew.add(pEItem);
        }    

    }
    listNew.add(pEItem_plus);    // this step add empty row into arraylist
    pEForm.setEpurchases(listNew); //setting all data with empty row into form 
     
    
    ModelAndView model = new ModelAndView("purchaseE_addrow");
    CompanyInformation tin= companyservice.getCompanyById(1);    
    String gstinCompany=tin.getCompanyTin().substring(0, 2);
    model.addObject("purchaseEFrom", pEForm);
    model.addObject("CompanyGstin", gstinCompany); 
    
    model.addObject("taxStructure", tServ.getTaxId(1));
    return model;
}

@RequestMapping(value={"FullrequirementGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView FullrequirementGrid(@ModelAttribute("purchaseEFrom") PurchaseEstimateForm purchaseEFrom, HttpServletRequest req)
{   
     PurchaseEstimateForm pEFFrom=purchaseEFrom; 
       PurchaseEstimate p=pEFFrom.getEpurchase();
        PurchaseEstimate pNew=pEFFrom.getEpurchase();
    ModelAndView model = new ModelAndView("purchaseE_addrow");  
    String first2CharofGST="";
    if(companyservice.getCompanyById(1)!=null)
    {
    CompanyInformation tin= companyservice.getCompanyById(1);    
    String gstinCompany=tin.getCompanyTin().substring(0, 2);
    first2CharofGST=gstinCompany;     
    }
   DecimalFormat df = new DecimalFormat("#.##"); 
    
    
  //  model.addObject("itemList", itemMasterService.getItemListROL());
    List<ItemMaster> list_item=itemMasterService.getItemListROL();
    ItemMaster im =new ItemMaster();
    List<PurchaseEstimateItem> newPi=new ArrayList<PurchaseEstimateItem>();
    Iterator itr=list_item.iterator();
    double totalAmt=0.0;
    double cgst=0.0;
    double sgat=0.0;
    double igat=0.0;
    while(itr.hasNext())
    {
      im=(ItemMaster) itr.next();
      PurchaseEstimateItem pi=new PurchaseEstimateItem();
      Double taxcgst=0.00; 
      Double taxsgst=0.00; 
      Double taxigst=0.00; 
      System.out.println(" p.getPos() "+purchaseEFrom.getEpurchase().getDate());
      if(p.getPos().equalsIgnoreCase(first2CharofGST))
      {
       taxcgst=im.getTaxCgst();
       taxsgst=im.getTaxSgst();
      }
      else
      {
       taxigst=im.getTaxIgst();
      }    
      double amount=Double.valueOf(im.getRol())*im.getTp();
      totalAmt=totalAmt+amount;
      double cgstloop=(amount*taxcgst)/100;      
      double sgstLoop=(amount*taxsgst)/100;      
      double igstLoop=(amount*taxigst)/100;
      if(Double.isNaN(cgstloop))
        cgstloop=0.00;
        else
        cgstloop=cgstloop; 
        if(Double.isNaN(sgstLoop))
        sgstLoop=0.00;
        else
        sgstLoop=sgstLoop;
        if(Double.isNaN(igstLoop))
        igstLoop=0.00;
        else
        igstLoop=igstLoop;
        
      cgst=cgst+cgstloop;
      sgat=sgat+sgstLoop;
      igat=igat+igstLoop;
      pi.setItemCode(im.getItemCode());
      pi.setNameOfTheItem(im.getItemName());
      pi.setQty(Double.valueOf(im.getRol()));
      pi.setTpRate(im.getTp());
      pi.setUnit(Double.valueOf(im.getUnit()));
      pi.setAmount(Double.valueOf(df.format(amount)));
      pi.setCgst(Double.valueOf(df.format(cgstloop)));
      pi.setVat(Double.valueOf(df.format(sgstLoop)));
      pi.setIgst(Double.valueOf(df.format(igstLoop)));
    //  pi.setTax(Double.valueOf(im.getTax()));
      pi.setTaxCgst(taxcgst);
      pi.setTaxSgst(taxsgst);
      pi.setTaxIgst(taxigst);
      newPi.add(pi);
    }
     System.out.println("test 2");
//    System.out.println(" totalAmt "+p.getDate());
    pNew.setTotalAmount(Double.valueOf(df.format(totalAmt)));
    pNew.setVat(Double.valueOf(df.format(sgat)));
    pNew.setCgst(Double.valueOf(df.format(cgst)));
    pNew.setIgst(Double.valueOf(df.format(igat)));
    pNew.setDate(new Date());
    pEFFrom.setEpurchase(pNew);
    pEFFrom.setEpurchases(newPi);
    model.addObject("purchaseEFrom", pEFFrom);
 
    return model;
}

@RequestMapping(value={"PEstimateInvoice"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView PurchaseEstimateInvoice(@RequestParam("id") String id){
    
    if(id!=null && id.length()>0){
        
        List list=companyservice.listcompany();
        CompanyInformation company=(CompanyInformation)list.iterator().next();
        
        
        PurchaseEstimate pE=purchaseEstimateService.getPurchaseEstimateById(id);
        List<Object[]> PurchaseEstimatelist=purchaseEstimateService.getPurchaseEstimateItemByPurchaseEstimateId(id);
      
      ModelAndView model=new ModelAndView("PEstimateInvoice");
      model.addObject("company", company);
      model.addObject("PurchaseEstimate", pE);
      model.addObject("PurchaseEstimateItem", PurchaseEstimatelist);
      company=companyservice.getCompanyById(1);
      model.addObject("companyFrom",company);
      return model;
    }
   else{
       ModelAndView model = new ModelAndView("redirect:PurchaseEstimate.html");
       return model;
   }
    
}

@RequestMapping(value={"GetDatatablePEGrid"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   
    public DatatableObject getDatatable( HttpServletRequest req)
    {
      DatatableObject db =new DatatableObject();
           String[] cols = {"purchase_estimate_id", "date", "name_of_supplier", "tin_no","mode"};

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

    String individualSearch = "";

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
    int totalAfterFilter = total;
    //result.put("sEcho",echo);
 
    try {
        String searchSQL = "";
        String sql = "SELECT pe.purchase_estimate_id,pe.date,pe.cgst,pe.vat,pe.total_amount,pe.id,pe.qty,pe.item_code FROM purchase_estimate AS pe";
        String searchTerm =req.getParameter("search[value]");
         String globeSearch =  " where (purchase_estimate_id like '"+searchTerm+"%')";
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
        // For aData
         List<Object[]> list2 = purchaseEstimateService.GetDatatableObject(sql);
         // For Filter Count 
        String sql2 = "SELECT pe.purchase_estimate_id,pe.date,pe.cgst,pe.vat,pe.total_amount,pe.id,pe.qty,pe.item_code FROM purchase_estimate AS pe";
       if (searchTerm != "") {
            sql2 += searchSQL;
          List<Object[]> count = purchaseEstimateService.GetDatatableCount(sql2);
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
